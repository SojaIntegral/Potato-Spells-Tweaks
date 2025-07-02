package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.Objects;

public class PotatoUtils {

    /*
    SOFTCAP FORMULAS

    They look like they are out of order, but this is the most likely order to be used,
    if we go by what the mod was intended to do.
    */

    static int cduncap = ServerConfigs.COOLDOWN_UNCAP.get();
    public static double cooldownsoftcap(double x) {
        if(cduncap == 2) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(cduncap == 1) {
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
        else if(cduncap == 3) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the Alternative formula | using 'else' in case user inputs something invalid
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
    }

    static int ctuncap = ServerConfigs.CAST_UNCAP.get();
    public static double castsoftcap(double x) {
        if(ctuncap == 2) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(ctuncap == 1) {
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
        else if(ctuncap == 3) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the alternative formula | using 'else' in case user inputs something invalid
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
    }

    static int runcap = ServerConfigs.RESIST_UNCAP.get();
    public static double resistsoftcap(double x) {
        if(runcap == 2) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8))) : 2;
        }
        else if(runcap == 1) {
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3))) : 2;
        }
        else if(runcap == 3) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46))) : 2;
        }
        else {
            //This is the Alternative formula | using 'else' in case user inputs something invalid
            return x >= 0 ? 2 - (30 / (29+x)) : 2 - ((30 - x) / 30);
        }
    }
}