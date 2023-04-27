public class Crossbow extends Weapon {

    public Crossbow() {
        super(Configurations.CROSSBOW);
        this.damage = Configurations.CROSSBOW_DAMAGE;
        this.unlocksAtLevel = Configurations.CROSSBOW_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.CROSSBOW_UPGRADE_COINS;
    }
}
