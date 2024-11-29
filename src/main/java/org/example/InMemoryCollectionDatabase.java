package org.example;

import lombok.Getter;
import org.example.dao.DaoFactory;
import org.example.dao.impl.InMemoryCollectionDaoFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class InMemoryCollectionDatabase {

    private List<DocumentManager.Author> authors = new ArrayList<>();
    private List<DocumentManager.Document> documents = new ArrayList<>();

    {
        DocumentManager.Author author1 = new DocumentManager.Author("1", "Dima");
        DocumentManager.Author author2 = new DocumentManager.Author("2", "Masha");
        DocumentManager.Author author3 = new DocumentManager.Author("3", "Yura");


        DocumentManager.Document document1 = new DocumentManager.Document("1", "ToDoList",
                "1)Drink coffee; 2)To do some work.", author1, Instant.now());
        DocumentManager.Document document2 = new DocumentManager.Document("2", "Wish list",
                "1)PS5; 2)New phone.", author2, Instant.now());
        DocumentManager.Document document3 = new DocumentManager.Document("3", "Training schedule",
                "Monday: 10km run. \n Tuesday: 3.2 km swim. \n Else: rest", author1, Instant.now());

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);

        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
    }

    public DaoFactory getDaoFactory() {
        return new InMemoryCollectionDaoFactory(this);
    }
}
