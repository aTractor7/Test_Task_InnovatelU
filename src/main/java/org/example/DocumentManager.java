package org.example;

import lombok.Builder;
import lombok.Data;
import org.example.dao.AuthorDao;
import org.example.dao.DaoFactory;
import org.example.dao.DocumentDao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * For implement this task focus on clear code, and make this solution as simple readable as possible
 * Don't worry about performance, concurrency, etc
 * You can use in Memory collection for sore data
 * <p>
 * Please, don't change class name, and signature for methods save, search, findById
 * Implementations should be in a single class
 * This class could be auto tested
 */
public class DocumentManager {

    private final AuthorDao authorDao;
    private final DocumentDao documentDao;

    public DocumentManager(DaoFactory daoFactory) {
        this.authorDao = daoFactory.createAuthorDao();
        this.documentDao = daoFactory.createDocumentDao();
    }

    /**
     * Implementation of this method should upsert the document to your storage
     * And generate unique id if it does not exist, don't change [created] field
     *
     * @param document - document content and author data
     * @return saved document
     */
    public Document save(Document document) {
        documentDao.save(document);
        authorDao.save(document.getAuthor());
        return document;
    }

    /**
     * Implementation this method should find documents which match with request
     *
     * @param request - search request, each field could be null
     * @return list matched documents
     */
    public List<Document> search(SearchRequest request) {
        return documentDao.search(request);
    }

    /**
     * Implementation this method should find document by id
     *
     * @param id - document id
     * @return optional document
     */
    public Optional<Document> findById(String id) {
        return documentDao.findById(id);
    }

    @Data
    @Builder
    public static class SearchRequest {
        private List<String> titlePrefixes;
        private List<String> containsContents;
        private List<String> authorIds;
        private Instant createdFrom;
        private Instant createdTo;
    }

    @Data
    @Builder
    public static class Document implements Entity{
        private String id;
        private String title;
        private String content;
        private Author author;
        private Instant created;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @Data
    @Builder
    public static class Author implements Entity{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public interface Entity {
        String getId();
        void setId(String id);
    }
}