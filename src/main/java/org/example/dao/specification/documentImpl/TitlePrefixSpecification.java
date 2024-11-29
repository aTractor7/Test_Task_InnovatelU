package org.example.dao.specification.documentImpl;

import org.example.DocumentManager;
import org.example.dao.specification.Specification;

import java.util.List;

public class TitlePrefixSpecification implements Specification<DocumentManager.Document> {

    private final List<String> prefixes;

    public TitlePrefixSpecification(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    @Override
    public boolean isSatisfiedBy(DocumentManager.Document document) {
        if(prefixes == null || prefixes.isEmpty()) return true;
        return prefixes.stream().anyMatch(prefix -> document.getTitle().startsWith(prefix));
    }
}
