package hibernate.service;

import hibernate.entity.Gmc;

import java.util.List;

public interface GmcService {
    List<Gmc> getGmcs();
    List<Gmc> getGmcsForPersona(int personaId);
}
