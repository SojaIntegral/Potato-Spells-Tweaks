package net.potato_modding.potatospells.utils;

import net.potato_modding.potatospells.config.ServerConfigs;

public class RebalanceHandler {

    /*
    SOFTCAP FORMULAS

    They look like they are out of order, but this is the most likely order to be used,
    if we go by what the mod was intended to do.
    */

    public static int reFormula = ServerConfigs.FORMULA_REBALANCE.get();
    public static double rebalanceFormula(double x) {
        if(reFormula == 0) {
            return x <= 1.75 ? x : 1 / (-16 * (x - 1.5)) + 2;
        }
        else if(reFormula == 2) {
            return x <= 4.80999 ? 2*(Math.sin(0.28*(x+0.8)))+0.034136 : 2;
        }
        else if(reFormula == 1) {
            return x <= 3.62699 ? 2*(Math.sin(0.4*(x+0.3)))+0.00624 : 2;
        }
        else if(reFormula == 3) {
            return x <= 8.01198 ? 2*(Math.sin(0.15*(x+2.46)))+0.001736 : 2;
        }
        else {
            //This is the Alternative formula | using 'else' in case user inputs something invalid
            return x >= 0 ? 1.966667 - (30 / (29+x)) : 2 - ((20 - x) / 20);
        }
    }
}