package zookool;

import java.net.InetAddress;

import org.apache.zookeeper.KeeperException;

public class OnElectionAction implements OnElectionCallback {
    private ServiceRegistry serviceRegistry;
    private final int port;

    public OnElectionAction(ServiceRegistry serviceRegistry, int port) {
        this.serviceRegistry = serviceRegistry;
        this.port = port;
    }

    @Override
    public void onElectedToBeLeader()  {
        try {
            serviceRegistry.unregisterFromCluster();
        } catch (KeeperException e) {
        } catch (InterruptedException e) {
        }
        serviceRegistry.registerForUpdates();
    }

    @Override
    public void onWorker() {
        try {
            String currentServerAddress = String.format("http://%s:%d",
                    InetAddress.getLocalHost().getCanonicalHostName(), port);
            serviceRegistry.registerToCluster(currentServerAddress);
        } catch (Exception e) {
            
        }
    }

}
