public class Helmet extends Armour {

    public Helmet() {
        super(Configurations.HELMET, Configurations.HELMET_ABSORB_DAMAGE);
        this.coinsToPurchase = Configurations.HELMET_PURCHASE_COST;
        this.coinsToUpgrade = Configurations.HELMET_UPGRADE_COST;
    }
}
