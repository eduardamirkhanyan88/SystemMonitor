package server.component;

import common.model.EntityBase;

import java.util.Collection;
import java.util.List;

/**
 * Created by Eduard on 6/25/2017.
 */
public interface IComponent<T extends EntityBase> {
    public void add(T item);
    public void add(Collection<T> item);
    public void remove(T item);
    public void remove(Collection<T> item);
    public List<T> get();
    public void clean();
}
