package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

public class ConfigFormulas {

    public static double modifier;

    static {
        // List of possible valid configs
        List<Integer> safetyCheck = new ArrayList<>();
        safetyCheck.add(1);
        safetyCheck.add(2);
        safetyCheck.add(3);
        safetyCheck.add(4);

        // Defining variables that hod the values that were input in the configs
        int config_check = ServerConfigs.FORMULA_REBALANCE.get();
        // Making sure we aren't trying to math out nonsense for spawn_armor.json
        if (!safetyCheck.contains(ServerConfigs.FORMULA_REBALANCE.get())) config_check = 4;
        if (config_check == 2) modifier = 1.326;
        else if (config_check == 3) modifier = 2.209;
        else modifier = 1;
    }

    // Variable attributes for mobs
    public static double SpellPower = 0;
    public static double SchoolPower = 0;
    public static double Resist = 0;
    public static double FireRes = 0;
    public static double IceRes = 0;
    public static double HolyRes = 0;
    public static double NatRes = 0;
    public static double EvokeRes = 0;
    public static double BloodRes = 0;
    public static double EndRes = 0;
    public static double LigRes = 0;
    public static double EldRes = 0;
    public static double AbyssRes = 0;
    public static double BladeRes = 0;
    public static double SoundRes = 0;
    public static double WindRes = 0;
    public static double Armor = 0;
    public static double Tough = 0;
    public static double Attack = 0;
    public static double boss_mod = modifier * ServerConfigs.BOSS_RESIST.get() / 100;
    public static double mini_mod = modifier * ServerConfigs.MINIBOSS_RESIST.get() / 100;
    public static double mob_mod = modifier * ServerConfigs.MOB_RESIST.get() / 100;
    public static double spec_mod = 1 + (modifier / 3.25);

    // Fixed stuff, won't change from mob to mob
    // Boss stuff
    public static double boss_armor_pen = 3;
    public static double boss_armor_shred = 0.2;
    public static double boss_prot_pen = 2;
    public static double boss_prot_shred = 0.2;
    // Miniboss stuff
    public static double mini_armor_pen = 2;
    public static double mini_armor_shred = 0.15;
    public static double mini_prot_pen = 1;
    public static double mini_prot_shred = 0.15;
    // Normal mob stuff
    public static double mob_armor_pen = 2;
    public static double mob_armor_shred = 0.1;
    public static double mob_prot_pen = 1.5;
    public static double mob_prot_shred = 0.1;

    // Familiars random attr value
    public static double randMax = (double) Math.clamp(ServerConfigs.FAMILIAR_RAND.get(), 0, 100) / 100;
}