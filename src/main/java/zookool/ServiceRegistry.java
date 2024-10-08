package zookool;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.*;

public class ServiceRegistry implements Watcher {
    private static final String REGISTRY_ZNODE = "/service_registry";
    private final ZooKeeper zooKeeper;
    private String currentZnode;
    private List<String> allServiceAddresses;

    public ServiceRegistry(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        createServiceRegistryZnode();
    }

    public void registerToCluster(String metaData) throws KeeperException, InterruptedException {
        this.currentZnode = zooKeeper.create(REGISTRY_ZNODE,
                metaData.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Registered to service registry.");

    }

    public void registerForUpdates() {
        try {
            updateAddress();
        } catch (KeeperException | InterruptedException e) {
        }
    }

    public synchronized List<String> getAllServiceAddresses() throws KeeperException, InterruptedException {
        if (allServiceAddresses == null) {
            updateAddress();
        }
        return allServiceAddresses;
    }

    public void unregisterFromCluster() throws KeeperException, InterruptedException{
        if (currentZnode != null && zooKeeper.exists(REGISTRY_ZNODE, false) != null) {
            zooKeeper.delete(currentZnode, -1);
        }
    }

    private void createServiceRegistryZnode() {
        try {
            if (zooKeeper.exists(REGISTRY_ZNODE, false) == null) {
                zooKeeper.create(REGISTRY_ZNODE, new byte[] {}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            System.out.println("Failed to create " + REGISTRY_ZNODE);
        }
    }

    private synchronized void updateAddress() throws KeeperException, InterruptedException {
        List<String> workerZnodes = zooKeeper.getChildren(REGISTRY_ZNODE, this);
        List<String> addresses = new ArrayList<>(workerZnodes.size());

        for (String workerZnode : workerZnodes) {
            String workerZnodeFullPath = REGISTRY_ZNODE + "/" + workerZnode;
            Stat stat = zooKeeper.exists(workerZnodeFullPath, false);
            if (stat == null) {
                continue;
            }
            byte[] addressBytes = zooKeeper.getData(workerZnodeFullPath, false, stat);
            String address = new String(addressBytes);
            addresses.add(address);
        }

        this.allServiceAddresses = Collections.unmodifiableList(addresses);
        System.out.println("The cluster addresses are :" + this.allServiceAddresses);

    }

    @Override
    public void process(WatchedEvent event) {
        try {
            updateAddress();
        } catch (KeeperException | InterruptedException e) {
            System.out.println("Failed to update addresses.");
        }
    }
}
