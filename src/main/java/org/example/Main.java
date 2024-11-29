package org.example;

import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InMemoryCollectionDatabase database = new InMemoryCollectionDatabase();
        DocumentManager documentManager = new DocumentManager(database.getDaoFactory());


        System.out.println("\n\n========================================================================================================\n");
        System.out.println("Find by id = 1:\n" + documentManager.findById("1"));
        System.out.println("\n\n========================================================================================================\n");



        DocumentManager.Author newAuthor = new DocumentManager.Author(null, "NewAuthor");
        DocumentManager.Document newDocument = new DocumentManager.Document("", "newDocument",
                "new content", newAuthor, Instant.now());

        System.out.println("\nSave newDocument:\n" + documentManager.save(newDocument));
        System.out.println("\n\n========================================================================================================\n");

        DocumentManager.SearchRequest searchRequestPrefixes = new DocumentManager.SearchRequest(List.of("new", "ToDo"),
                null, null, null, null);
        DocumentManager.SearchRequest searchRequestContent = new DocumentManager.SearchRequest(null,
                List.of("new", "PS5"), null, null, null);
        DocumentManager.SearchRequest searchRequestAuthors = new DocumentManager.SearchRequest(null,
                 null, List.of("1", "2"), null, null);
        DocumentManager.SearchRequest searchRequestCreationTime = new DocumentManager.SearchRequest(null,
                null, null, Instant.parse("2024-11-13T10:00:00Z"), Instant.now());

        System.out.println("\nSearch Request Prefixes (\"new\", \"ToDo\")" + documentManager.search(searchRequestPrefixes));
        System.out.println("\nSearch Request Content (\"new\", \"PS5\")" + documentManager.search(searchRequestContent));
        System.out.println("\nSearch Request Authors (\"1\", \"2\")" + documentManager.search(searchRequestAuthors));
        System.out.println("\nSearch Request CreationTime (\"2024-11-27\", now)" + documentManager.search(searchRequestCreationTime));
        System.out.println("\n\n========================================================================================================\n");
    }
}
