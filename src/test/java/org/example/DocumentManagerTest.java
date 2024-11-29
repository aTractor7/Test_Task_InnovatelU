package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.DaoFactory;
import org.example.dao.DocumentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DocumentManagerTest {

    private DocumentManager documentManager;
    private AuthorDao authorDao;
    private DocumentDao documentDao;

    @BeforeEach
    void setUp() {
        // Моки для DAO
        authorDao = mock(AuthorDao.class);
        documentDao = mock(DocumentDao.class);

        // Мок для фабрики DAO
        DaoFactory daoFactory = mock(DaoFactory.class);
        when(daoFactory.createAuthorDao()).thenReturn(authorDao);
        when(daoFactory.createDocumentDao()).thenReturn(documentDao);

        // Створення DocumentManager
        documentManager = new DocumentManager(daoFactory);
    }

    @Test
    void testSaveDocument() {
        // Вхідні дані
        DocumentManager.Author author = DocumentManager.Author.builder()
                .id("author1")
                .name("John Doe")
                .build();

        DocumentManager.Document document = DocumentManager.Document.builder()
                .id("doc1")
                .title("Test Document")
                .content("This is a test document.")
                .author(author)
                .created(Instant.now())
                .build();

        // Виклик методу
        DocumentManager.Document result = documentManager.save(document);

        // Перевірка
        assertEquals(document, result);
        verify(authorDao).save(author); // Перевіряємо, що автор збережений
        verify(documentDao).save(document); // Перевіряємо, що документ збережений
    }

    @Test
    void testSearchDocuments() {
        // Вхідні дані
        DocumentManager.SearchRequest request = DocumentManager.SearchRequest.builder()
                .titlePrefixes(List.of("Test"))
                .containsContents(List.of("document"))
                .authorIds(List.of("author1"))
                .createdFrom(Instant.now().minusSeconds(3600))
                .createdTo(Instant.now())
                .build();

        List<DocumentManager.Document> expectedDocuments = List.of(
                DocumentManager.Document.builder()
                        .id("doc1")
                        .title("Test Document")
                        .content("This is a test document.")
                        .author(DocumentManager.Author.builder().id("author1").name("John Doe").build())
                        .created(Instant.now())
                        .build()
        );

        // Налаштовуємо мок
        when(documentDao.search(request)).thenReturn(expectedDocuments);

        // Виклик методу
        List<DocumentManager.Document> result = documentManager.search(request);

        // Перевірка
        assertEquals(expectedDocuments, result);
        verify(documentDao).search(request); // Перевіряємо, що пошук був викликаний
    }

    @Test
    void testFindDocumentById() {
        // Вхідні дані
        String documentId = "doc1";
        DocumentManager.Document expectedDocument = DocumentManager.Document.builder()
                .id(documentId)
                .title("Test Document")
                .content("This is a test document.")
                .author(DocumentManager.Author.builder().id("author1").name("John Doe").build())
                .created(Instant.now())
                .build();

        // Налаштовуємо мок
        when(documentDao.findById(documentId)).thenReturn(Optional.of(expectedDocument));

        // Виклик методу
        Optional<DocumentManager.Document> result = documentManager.findById(documentId);

        // Перевірка
        assertTrue(result.isPresent());
        assertEquals(expectedDocument, result.get());
        verify(documentDao).findById(documentId); // Перевіряємо виклик
    }

    @Test
    void testFindDocumentById_NotFound() {
        // Вхідні дані
        String documentId = "unknown";

        // Налаштовуємо мок
        when(documentDao.findById(documentId)).thenReturn(Optional.empty());

        // Виклик методу
        Optional<DocumentManager.Document> result = documentManager.findById(documentId);

        // Перевірка
        assertTrue(result.isEmpty());
        verify(documentDao).findById(documentId); // Перевіряємо виклик
    }
}
