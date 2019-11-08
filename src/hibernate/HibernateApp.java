package hibernate;

import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.List;

public class HibernateApp {

    public static void main(String args[]) {
        HibernateApp app = new HibernateApp();
        int id=0;
        try {
            // Persona test
            int wId=app.findWeapon("Buffalo skinner");
            // It appears we handle a M-to-M differently depending on if the other record already exists
            id=app.findPersona("Kai Elias");
            id = app.createPersona(wId);
            app.readPersona(id);
            app.listAllPersona();
            // This is an alternate read using string search. It would be more robust in a production program (for finding multiple Aidens)
            id=app.findPersona("Aiden");
            app.readPersona(id);
            app.updatePersona(id, "Aiden Blackthorne");
            app.readPersona(id);
            app.deletePersona(id);
            app.listAllPersona();
            // This is just to test that related rows don't have to be added at the same time
            id=app.createPersonaAlone();
            app.readPersona(id);
            app.deletePersona(id);

                // Book test
            id = app.createBook();
            app.readBook(id);
            app.updateBook(id, "Biting Serendipity");
            app.readBook(id);
            app.deleteBook(id);
            app.readBook(id);

            // Custom test
            id = app.createCustom();
            app.readCustom(id);
            app.updateCustom(id, "Bargaining rights");
            app.readCustom(id);
            app.deleteCustom(id);
            app.readCustom(id);

            // Glossary test
            id = app.createGlossary();
            app.readGlossary(id);
            app.updateGlossary(id, "Ja");
            app.readGlossary(id);
            app.deleteGlossary(id);
            app.readGlossary(id);

            // Gmc test
            int pId=app.findPersona("Elena");
            id = app.createGmc(pId);
            app.readGmc(id);
            app.updateGmc(id, "Get her permanent detective's shield");
            app.readGmc(id);
            app.deleteGmc(id);
            app.readGmc(id);

            // Organization test
            id = app.createOrganization();
            app.readOrganization(id);
            app.updateOrganization(id, "Iowa Alliance");
            app.readOrganization(id);
            app.deleteOrganization(id);
            app.readOrganization(id);

            // Research test
            id = app.createResearch();
            app.readResearch(id);
            app.updateResearch(id, "Illinois crime labs");
            app.readResearch(id);
            app.deleteResearch(id);
            app.readResearch(id);

            // Setting test
            id = app.createSetting();
            app.readSetting(id);
            app.updateSetting(id, "Meiers Corners");
            app.readSetting(id);
            app.deleteSetting(id);
            app.readSetting(id);

            // UnresolvedPlotPoint test
            id = app.createUnresolvedPlotPoint();
            app.readUnresolvedPlotPoint(id);
            app.updateUnresolvedPlotPoint(id, "Drusilla");
            app.readUnresolvedPlotPoint(id);
            app.deleteUnresolvedPlotPoint(id);
            app.readUnresolvedPlotPoint(id);

            // VPower test
            id = app.createVPower();
            app.readVPower(id);
            app.updateVPower(id, "Misting");
            app.readVPower(id);
            app.deleteVPower(id);
            app.readVPower(id);

            // Weapon test
            id = app.createWeapon();
            app.readWeapon(id);
            app.updateWeapon(id, "Seax switchblade");
            app.readWeapon(id);
            app.deleteWeapon(id);
            app.readWeapon(id);

        } finally {
            app.close();
        }
    }


    private SessionFactory factory;

