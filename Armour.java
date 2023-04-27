public abstract class Armour implements IArmour {

    protected String armourType;
    protected int level;
    protected int absorbDamage;
    protected int coinsToPurchase;
    protected int coinsToUpgrade;

    public Armour(String armourType, int absorbDamage) {
        this.armourType = armourType;
        this.absorbDamage = absorbDamage;
        this.level = 1;
    }

    @Override
    public void increaseAbsorbDamage(int increaseDamageBy) {
        if (increaseDamageBy < 0) return;
        this.absorbDamage += increaseDamageBy;
    }

    @Override
    public UpgradeArmourState upgradeArmour() {
        if (this.level == Configurations.MAX_ARMOUR_LEVEL) return UpgradeArmourState.ALREADY_MAXED;
        ++this.level;
        return UpgradeArmourState.SUCCESS;
    }

    public String getArmourType() {
        return armourType;
    }

    public int getLevel() {
        return level;
    }

    public int getCoinsToPurchase() {
        return coinsToPurchase;
    }

    public int getCoinsToUpgrade() {
        return this.coinsToUpgrade;
    }

    public int getAbsorbDamage() {
        return absorbDamage;
    }
}
