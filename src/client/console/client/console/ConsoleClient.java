package client.console;

import client.loader.IClient;
import client.service.ISystemDataTrackerService;
import client.service.SystemDataTrackerService;
import common.model.ProcessModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public class ConsoleClient implements IClient {
    //Later this can be removed and instead of updating by time interval can subscribe to system's process change event in backend
    private static final int CONSOLEREFRESHINTERVAL = 200;
    private ISystemDataTrackerService dataTrackerService;
    public ConsoleClient(){
        dataTrackerService = SystemDataTrackerService.getInstance();
    }

    @Override
    public void run(){
        while (true) {
            List<ProcessModel> currList = new ArrayList<ProcessModel>();
            currList.addAll(SystemDataTrackerService.getInstance().getProcessList());
            for(ProcessModel currProc:currList){
                System.out.println(currProc.toString());
            }
            try {
                Thread.sleep(CONSOLEREFRESHINTERVAL);
            }
            catch (InterruptedException ex){
            }
        }
    }
}
