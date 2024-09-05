package zookool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

public class App implements Watcher {

    private ZooKeeper zooKeeper;

    public App() {
        try {
            this.zooKeeper = new ZooKeeper("localhost:1001", 0, this);
        } catch (IOException e) {
            System.out.println("Failed to connect to zookeeper");
        }
    }

    @Override
    public void process(WatchedEvent event) {

    }

    public static void main(String[] args) {
        App app = new App();
        app.await();
        String path = "/nodes/";
        byte[] data = new byte[512];
        List<ACL> acl = new ArrayList<>();
        try {
            app.zooKeeper.create(path, data, acl, CreateMode.EPHEMERAL_SEQUENTIAL);

        } catch (InterruptedException | KeeperException e) {
            System.out.println("failed to create child znodes with: " + e);
        }

        try {
            app.close();
        } catch (InterruptedException e) {
            System.out.println("closing connection to zk");
        }
    }

    private synchronized void await() {
        try {
            wait();
        } catch (InterruptedException i) {
        } finally {
            notify();
        }
    }

    private void close() throws InterruptedException {
        this.zooKeeper.close();
    }
}
