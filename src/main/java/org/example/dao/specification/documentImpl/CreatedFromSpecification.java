package org.example.dao.specification.documentImpl;

import org.example.DocumentManager;
import org.example.dao.specification.Specification;

import java.time.Instant;

public class CreatedFromSpecification implements Specification<DocumentManager.Document> {

    private final Instant createdFrom;

    public CreatedFromSpecification(Instant createdFrom) {
        this.createdFrom = createdFrom;
    }


    @Override
    public boolean isSatisfiedBy(DocumentManager.Document document) {
        if(createdFrom == null) return true;
        return createdFrom.isBefore(document.getCreated());
    }
}
