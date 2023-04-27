public class Rifle extends Weapon {

    public Rifle() {
        super(Configurations.RIFLE);
        this.damage = Configurations.RIFLE_DAMAGE;
        this.unlocksAtLevel = Configurations.RIFLE_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.RIFLE_UPGRADE_COINS;
    }
}
