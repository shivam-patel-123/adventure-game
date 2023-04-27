public class Sniper extends Weapon {

    public Sniper() {
        super(Configurations.SNIPER);
        this.damage = Configurations.SNIPER_DAMAGE;
        this.unlocksAtLevel = Configurations.SNIPER_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.SNIPER_UPGRADE_COINS;
    }
}
