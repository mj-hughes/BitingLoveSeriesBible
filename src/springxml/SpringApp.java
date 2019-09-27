package springxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springxml.beans.Persona;
import springxml.beans.Weapon;

public class SpringApp {
    public static void main(String[] args) {
        // load the Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Ask the context for a character (persona)
        Persona myCharacter = context.getBean("myCharacter", Persona.class);

        // Call bean's getters for literal values
        System.out.println("From app Name: "+myCharacter.getName());
        System.out.println("From app Height: "+myCharacter.getHeight());
        System.out.println("From app Eye color: "+myCharacter.getEye_color());
        System.out.println("From app Hair color: "+myCharacter.getHair_color());
        System.out.println("From app Body type: "+myCharacter.getBody_type());
        System.out.println("From app Picture link: "+myCharacter.getPicture_link());
        String notes = new String(myCharacter.getNotes());
        System.out.println("From app Notes: "+ notes);

        // call logging-service bean's method
        myCharacter.logChange(myCharacter.getName()+"; age:  "+myCharacter.getAge_year_zero());
        myCharacter.logChange("Done with character!");

        // Ask context for a weapon
        Weapon myWeapon = context.getBean("myWeapon", Weapon.class);
        // Call bean for weapon -- NOTE loaded info into persona properties. Is there a way to get the context to recognize a second properties?
        System.out.println("From app weapon id: "+myWeapon.getWeaponId());
        System.out.println("From app weapon name: "+myWeapon.getWeaponName());
        System.out.println("From app weapon description: "+myWeapon.getDescription());

        myWeapon.logChange("Done with weapon!");

        context.close();

    }
}
