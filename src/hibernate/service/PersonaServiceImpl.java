package hibernate.service;

import hibernate.dao.PersonaDAO;
import hibernate.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    private ImageFileService imageFileService;


    @Override
    @Transactional
    public List<Persona> getPersonas() {
        return personaDAO.getPersonas();
    }

    @Override
    @Transactional
    public Persona getPersona(int theId) {
        return personaDAO.getPersona(theId);
    }

    @Override
    @Transactional
    public void savePersona(Persona thePersona,
                            MultipartFile file,
                            String applicationPath) {

        String filename = imageFileService.saveFile(
                file, applicationPath, thePersona.getImagePath());

        if (filename != null) {
            thePersona.setPictureLink(filename);
        } else {
            if ((thePersona.getPictureLink()==null) || (thePersona.getPictureLink().isEmpty())) {
                thePersona.setPictureLink("pictureNotFound.jpg");
            }
        }

        personaDAO.savePersona(thePersona);
    }

    @Override
    @Transactional
    public void deletePersona(int id) {
        personaDAO.deletePersona(id);
    }

    @Override
    @Transactional
    public List<Persona> getPersonasByName(String theSearchTerm) {
        return personaDAO.getPersonasByName(theSearchTerm);
    };
}