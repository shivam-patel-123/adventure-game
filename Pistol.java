public class Pistol extends Weapon {

    public Pistol() {
        super(Configurations.PISTOL);
        this.damage = Configurations.PISTOL_DAMAGE;
        this.unlocksAtLevel = Configurations.PISTOL_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.PISTOL_UPGRADE_COINS;
    }
}
