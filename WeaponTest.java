public class WeaponTest {

    public WeaponTest() {
        upgradeWeaponTest();
        upgradeWeaponIncorrectTest();
        setWeaponNameTest();
        setIncorrectWeaponNameTest();
        getWeaponNameTest();
        getDamageTest();
        getWeaponLevel();
        getUnlocksAtLevelTest();
        getUpgradeCoinsTest();
    }

    public void upgradeWeaponTest() {
        Weapon weapon = new Crossbow();
        weapon.upgradeWeapon();

        if (weapon.getWeaponLevel() == 2) {
            System.out.println("upgradeWeaponTest - PASS");
        } else {
            System.out.println("upgradeWeaponTest - FAIL");
        }
    }

    public void upgradeWeaponIncorrectTest() {
        Weapon weapon = new Crossbow();
        weapon.upgradeWeapon();

        if (weapon.getWeaponLevel() == 2) {
            System.out.println("upgradeWeaponIncorrectTest - PASS");
        } else {
            System.out.println("upgradeWeaponIncorrectTest - FAIL");
        }
    }

    public void setWeaponNameTest() {
        Weapon weapon = new Crossbow();
        weapon.setWeaponName("Super Crossbow");

        if (weapon.getWeaponName().equals("Super Crossbow")) {
            System.out.println("setWeaponNameTest - PASS");
        } else {
            System.out.println("setWeaponNameTest - FAIL");
        }
    }

    public void setIncorrectWeaponNameTest() {
        Weapon weapon = new Crossbow();
        weapon.setWeaponName("123Crossbow");

        if (weapon.getWeaponName().equals("Crossbow")) {
            System.out.println("setIncorrectWeaponNameTest - PASS");
        } else {
            System.out.println("setIncorrectWeaponNameTest - FAIL");
        }
    }

    public void getWeaponNameTest() {
        Weapon weapon = new Sniper();

        if (weapon.getWeaponName().equals(Configurations.SNIPER)) {
            System.out.println("getWeaponNameTest - PASS");
        } else {
            System.out.println("getWeaponNameTest - FAIL");
        }
    }

    public void getDamageTest() {
        Weapon weapon = new GrenadeLauncher();

        if (weapon.getDamage() == Configurations.GRENADE_LAUNCHER_DAMAGE) {
            System.out.println("getDamageTest - PASS");
        } else {
            System.out.println("getDamageTest - FAIL");
        }
    }

    public void getWeaponLevel() {
        Weapon weapon = new Crossbow();

        if (weapon.getWeaponLevel() == 1) {
            System.out.println("getWeaponLevelTest - PASS");
        } else {
            System.out.println("getWeaponLevelTest - FAIL");
        }
    }

    public void getUnlocksAtLevelTest() {
        Weapon weapon = new Crossbow();

        if (weapon.getUnlocksAtLevel() == Configurations.CROSSBOW_UNLOCK_LEVEL) {
            System.out.println("getUnlocksAtLevelTest - PASS");
        } else {
            System.out.println("getUnlocksAtLevelTest - FAIL");
        }
    }

    public void getUpgradeCoinsTest() {
        Weapon weapon = new Crossbow();

        if (weapon.getUpgradeCoins() == Configurations.CROSSBOW_UPGRADE_COINS) {
            System.out.println("getUpgradeCoinsTest - PASS");
        } else {
            System.out.println("getUpgradeCoinsTest - FAIL");
        }
    }
}
