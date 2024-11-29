package org.example.dao.specification.documentImpl;

import org.example.DocumentManager;
import org.example.dao.specification.Specification;

import java.util.List;

public class AuthorSpecification implements Specification<DocumentManager.Document> {

    private final List<String> authorsId;

    public AuthorSpecification(List<String> authorsId) {
        this.authorsId = authorsId;
    }

    @Override
    public boolean isSatisfiedBy(DocumentManager.Document document) {
        if(authorsId == null || authorsId.isEmpty()) return true;
        return authorsId.contains(document.getAuthor().getId());
    }
}
