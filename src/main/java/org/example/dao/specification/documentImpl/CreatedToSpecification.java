package org.example.dao.specification.documentImpl;

import org.example.DocumentManager;
import org.example.dao.specification.Specification;

import java.time.Instant;

public class CreatedToSpecification implements Specification<DocumentManager.Document> {

    private final Instant createdTo;

    public CreatedToSpecification(Instant createdTo) {
        this.createdTo = createdTo;
    }

    @Override
    public boolean isSatisfiedBy(DocumentManager.Document document) {
        if (createdTo == null) return true;
        return createdTo.isAfter(document.getCreated());
    }
}
