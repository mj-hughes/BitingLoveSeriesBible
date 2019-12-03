package hibernate.service;

import hibernate.dao.WeaponDAO;
import hibernate.entity.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {
    @Autowired
    private WeaponDAO weaponDAO;

    @Override
    @Transactional
    public List<Weapon> getWeapons() {
        return weaponDAO.getWeapons();
    }

    @Override
    @Transactional
    public List<Weapon> getWeaponsForPersona(int personaId) {
        return weaponDAO.getWeaponsForPersona(personaId);
    }
}
