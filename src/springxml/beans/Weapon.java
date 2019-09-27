package springxml.beans;

import springxml.services.LogService;

public class Weapon {
    private int weaponId;
    private String weaponName;
    private String description;
    private LogService logService;

    public Weapon() {
    }

    // constructor that accepts object of the service: whether console or file is chosen in the xml file
    public Weapon(LogService logService) {
        this.logService=logService;
    }

    // Setter for log service
    public void setLogService(LogService logService) {
        this.logService=logService;
    }
    public void logChange(String msg) {
        logService.log(msg);
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
