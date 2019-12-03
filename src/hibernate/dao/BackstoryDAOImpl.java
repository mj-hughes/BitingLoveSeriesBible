package hibernate.dao;

import hibernate.entity.Backstory;
import hibernate.entity.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackstoryDAOImpl implements BackstoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Backstory> getBackstories() {
        Session session = sessionFactory.getCurrentSession();
        List<Backstory> list = session.createQuery("from Backstory ", Backstory.class).getResultList();
        return list;

    }

    @Override
    public List<Backstory> getBackstoriesForPersona(int personaId) {
        Session session = sessionFactory.getCurrentSession();
        Persona persona = session.get(Persona.class, personaId);
        // Touch list to trigger lazy load
        persona.getBackstories().size();
        return persona.getBackstories();

    }
}
