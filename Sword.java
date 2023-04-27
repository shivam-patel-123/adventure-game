public class Sword extends Weapon {

    public Sword() {
        super(Configurations.SWORD);
        this.damage = Configurations.SWORD_DAMAGE;
        this.unlocksAtLevel = Configurations.SWORD_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.SWORD_UPGRADE_COINS;
    }
}
