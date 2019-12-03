package hibernate.service;

import hibernate.entity.Backstory;

import java.util.List;

public interface BackstoryService {
    List<Backstory> getBackstories();
    List<Backstory> getBackstoriesForPersona(int personaId);
}
