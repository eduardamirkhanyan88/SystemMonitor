package server.utility;

import common.model.ProcessModel;
import org.hyperic.sigar.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public class SystemDataProviderUtil implements ISystemDataProvider {

    //Later can be removed and instead of time interval can be subscribed to system process update event, in this case also can be moved to config file.

    private Sigar sigar;
    private static final Object SYNCOBJECT = new Object();

    private SystemDataProviderUtil(){
        this.sigar = new Sigar();
    }

    private static class SystemDataGetterUtilHelper{
        private static final SystemDataProviderUtil Instance = new SystemDataProviderUtil();
    }

    public static SystemDataProviderUtil getInstance(){
        return SystemDataGetterUtilHelper.Instance;
    }


    public List<ProcessModel> getCurrentProcessList(){
        synchronized (SYNCOBJECT) {
            //this.sigar = new Sigar();
            List<ProcessModel> resList = new ArrayList<ProcessModel>();
            long[] pid = getProcList();
            for (long currpId : pid) {
                ProcState procState = getProcState(currpId);
                ProcessModel procModel = new ProcessModel(currpId, procState.getState(), procState.getPriority(), procState.getName(), procState.getThreads());
                resList.add(procModel);
            }
            return resList;
        }
    }

    private long[] getProcList()  {
        long[] result = new long[]{};
        try{
            result = sigar.getProcList();
        }
        catch (SigarException ex){
        }
        return result;
    }

    private ProcState getProcState(long pid){
        ProcState result = null;
        try {
            result = sigar.getProcState(pid);
        }

        catch (SigarException ex){
        }
        return result;
    }
}
