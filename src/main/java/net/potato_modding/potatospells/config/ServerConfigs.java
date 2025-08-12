package net.potato_modding.potatospells.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;

    // Main config
    public static ModConfigSpec.ConfigValue<Boolean> BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Integer> BOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MINIBOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MOB_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> SUMMON_RESIST;
    public static ModConfigSpec.ConfigValue<Boolean> IV_SYSTEM;
    public static ModConfigSpec.ConfigValue<Integer> SHINY_CHANCE;

    // Familiars Compat
    public static ModConfigSpec.ConfigValue<Integer> IV_RANGE;
    public static ModConfigSpec.ConfigValue<Boolean> NATURE_SYSTEM;

    static {
        {
            BUILDER.push("Attribute System");
            BUILDER.comment("If mobs will have random variation in their attributes (does not include players)");
            IV_SYSTEM = BUILDER.worldRestart().define("Random Attribute Variation", true);
            BUILDER.comment("Chance for perfect attributes | 1 = 100%");
            BUILDER.comment("4096 = 0.025% chance | Min: 1 | Max: 8192 | Default: 4096");
            SHINY_CHANCE = BUILDER.worldRestart().define("Chance for Perfect Attributes", 4096);
            BUILDER.comment("Bonus attributes for mobs will be increased by whatever number you put here");
            BUILDER.comment("Maximum: 10000% | Minimum: 1% | Default: 15%");
            IV_RANGE = BUILDER.worldRestart().define("Attributes variance", 15);
            BUILDER.comment("Natures increase one attribute by 10% and reduce another by the same amount");
            BUILDER.comment("This bonus multiplies everything else, so is quite powerful");
            BUILDER.comment("WARNING: Only includes familiars by default! Add new ones via datapack");
            NATURE_SYSTEM = BUILDER.worldRestart().define("Mobs Natures", true);
            BUILDER.pop();
        }
        {
            BUILDER.push("Automatic Balance");
            BUILDER.comment("Turns ON/OFF difficulty scaling for ALL mobs | Default: true [ON]");
            BUILDER.comment("Automatic Rebalance does not affect manual bosses attributes");
            BOSS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);
            {
                BUILDER.push("Modifiers");
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                BOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Bosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                MINIBOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Minibosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                MOB_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Mobs", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                SUMMON_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Summons", 100);
                BUILDER.pop();
            }
            BUILDER.pop();
        }
        BUILDING = BUILDER.build();
    }
}