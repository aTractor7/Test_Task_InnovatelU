package org.example.dao.impl;

import org.example.DocumentManager;
import org.example.dao.AuthorDao;
import org.example.dao.InMemoryCollectionDao;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.util.IdGenerator.generateId;

public class InMemoryCollectionAuthorDao extends InMemoryCollectionDao<DocumentManager.Author> implements AuthorDao {

    public InMemoryCollectionAuthorDao(List<DocumentManager.Author> connection) {
        super(connection);
    }

    @Override
    public void save(DocumentManager.Author author) {
        if(author.getId() == null || author.getId().isEmpty() || isDuplicateId(author.getId(), connection)) {
            setUniqueId(author);
        }

        connection.add(author);
    }

    @Override
    public Optional<DocumentManager.Author> findById(String id) {
        return connection.stream()
                .filter(author -> author.getId().equals(id))
                .findAny();
    }
}
