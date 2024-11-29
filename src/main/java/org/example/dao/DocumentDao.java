package org.example.dao;

import org.example.DocumentManager;

import java.util.List;

public interface DocumentDao extends AbstractDao<DocumentManager.Document> {

    List<DocumentManager.Document> search(DocumentManager.SearchRequest request);
}
