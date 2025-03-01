package threads;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.Random;

public class ServerNodes {

    private static final String host = "locahost";
    private static final int baseNodeId = 8081;
    private final BlockingQueue<JsonObject> inbox = new LinkedBlockingQueue<>();

    private static final String PKT = "pkt";
    private static final String SND = "snd";
    private static final String RCV = "rcv";

    private static final String MSG = "msg";

    private static final String TYPE = "type";
    private static final JsonPrimitive TRM = new JsonPrimitive("trm");
    private static final JsonPrimitive TOK = new JsonPrimitive("tok");
    private static final JsonPrimitive PL = new JsonPrimitive("pl");

    private static final String EVENT = "event";
    private static final String NODE = "node";
    private static final JsonPrimitive IN = new JsonPrimitive("<");
    private static final JsonPrimitive OUT = new JsonPrimitive(">");
    private static final JsonPrimitive DEACTIVATE = new JsonPrimitive("d");
    private static final String VC = "vc";
    private final Map<Integer, Pair> nodes;
    private VectorClock vc;

    private final Random randomWork = new Random();

    public ServerNodes(final Map<Integer, Pair> nodes, final int id, final boolean isInitiator) throws Exception {
        this.nodes = nodes;
        this.vc = new VectorClock(id, nodes.size());
        int port = 8081 + id;

        final BlockingQueue<JsonObject> inbox = new LinkedBlockingQueue<>();
        boolean active = true;
        Color color = Color.white;
        int counter = 0;
        if (isInitiator) {
            JsonObject logline = new JsonObject();
            logline.add("N", new JsonPrimitive(this.nodes.size()));
            System.out.println(logline);

            final JsonObject pkt = new JsonObject();
            pkt.add(SND, new JsonPrimitive(id));
            pkt.add(RCV, new JsonPrimitive(id));
            pkt.add(MSG, getTok(0, Color.black));
            pkt.add(VC, vc.toJson());
            inbox.put(pkt);
        }

        boolean terminationDetected = false;

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Thread.currentThread().setName("Receiver #" + id);
            try (ServerSocket serverSocket = new ServerSocket(nodes.get(id).port)) {
                while (true) {
                    final Socket socket = serverSocket.accept();
                    final InputStream inputStream = socket.getInputStream();
                    final DataInputStream dataInputStream = new DataInputStream(inputStream);
                    final String in = dataInputStream.readUTF();

                    final JsonObject pkt = JsonParser.parseString(in).getAsJsonObject();

                    inbox.add(pkt);

                    if (pkt.get(MSG).getAsJsonObject().get(TYPE).equals(TRM)) {
                        dataInputStream.close();
                        inputStream.close();
                        socket.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                e.getMessage();
                throw e;
            } finally {
                executorService.shutdown();
            }
        });

        while (true) {
            final JsonObject pkt = inbox.take();
            JsonObject logline = new JsonObject();
            logline.add(EVENT, IN);
            logline.add(NODE, new JsonPrimitive(id));
            pkt.add(VC, vc.tickAndMerge(pkt.get(VC).getAsJsonObject()));
            logline.add(PKT, pkt);
            System.out.println(logline);

            // handle received message
            final JsonObject msg = pkt.get(MSG).getAsJsonObject();

            if (msg.get(TYPE).equals(PL)) {
                active = true;
                counter--;
                color = Color.black;

            } else if (msg.get(TYPE).equals(TRM)) {
                assert !active;
                assert inbox.isEmpty();
                return;
            } else if (!msg.get(TYPE).equals(TOK)) {
                logline.add("failure",
                        new JsonPrimitive(String.format("unknown message type: %s", msg.get(TYPE).getAsString())));
                System.out.println(logline);
                throw new IllegalArgumentException("unknow message type");
            }

            if (active) {
                Thread.sleep(randomWork.nextInt(100));
                if (nodes.size() > 1 && randomWork.nextBoolean()) {
                    counter++;
                    int receiver;

                    do {
                        receiver = randomWork.nextInt(nodes.size());
                    } while (receiver == id);
                    sendMsg(id, receiver, null);
                }
            }
        }

    }

    private void sendMsg(int id, int receiver, final JsonObject ob) {

    }

    private JsonObject getTok(final int q, final Color color) throws Exception {
        final JsonObject result = new JsonObject();
        result.add(TYPE, TOK);
        result.add("q", new JsonPrimitive(q));
        result.add("color", new JsonPrimitive(color.toString()));
        return result;
    }

    // connect to server socket and message the node over tcp
}
