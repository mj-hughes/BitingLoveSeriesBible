package hibernate.dao;

import hibernate.entity.Gmc;
import hibernate.entity.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GmcDAOImpl implements GmcDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Gmc> getGmcs() {
        Session session = sessionFactory.getCurrentSession();
        List<Gmc> list = session.createQuery("from Gmc ", Gmc.class).getResultList();
        return list;
    }

    @Override
    public List<Gmc> getGmcsForPersona(int personaId) {
        Session session = sessionFactory.getCurrentSession();
        Persona persona = session.get(Persona.class, personaId);
        // Touch list to trigger lazy load
        persona.getGmcs().size();
        return persona.getGmcs();
    }

}
