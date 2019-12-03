package hibernate.dao;

import hibernate.entity.Weapon;

import java.util.List;

public interface WeaponDAO {
    List<Weapon> getWeapons();
    List<Weapon> getWeaponsForPersona(int personaId);
}
