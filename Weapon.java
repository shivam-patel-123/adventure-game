public class Weapon {

    protected String weaponName;
    protected int damage;
    protected int weaponLevel;
    protected int unlocksAtLevel;
    protected int upgradeCoins;

    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.weaponLevel = 1;
    }

    public void upgradeWeapon() {
        this.weaponLevel++;
        increaseWeaponDamage(Configurations.INCREASE_DAMAGE);
    }

    private void increaseWeaponDamage(int damage) {
        if (damage <= 0) return;
        this.damage += damage;
    }

    public void setWeaponName(String weaponName) {
        if (!Utils.validateName(weaponName)) return;
        this.weaponName = weaponName;
    }

    public String getWeaponName() {
        return weaponName;
    }
    public int getDamage() {
        return damage;
    }
    public int getWeaponLevel() {
        return this.weaponLevel;
    }
    public int getUnlocksAtLevel() {
        return this.unlocksAtLevel;
    }
    public int getUpgradeCoins() {
        return this.upgradeCoins;
    }
}
