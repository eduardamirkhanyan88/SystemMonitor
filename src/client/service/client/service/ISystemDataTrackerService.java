package client.service;

import common.model.ProcessModel;

import java.util.List;

/**
 * Created by Eduard on 6/26/2017.
 */
public interface ISystemDataTrackerService {
    public List<ProcessModel> getProcessList();
}
