package hibernate.dao;

import hibernate.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> list = session.createQuery("from Book ", Book.class).getResultList();
        return list;
    }

    @Override
    public Book getBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Override
    public void saveBook(Book theBook) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(theBook);
    }

    @Override
    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("delete from Book where id=:delId");
        query.setParameter("delId", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> getBooksByName(String theSearchTerm) {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery(
                "from Book where lower(title) like :search");
        theSearchTerm="%"+theSearchTerm.toLowerCase()+"%";
        query.setParameter("search", theSearchTerm);
        return query.getResultList();

    }
}
