package org.example.dao.specification.documentImpl;

import org.example.DocumentManager;
import org.example.dao.specification.Specification;

import java.util.List;

public class ContainsContentSpecification implements Specification<DocumentManager.Document> {

    private final List<String> contents;

    public ContainsContentSpecification(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public boolean isSatisfiedBy(DocumentManager.Document document) {
        if(contents == null || contents.isEmpty()) return true;
        return contents.stream().anyMatch(content -> document.getContent().contains(content));
    }
}
