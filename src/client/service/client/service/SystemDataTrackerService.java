package client.service;

import common.model.ProcessModel;
import server.service.ISystemDataTrackerService;

import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public class SystemDataTrackerService implements client.service.ISystemDataTrackerService {
    private ISystemDataTrackerService serverSysDataTrackerService;
    private SystemDataTrackerService(){
        this.serverSysDataTrackerService = server.service.SystemDataTrackerService.getInstance();
    }

    private static class SystemDataTrackerServiceHelper{
        private static final SystemDataTrackerService Instance = new SystemDataTrackerService();
    }

    public static SystemDataTrackerService getInstance(){
        return SystemDataTrackerServiceHelper.Instance;
    }

    public List<ProcessModel> getProcessList()
    {
        return this.serverSysDataTrackerService.GetProcessList();
    }
}
