package zookool;

public interface OnElectionCallback {
    void onElectedToBeLeader();

    void onWorker();
}
