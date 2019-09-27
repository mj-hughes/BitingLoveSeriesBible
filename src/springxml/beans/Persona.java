package springxml.beans;

import springxml.services.LogService;
import springxml.services.LogServiceToConsole;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private int persona_id;
    private String name;
    private int age_year_zero;
    private String height;
    private String eye_color;
    private String hair_color;
    private String body_type;
    private String picture_link;
    private char[] notes;
    private LogService logService;

    // will have weapon, book, backstory lists
//    private List<Weapon> personaWeapon = new ArrayList<>();
//    private List<Book> personaBook = new ArrayList<>();
//    private List<Backstory> personaBackstory=new ArrayList<>();

    // no-arg constructor for persona
    public Persona() {
    }

    // constructor that accepts object of the service: whether console or file is chosen in the xml file
    public Persona(LogService logService) {
        this.logService=logService;
    }

    // Setter for log service
    public void setLogService(LogService logService) {
        this.logService=logService;
    }
    public void logChange(String msg) {
        logService.log(msg);
    }

    public int getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(int persona_id) {
        this.persona_id = persona_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge_year_zero() {
        return age_year_zero;
    }

    public void setAge_year_zero(int age_year_zero) {
        this.age_year_zero = age_year_zero;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public char[] getNotes() {
        return notes;
    }

    public void setNotes(char[] notes) {
        this.notes = notes;
    }
}
