package common.model;



/**
 * Created by Eduard on 6/25/2017.
     */
public class ProcessModel extends EntityBase {
    private long pid;
    private char state;
    private int priority;
    private String processname;
    private long threads;

    public ProcessModel(){}

    public ProcessModel(long pId, char state, int priority, String processName, long threads){
        super();
        this.pid = pId;
        this.state = state;
        this.priority = priority;
        this.processname = processName;
        this.threads = threads;
    }

    public long getPid() {
        return this. pid;
    }

    public void setPid(long pId){
        this.pid = pId;
    }

    public char getState() {
        return this.state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public int getPriority() { return this.priority; }

    public void setPriority(int priority) { this.priority = priority; }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processName) { this.processname = processName; }

    public long getThreads() { return this.threads; }

    public void setThreads(long threads) { this.threads = threads; }

    @Override
    public String toString() {
        return "PId="+pid+"; State="+state+"; Priority="+priority+"; ProcessName="+processname+"; Threads="+threads;
    }
}
