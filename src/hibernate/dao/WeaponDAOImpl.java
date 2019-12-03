package hibernate.dao;

import hibernate.entity.Persona;
import hibernate.entity.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeaponDAOImpl implements WeaponDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Weapon> getWeapons() {
        Session session = sessionFactory.getCurrentSession();
        List<Weapon> list = session.createQuery("from Weapon ", Weapon.class).getResultList();
        return list;
    }

    @Override
    public List<Weapon> getWeaponsForPersona(int personaId) {
        Session session = sessionFactory.getCurrentSession();
        Persona persona = session.get(Persona.class, personaId);
        // Touch list to trigger lazy load
        persona.getWeapons().size();
        return persona.getWeapons();
    }
}
