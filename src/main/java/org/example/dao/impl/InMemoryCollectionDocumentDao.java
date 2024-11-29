package org.example.dao.impl;

import org.example.DocumentManager;
import org.example.dao.DocumentDao;
import org.example.dao.InMemoryCollectionDao;
import org.example.dao.specification.AndSpecification;
import org.example.dao.specification.Specification;
import org.example.dao.specification.documentImpl.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.util.IdGenerator.generateId;

public class InMemoryCollectionDocumentDao extends InMemoryCollectionDao<DocumentManager.Document> implements DocumentDao {

    public InMemoryCollectionDocumentDao(List<DocumentManager.Document> documents) {
        super(documents);
    }

    @Override
    public void save(DocumentManager.Document document) {
        if(document.getId() == null || document.getId().isEmpty()  || isDuplicateId(document.getId(), connection)) {
            setUniqueId(document);
        }

        connection.add(document);
    }

    @Override
    public Optional<DocumentManager.Document> findById(String id) {
        return connection.stream()
                .filter(document -> document.getId().equals(id))
                .findAny();
    }

    @Override
    public List<DocumentManager.Document> search(DocumentManager.SearchRequest request) {
        

        return null;
    }
}
