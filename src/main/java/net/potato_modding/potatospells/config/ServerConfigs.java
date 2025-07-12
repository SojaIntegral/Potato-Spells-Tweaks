package net.potato_modding.potatospells.config;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;
    public static ModConfigSpec.ConfigValue<Integer> FORMULA_REBALANCE;

    public static ModConfigSpec.ConfigValue<Boolean> BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Boolean> MINIBOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Boolean> MOB_SWITCH;

    public static ModConfigSpec.ConfigValue<Double> TYROS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> TYROS_POWER;
    public static ModConfigSpec.ConfigValue<Double> TYROS_FIRE_SPELL;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> TYROS_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> TYROS_ATTACK;

    public static ModConfigSpec.ConfigValue<Double> DEAD_POWER;
    public static ModConfigSpec.ConfigValue<Double> DEAD_BLOOD_SPELL;
    public static ModConfigSpec.ConfigValue<Double> DEAD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> DEAD_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> DEAD_ATTACK;

    public static ModConfigSpec.ConfigValue<Double> NETMONST_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> NETMONST_TOUGHNESS;

    public static ModConfigSpec.ConfigValue<Double> LEVIA_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> LEVIA_ATTACK;

    static {

        // MAIN CONFIG
        {
            BUILDER.push("Re-Balance");
            BUILDER.comment("Accept '1', '2', '3' or '4'");
            BUILDER.comment("1 = 'Default': Maximum at ~3.6 (Made for your average ISS experience)");
            BUILDER.comment("2 = 'Nerfed': Maximum at ~5.0 (Made for SMPs and PvP)");
            BUILDER.comment("3 = 'Apotheosis': Maximum at ~8.0 (Made for Apotheosis compat)");
            BUILDER.comment("4 = 'Alternative': Uncapped (Recommended for PvP servers)");
            FORMULA_REBALANCE = BUILDER.worldRestart().define("Rebalanced Formula", 1);
            BUILDER.comment("WARNING: This affects Spell Resistance, Cast Speed AND Cooldown Reduction!");
            BUILDER.pop();
        }

        // BOSSES CONFIGS
        {
            BUILDER.push("BOSS");
            BUILDER.comment("Turns ON/OFF automatic attributes balance for Bosses. | Default: true");
            BOSS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);
            BUILDER.comment("Turning automatic rebalance off will create A LOT of stuff here. I warned you.");

            // TYROS (FIRE BOSS)
            {
                BUILDER.push("Tyros Attributes");
                TYROS_POWER = BUILDER.worldRestart().define("Spell Power", 1.75);
                TYROS_FIRE_SPELL = BUILDER.worldRestart().define("Fire Spell Power", 1.75);
                TYROS_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.35);
                TYROS_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.85);
                TYROS_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 0.65);
                TYROS_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.8);
                TYROS_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.5);
                TYROS_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.25);
                TYROS_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 0.9);
                TYROS_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 0.75);
                TYROS_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    TYROS_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 0.5);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    TYROS_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.15);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    TYROS_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.05);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    TYROS_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 0.7);
                }
                TYROS_ARMOR = BUILDER.worldRestart().define("Armor ", 15.0);
                TYROS_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 10.0);
                TYROS_ATTACK = BUILDER.worldRestart().define("Attack Damage", 13.0);
                BUILDER.pop();
            }

            // DEAD KING
            {
                BUILDER.push("Dead King Attributes");
                DEAD_POWER = BUILDER.worldRestart().define("Spell Power", 1.35);
                DEAD_BLOOD_SPELL = BUILDER.worldRestart().define("Blood Spell Power", 1.45);
                DEAD_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.35);
                DEAD_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 0.45);
                DEAD_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.15);
                DEAD_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", -0.25);
                DEAD_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.25);
                DEAD_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.75);
                DEAD_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.55);
                DEAD_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 0.85);
                DEAD_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 2.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    DEAD_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.85);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    DEAD_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 0.8);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    DEAD_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 0.35);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    DEAD_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.2);
                }
                DEAD_ARMOR = BUILDER.worldRestart().define("Armor ", 25.0);
                DEAD_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 5.0);
                DEAD_ATTACK = BUILDER.worldRestart().define("Attack Damage", 11.0);
                BUILDER.pop();
            }

            // NETHERITE MONSTROSITY
            {
                BUILDER.push("Netherite Monstrosity");
                NETMONST_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.2);
                NETMONST_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", -0.85);
                NETMONST_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 0.2);
                NETMONST_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.6);
                NETMONST_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 3.25);
                NETMONST_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 0.45);
                NETMONST_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.3);
                NETMONST_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.95);
                NETMONST_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 0.35);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    NETMONST_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 0.4);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    NETMONST_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 2.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    NETMONST_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.7);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    NETMONST_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.35);
                }
                NETMONST_ARMOR = BUILDER.worldRestart().define("Armor ", 30.0);
                NETMONST_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 12.0);
                BUILDER.pop();
            }
            BUILDER.pop();
        }

        // MINIBOSSES
        {
            BUILDER.push("MINIBOSS");
            BUILDER.comment("Turns ON/OFF automatic attributes balance for Mini-Bosses. | Default: true");
            MINIBOSS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);

            {

            }
            BUILDER.pop();
        }

        BUILDING = BUILDER.build();
    }
}