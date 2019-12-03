package hibernate.service;

import hibernate.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBook(int id);
    void saveBook(Book theBook);
    void deleteBook(int theId);
    List<Book> getBooksByName(String theSearchTerm);
    List<Book> getBooksForPersona(int personaId);
}
