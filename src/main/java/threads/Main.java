package threads;



import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static class Data {
        int x, y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Data getData() {
            return this;
        }
    }

    public static void createServers(){
        Map<Integer, ServerNodes> servers = new HashMap<>();

        for (int i = 0; i <= 2; i++) {
            ServerNodes node = new ServerNodes(i);
            servers.put(i, node);
        }
        
        for (int i = 0; i <=2; i++) {
            // send messages to servers
            ServerNodes s = servers.get(i);
        }
    }

    public static void start() {
        Data data = new Data(0, 0);
        ReadWriteLock rw = new ReentrantReadWriteLock();
        Runnable p1 = () -> {
            rw.writeLock().lock();
            data.setXY(1, 1);
            rw.writeLock().unlock();
        };

        Runnable p2 = () -> {
            rw.writeLock().lock();
            data.setXY(2, 2);
            rw.writeLock().unlock();
        };

        Runnable w1 = () -> {
            rw.readLock().lock();
            System.out.printf("x = %d and y = %d \n", data.x, data.y);
            rw.readLock().unlock();
        };

        ExecutorService es = Executors.newFixedThreadPool(5);
        es.execute(p1);
        es.execute(p2);
        es.execute(w1);
        es.execute(w1);
        es.execute(w1);
        
        try {
            es.shutdown();
        } catch (Exception e) {
            System.err.println("could not shutdown executor service");
        }
    }

    public static void main(String[] args) {
        createServers();
        System.out.println("end of simulation");
    }
}
