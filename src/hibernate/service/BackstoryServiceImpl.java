package hibernate.service;

import hibernate.dao.BackstoryDAO;
import hibernate.entity.Backstory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BackstoryServiceImpl implements BackstoryService {
    @Autowired
    private BackstoryDAO backstoryDAO;

    @Override
    @Transactional
    public List<Backstory> getBackstories() {
        return backstoryDAO.getBackstories();
    }

    @Override
    @Transactional
    public List<Backstory> getBackstoriesForPersona(int personaId) {
            return backstoryDAO.getBackstoriesForPersona(personaId);
    }
}
