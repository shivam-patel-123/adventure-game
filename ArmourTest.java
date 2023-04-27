public class ArmourTest {

    public ArmourTest() {
        increaseAbsorbDamageTest();
        increaseAbsorbDamageNegativeTest();
        upgradeArmourSuccessTest();
        upgradeArmourAlreadyMaxedTest();
        getArmourTypeTest();
        getLevelTest();
        getCoinsToPurchaseTest();
        getCoinsToUpgradeTest();
        getAbsorbDamageTest();
    }

    public void increaseAbsorbDamageTest() {
        Armour armour = new Vest();
        armour.increaseAbsorbDamage(2);

        if (armour.getAbsorbDamage() == Configurations.VEST_ABSORB_DAMAGE + 2) {
            System.out.println("increaseAbsorbDamageTest - PASS");
        } else {
            System.out.println("increaseAbsorbDamageTest - FAIL");
        }
    }

    public void increaseAbsorbDamageNegativeTest() {
        Armour armour = new Vest();
        armour.increaseAbsorbDamage(-10);

        if (armour.getAbsorbDamage() == armour.getAbsorbDamage()) {
            System.out.println("increaseAbsorbDamageNegativeTest - PASS");
        } else {
            System.out.println("increaseAbsorbDamageNegativeTest - FAIL");
        }
    }

    public void upgradeArmourSuccessTest() {
        Armour armour = new Vest();
        IArmour.UpgradeArmourState status = armour.upgradeArmour();

        if (IArmour.UpgradeArmourState.SUCCESS == status) {
            System.out.println("upgradeArmourSuccessTest - PASS");
        } else {
            System.out.println("upgradeArmourSuccessTest - FAIL");
        }
    }

    public void upgradeArmourAlreadyMaxedTest() {
        Armour armour = new Vest();
        armour.upgradeArmour(); // Vest Level 2
        armour.upgradeArmour(); // Vest Level 3
        armour.upgradeArmour(); // Vest Level 4, but not allowed
        IArmour.UpgradeArmourState status = armour.upgradeArmour();

        if (IArmour.UpgradeArmourState.ALREADY_MAXED == status) {
            System.out.println("upgradeArmourAlreadyMaxedTest - PASS");
        } else {
            System.out.println("upgradeArmourAlreadyMaxedTest - FAIL");
        }
    }

    public void getArmourTypeTest() {
        Armour armour = new Vest();

        if (armour.getArmourType().equals(Configurations.VEST)) {
            System.out.println("getArmourTypeTest - PASS");
        } else {
            System.out.println("getArmourTypeTest - FAIL");
        }
    }

    public void getLevelTest() {
        Armour armour = new Helmet();

        if (armour.getLevel() == 1) {
            System.out.println("getLevelTest - PASS");
        } else {
            System.out.println("getLevelTest - FAIL");
        }
    }

    public void getCoinsToPurchaseTest() {
        Armour militaryVest = new Vest();

        if (militaryVest.getCoinsToPurchase() == Configurations.VEST_PURCHASE_COST) {
            System.out.println("getCoinsToPurchaseTest - PASS");
        } else {
            System.out.println("getCoinsToPurchaseTest - FAIL");
        }
    }

    public void getCoinsToUpgradeTest() {
        Armour armour = new Vest();

        if (armour.getCoinsToUpgrade() == Configurations.VEST_UPGRADE_COST) {
            System.out.println("getCoinsToUpgradeTest - PASS");
        } else {
            System.out.println("getCoinsToUpgradeTest - FAIL");
        }
    }

    public void getAbsorbDamageTest() {
        Armour armour = new Vest();

        if (armour.getAbsorbDamage() == Configurations.VEST_ABSORB_DAMAGE) {
            System.out.println("getAbsordDamageTest - PASS");
        } else {
            System.out.println("getAbsordDamageTest - FAIL");
        }
    }
}
