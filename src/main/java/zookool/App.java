package zookool;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class App implements Watcher {
    private ZooKeeper zooKeeper;

    public App() {
        try {
            this.zooKeeper = new ZooKeeper("localhost:", 0, this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String []args){

    }

    @Override
    public void process(WatchedEvent event) {

    }
}
