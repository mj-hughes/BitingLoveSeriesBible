package hibernate.service;

import hibernate.dao.BookDAO;
import hibernate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public Book getBook(int id) {  return bookDAO.getBook(id);   }

    @Override
    @Transactional
    public void saveBook(Book theBook) {
        bookDAO.saveBook(theBook);
    }

    @Override
    @Transactional
    public void deleteBook(int id) { bookDAO.deleteBook(id);}

    @Override
    @Transactional
    public List<Book> getBooksByName(String theSearchTerm) {
        return bookDAO.getBooksByName(theSearchTerm);
    }

    @Override
    @Transactional
    public List<Book> getBooksForPersona(int personaId) {
        return bookDAO.getBooksForPersona(personaId);
    }
}
