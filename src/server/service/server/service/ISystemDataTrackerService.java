package server.service;

import common.model.EntityBase;
import common.model.ProcessModel;

import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public interface ISystemDataTrackerService {
    public List<ProcessModel> GetProcessList();
}
