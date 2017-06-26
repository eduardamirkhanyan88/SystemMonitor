package common.repository;

import common.model.EntityBase;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Eduard on 6/24/2017.
 */
public interface IRepository<T extends EntityBase> {
    public List<T> get();

    public default Optional<T> get(String id){
        return get().stream().filter(entity -> entity.getId().equals(id)).findAny();
    }

    public default List<T> getItem(Predicate<T> predicate){
        return get().stream().filter(predicate).collect(Collectors.toList());
    }

    public void add(T item);

    public default void add(Collection<T> items){
        items.forEach(this::add);
    }

    public default void add(T... items){
        add(Arrays.asList(items));
    }

    public void remove(T item);

    public default void remove(Collection<T> items){
        items.forEach(this::remove);
    }

    public default void remove(T... items){
        remove(Arrays.asList(items));
    }

    public default void remove(String id){
        remove(entity -> entity.getId().equals(id));
    }


    public default void remove(Predicate<T> predicate){
        getItem(predicate).forEach(this::remove);
    }
}
