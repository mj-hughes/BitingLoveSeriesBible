package hibernate.dao;

import hibernate.entity.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaDAOImpl implements PersonaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Persona> getPersonas() {
        Session session = sessionFactory.getCurrentSession();
        List<Persona> personaList = session.createQuery("from Persona", Persona.class).getResultList();
        return personaList;
    }

    @Override
    public Persona getPersona(int theId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Persona.class, theId);
    }

    @Override
    public void savePersona(Persona thePersona) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(thePersona);
    }

    @Override
    public void deletePersona(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "delete from Persona where id = :delId");
        query.setParameter("delId", theId);
        query.executeUpdate();
    }

    @Override
    public List<Persona> getPersonasByName(String theSearchTerm) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "from Persona where lower(name) like :search");
        theSearchTerm="%"+theSearchTerm.toLowerCase()+"%";
        query.setParameter("search", theSearchTerm);
        return query.getResultList();
    }

}
