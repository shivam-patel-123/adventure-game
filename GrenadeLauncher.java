public class GrenadeLauncher extends Weapon {

    public GrenadeLauncher() {
        super(Configurations.GRENADE_LAUNCHER);
        this.damage = Configurations.GRENADE_LAUNCHER_DAMAGE;
        this.unlocksAtLevel = Configurations.GRENADE_LAUNCHER_UNLOCK_LEVEL;
        this.upgradeCoins = Configurations.GRENADE_LAUNCHER_UPGRADE_COINS;
    }
}
