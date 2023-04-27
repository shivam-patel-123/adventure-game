public interface IArmour {

     enum PurchaseArmourState {
        SUCCESS,
        ALREADY_PURCHASED,
        NOT_ENOUGH_COINS
    }

    enum UpgradeArmourState {
        SUCCESS,
        ALREADY_MAXED,
        NOT_ENOUGH_COINS
    }

    void increaseAbsorbDamage(int increaseDamageBy);
    UpgradeArmourState upgradeArmour();
}
