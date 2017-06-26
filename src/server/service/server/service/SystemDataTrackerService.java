package server.service;

import common.model.ProcessModel;
import server.component.IComponent;
import server.component.ProcessComponent;
import server.utility.ISystemDataProvider;
import server.utility.SystemDataProviderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public class SystemDataTrackerService implements ISystemDataTrackerService {

    private static final int PROCESSREFRESHTIME = 100;
    private static final Object SYNCOBJECT = new Object();

    private static final List<ProcessModel> processList = new ArrayList<ProcessModel>();
    private IComponent<ProcessModel> processComponent;
    private ISystemDataProvider systemDataGetter;
    private SystemDataTrackerService(){
        this.processComponent = ProcessComponent.getInstance();
        this.systemDataGetter = SystemDataProviderUtil.getInstance();
        Thread thread = new Thread("Worker Thread"){
            public void run(){
                refreshProcessList();
            }
        };
        thread.start();
    }

    private static class SystemDataTrackerServiceHelper{
        private static final SystemDataTrackerService Instance = new SystemDataTrackerService();
    }

    public static SystemDataTrackerService getInstance(){
        return SystemDataTrackerServiceHelper.Instance;
    }

    public List<ProcessModel> GetProcessList()
    {
        List<ProcessModel> resList = new ArrayList<ProcessModel>();
        synchronized (SYNCOBJECT){
            resList.addAll(processList);
        }
        return resList;
    }

    private void refreshProcessList(){
        while(true){
            try {
                //Later instead of thread sleep can just subscribe to system events for any change of the processes and in that case only update.
                Thread.sleep(PROCESSREFRESHTIME);
                List<ProcessModel> tmpList = new ArrayList<ProcessModel>();
                tmpList.addAll(this.systemDataGetter.getCurrentProcessList());
                this.processComponent.clean();
                this.processComponent.add(tmpList);
                synchronized (SYNCOBJECT) {
                    processList.clear();
                    processList.addAll(this.processComponent.get());
                }
            }
            catch (InterruptedException ex) {
            }
            catch (Exception ex) {
            }
        }
    }
}
