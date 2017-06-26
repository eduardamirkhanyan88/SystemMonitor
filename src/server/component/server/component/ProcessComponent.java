package server.component;

import common.model.ProcessModel;
import common.repository.IRepository;
import common.repository.ProcessInMemoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Eduard on 6/24/2017.
 */
public class ProcessComponent implements IComponent<ProcessModel>{
    private static final Object SYNCOBJECT = new Object();
    private IRepository<ProcessModel> procRepository;
    private ProcessComponent(){
        this.procRepository = new ProcessInMemoryRepository();
    }

    private static class ProcessComponentHelper{
        private static final ProcessComponent Instance = new ProcessComponent();
    }

    public static ProcessComponent getInstance(){
            return ProcessComponentHelper.Instance;
    }

    public void add(ProcessModel proc){
        synchronized (SYNCOBJECT) {
            this.procRepository.add(proc);
        }
    }

    public void add(Collection<ProcessModel> procColl){
        synchronized (SYNCOBJECT) {
            this.procRepository.add(procColl);
        }
    }

    public void remove(ProcessModel proc){
        synchronized (SYNCOBJECT) {
            this.procRepository.remove(proc);
        }
    }

    public void clean(){
        synchronized (SYNCOBJECT) {
            this.procRepository.remove((i)->i == i);
        }
    }

    public void remove(Collection<ProcessModel> procColl){
        synchronized (SYNCOBJECT) {
            this.procRepository.remove(procColl);
        }
    }

    public List<ProcessModel> get(){
        synchronized (SYNCOBJECT) {
            return this.procRepository.get();
        }
    }

}
