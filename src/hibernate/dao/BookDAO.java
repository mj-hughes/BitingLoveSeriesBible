package hibernate.dao;

import hibernate.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    Book getBook(int id);
    void saveBook(Book theBook);
    void deleteBook(int id);
    List<Book> getBooksByName(String theSearchTerm);
    List<Book> getBooksForPersona(int personaId);

}
