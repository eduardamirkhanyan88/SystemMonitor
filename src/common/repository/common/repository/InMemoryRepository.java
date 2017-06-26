package common.repository;

import common.model.EntityBase;

import java.util.*;

/**
 * Created by Eduard on 6/25/2017.
 */
public abstract class InMemoryRepository<T extends EntityBase> implements IRepository<T> {
    private List<T> items = new ArrayList<>();

    @Override
    public List<T> get() { return items; }

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public void remove(T item) {
        items.remove(item);
    }
}
