package net.potato_modding.potatospell.utils;

import net.potato_modding.potatospell.config.ServerConfigs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

public class RebalanceHandler {

    /*
    SOFTCAP FORMULAS
    */

    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JavaScript");
    private static final Map<Double, Double> CACHE = new HashMap<>();
    private static String lastFormula = null;
    public static double customFormula(double x) {
        String formula = ServerConfigs.FORMULA_CUSTOM.get();

        if (!formula.equals(lastFormula)) {
            CACHE.clear();
            lastFormula = formula;
        }

        if (CACHE.containsKey(x)) {
            return CACHE.get(x);
        }

        try {
            ENGINE.put("x", x);
            Object result = ENGINE.eval(formula);
            if (result instanceof Number number) {
                return number.doubleValue();
            }
        } catch (Exception e) {
            System.err.println("Invalid formula: " + formula);
            e.printStackTrace();
        }
        return 1.0;
    }

    public static double rebalanceFormula(double x) {
        int reFormula = ServerConfigs.FORMULA_REBALANCE.get();

        return switch (reFormula) {
            case 0 -> x <= 1.75 ? x : 1 / (-16 * (x - 1.5)) + 2;
            case 1 -> x <= 3.62699 ? 2 * (Math.sin(0.4 * (x + 0.3))) + 0.00624 : 2;
            case 2 -> x <= 4.80999 ? 2 * (Math.sin(0.28 * (x + 0.8))) + 0.034136 : 2;
            case 3 -> x <= 8.01198 ? 2 * (Math.sin(0.15 * (x + 2.46))) + 0.001736 : 2;
            case 4 -> x >= 0 ? 1.966667 - (30 / (29 + x)) : 2 - ((20 - x) / 20);
            case 5 -> x <= 1.5 ? x : -.25 * (1 / (x - 1)) + 2;
            default -> customFormula(x);
        };
    }
}