    public HibernateApp() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Persona.class)
                .addAnnotatedClass(Backstory.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Custom.class)
                .addAnnotatedClass(Glossary.class)
                .addAnnotatedClass(Gmc.class)
                .addAnnotatedClass(Organization.class)
                .addAnnotatedClass(Research.class)
                .addAnnotatedClass(Setting.class)
                .addAnnotatedClass(UnresolvedPlotPoint.class)
                .addAnnotatedClass(VPower.class)
                .addAnnotatedClass(Weapon.class)
                .buildSessionFactory();
    }

    private void close() {
        factory.close();
    }


    // ** Persona CRUD statements **/
    private int createPersona(int wId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Persona newPersona = new Persona("Aiden", 209, "6'3\"", "000000", "Black", "Lithe, muscled, deadly", "", "Born 1800. Long-fingered hands, thin but sexy lips. Assassin. Even the sens of dark danger clung to him like a shadow. Aiden's bad feelings are never well defined but always right.");

        // GMC is a 1-to-M unidirectional relationship. The cascade type is ALL. The fetch type is lazy.
        //  Note: there are convenience methods in the Persona entity class to compensate for all lazy loads.
        Gmc g1 = new Gmc("E", "Aiden's external goal","Aiden's external motive", "Aiden's external conflict");
        Gmc g2 = new Gmc("I", "Aiden's internal goal", "Aiden's internal motive","Aiden's internal conflict");
        newPersona.add(g1);
        newPersona.add(g2);

        // Backstory is a 1-to-M unidirectional relationship. The cascade type is ALL. The fetch type is lazy.
        Backstory bStory = new Backstory("Made vampire ca 1810", "Made by Nosferatu as one of the first of his turning orphans.");
        newPersona.add(bStory);

        // Book is a  relationship. The cascade type is all but REMOVE. The fetch type is lazy.
        //   Note: It's M-to-M, but Aiden's book exists, so only do newPersona save
        Book b = new Book();
        b.setId(8);
        newPersona.add(b);

        // Weapon a M-to-M  relationship. The cascade type is all but REMOVE. The fetch type is lazy.
        //   Note: if we're adding a new weapon, we need to save both.
        if (wId==0) {
            Weapon w = new Weapon("Buffalo skinner", "Sharp enough to skin, heavy enough to chop through bone, 10\" 1095 higih carbon steel");
            session.save(w);
            w.add(newPersona);
        } else {
            Weapon w = new Weapon();
            w.setId(wId);
            newPersona.add(w);
        }

        session.save(newPersona);

        session.getTransaction().commit();
        return newPersona.getId();
    }
    private void readPersona(int pId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a persona, will return null
        Persona p = session.get(Persona.class, pId);
        if (p==null) {
            System.out.println("No persona found for ID "+pId);
        } else {
            // Prove the read was correct
            System.out.println(p);
        }
        session.getTransaction().commit();
    }
    private void updatePersona(int pId, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Persona p = session.get(Persona.class, pId);
        if (p!=null) {
            p.setName(newName);
        } else {
            System.out.println("No persona found for ID "+pId);
        }

        session.getTransaction().commit();
    }
    private void deletePersona(int pId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Persona pDel = session.get(Persona.class, pId);
        if (pDel!=null) {
            session.delete(pDel);
        } else {
            System.out.println("No persona found for ID "+pId);
        }

        session.getTransaction().commit();
    }
    private void listAllPersona() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // List queries don't error out if no rows are found.
        List<Persona> list = session.createQuery("from hibernate.entity.Persona").getResultList();
        printPersonaList(list);

        session.getTransaction().commit();
    }
    private void printPersonaList(List<Persona> list) {
        if (list.isEmpty()) {
            System.out.println("No characters in list");
        } else {
            for (Persona p: list) {
                System.out.println(p);
            }
        }
    }
    private int findPersona(String name) {
        int id=0;
        Persona p=null;
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        name=name.toLowerCase();
        String hql = "from hibernate.entity.Persona p where lower(p.name) like '%"+name+"%'";
        //List<Persona> list = session.createQuery(hql, Persona.class).getResultList();
        //int id=list.get(0).getId();
        try {
            p = session.createQuery(hql, Persona.class).getSingleResult();
        }
        catch (NoResultException e) {
            // No row found
        }

        if (p!=null) {
            id = p.getId();
        }
        // Parameterized query:
        // String hql = "FROM Donut E WHERE E.id = :abc";
        // Query<Donut> query =
        //               session.createQuery(hql, Donut.class);
        // query.setParameter("abc",10);
        // Donut donut = query.getSingleResult();

        session.getTransaction().commit();
        return id;
    }
    private int createPersonaAlone() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Persona newPersona = new Persona("Sunny Ruffles", 25, "5'2\"", "000000", "Black", "Small", "", "New police officer.");
        session.save(newPersona);

        session.getTransaction().commit();
        return newPersona.getId();
    }


    // ** Backstory CRUD statements **/

    // ** Book CRUD statements **/
    private int createBook() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Book b = new Book("Biting Sera", 8, 3);
        session.save(b);
        session.getTransaction().commit();
        return b.getId();
    }
    private void readBook(int bId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Book, will return null
        Book b = session.get(Book.class, bId);
        if (b==null) {
            System.out.println("No Book found for ID "+bId);
        } else {
            // Prove the read was correct
            System.out.println(b);
        }
        session.getTransaction().commit();
    }
    private void updateBook(int bId, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Book b = session.get(Book.class, bId);
        if (b!=null) {
            b.setTitle(newName);
        } else {
            System.out.println("No Book found for ID "+bId);
        }

        session.getTransaction().commit();
    }
    private void deleteBook(int bId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Book bDel = session.get(Book.class, bId);
        if (bDel!=null) {
            session.delete(bDel);
        } else {
            System.out.println("No Book found for ID "+bId);
        }

        session.getTransaction().commit();
    }

    // ** Custom CRUD statements **/
    private int createCustom() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Custom newCustom = new Custom("Bargain Rights", "When there's a dispute over territory, one representative from each claimant sits down at the bargaining table.");
        session.save(newCustom);

        session.getTransaction().commit();
        return newCustom.getId();
    }
    private void readCustom(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Custom, will return null
        Custom readCustom = session.get(Custom.class, id);
        if (readCustom==null) {
            System.out.println("No Custom found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readCustom);
        }
        session.getTransaction().commit();
    }
    private void updateCustom(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Custom updateCustom = session.get(Custom.class, id);
        if (updateCustom!=null) {
            updateCustom.setCustomName(newName);
        } else {
            System.out.println("No Custom found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteCustom(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Custom deleteCustom = session.get(Custom.class, id);
        if (deleteCustom!=null) {
            session.delete(deleteCustom);
        } else {
            System.out.println("No Custom found for ID "+id);
        }

        session.getTransaction().commit();
    }

    // ** Glossary CRUD statements **/
    private int createGlossary() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Glossary newGlossary = new Glossary("Ya", "German-yes");
        session.save(newGlossary);

        session.getTransaction().commit();
        return newGlossary.getId();
    }
    private void readGlossary(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Glossary, will return null
        Glossary readGlossary = session.get(Glossary.class, id);
        if (readGlossary==null) {
            System.out.println("No Glossary found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readGlossary);
        }
        session.getTransaction().commit();
    }
    private void updateGlossary(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Glossary updateGlossary = session.get(Glossary.class, id);
        if (updateGlossary!=null) {
            updateGlossary.setGlossaryName(newName);
        } else {
            System.out.println("No Glossary found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteGlossary(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Glossary deleteGlossary = session.get(Glossary.class, id);
        if (deleteGlossary!=null) {
            session.delete(deleteGlossary);
        } else {
            System.out.println("No Glossary found for ID "+id);
        }

        session.getTransaction().commit();
    }


    // ** Gmc CRUD statements **/
    private int createGmc(int pId) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Gmc newGmc = new Gmc("E", "Get her detective's shield", "To make her dad proud", "Obstacles to solving the case");
        Persona thisPersona = session.get(Persona.class, pId);
        thisPersona.add(newGmc);

        session.save(thisPersona);

        session.getTransaction().commit();
        return newGmc.getId();
    }
    private void readGmc(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Gmc, will return null
        Gmc readGmc = session.get(Gmc.class, id);
        if (readGmc==null) {
            System.out.println("No Gmc found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readGmc);
        }
        session.getTransaction().commit();
    }
    private void updateGmc(int id, String newGoal) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Gmc updateGmc = session.get(Gmc.class, id);
        if (updateGmc!=null) {
            updateGmc.setGoal(newGoal);
        } else {
            System.out.println("No Gmc found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteGmc(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Gmc deleteGmc = session.get(Gmc.class, id);
        if (deleteGmc!=null) {
            session.delete(deleteGmc);
        } else {
            System.out.println("No Gmc found for ID "+id);
        }

        session.getTransaction().commit();
    }


    // ** Organization CRUD statements **/
    private int createOrganization() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Organization newOrganization = new Organization("Iowa", "Vampire alliance based in Iowa, led by Kai Elias.");
        session.save(newOrganization);

        session.getTransaction().commit();
        return newOrganization.getId();
    }
    private void readOrganization(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Organization, will return null
        Organization readOrganization = session.get(Organization.class, id);
        if (readOrganization==null) {
            System.out.println("No Organization found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readOrganization);
        }
        session.getTransaction().commit();
    }
    private void updateOrganization(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Organization updateOrganization = session.get(Organization.class, id);
        if (updateOrganization!=null) {
            updateOrganization.setOrganizationName(newName);
        } else {
            System.out.println("No Organization found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteOrganization(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Organization deleteOrganization = session.get(Organization.class, id);
        if (deleteOrganization!=null) {
            session.delete(deleteOrganization);
        } else {
            System.out.println("No Organization found for ID "+id);
        }

        session.getTransaction().commit();
    }


    // ** Research CRUD statements **/
    private int createResearch() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Research newResearch = new Research("Crime Labs", "Most police departments don't have on-site crime labs, but send it to one of the 6 in the state.");
        session.save(newResearch);

        session.getTransaction().commit();
        return newResearch.getId();
    }
    private void readResearch(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Research, will return null
        Research readResearch = session.get(Research.class, id);
        if (readResearch==null) {
            System.out.println("No Research found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readResearch);
        }
        session.getTransaction().commit();
    }
    private void updateResearch(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Research updateResearch = session.get(Research.class, id);
        if (updateResearch!=null) {
            updateResearch.setResearchName(newName);
        } else {
            System.out.println("No Research found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteResearch(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Research deleteResearch = session.get(Research.class, id);
        if (deleteResearch!=null) {
            session.delete(deleteResearch);
        } else {
            System.out.println("No Research found for ID "+id);
        }

        session.getTransaction().commit();
    }

    // ** Setting CRUD statements **/
    private int createSetting() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Setting newSetting = new Setting("Meier's Corners", "Small town a short distance west of Chicago in miles but lightyears away in attitude.");
        session.save(newSetting);

        session.getTransaction().commit();
        return newSetting.getId();
    }
    private void readSetting(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Setting, will return null
        Setting readSetting = session.get(Setting.class, id);
        if (readSetting==null) {
            System.out.println("No Setting found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readSetting);
        }
        session.getTransaction().commit();
    }
    private void updateSetting(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Setting updateSetting = session.get(Setting.class, id);
        if (updateSetting!=null) {
            updateSetting.setSettingName(newName);
        } else {
            System.out.println("No Setting found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteSetting(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Setting deleteSetting = session.get(Setting.class, id);
        if (deleteSetting!=null) {
            session.delete(deleteSetting);
        } else {
            System.out.println("No Setting found for ID "+id);
        }

        session.getTransaction().commit();
    }

    // ** UnresolvedPlotPoint CRUD statements **/
    private int createUnresolvedPlotPoint() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        UnresolvedPlotPoint newUnresolvedPlotPoint = new UnresolvedPlotPoint("Dru","Resolved: She's working for Adelaide's Heart",  "What is her payoff?");
        session.save(newUnresolvedPlotPoint);

        session.getTransaction().commit();
        return newUnresolvedPlotPoint.getId();
    }
    private void readUnresolvedPlotPoint(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a UnresolvedPlotPoint, will return null
        UnresolvedPlotPoint readUnresolvedPlotPoint = session.get(UnresolvedPlotPoint.class, id);
        if (readUnresolvedPlotPoint==null) {
            System.out.println("No UnresolvedPlotPoint found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readUnresolvedPlotPoint);
        }
        session.getTransaction().commit();
    }
    private void updateUnresolvedPlotPoint(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        UnresolvedPlotPoint updateUnresolvedPlotPoint = session.get(UnresolvedPlotPoint.class, id);
        if (updateUnresolvedPlotPoint!=null) {
            updateUnresolvedPlotPoint.setUnresolvedPlotPointName(newName);
        } else {
            System.out.println("No UnresolvedPlotPoint found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteUnresolvedPlotPoint(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        UnresolvedPlotPoint deleteUnresolvedPlotPoint = session.get(UnresolvedPlotPoint.class, id);
        if (deleteUnresolvedPlotPoint!=null) {
            session.delete(deleteUnresolvedPlotPoint);
        } else {
            System.out.println("No UnresolvedPlotPoint found for ID "+id);
        }

        session.getTransaction().commit();
    }

    // ** VPower CRUD statements **/
    private int createVPower() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        VPower newVPower = new VPower("Mist", "At approximately 100 years of age, vampires can blow their bodies apart into mist. Mist is the fastest form a vampire travel. They can stream through small spaces.", "Has to do with cell cohesion.");
        session.save(newVPower);

        session.getTransaction().commit();
        return newVPower.getId();
    }
    private void readVPower(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a VPower, will return null
        VPower readVPower = session.get(VPower.class, id);
        if (readVPower==null) {
            System.out.println("No VPower found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readVPower);
        }
        session.getTransaction().commit();
    }
    private void updateVPower(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        VPower updateVPower = session.get(VPower.class, id);
        if (updateVPower!=null) {
            updateVPower.setPowerName(newName);
        } else {
            System.out.println("No VPower found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteVPower(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        VPower deleteVPower = session.get(VPower.class, id);
        if (deleteVPower!=null) {
            session.delete(deleteVPower);
        } else {
            System.out.println("No VPower found for ID "+id);
        }

        session.getTransaction().commit();
    }

    // ** Weapon CRUD statements **/
    private int createWeapon() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Weapon newWeapon = new Weapon("Seax Switchblade", "9\" switchblade with 4 1/2 \" blade");
        session.save(newWeapon);

        session.getTransaction().commit();
        return newWeapon.getId();
    }
    private void readWeapon(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // If id doesn't map to a Weapon, will return null
        Weapon readWeapon = session.get(Weapon.class, id);
        if (readWeapon==null) {
            System.out.println("No Weapon found for ID "+id);
        } else {
            // Prove the read was correct
            System.out.println(readWeapon);
        }
        session.getTransaction().commit();
    }
    private void updateWeapon(int id, String newName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Weapon updateWeapon = session.get(Weapon.class, id);
        if (updateWeapon!=null) {
            updateWeapon.setWeaponName(newName);
        } else {
            System.out.println("No Weapon found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private void deleteWeapon(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Weapon deleteWeapon = session.get(Weapon.class, id);
        if (deleteWeapon!=null) {
            session.delete(deleteWeapon);
        } else {
            System.out.println("No Weapon found for ID "+id);
        }

        session.getTransaction().commit();
    }
    private int findWeapon(String name) {
        int id=0;
        Weapon w=null;
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        name=name.toLowerCase();
        String hql = "from hibernate.entity.Weapon w where lower(w.weaponName) like '%"+name+"%'";
        try {
            w = session.createQuery(hql, Weapon.class).getSingleResult();
        }
        catch (NoResultException e) {
            // No row found
        }
        if (w!=null) {
            id = w.getId();
        }

        session.getTransaction().commit();
        return id;
    }


}
