package org.example.dao;

public interface DaoFactory {

     AuthorDao createAuthorDao();

     DocumentDao createDocumentDao();
}
