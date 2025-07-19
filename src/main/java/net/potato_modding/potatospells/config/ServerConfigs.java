package net.potato_modding.potatospells.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;

    // Main config
    public static ModConfigSpec.ConfigValue<Integer> FORMULA_REBALANCE;
    public static ModConfigSpec.ConfigValue<Boolean> BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Integer> BOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MINIBOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MOB_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> SUMMON_RESIST;
    public static ModConfigSpec.ConfigValue<Boolean> BUFF_STACKING;
    public static ModConfigSpec.ConfigValue<Boolean> SHINY;

    // Familiars Compat
    public static ModConfigSpec.ConfigValue<Integer> FAMILIAR_RAND;
    public static ModConfigSpec.ConfigValue<Boolean> FAMILIAR_NATURE;

    static {

        // MAIN CONFIG
        {
            BUILDER.push("Re-Balance");
            BUILDER.comment("Valid values: 0, 1, 2, 3 or 4 | Default: 0");
            BUILDER.comment("0 = 'Original': No maximum value (Made for your average ISS experience)");
            BUILDER.comment("1 = 'Default': Recommended for pure ISS modpacks");
            BUILDER.comment("2 = 'Custom': Recommended for regular modpacks and multiplayer");
            BUILDER.comment("3 = 'Apotheosis': Made modpacks with Apotheosis and other 'ridiculous' mods");
            BUILDER.comment("4 = 'Alternative': Great for balanced ISS PvP (Heavily nerfs scaling)");
            BUILDER.comment("WARNING: This affects [Spell Resistance], [Cast Speed] AND [Cooldown Reduction]!");
            FORMULA_REBALANCE = BUILDER.worldRestart().define("Rebalance", 0);
            BUILDER.pop();
        }
        {
            BUILDER.push("Effect Stacking");
            BUILDER.comment("By turning this on, you can prevent two effects from being applied at the same time");
            BUILDER.comment("WARNING: Must be configured via datapack! See instructions in [mob_effect] folder!");
            BUFF_STACKING = BUILDER.worldRestart().define("Buff Stacking", false);
            BUILDER.pop();
        }
        {
            BUILDER.push("Attribute System");
            BUILDER.comment("Bonus attributes for mobs will be increased by whatever number you put here");
            BUILDER.comment("Maximum: 10000% | Minimum: 1% | Default: 15%");
            FAMILIAR_RAND = BUILDER.worldRestart().define("Attributes variance", 15);
            BUILDER.comment("Natures increase one attribute by 10% and reduce another by the same amount");
            BUILDER.comment("This bonus multiplies everything else, so is quite powerful");
            BUILDER.comment("WARNING: Only includes familiars by default! Add new ones via datapack");
            FAMILIAR_NATURE = BUILDER.worldRestart().define("Mobs Natures", false);
            SHINY = BUILDER.worldRestart().define("Chance of Perfect attributes", false);
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