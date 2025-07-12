package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

public class ConfigFormulas {

    // Defining variables that hod the values that were input in the configs
    public static int a;
    public static double m;

    static {
        // List of possible valid configs
        List<Integer> safetyCheck = new ArrayList<>();
        safetyCheck.add(1);
        safetyCheck.add(2);
        safetyCheck.add(3);
        safetyCheck.add(4);

        a = ServerConfigs.FORMULA_REBALANCE.get();
        // Making sure we aren't trying to math out nonsense for a
        if(!safetyCheck.contains(ServerConfigs.FORMULA_REBALANCE.get())) a = 4;
        if(a == 2) m = 1.326;
        else if(a == 3) m = 2.209;
        else m = 1;

    }

    // Attributes for mobs
    public static double SpellPower = 0;
    public static double SchoolPower = 0;
    public static double Resist = 0;
    public static double FireRes = 0;
    public static double IceRes = 0;
    public static double HolyRes = 0;
    public static double NatRes = 0;
    public static double BldRes = 0;
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
}