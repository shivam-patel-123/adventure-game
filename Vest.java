public class Vest extends Armour {

    public Vest() {
        super(Configurations.VEST, Configurations.VEST_ABSORB_DAMAGE);
        this.coinsToPurchase = Configurations.VEST_PURCHASE_COST;
        this.coinsToUpgrade = Configurations.VEST_UPGRADE_COST;
    }
}
