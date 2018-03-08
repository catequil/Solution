package org.mysolution;

import java.util.*;

public class EntityRepo {
    private Map<Class, List<Entity>> solutionDB = new HashMap<>();

    public <T extends Entity> T create(Class<T> entityClass) {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Entity> Optional<T> find(Class<T> entityClass, String id) {
        List<T> list = (List<T>) solutionDB.getOrDefault(entityClass, new ArrayList<>());
        return list.stream().filter(e -> e != null && e.getId() != null && e.getId().equals(id)).findFirst();
    }

    public <T extends Entity> void save(T entity) {
        Class entityClass = entity.getClass();
        List<Entity> list = solutionDB.getOrDefault(entityClass, new ArrayList<>());
        Optional<T> foundEntityOptional = find(entityClass, entity.getId());
        foundEntityOptional.ifPresent(list::remove);
        list.add(entity);
        solutionDB.put(entityClass, list);
    }

    public <T extends Entity> List<T> getList(Class<T> entityClass) {
        return (List<T>) solutionDB.get(entityClass);
    }

}
