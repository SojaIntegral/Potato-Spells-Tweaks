package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

public class ConfigFormulas {

    // Defining variables that hod the values that were input in the configs
    public static int a;
    public static int b;
    public static int c;

    static {
        // List of possible valid configs
        List<Integer> safetyCheck = new ArrayList<>();
        safetyCheck.add(1);
        safetyCheck.add(2);
        safetyCheck.add(3);
        safetyCheck.add(4);

        a = ServerConfigs.COOLDOWN_UNCAP.get();
        // Making sure we aren't trying to math out nonsense for a
        if(!safetyCheck.contains(ServerConfigs.COOLDOWN_UNCAP.get())) a = 4;

        b = ServerConfigs.CAST_UNCAP.get();
        // Making sure we aren't trying to math out nonsense for b
        if(!safetyCheck.contains(ServerConfigs.CAST_UNCAP.get())) b = 4;

        c = ServerConfigs.RESIST_UNCAP.get();
        // Making sure we aren't trying to math out nonsense for c
        if(!safetyCheck.contains(ServerConfigs.RESIST_UNCAP.get())) c = 4;
    }

    // Now defining the formula for the sake of organization
    // m stands for Modifier
    public static double m;

    static {
        int a1 = a/12;
        int b1 = b/9;
        int c1 = c;
        int exp = c1 - (a1+b1);

        // Trust me, this works
        // This is peak math
        // Edit: This is not working properly (fix later)
        m = Math.pow(2, (Math.pow(exp, 2) / 4));
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