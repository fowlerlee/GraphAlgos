package threads;

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

    public ServerNodes(int increase) {
        final ExecutorService es = Executors.newSingleThreadExecutor();
        int port = 8081 + increase;
        es.submit(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket server = new ServerSocket(port)) {
                    while (true) {
                        final Socket socket = server.accept();
                        final InputStream inputStream = socket.getInputStream();
                        final DataInputStream dataInputStream = new DataInputStream(inputStream);
                        final String in = dataInputStream.readUTF();

                        final JsonObject pkt = JsonParser.parseString(in).getAsJsonObject();
                        inbox.add(pkt);
                        if (pkt.get(MSG).getAsJsonObject().get(TYPE).equals(TRM)) {
                            dataInputStream.close();
                            inputStream.close();
                            socket.close();

                        }
                    }
                } catch (Exception e) {
                    System.err.println("failed server socket while listening at node: " + port);
                } finally {
                }
            }
        });
        es.shutdown();
    }
}
