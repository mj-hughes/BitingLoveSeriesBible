package hibernate.service;

import hibernate.dao.GmcDAO;
import hibernate.entity.Gmc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GmcServiceImpl implements GmcService {
    @Autowired
    private GmcDAO gmcDAO;

    @Override
    @Transactional
    public List<Gmc> getGmcs() {
        return gmcDAO.getGmcs();
    }

    @Override
    @Transactional
    public List<Gmc> getGmcsForPersona(int personaId) {
        return gmcDAO.getGmcsForPersona(personaId);
    }
}
