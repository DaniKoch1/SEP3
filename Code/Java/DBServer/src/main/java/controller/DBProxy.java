package controller;

import model.*;

import java.util.List;

public interface DBProxy {

    List<Book> advancedSearch(String isbn, String title, String author, int year, Book.Category category);

    @SuppressWarnings("unchecked")
    List<Book> advancedSearchInLibrary(String libraryId, String isbn, String title, String author, int year, Book.Category category);

    @SuppressWarnings("unchecked")
    List<Book> advancedSearchInBookStore(String bookStoreId, String isbn, String title, String author, int year, Book.Category category);

    DetailedBook getBookDetails(String isbn) throws HibernateAdapter.BookNotFoundException;

    Book getBookByLibraryBookId(String bookid) throws HibernateAdapter.BookNotFoundException;

    Book getBookByIsbn(String isbn) throws HibernateAdapter.BookNotFoundException;

    @SuppressWarnings("unchecked")
    List<LibraryStorage> getLibrariesStorageByIsbnAndLibrary(String isbn, String libraryid);

    void addBookToLibrary(LibraryStorage libraryBook);

    void addBookToBookStore(BookStoreStorage bookStoreBook);

    void deleteBookFromLibrary(LibraryStorage libraryBook);

    void deleteBookFromBookStore(BookStoreStorage bookStoreBook);

    void addCustomer(Customer customer) throws HibernateAdapter.CustomerEmailException;
}
