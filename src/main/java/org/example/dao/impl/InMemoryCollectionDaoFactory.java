package org.example.dao.impl;

import org.example.InMemoryCollectionDatabase;
import org.example.dao.AuthorDao;
import org.example.dao.DaoFactory;
import org.example.dao.DocumentDao;

public class InMemoryCollectionDaoFactory implements DaoFactory {

    private final InMemoryCollectionDatabase database;

    public InMemoryCollectionDaoFactory(InMemoryCollectionDatabase database) {
        this.database = database;
    }

    @Override
    public AuthorDao createAuthorDao() {
        return new InMemoryCollectionAuthorDao(database.getAuthors());
    }

    @Override
    public DocumentDao createDocumentDao() {
        return new InMemoryCollectionDocumentDao(database.getDocuments());
    }
}
