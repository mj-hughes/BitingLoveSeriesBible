package hibernate.dao;

import hibernate.entity.Persona;

import java.util.List;

public interface PersonaDAO {
    List<Persona> getPersonas();
    Persona getPersona(int theId);
    void savePersona(Persona thePersona);
    void deletePersona(int theId);
    List<Persona> getPersonasByName(String theSearchTerm);
}
