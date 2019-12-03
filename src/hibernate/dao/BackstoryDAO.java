package hibernate.dao;

import hibernate.entity.Backstory;

import java.util.List;

public interface BackstoryDAO {
    List<Backstory> getBackstories();
    List<Backstory> getBackstoriesForPersona(int personaId);
}
