package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.Objects;

public class PotatoUtils {

    /*
    Softcap Formulas
    */

    static String cduncap = ServerConfigs.COOLDOWN_UNCAP.get();
    public static double cooldownsoftcap(double x) {
        if(Objects.equals(cduncap, "0")) {
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
        else if(Objects.equals(cduncap, "2")) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(Objects.equals(cduncap, "3")) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the default formula | using 'else' in case user inputs something invalid
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
    }

    static String ctuncap = ServerConfigs.CAST_UNCAP.get();
    public static double castsoftcap(double x) {
        if(Objects.equals(ctuncap, "0")) {
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
        else if(Objects.equals(ctuncap, "2")) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(Objects.equals(ctuncap, "3")) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the default formula | using 'else' in case user inputs something invalid
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
    }

    static String runcap = ServerConfigs.RESIST_UNCAP.get();
    public static double resistsoftcap(double x) {
        if(Objects.equals(runcap, "0")) {
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
        else if(Objects.equals(runcap, "2")) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(Objects.equals(runcap, "3")) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the default formula | using 'else' in case user inputs something invalid
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
    }
}