package org.example.dao;

import org.example.DocumentManager;
import org.example.util.IdGenerator;

import java.util.List;

import static org.example.util.IdGenerator.generateId;

public abstract class InMemoryCollectionDao<T extends DocumentManager.Entity>{

    protected List<T> connection;

    public InMemoryCollectionDao(List<T> connection) {
        this.connection = connection;
    }

    protected boolean isDuplicateId(String id, List<? extends DocumentManager.Entity> entities) {
        return entities.stream()
                .map(DocumentManager.Entity::getId)
                .toList()
                .contains(id);
    }

    protected void setUniqueId(DocumentManager.Entity entity) {
        String id;

        do {
            id = generateId();
        } while (isDuplicateId(id, connection));

        entity.setId(id);
    }
}
