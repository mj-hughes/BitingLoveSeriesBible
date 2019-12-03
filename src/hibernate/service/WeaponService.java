package hibernate.service;

import hibernate.entity.Weapon;

import java.util.List;

public interface WeaponService {
    List<Weapon> getWeapons();
    List<Weapon> getWeaponsForPersona(int personaId);

}
