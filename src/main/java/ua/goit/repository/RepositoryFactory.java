package ua.goit.repository;

import ua.goit.model.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {
        private final static Map<String, CrudRepository> REPOSITORIES = new ConcurrentHashMap<> ();

        public synchronized static <E extends BaseEntity<ID>, R extends CrudRepository<E, ID>, ID> CrudRepository<E, ID> of(Class<E> modelClass) {
            final String modelName = modelClass.getName();
            if (!REPOSITORIES.containsKey(modelName)) {
                REPOSITORIES.put(modelName, new CrudRepositoryImpl(modelClass));
            }
            return REPOSITORIES.get(modelName);
        }
    }

