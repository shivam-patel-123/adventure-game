import java.util.HashMap;
import java.util.Map;

public class Configurations {

    public static final int INITIAL_PURCHASE_COINS = 1000;
    public static final double INITIAL_HIT_POINTS = 100.0;
    public static final int COINS_AFTER_LEVEL_COMPLETION = 300;
    public static final int INCREASE_DAMAGE = 2; // indicates increase in damage after weapon is upgraded
    public static final Map<Integer, Integer> aimMap = new HashMap<>() {
        {
            put(1, 30); // 30 indicates legshot, so its damage will be the 30% of the base weapon damage
            put(2, 100); // Similarly 100 means headshot
            put(3, 60); // 60 means bodyshot
        }
    };
    public static final String HEAD_SHOT = "headshot";
    public static final String BODY_SHOT = "bodyshot";
    public static final String LEG_SHOT = "legshot";
    public static final Map<Integer, String> shotTypeMap = new HashMap<>() {
        {
            put(1, LEG_SHOT);
            put(2, HEAD_SHOT);
            put(3, BODY_SHOT);
        }
    };

    // COINS ADDED AFTER PURCHASING PLANS
    public static final int BASIC_PACK_COINS = 500;
    public static final int STANDARD_PACK_COINS = 1000;
    public static final int PREMIUM_PACK_COINS = 1500;

    // DUMMY CARD DETAILS
    public static final String CARD_NUMBER = "4242424242424242";
    public static final int CARD_CVV = 123;

    // WEAPON CONFIGURATIONS
    public static final String CROSSBOW = "Crossbow";
    public static final String SWORD = "Sword";
    public static final String PISTOL = "Pistol";
    public static final String RIFLE = "Rifle";
    public static final String GRENADE_LAUNCHER = "Grenade Launcher";
    public static final String SNIPER = "Sniper";

    public static final int CROSSBOW_DAMAGE = 19;
    public static final int SWORD_DAMAGE = 24;
    public static final int PISTOL_DAMAGE = 30;
    public static final int RIFLE_DAMAGE = 43;
    public static final int GRENADE_LAUNCHER_DAMAGE = 78;
    public static final int SNIPER_DAMAGE = 92;

    public static final int CROSSBOW_UNLOCK_LEVEL = 1;
    public static final int SWORD_UNLOCK_LEVEL = 2;
    public static final int PISTOL_UNLOCK_LEVEL = 3;
    public static final int RIFLE_UNLOCK_LEVEL = 4;
    public static final int GRENADE_LAUNCHER_UNLOCK_LEVEL = 5;
    public static final int SNIPER_UNLOCK_LEVEL = 6;

    public static final int CROSSBOW_UPGRADE_COINS = 600;
    public static final int SWORD_UPGRADE_COINS = 700;
    public static final int PISTOL_UPGRADE_COINS = 800;
    public static final int RIFLE_UPGRADE_COINS = 900;
    public static final int GRENADE_LAUNCHER_UPGRADE_COINS = 1000;
    public static final int SNIPER_UPGRADE_COINS = 1200;

    // ARMOUR CONFIGURATIONS
    public static final int MAX_ARMOUR_LEVEL = 3;
    public static final String VEST = "vest";
    public static final String HELMET = "helmet";

    public static final int VEST_ABSORB_DAMAGE = 3;
    public static final int HELMET_ABSORB_DAMAGE = 4;

    public static final int VEST_PURCHASE_COST = 300;
    public static final int HELMET_PURCHASE_COST = 500;

    public static final int VEST_UPGRADE_COST = 600;
    public static final int HELMET_UPGRADE_COST = 800;
}
