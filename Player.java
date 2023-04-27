import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;

public class Player {

    private String name;
    private String uniqueId;
    private int purchaseCoins;
    private double mainBalance;
    private int currentLevel;
    private double hitPoints;
    private Weapon weapon;
    private Vest vest;
    private Helmet helmet;

    private void initializePlayer() {
        this.uniqueId = UUID.randomUUID().toString();
        this.purchaseCoins = Configurations.INITIAL_PURCHASE_COINS;
        this.mainBalance = 0;
        this.currentLevel = 1;
        this.hitPoints = Configurations.INITIAL_HIT_POINTS;
    }

    public Player(String name) {
        this.name = name;
        initializePlayer();
    }

    public Player(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
        initializePlayer();
    }

    public Player(String name, Weapon weapon, Vest vest) {
        this.name = name;
        this.weapon = weapon;
        this.vest = vest;
        initializePlayer();
    }

    public Player(String name, Weapon weapon, Vest vest, Helmet helmet) {
        this.name = name;
        this.weapon = weapon;
        this.vest = vest;
        this.helmet = helmet;
        initializePlayer();
    }

    public IPlayer.NewPlayerState createNewPlayer(IPlayer playersDB) {
        return playersDB.createNewPlayer(this);
    }

    public double attack(IPlayer playerDB, Player opponentPlayer, int aimNumber) {
        Map<Integer, Integer> aimMap = Configurations.aimMap;

        double d = opponentPlayer.getHitPoints() - ((aimMap.get(aimNumber) / 100.0) * weapon.getDamage());
        if (opponentPlayer.vest != null && Configurations.shotTypeMap.get(aimNumber).equals(Configurations.BODY_SHOT)) {
            d += opponentPlayer.vest.getAbsorbDamage();
        }
        if (opponentPlayer.helmet != null && Configurations.shotTypeMap.get(aimNumber).equals(Configurations.HEAD_SHOT)) {
            d += opponentPlayer.helmet.getAbsorbDamage();
        }
        opponentPlayer.setHitPoints(Double.parseDouble(new DecimalFormat("0.00").format(d)));

        IPlayer.UpdatePlayerState updateStatus = playerDB.updatePlayer(opponentPlayer);
        if(updateStatus == IPlayer.UpdatePlayerState.SUCCESS) {
            return opponentPlayer.getHitPoints();
        }
        return -1;
    }

    public boolean upgradeWeaponLevel(IPlayer playersDB) {
        if (getPurchaseCoins() < getWeapon().upgradeCoins) return false;

        setPurchaseCoins(this.purchaseCoins - this.weapon.getUpgradeCoins());

        weapon.upgradeWeapon();
        playersDB.updatePlayer(this);
        return true;
    }

    public boolean changeWeapon(IPlayer playersDB, Weapon weapon) {
        if (weapon.getUnlocksAtLevel() > getLevel()) return false;
        this.weapon = weapon;
        playersDB.updatePlayer(this);
        return true;
    }

    public IArmour.PurchaseArmourState purchaseArmour(IPlayer playersDB, Armour armour) {
        if (armour != null && armour.getArmourType().equals(Configurations.VEST)) {
            if(this.vest != null) return IArmour.PurchaseArmourState.ALREADY_PURCHASED;
            if (getPurchaseCoins() < armour.getCoinsToPurchase()) return IArmour.PurchaseArmourState.NOT_ENOUGH_COINS;

            setPurchaseCoins(this.purchaseCoins - armour.getCoinsToPurchase());
            setVest((Vest) armour);
            playersDB.updatePlayer(this);
            return IArmour.PurchaseArmourState.SUCCESS;
        }
        if (armour != null && armour.getArmourType().equals(Configurations.HELMET)) {
            if(this.helmet != null) return IArmour.PurchaseArmourState.ALREADY_PURCHASED;
            if(getPurchaseCoins() < armour.getCoinsToPurchase()) return IArmour.PurchaseArmourState.NOT_ENOUGH_COINS;

            setPurchaseCoins(this.purchaseCoins - armour.getCoinsToPurchase());
            setHelmet((Helmet) armour);
            playersDB.updatePlayer(this);
            return IArmour.PurchaseArmourState.SUCCESS;
        }
        return null;
    }

    public IArmour.UpgradeArmourState upgradeArmour(String armourType) {
        if (this.vest != null && this.vest.getArmourType().equals(armourType)) {
            return upgradeVest();
        }
        if (this.helmet != null && this.helmet.getArmourType().equals(armourType)) {
            return upgradeHelmet();
        }
        return null;
    }

    public IArmour.UpgradeArmourState upgradeVest() {
        int coinsToUpgrade = this.vest.getCoinsToUpgrade();
        if (this.purchaseCoins < coinsToUpgrade) return IArmour.UpgradeArmourState.NOT_ENOUGH_COINS;
        setPurchaseCoins(this.purchaseCoins - coinsToUpgrade);
        return this.vest.upgradeArmour();
    }

    public IArmour.UpgradeArmourState upgradeHelmet() {
        int coinsToUpgrade = this.helmet.getCoinsToUpgrade();
        if (this.purchaseCoins < coinsToUpgrade) return IArmour.UpgradeArmourState.NOT_ENOUGH_COINS;
        setPurchaseCoins(this.purchaseCoins - coinsToUpgrade);
        return this.helmet.upgradeArmour();
    }

    public void setName(String name) {
        boolean isNameValid = Utils.validateName(name);
        if(!isNameValid) return;
        this.name = name;
    }

    public void setHitPoints(double hitPoints) {
        if (hitPoints > Configurations.INITIAL_HIT_POINTS) {
            this.hitPoints = Configurations.INITIAL_HIT_POINTS;
            return;
        } else if (hitPoints < 0) {
            this.hitPoints = 0;
            return;
        }
        this.hitPoints = hitPoints;
    }

    public void addPurchaseCoins(int purchaseCoins) {
        if (purchaseCoins < 0) return;
        this.purchaseCoins += purchaseCoins;
    }

    public void setPurchaseCoins(int purchaseCoins) {
        if (purchaseCoins < 0) return;
        this.purchaseCoins = purchaseCoins;
    }

    public void setMainBalance(double balance) {
        if (balance <= 0) {
            this.mainBalance = 0;
            return;
        }
        this.mainBalance = balance;
    }

    public void setWeapon(Weapon weapon) {
        if (getLevel() < weapon.unlocksAtLevel) {
            return ;
        }
        this.weapon = weapon;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public void incrementLevel() {
        this.currentLevel++;
    }

    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public double getMainBalance() {
        return mainBalance;
    }

    public int getPurchaseCoins() {
        return purchaseCoins;
    }

    public int getLevel() {
        return currentLevel;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armour getVest() {
        return vest;
    }

    public Armour getHelmet() {
        return helmet;
    }
}
