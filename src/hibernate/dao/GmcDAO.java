package hibernate.dao;

import hibernate.entity.Gmc;

import java.util.List;

public interface GmcDAO {
    List<Gmc> getGmcs();
    List<Gmc> getGmcsForPersona(int personaId);
}
