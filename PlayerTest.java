public class PlayerTest {

    public PlayerTest() {
        createNewPlayerSuccessTest();
        createNewPlayerIncorrectDataTest();
        attackKnownPlayerTest();
        attackPlayerWithVestTest();
        attackPlayerWithHelmetTest();
        attackUnknownPlayerTest();
        setNameTest();
        setIncorrectNameTest();
        setHitPointsTest();
        setExcessiveHitPointsTest();
        setNegativeHitPointsTest();
        setWeaponTest();
        setWeaponIncorrectTest();
        setMainBalanceTest();
        setMainBalanceNegativeTest();
        setPurchaseCoinsTest();
        setPurchaseCoinsNegativeTest();
        setVestTest();
        setNullVestTest();
        setVestOfHighLevelTest();
        setHelmetTest();
        setNullHelmetTest();
        setHelmetOfHighLevelTest();
        incrementLevelTest();
        upgradeWeaponLevelTest();
        upgradeWeaponWithLowCoinsTest();
        upgradeArmourWithInsufficientCoinsTest();
        upgradeArmourVestTest();
        upgradeArmourHelmetTest();
        upgradeArmourIncorrectTest();
        upgradeVestTest();
        upgradeVestWithInsufficientCoinsTest();
        changeWeaponTest();
        changeWeaponAtLowLevelTest();
        purchaseVestTest();
        purchaseHelmetTest();
        purchaseArmourIncorrectTest();
        addPurchaseCoinsTest();
        addNegativePurchaseCoinsTest();
        getNameTest();
        getUniqueIdTest();
        getMainBalanceTest();
        getPurchaseCoinsTest();
        getLevelTest();
        getHitPointsTest();
        getWeaponTest();
        getVestTest();
        getHelmetTest();
    }

    public void createNewPlayerSuccessTest() {
        Player p = new Player("Shivam");
        IPlayer.NewPlayerState out = p.createNewPlayer(new PlayersDBMock());

        if (IPlayer.NewPlayerState.SUCCESS == out) {
            System.out.println("createNewPlayerSuccessTest - PASS");
        } else {
            System.out.println("createNewPlayerSuccessTest - FAIL");
        }
    }
    public void createNewPlayerIncorrectDataTest() {
        Player p = new Player("", new Crossbow());
        IPlayer.NewPlayerState out = p.createNewPlayer(new PlayersDBMock());

        if (IPlayer.NewPlayerState.INCORRECT_DATA == out) {
            System.out.println("createNewPlayerIncorrectDataTest - PASS");
        } else {
            System.out.println("createNewPlayerIncorrectDataTest - FAIL");
        }
    }

    public void attackKnownPlayerTest() {
        PlayersDBMock playersDBMock = new PlayersDBMock();

        Player p1 = playersDBMock.getPlayerByIndex(0);
        Player p2 = playersDBMock.getPlayerByIndex(1);

        p1.setHitPoints(100);
        p2.setHitPoints(100);
        p2.setHelmet(null);
        p2.setVest(null);

        double expectedHitPointsOfP2 = p2.getHitPoints() - ((100 / 100.0) * p1.getWeapon().getDamage());
        double actualHitPointsOfP2 = p1.attack(playersDBMock, p2, 2);

        if (expectedHitPointsOfP2 == actualHitPointsOfP2) {
            System.out.println("attackKnownPlayerTest - PASS");
        } else {
            System.out.println("attackKnownPlayerTest - FAIL");
        }
    }

    public void attackPlayerWithVestTest() {
        PlayersDBMock playersDBMock = new PlayersDBMock();

        Player p1 = playersDBMock.getPlayerByIndex(1);
        Player p2 = playersDBMock.getPlayerByIndex(3);

        p1.setHitPoints(100);
        p2.setHitPoints(100);
        double expectedHitPointsOfP2 = (p2.getHitPoints() - ((Configurations.aimMap.get(3) / 100.0) * p1.getWeapon().getDamage())) + p2.getVest().getAbsorbDamage();
        double actualHitPointsOfP2 = p1.attack(playersDBMock, p2, 3);

        if (actualHitPointsOfP2 == expectedHitPointsOfP2) {
            System.out.println("attackPlayerWithVestTest - PASS");
        } else {
            System.out.println("attackPlayerWithVestTest - FAIL");
        }
    }

    public void attackPlayerWithHelmetTest() {
        PlayersDBMock playersDBMock = new PlayersDBMock();

        Player p1 = playersDBMock.getPlayerByIndex(0);
        Player p2 = playersDBMock.getPlayerByIndex(1);

        p1.setHitPoints(100);
        p2.setHitPoints(100);
        double expectedHitPointsOfP2 = (p2.getHitPoints() - ((Configurations.aimMap.get(2) / 100.0) * p1.getWeapon().getDamage())) + p2.getHelmet().getAbsorbDamage();
        double actualHitPointsOfP2 = p1.attack(playersDBMock, p2, 2);

        if (expectedHitPointsOfP2 == actualHitPointsOfP2) {
            System.out.println("attackPlayerWithHelmetTest - PASS");
        } else {
            System.out.println("attackPlayerWithHelmetTest - FAIL");
        }
    }

    public void attackUnknownPlayerTest() {
        PlayersDBMock playersDBMock = new PlayersDBMock();

        Player p1 = playersDBMock.getAllPlayers().get(0);
        Player p2 = new Player("Unknown Player");

        p1.setWeapon(new Crossbow());
        p1.setHitPoints(100);
        p2.setHitPoints(100);
        double opponentHitPoint = p1.attack(playersDBMock, p2, 2);

        if (opponentHitPoint == -1) {
            System.out.println("attackUnknownPlayerTest - PASS");
        } else {
            System.out.println("attackUnknownPlayerTest - FAIL");
        }
    }

    public void setNameTest() {
        Player player = new Player("Shivam");
        player.setName("Random Name");

        if (player.getName().equals("Random Name")) {
            System.out.println("setNameTest - PASS");
        } else {
            System.out.println("setNameTest - FAIL");
        }
    }

    public void setIncorrectNameTest() {
        Player player = new Player("Shivam");
        player.setName("123_Shivam");

        if(player.getName().equals("Shivam")) {
            System.out.println("setIncorrectNameTest - PASS");
        } else {
            System.out.println("setIncorrectNameTest - FAIL");
        }
    }

    public void setHitPointsTest() {
        Player player = new Player("Shivam");
        player.setHitPoints(87.5);

        if (player.getHitPoints() == 87.5) {
            System.out.println("setHitPointsTest - PASS");
        } else {
            System.out.println("setHitPointsTest - FAIL");
        }
    }

    public void setExcessiveHitPointsTest() {
        Player player = new Player("Shivam");
        player.setHitPoints(800);

        if (player.getHitPoints() == 100) {
            System.out.println("setExcessiveHitPointsTest - PASS");
        } else {
            System.out.println("setExcessiveHitPointsTest - FAIL");
        }
    }

    public void setNegativeHitPointsTest() {
        Player player = new Player("Shivam");
        player.setHitPoints(-90.9);

        if (player.getHitPoints() == 0) {
            System.out.println("setNegativeHitPointsTest - PASS");
        } else {
            System.out.println("setNegativeHitPointsTest - FAIL");
        }
    }

    public void setWeaponTest() {
        Player player = new Player("Shivam", new Crossbow());

        if(player.getWeapon().getWeaponName().equals("Crossbow")) {
            System.out.println("setWeapon - PASS");
        } else {
            System.out.println("setWeapon - FAIL");
        }
    }

    public void setWeaponIncorrectTest() {
        Player player = new Player("Shivam", new Sniper());

        if(player.getWeapon().getWeaponName().equals("Sniper")) {
            System.out.println("setWeaponIncorrectTest - PASS");
        } else {
            System.out.println("setWeaponIncorrectTest - FAIL");
        }
    }

    public void setMainBalanceTest() {
        Player player = new Player("Shivam");
        player.setMainBalance(98.98);

        if (98.98 == player.getMainBalance()) {
            System.out.println("setMainBalanceTest - PASS");
        } else {
            System.out.println("setMainBalanceTest - FAIL");
        }
    }

    public void setMainBalanceNegativeTest() {
        Player player = new Player("Shivam");
        player.setMainBalance(-98.98);

        if(player.getMainBalance() == 0) {
            System.out.println("setMainBalanceNegativeTest - PASS");
        } else {
            System.out.println("setMainBalanceNegativeTest - FAIL");
        }
    }

    public void setPurchaseCoinsTest() {
        Player player = new Player("Shivam");
        player.setPurchaseCoins(200);

        if (player.getPurchaseCoins() == 200) {
            System.out.println("setPurchaseCoinsTest - PASS");
        } else {
            System.out.println("setPurchaseCoinsTest - FAIL");
        }
    }

    public void setPurchaseCoinsNegativeTest() {
        Player player = new Player("Shivam");
        player.setPurchaseCoins(-200);

        if (player.getPurchaseCoins() == 1000) {
            System.out.println("setPurchaseCoinsNegativeTest - PASS");
        } else {
            System.out.println("setPurchaseCoinsNegativeTest - FAIL");
        }
    }

    public void setVestTest() {
        Player player = new Player("Shivam");
        player.setVest(new Vest());

        if (player.getVest() != null && player.getVest() instanceof Vest) {
            System.out.println("setVestTest - PASS");
        } else {
            System.out.println("setVestTest - FAIL");
        }
    }

    public void setNullVestTest() {
        Player player = new Player("Shivam");
        player.setVest(null);

        if (player.getVest() == null) {
            System.out.println("setNullVestTest - PASS");
        } else {
            System.out.println("setNullVestTest - FAIL");
        }
    }

    public void setVestOfHighLevelTest() {
        Player player = new Player("Shivam");
        Vest highLevelVest = new Vest();
        highLevelVest.upgradeArmour();
        player.setVest(highLevelVest);

        if (player.getVest() != null && player.getVest() instanceof Vest && player.getVest().getLevel() == 2) {
            System.out.println("setVestOfHighLevelTest - PASS");
        } else {
            System.out.println("setVestOfHighLevelTest - FAIL");
        }
    }

    public void setHelmetTest() {
        Player player = new Player("Shivam");
        player.setHelmet(new Helmet());

        if (player.getHelmet() != null && player.getHelmet() instanceof Helmet) {
            System.out.println("setHelmetTest - PASS");
        } else {
            System.out.println("setHelmetTest - FAIL");
        }
    }

    public void setNullHelmetTest() {
        Player player = new Player("Shivam");
        player.setHelmet(null);

        if (player.getHelmet() == null) {
            System.out.println("setNullHelmetTest - PASS");
        } else {
            System.out.println("setNullHelmetTest - FAIL");
        }
    }

    public void setHelmetOfHighLevelTest() {
        Player player = new Player("Shivam");
        Helmet highLevelHelmet = new Helmet();
        highLevelHelmet.upgradeArmour();
        player.setHelmet(highLevelHelmet);

        if (player.getHelmet() != null && player.getHelmet() instanceof Helmet && player.getHelmet().getLevel() == 2) {
            System.out.println("setHelmetOfHighLevelTest - PASS");
        } else {
            System.out.println("setHelmetOfHighLevelTest - FAIL");
        }
    }

    public void incrementLevelTest() {
        Player player = new Player("Shivam");
        player.incrementLevel();

        if (player.getLevel() == 2) {
            System.out.println("incrementLevelTest - PASS");
        } else {
            System.out.println("incrementLevelTest - FAIL");
        }
    }

    public void upgradeWeaponLevelTest() {
        Player player = new Player("Shivam", new Crossbow());
        player.upgradeWeaponLevel(new PlayersDBMock());

        if(player.getWeapon().getWeaponLevel() == 2 && player.getPurchaseCoins() == Configurations.INITIAL_PURCHASE_COINS - Configurations.CROSSBOW_UPGRADE_COINS) {
            System.out.println("upgradeWeaponLevelTest - PASS");
        } else {
            System.out.println("upgradeWeaponLevelTest - FAIL");
        }
    }

    public void upgradeWeaponWithLowCoinsTest() {
        Player player = new Player("Shivam", new Crossbow());
        player.setPurchaseCoins(100);

        if(player.getWeapon().getWeaponLevel() == 1) {
            System.out.println("upgradeWeaponWithLowCoinsTest - PASS");
        } else {
            System.out.println("upgradeWeaponWithLowCoinsTest - FAIL");
        }
    }

    public void upgradeArmourWithInsufficientCoinsTest() {
        Player player = new Player("Shivam", null, new Vest());
        player.setPurchaseCoins(50);
        player.upgradeArmour(Configurations.VEST);

        if (player.getVest().getLevel() == 1) {
            System.out.println("upgradeArmourWithInsufficientCoinsTest - PASS");
        } else {
            System.out.println("upgradeArmourWithInsufficientCoinsTest - FAIL");
        }
    }

    public void upgradeArmourVestTest() {
        Player player = new Player("Shivam", null, new Vest());
        player.upgradeArmour(Configurations.VEST);

        if(player.getVest().getLevel() == 2) {
            System.out.println("upgradeArmourVestTest - PASS");
        } else {
            System.out.println("upgradeArmourVestTest - FAIL");
        }
    }

    public void upgradeArmourHelmetTest() {
        Player player = new Player("Shivam", null, null, new Helmet());
        player.upgradeArmour(Configurations.HELMET);

        if (player.getHelmet().getLevel() == 2) {
            System.out.println("upgradeArmourHelmetTest - PASS");
        } else {
            System.out.println("upgradeArmourHelmetTest - FAIL");
        }
    }

    public void upgradeArmourIncorrectTest() {
        Player player = new Player("Shivam");
        player.upgradeArmour(Configurations.VEST);

        if (player.getVest() == null) {
            System.out.println("upgradeArmourIncorrectTest - PASS");
        } else {
            System.out.println("upgradeArmourIncorrectTest - FAIL");
        }
    }

    public void upgradeVestTest() {
        Player player = new Player("Shivam", null, new Vest());
        player.upgradeVest();

        if(player.getVest().getLevel() == 2) {
            System.out.println("upgradeVestTest - PASS");
        } else {
            System.out.println("upgradeVestTest - FAIL");
        }
    }

    public void upgradeVestWithInsufficientCoinsTest() {
        Player player = new Player("Shivam", null, new Vest());
        player.setPurchaseCoins(50);
        player.upgradeVest();

        if (player.getVest().getLevel() == 1) {
            System.out.println("upgradeVestWithInsufficientCoinsTest - PASS");
        } else {
            System.out.println("upgradeVestWithInsufficientCoinsTest - FAIL");
        }
    }

    public void changeWeaponTest() {
        Player player = new Player("Shivam", new Crossbow());
        player.incrementLevel();
        player.changeWeapon(new PlayersDBMock(), new Sword());

        if(player.getWeapon().getWeaponName().equals(Configurations.SWORD)) {
            System.out.println("changeWeaponTest - PASS");
        } else {
            System.out.println("changeWeaponTest - FAIL");
        }
    }

    public void changeWeaponAtLowLevelTest() {
        Player player = new Player("Shivam", new Crossbow());
        player.changeWeapon(new PlayersDBMock(), new Sniper());

        if (player.getWeapon().getWeaponName().equals(Configurations.CROSSBOW)) {
            System.out.println("changeWeaponAtLowLevelTest - PASS");
        } else {
            System.out.println("changeWeaponAtLowLevelTest - FAIL");
        }
    }

    public void purchaseVestTest() {
        Player player = new Player("Shivam");
        player.purchaseArmour(new PlayersDBMock(), new Vest());

        if (player.getVest() instanceof Vest) {
            System.out.println("purchaseVestTest - PASS");
        } else {
            System.out.println("purchaseVestTest - FAIL");
        }
    }

    public void purchaseHelmetTest() {
        Player player = new Player("Shivam");
        player.purchaseArmour(new PlayersDBMock(), new Helmet());

        if (player.getHelmet() instanceof Helmet) {
            System.out.println("purchaseHelmetTest - PASS");
        } else {
            System.out.println("purchaseHelmetTest - FAIL");
        }
    }

    public void purchaseArmourIncorrectTest() {
        Player player = new Player("Shivam");
        player.purchaseArmour(new PlayersDBMock(), null);

        if (player.getVest() == null && player.getHelmet() == null) {
            System.out.println("purchaseArmourIncorrectTest - PASS");
        } else {
            System.out.println("purchaseArmourIncorrectTest - FAIL");
        }
    }

    public void addPurchaseCoinsTest() {
        Player player = new Player("Shivam");
        player.addPurchaseCoins(200);

        if (player.getPurchaseCoins() == 1200) {
            System.out.println("addPurchaseCoinsTest - PASS");
        } else {
            System.out.println("addPurchaseCoinsTest - FAIL");
        }
    }

    public void addNegativePurchaseCoinsTest() {
        Player player = new Player("Shivam");
        player.addPurchaseCoins(-200);

        if (player.getPurchaseCoins() == Configurations.INITIAL_PURCHASE_COINS) {
            System.out.println("addNegativePurchaseCoinsTest - PASS");
        } else {
            System.out.println("addNegativePurchaseCoinsTest - FAIL");
        }
    }

    public void getNameTest() {
        Player player = new Player("Shivam");

        if(player.getName().equals("Shivam")) {
            System.out.println("getNameTest - PASS");
        } else {
            System.out.println("getNameTest - FAIL");
        }
    }

    public void getUniqueIdTest() {
        Player player = new Player("Shivam");

        if (!player.getUniqueId().equals("")) {
            System.out.println("getUniqueIdTest - PASS");
        } else {
            System.out.println("getUniqueIdTest - FAIL");
        }
    }

    public void getMainBalanceTest() {
        Player player = new Player("Shivam");
        player.setMainBalance(980);

        if (player.getMainBalance() == 980) {
            System.out.println("getMainBalanceTest - PASS");
        } else {
            System.out.println("getMainBalanceTest - FAIL");
        }
    }

    public void getPurchaseCoinsTest() {
        Player player = new Player("Shivam");
        player.setPurchaseCoins(1000);

        if (player.getPurchaseCoins() == 1000) {
            System.out.println("getPurchaseCoinsTest - PASS");
        } else {
            System.out.println("getPurchaseCoinsTest - FAIL");
        }
    }

    public void getLevelTest() {
        Player player = new Player("Shivam");

        if (player.getLevel() == 1) {
            System.out.println("getLevelTest - PASS");
        } else {
            System.out.println("getLevelTest - FAIL");
        }
    }

    public void getHitPointsTest() {
        Player player = new Player("Shivam");

        if (player.getHitPoints() == Configurations.INITIAL_HIT_POINTS) {
            System.out.println("getHitPointsTest - PASS");
        } else {
            System.out.println("getHitPointsTest - FAIL");
        }
    }

    public void getWeaponTest() {
        Player player = new Player("Shivam", new Crossbow());

        if (player.getWeapon() != null && player.getWeapon() instanceof Crossbow) {
            System.out.println("getWeaponTest - PASS");
        } else {
            System.out.println("getWeaponTest - FAIL");
        }
    }

    public void getVestTest() {
        Player player = new Player("Shivam", null, new Vest());

        if (player.getVest() != null && player.getVest() instanceof Vest) {
            System.out.println("getVestTest - PASS");
        } else {
            System.out.println("getVestTest - FAIL");
        }
    }

    public void getHelmetTest() {
        Player player = new Player("Shivam", null, null, new Helmet());

        if (player.getHelmet() != null && player.getHelmet() instanceof Helmet) {
            System.out.println("getHelmetTest - PASS");
        } else {
            System.out.println("getHelmetTest - FAIL");
        }
    }
}
