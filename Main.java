import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Player realPlayer;
    public static Player computerPlayer;
    public static PlayersDB playersDB = new PlayersDB();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("test")) {
            System.out.println("----- PLAYERTEST CLASS TESTS :");
            new PlayerTest();
            System.out.println("\n----- WEAPONTEST CLASS TESTS :");
            new WeaponTest();
            System.out.println("\n----- ARMOURTEST CLASS TESTS :");
            new ArmourTest();

        } else {
            boolean isRunning = true;
            boolean isPlayerCreated = false;
            int choice;

            while(isRunning) {
                printWelcomeMessage();
                printGameRules();
                printMainMenu();

                System.out.print("Enter Your Choice : ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 1 :
                        isPlayerCreated = createNewPlayer();
                        computerPlayer = new Player("Computer Player", new Crossbow());
                        computerPlayer.createNewPlayer(playersDB);
                        if (!isPlayerCreated) {
                            System.out.println("Invalid Name Entered ! Name can't be empty or can't start with number");
                            continue;
                        }
                    break;

                    case 2 :
                        printExitMessage();
                        isRunning = false;
                    break;

                    default: break;
                }

                if (isPlayerCreated) {
                    boolean isMainScreen = true;
                    while(isMainScreen) {
                        printInstruction();
                        initializePlayers();
                        System.out.print("Enter Your Choice : ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice) {
                            case 1 :
                                System.out.println("========================================================");
                                System.out.println(realPlayer.getName().toUpperCase() + "'s STATS :\n" +
                                        "----- Weapon : " + realPlayer.getWeapon().getWeaponName() + " (Level : " + realPlayer.getWeapon().getWeaponLevel() + ")" + "\n" +
                                        "----- Level : " + realPlayer.getLevel() + "\n" +
                                        "----- Vest : " + (realPlayer.getVest() != null ? "(Level - " + realPlayer.getVest().getLevel() + ")" : "No Vest Available") + "\n" +
                                        "----- Helmet : " + (realPlayer.getHelmet() != null ? "(Level - " + realPlayer.getHelmet().getLevel() + ")" : "No Helmet Available"));
                                System.out.println();
                                System.out.println(computerPlayer.getName().toUpperCase() + "'s Stats :\n" +
                                        "----- Weapon : " + computerPlayer.getWeapon().getWeaponName() + " (Level : " + computerPlayer.getWeapon().getWeaponLevel() + ")" + "\n" +
                                        "----- Level : " + computerPlayer.getLevel() + "\n" +
                                        "----- Vest : " + (computerPlayer.getVest() != null ? "(Level - " + computerPlayer.getVest().getLevel() + ")" : "No Vest Available") + "\n" +
                                        "----- Helmet : " + (computerPlayer.getHelmet() != null ? "(Level - " + computerPlayer.getHelmet().getLevel() + ")" : "No Helmet Available"));
                                System.out.println("========================================================");

                                System.out.println("\n***** To mimic aim, you will select a number from 1, 2 or 3. *****\n");

                                Player winner = playGame();
                                winner.incrementLevel();
                                winner.addPurchaseCoins(Configurations.COINS_AFTER_LEVEL_COMPLETION);

                                if (winner != computerPlayer) {
                                    System.out.println("========================================================");
                                    System.out.println("You won!!");
                                    System.out.println(winner.getName() + ", You got " + Configurations.COINS_AFTER_LEVEL_COMPLETION + " PC. Now your Purchase Coins are " + winner.getPurchaseCoins() + " PC");
                                }
                                else {
                                    System.out.println("========================================================");
                                    System.out.println(computerPlayer.getName() + " Won!!");
                                }

                                break;

                            case 2 :
                                System.out.println("----- 1- Crossbow" + " (Unlocks at level - " + Configurations.CROSSBOW_UNLOCK_LEVEL + ")\n" +
                                        "----- 2- Sword" + " (Unlocks at level - " + Configurations.SWORD_UNLOCK_LEVEL + ")\n" +
                                        "----- 3- Pistol" + " (Unlocks at level - " + Configurations.PISTOL_UNLOCK_LEVEL + ")\n" +
                                        "----- 4- Rifle" + " (Unlocks at level - " + Configurations.RIFLE_UNLOCK_LEVEL + ")\n" +
                                        "----- 5- Grenade Launcher" + " (Unlocks at level - " + Configurations.GRENADE_LAUNCHER_UNLOCK_LEVEL + ")\n" +
                                        "----- 6- Sniper" + " (Unlocks at level - " + Configurations.SNIPER_UNLOCK_LEVEL + ")");
                                System.out.println("----- YOUR LEVEL : " + realPlayer.getLevel());
                            break;

                            case 3:
                                System.out.println("----- 1- Vest\n" +
                                        "----- 2- Helmet\n" +
                                        "----- 3- Back To Menu");
                                System.out.print("----- Enter your choice : ");
                                int armourChoice = scanner.nextInt();
                                scanner.nextLine();

                                boolean isInsideArmour = true;

                                while(isInsideArmour) {
                                    switch(armourChoice) {
                                        case 1 :
                                            purchaseArmour(new Vest(), realPlayer);
                                            isInsideArmour = false;
                                        break;

                                        case 2 :
                                            purchaseArmour(new Helmet(), realPlayer);
                                            isInsideArmour = false;
                                        break;

                                        case 3:
                                            isInsideArmour = false;
                                        break;

                                        default :
                                        break;
                                    }
                                }
                            break;

                            case 4 :
                                System.out.println("----- 1- Vest\n" +
                                        "----- 2- Helmet\n" +
                                        "----- 3- Back To Menu");
                                System.out.print("----- Enter your choice : ");
                                int armourUpgradeChoice = scanner.nextInt();
                                scanner.nextLine();

                                boolean isInsideArmourUpgrade = true;

                                while (isInsideArmourUpgrade) {
                                    switch(armourUpgradeChoice) {
                                        case 1 :
                                            if (realPlayer.getVest() != null) {
                                                upgradeArmour(Configurations.VEST, realPlayer);
                                            }
                                            isInsideArmourUpgrade = false;
                                        break;

                                        case 2 :
                                            if (realPlayer.getHelmet() != null) {
                                                upgradeArmour(Configurations.HELMET, realPlayer);
                                            }
                                            isInsideArmourUpgrade = false;
                                        break;

                                        case 3 :
                                            isInsideArmourUpgrade = false;
                                        break;

                                        default :
                                        break;
                                    }
                                }

                            break;

                            case 5 :
                                Weapon realPlayerWeapon = realPlayer.getWeapon();
                                boolean isWeaponUpgraded = realPlayer.upgradeWeaponLevel(playersDB);
                                if (isWeaponUpgraded) {
                                    System.out.println(realPlayerWeapon.getWeaponName() + " has been updated to Level " + realPlayerWeapon.getWeaponLevel());
                                    System.out.println(realPlayerWeapon.getWeaponName() + "'s damage has been increased by " + Configurations.INCREASE_DAMAGE + ". It's now " + realPlayerWeapon.getDamage());
                                    System.out.println("Purchase Coins : " + realPlayer.getPurchaseCoins() + " PC.");
                                }
                                else {
                                    System.out.println("Not Enough Purchase Coins available. You only have " + realPlayer.getPurchaseCoins() + " PC.");
                                }
                            break;

                            case 6 :
                                System.out.println("----- 1- Crossbow\n" +
                                        "----- 2- Sword\n" +
                                        "----- 3- Pistol\n" +
                                        "----- 4- Rifle\n" +
                                        "----- 5- Grenade Launcher\n" +
                                        "----- 6- Sniper");

                                System.out.print("Enter your choice : ");
                                int chooseWeapon = scanner.nextInt();
                                scanner.nextLine();

                                switch(chooseWeapon) {
                                    case 1 :
                                        changeWeapon(new Crossbow());
                                    break;

                                    case 2 :
                                        changeWeapon(new Sword());
                                    break;

                                    case 3 :
                                        changeWeapon(new Pistol());
                                    break;

                                    case 4 :
                                        changeWeapon(new Rifle());
                                    break;

                                    case 5 :
                                        changeWeapon(new GrenadeLauncher());
                                        break;

                                    case 6 :
                                        changeWeapon(new Sniper());

                                    default :
                                        break;
                                }
                            break;

                            case 7 :
                                System.out.println("For the sake of this Assignment we can just have a dummy card.\nIt will just add the amount entered by the user to our Game.\nBelow is the details of Dummy Card. Please use that one:\n" +
                                        "============================================================\n" +
                                        "CARD NUMBER : " + Configurations.CARD_NUMBER + "\n" +
                                        "CARD CVV : " + Configurations.CARD_CVV + "\n" +
                                        "============================================================");

                                System.out.print("Enter Card Number : ");
                                String cardNumber = scanner.nextLine();

                                System.out.print("Enter CVV : ");
                                int cardCVV = scanner.nextInt();

                                System.out.print("Enter Amount : ");
                                int amount = scanner.nextInt();

                                if (cardNumber.equals(Configurations.CARD_NUMBER) && cardCVV == Configurations.CARD_CVV) {
                                    realPlayer.setMainBalance(amount);
                                    System.out.println("Payment Successful!! Your Main Balance is $" + realPlayer.getMainBalance());
                                } else {
                                    System.out.println("Invalid Card Details entered !");
                                }
                            break;

                            case 8 :
                                showProfile();
                            break;

                            case 9 :
                                boolean isInsideBuiCoins = true;
                                while(isInsideBuiCoins) {
                                    System.out.println("1- Basic Package - $25 (You will get " + Configurations.BASIC_PACK_COINS + "PC)\n" +
                                            "2- Standard Package - $55 (You will get " + Configurations.STANDARD_PACK_COINS + "PC)\n" +
                                            "3- Premium Package - $ 110 (You will get " + Configurations.PREMIUM_PACK_COINS + "PC)\n" +
                                            "4- Back To Menu");

                                    System.out.print("Enter Your Choice : ");
                                    int packageNumber = scanner.nextInt();
                                    scanner.nextLine();

                                    switch(packageNumber) {
                                        case 1 :
                                            purchaseCoins(25, Configurations.BASIC_PACK_COINS);
                                            isInsideBuiCoins = false;
                                            break;

                                        case 2 :
                                            purchaseCoins(55, Configurations.STANDARD_PACK_COINS);
                                            isInsideBuiCoins = false;
                                            break;

                                        case 3 :
                                            purchaseCoins(110, Configurations.PREMIUM_PACK_COINS);
                                            isInsideBuiCoins = false;
                                            break;

                                        case 4 :
                                            isInsideBuiCoins = false;
                                            break;

                                        default :
                                            break;
                                    }
                                }

                            break;

                            case 10 :
                                isMainScreen = false;
                                isRunning = false;
                                printExitMessage();
                                break;

                            default : break;
                        }
                    }

                }
            }
        }
    }

    public static void upgradeArmour(String armourType, Player player) {
        IArmour.UpgradeArmourState upgradeArmourState = player.upgradeArmour(armourType);

        if (upgradeArmourState == IArmour.UpgradeArmourState.SUCCESS) {
            if (armourType.equals(Configurations.VEST))
                System.out.println(player.getVest().getArmourType().substring(0, 1).toUpperCase() + player.getVest().getArmourType().substring(1)  + " is upgraded to level " + player.getVest().getLevel() + ". Your Purchase Coins is " + player.getPurchaseCoins() + "PC.");
            if (armourType.equals(Configurations.HELMET))
                System.out.println(player.getHelmet().getArmourType().substring(0, 1).toUpperCase() + player.getHelmet().getArmourType().substring(1)  + " is upgraded to level " + player.getHelmet().getLevel() + ". Your Purchase Coins is " + player.getPurchaseCoins() + "PC.");
        }
        else if (upgradeArmourState == IArmour.UpgradeArmourState.NOT_ENOUGH_COINS) {
            System.out.println("You don't have enough purchase coins to upgrade. You only have " + player.getPurchaseCoins() + "PC.");
        }
        else if (upgradeArmourState == IArmour.UpgradeArmourState.ALREADY_MAXED) {
            if (armourType.equals(Configurations.VEST))
                System.out.println(player.getVest().getArmourType().substring(0, 1).toUpperCase() + player.getVest().getArmourType().substring(1) + " is already maxed.");
            if (armourType.equals(Configurations.HELMET))
                System.out.println(player.getHelmet().getArmourType().substring(0, 1).toUpperCase() + player.getHelmet().getArmourType().substring(1) + " is already maxed.");

        }
    }

    public static void purchaseArmour(Armour armour, Player player) {
        IArmour.PurchaseArmourState res =  player.purchaseArmour(playersDB, armour);
        if (res == IArmour.PurchaseArmourState.SUCCESS) {
            System.out.println(armour.getArmourType().substring(0, 1).toUpperCase() + armour.getArmourType().substring(1)  + " purchased successful!! Your Purchase Coins is now " + player.getPurchaseCoins() + "PC.");
        }
        if (res == IArmour.PurchaseArmourState.NOT_ENOUGH_COINS) {
            System.out.println("You don't have enough coins to purchase armour. You only have " + player.getPurchaseCoins() + "PC.");
        }
        if (res == IArmour.PurchaseArmourState.ALREADY_PURCHASED) {
            System.out.println("You already have the Vest. You are not allowed to buy multiple of them.");
        }
    }

    public static void purchaseCoins(int pack, int coinsToAdd) {
        double currentBalance = realPlayer.getMainBalance();
        if (currentBalance >= pack) {
            realPlayer.setMainBalance(currentBalance - pack);
            realPlayer.addPurchaseCoins(coinsToAdd);
            System.out.println(Configurations.PREMIUM_PACK_COINS + "PC has been added. Your coins is now " + realPlayer.getPurchaseCoins() + "PC.");
        } else {
            System.out.println("You don't have enough Main Balance to Purchase Coins.");
        }
    }

    public static void changeWeapon(Weapon weapon) {
        boolean isWeaponChanged = realPlayer.changeWeapon(playersDB, weapon);
        if (isWeaponChanged) {
            System.out.println("Weapon changed to " + realPlayer.getWeapon().getWeaponName());
        } else {
            System.out.println("Your level is too low to upgrade");
        }
    }

    public static void printWelcomeMessage() {
        System.out.println("========================================================\n" +
                "WELCOME TO NEW ADVENTURE GAME\n" +
                "========================================================");
    }

    public static void printGameRules() {
        System.out.println("\n***** Game Rules *****\n" +
                "1- The game is a single player and not a multi-player game. You will play against a Computer Player.\n" +
                "2. You will get first chance over a Computer player\n" +
                "3. As you progress to a new level, you will get " + Configurations.COINS_AFTER_LEVEL_COMPLETION + " Purchase Coins as a reward.\n" +
                "4. You will get " + Configurations.INITIAL_PURCHASE_COINS + " Purchase Coins right off the bat, you can use this PC's to buy / upgrade armours (i.e. Helmet & Vest) or upgrade weapon.\n" +
                "5. You can also add Coins by adding a real money through your Credit/Debit Card.\n" +
                "6. As you move to a higher level, you will unlock a new Weapon.\n");
    }

    public static void printMainMenu() {
        System.out.println("1- Create New Player\n" +
                "2- Quit Game\n" +
                "========================================================");
    }

    public static void printExitMessage() {
        System.out.println("==================== THANK YOU ====================");
    }

    public static void printInstruction() {
        System.out.println("========================================================");
        if (realPlayer.getLevel() == 1) {
            System.out.println("1- Start Game");
        } else {
            System.out.println("1- Play Next Level");
        }
        System.out.println("2- See Weapons List\n" +
                "3- Buy Armours\n" +
                "4- Upgrade Armours\n" +
                "5- Upgrade Current Weapon Level\n" +
                "6- Change Weapon\n" +
                "7- Add Main Balance\n" +
                "8- See Your Profile\n" +
                "9- Buy Coins\n" +
                "10- Quit Game");
    }

    public static void initializePlayers() {
        realPlayer.setHitPoints(100);
        computerPlayer.setHitPoints(100);
    }

    public static boolean createNewPlayer() {
        System.out.print("Enter Your Player Name : ");
        String name = scanner.nextLine();

        if(!Utils.validateName(name) || name.equals("")) return false;

        realPlayer = new Player(name, new Crossbow());
        realPlayer.createNewPlayer(playersDB);
        System.out.println("\n********** PLAYER SUCCESSFULLY CREATED **********\n");
        showProfile();
        return true;
    }

    public static void showProfile() {
        System.out.println("==================== YOUR PROFILE ====================");
        System.out.println("----- Player Name : " + realPlayer.getName() + "\n" +
                "----- Unique Id : " + realPlayer.getUniqueId() + "\n" +
                "----- Purchase Coins : " + realPlayer.getPurchaseCoins() + " PC" + "\n" +
                "----- Main Balance : " + "$" + realPlayer.getMainBalance() + "\n" +
                "----- Current Level : " + realPlayer.getLevel() + "\n" +
                "----- Weapon : " + realPlayer.getWeapon().getWeaponName() + " (Level : " + realPlayer.getWeapon().getWeaponLevel() + ")\n" +
                "----- Vest : " + (realPlayer.getVest() != null ? "(Level : " + realPlayer.getVest().getLevel() + ")" : "No Vest Available") + "\n" +
                "----- Helmet : " + (realPlayer.getHelmet() != null ?  "(Level : " + realPlayer.getHelmet().getLevel() + ")" : "No Helmet Available"));
    }

    public static int getAimNumber() {
        return new Random().nextInt(3) + 1;
    }

    public static void printPlayersHealth() {
        System.out.println(realPlayer.getName().toUpperCase() + "'s HEALTH= " + realPlayer.getHitPoints() + "%\t\t" + computerPlayer.getName().toUpperCase() + "'s HEALTH = " + computerPlayer.getHitPoints() + "%");
    }

    public static Player playGame() {
        double computerHitPoint = 100;
        double realPlayerHitPoint = 100;
        int aimNumber = 0;
        Player winner = null;

        printPlayersHealth();

        while (computerHitPoint > 0 || realPlayerHitPoint > 0) {
            System.out.print("Enter any number from 1, 2 & 3 : ");
            aimNumber = scanner.nextInt();
            scanner.nextLine();

            if (aimNumber > 0 && aimNumber <= 3){
                computerHitPoint = realPlayer.attack(playersDB, computerPlayer, aimNumber);
                System.out.println(realPlayer.getName().toUpperCase() + " got " + Configurations.shotTypeMap.get(aimNumber) + " on " + computerPlayer.getName().toUpperCase() + ".");
                printPlayersHealth();

                if (computerHitPoint <= 0) {
                    winner = realPlayer;
                    break;
                }

                System.out.println("--------------------------------------------------");

                aimNumber = getAimNumber();
                realPlayerHitPoint = computerPlayer.attack(playersDB, realPlayer, aimNumber);
                System.out.println(computerPlayer.getName().toUpperCase() + " got " + Configurations.shotTypeMap.get(aimNumber) + " on you.");
                printPlayersHealth();

                if (realPlayerHitPoint <= 0) {
                    winner = computerPlayer;
                    break;
                }
                System.out.println("--------------------------------------------------");
            }

        }
        return winner;
    }
}
