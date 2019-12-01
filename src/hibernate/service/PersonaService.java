package hibernate.service;

import hibernate.entity.Persona;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonaService {
    List<Persona> getPersonas();
    Persona getPersona(int theId);
    void savePersona(Persona thePersona, MultipartFile file, String applicationPath);
    void deletePersona(int theId);
    List<Persona> getPersonasByName(String theSearchTerm);
}
