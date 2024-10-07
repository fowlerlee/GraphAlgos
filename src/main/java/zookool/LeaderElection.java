package zookool;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class LeaderElection implements Watcher {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;
    private static final String ELECION_NAMESPACE = "/election";
    private ZooKeeper zooKeeper;
    private String currentZnodeName;
    private static final String TARGET_ZNODE = "/target_znode";

    public static void main(String[] arg) throws IOException, InterruptedException, KeeperException {
        LeaderElection leaderElection = new LeaderElection();
        leaderElection.connectToZookeeper();
        leaderElection.volunteerForLeadership();
        leaderElection.reelectLeader();
        leaderElection.watchTargetZnode();
        leaderElection.run();
        leaderElection.close();
        System.out.println("Disconnected from Zookeeper, exiting application");
    }

    public void volunteerForLeadership() throws KeeperException, InterruptedException {
        String znodePrefix = ELECION_NAMESPACE + "/c";
        String znodeFullPath = zooKeeper.create(znodePrefix, new byte[] {}, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("znode name " + znodeFullPath);
        this.currentZnodeName = znodeFullPath.replace(ELECION_NAMESPACE + "/", "");
    }

    public void reelectLeader() throws KeeperException, InterruptedException {
        Stat predecessorStat = null;
        String predecessorZnodeName = "";
        while (predecessorStat == null) {
            List<String> children = zooKeeper.getChildren(ELECION_NAMESPACE, false);
            Collections.sort(children);
            String smallestChild = children.get(0);

            if (smallestChild.equals(currentZnodeName)) {
                System.out.println("I am the leader");
                return;
            } else {
                System.out.println("I am not the leader");
                int predecessorIndex = Collections.binarySearch(children, currentZnodeName) - 1;
                predecessorZnodeName = children.get(predecessorIndex);
                predecessorStat = zooKeeper.exists(ELECION_NAMESPACE + "/" + predecessorZnodeName, this);

            }
        }

        System.out.println("Watching znode: " + predecessorZnodeName);
        System.out.println("");
    }

    public void connectToZookeeper() throws IOException {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    public void watchTargetZnode() throws KeeperException, InterruptedException {
        Stat stat = this.zooKeeper.exists(TARGET_ZNODE, this);
        if (stat == null) {
            return;
        }
        byte[] data = this.zooKeeper.getData(TARGET_ZNODE, this, stat);
        List<String> children = this.zooKeeper.getChildren(TARGET_ZNODE, this);
        System.out.println("Data " + new String(data) + " children: " + children);

    }

    private void run() throws InterruptedException {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    private void close() throws InterruptedException {
        this.zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to Zookeeper");
                } else {
                    synchronized (zooKeeper) {
                        System.out.println("Disconnected from Zookeeper event");
                        zooKeeper.notifyAll();
                    }
                }
                break;
            case NodeDeleted:
                System.out.println(TARGET_ZNODE + " was deleted.");
                try {
                    reelectLeader();
                } catch (Exception e) {
                    System.out.println("Failed to reelect a leader.");
                }
                break;
            case NodeCreated:
                System.out.println(TARGET_ZNODE + " was created.");
                break;
            case NodeDataChanged:
                System.out.println(TARGET_ZNODE + " data changed.");
                break;
            case NodeChildrenChanged:
                System.out.println(TARGET_ZNODE + " children changed.");
                break;
        }

        // try {
        //     watchTargetZnode();
        // } catch (Exception e) {
        //     System.out.println("Failed to register watcher for " + TARGET_ZNODE);
        // }
    }
}
