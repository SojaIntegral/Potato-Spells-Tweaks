package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

public class ConfigFormulas {

    public static double modifier;

    // Rebalance Formula
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

    // Modifiers
    public static double mobType = 0;
    public static double ArmorMod = 0;
    public static double ToughMod = 0;
    public static double AttackMod = 0;
    public static double boss_mod = 1.25 * modifier * ServerConfigs.BOSS_RESIST.get() / 100;
    public static double mini_mod = 1.1 * modifier * ServerConfigs.MINIBOSS_RESIST.get() / 100;
    public static double mob_mod = 0.9 * modifier * ServerConfigs.MOB_RESIST.get() / 100;
    public static double summon_mod = 0.8 * modifier * ServerConfigs.MOB_RESIST.get() / 100;
    public static double spec_mod = 1 + (modifier / 3.25);

    // Familiars random attr value
    public static double randMax = (double) Math.clamp(ServerConfigs.FAMILIAR_RAND.get(), 0, 100) / 100;


    // REMOVE
    public static double SchoolPower = 0;
}