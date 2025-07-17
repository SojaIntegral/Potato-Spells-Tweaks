package net.potato_modding.potatospells.config;

import net.neoforged.fml.ModList;
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
    public static ModConfigSpec.ConfigValue<Boolean> BUFF_STACKING;

    // Familiars Compat
    public static ModConfigSpec.ConfigValue<Integer> FAMILIAR_RAND;
    public static ModConfigSpec.ConfigValue<Boolean> FAMILIAR_TOGGLE;
    public static ModConfigSpec.ConfigValue<Boolean> FAMILIAR_NATURE;

    // From here on it's all bosses
    public static ModConfigSpec.ConfigValue<Boolean> TYROS_SWITCH;
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

    public static ModConfigSpec.ConfigValue<Boolean> DEAD_SWITCH;
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

    public static ModConfigSpec.ConfigValue<Boolean> ANCIENT_REM_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> ANCIENT_REM_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> HARBINGER_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> HARBINGER_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> IGNIS_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> IGNIS_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> LEVIA_SWITCH;
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

    public static ModConfigSpec.ConfigValue<Boolean> MALEDICTUS_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> MALEDICTUS_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> NETMONST_SWITCH;
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
    public static ModConfigSpec.ConfigValue<Double> NETMONST_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> SCYLLA_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> SCYLLA_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> ENDER_DRAGON_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> ENDER_DRAGON_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> WITHER_BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> WITHER_BOSS_ATTACK;

    public static ModConfigSpec.ConfigValue<Boolean> WARDEN_SWITCH;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> WARDEN_ATTACK;

    static {

        // MAIN CONFIG
        {
            BUILDER.push("Re-Balance");
            BUILDER.comment("Valid values: 1, 2, 3 or 4 | Default: 1");
            BUILDER.comment("1 = 'Default': Maximum at ~3.6 (Made for your average ISS experience)");
            BUILDER.comment("2 = 'Custom': Maximum at ~5.0 (Made for SMPs and PvP)");
            BUILDER.comment("3 = 'Apotheosis': Maximum at ~8.0 (Made for Apotheosis modpacks)");
            BUILDER.comment("4 = 'Alternative': Uncapped (Heavily nerfs scaling)");
            BUILDER.comment("WARNING: This affects [Spell Resistance], [Cast Speed] AND [Cooldown Reduction]!");
            FORMULA_REBALANCE = BUILDER.worldRestart().define("Rebalanced Formula", 1);
            BUFF_STACKING = BUILDER.worldRestart().define("Buff Stacking", false);
            BUILDER.pop();
        }

        {
            BUILDER.push("Familiars");
            BUILDER.comment("Adds additional attributes to Familiars");
            BUILDER.comment("This will add strengths and weaknesses to Familiars");
            BUILDER.comment("As well as give them a bit more power");
            BUILDER.comment("WARNING: The other Familiars configs need this to be [true]");
            FAMILIAR_TOGGLE = BUILDER.worldRestart().define("Familiar Bonus Attributes", false);
            {
                BUILDER.push("Attributes");
                BUILDER.comment("Random variance to Familiars");
                BUILDER.comment("This will give them a bonus from 0 to whatever number you put here");
                BUILDER.comment("Maximum: 100% | Minimum: 0% | Default: 15%");
                FAMILIAR_RAND = BUILDER.worldRestart().define("Familiar Attributes variance", 15);
                BUILDER.comment("Natures increase one attribute by 10% and reduce another by the same amount");
                BUILDER.comment("This bonus multiplies everything else, so is quite powerful");
                FAMILIAR_NATURE = BUILDER.worldRestart().define("Familiar Natures", false);
                BUILDER.pop();
            }
            BUILDER.pop();
        }

        // BOSSES CONFIGS
        {
            BUILDER.push("Bosses");
            BUILDER.comment("Turns ON/OFF difficulty scaling for ALL mobs | Default: true [ON]");
            BUILDER.comment("Automatic Rebalance does not affect manual bosses attributes");
            BOSS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);
            {
                BUILDER.push("Modifiers");
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100%");
                BOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Bosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100%");
                MINIBOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Minibosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100%");
                MOB_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Mobs", 100);
                BUILDER.pop();
            }

            // TYROS (FIRE BOSS)
            {
                BUILDER.push("Tyros Attributes");
                TYROS_SWITCH = BUILDER.worldRestart().define("Edit Tyros", false);
                BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                TYROS_POWER = BUILDER.worldRestart().define("Spell Power", 1.0);
                TYROS_FIRE_SPELL = BUILDER.worldRestart().define("Fire Spell Power", 1.0);
                TYROS_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                TYROS_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                TYROS_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                TYROS_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                TYROS_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                TYROS_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                TYROS_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                TYROS_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                TYROS_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    TYROS_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    TYROS_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    TYROS_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    TYROS_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                }
                TYROS_ARMOR = BUILDER.worldRestart().define("Armor ", 5.0);
                TYROS_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                TYROS_ATTACK = BUILDER.worldRestart().define("Attack Damage", 10.0);
                BUILDER.pop();
            }

            // DEAD KING
            {
                BUILDER.push("Dead King Attributes");
                DEAD_SWITCH = BUILDER.worldRestart().define("Edit Dead King", false);
                BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                DEAD_POWER = BUILDER.worldRestart().define("Spell Power", 1.0);
                DEAD_BLOOD_SPELL = BUILDER.worldRestart().define("Blood Spell Power", 1.0);
                DEAD_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                DEAD_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                DEAD_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                DEAD_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                DEAD_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                DEAD_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                DEAD_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                DEAD_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                DEAD_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    DEAD_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    DEAD_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    DEAD_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    DEAD_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                }
                DEAD_ARMOR = BUILDER.worldRestart().define("Armor ", 5.0);
                DEAD_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                DEAD_ATTACK = BUILDER.worldRestart().define("Attack Damage", 8.0);
                BUILDER.pop();
            }

            // ANCIENT REMNANT
            if (ModList.get().isLoaded("cataclysm")) {
                {
                    BUILDER.push("Ancient Remnant Attributes");
                    ANCIENT_REM_SWITCH = BUILDER.worldRestart().define("Edit Ancient Remnant", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    ANCIENT_REM_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    ANCIENT_REM_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    ANCIENT_REM_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    ANCIENT_REM_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    ANCIENT_REM_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    ANCIENT_REM_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    ANCIENT_REM_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    ANCIENT_REM_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    ANCIENT_REM_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        ANCIENT_REM_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        ANCIENT_REM_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        ANCIENT_REM_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        ANCIENT_REM_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    ANCIENT_REM_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    ANCIENT_REM_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    ANCIENT_REM_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // HARBINGER
                {
                    BUILDER.push("The Harbinger Attributes");
                    HARBINGER_SWITCH = BUILDER.worldRestart().define("Edit Harbinger", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    HARBINGER_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    HARBINGER_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    HARBINGER_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    HARBINGER_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    HARBINGER_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    HARBINGER_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    HARBINGER_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    HARBINGER_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    HARBINGER_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        HARBINGER_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        HARBINGER_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        HARBINGER_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        HARBINGER_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    HARBINGER_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    HARBINGER_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    HARBINGER_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // IGNIS
                {
                    BUILDER.push("Ignis Attributes");
                    IGNIS_SWITCH = BUILDER.worldRestart().define("Edit Ignis", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    IGNIS_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    IGNIS_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    IGNIS_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    IGNIS_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    IGNIS_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    IGNIS_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    IGNIS_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    IGNIS_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    IGNIS_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        IGNIS_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        IGNIS_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        IGNIS_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        IGNIS_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    IGNIS_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    IGNIS_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    IGNIS_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // LEVIATHAN
                {
                    BUILDER.push("Leviathan Attributes");
                    LEVIA_SWITCH = BUILDER.worldRestart().define("Edit Leviathan", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    LEVIA_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    LEVIA_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    LEVIA_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    LEVIA_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    LEVIA_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    LEVIA_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    LEVIA_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    LEVIA_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    LEVIA_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        LEVIA_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        LEVIA_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        LEVIA_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        LEVIA_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    LEVIA_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    LEVIA_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    LEVIA_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // MALEDICTUS
                {
                    BUILDER.push("Maledictus Attributes");
                    MALEDICTUS_SWITCH = BUILDER.worldRestart().define("Edit Maledictus", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    MALEDICTUS_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    MALEDICTUS_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    MALEDICTUS_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    MALEDICTUS_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    MALEDICTUS_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    MALEDICTUS_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    MALEDICTUS_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    MALEDICTUS_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    MALEDICTUS_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        MALEDICTUS_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        MALEDICTUS_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        MALEDICTUS_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        MALEDICTUS_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    MALEDICTUS_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    MALEDICTUS_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    MALEDICTUS_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // NETHERITE MONSTROSITY
                {
                    BUILDER.push("Netherite Monstrosity Attributes");
                    NETMONST_SWITCH = BUILDER.worldRestart().define("Edit Netherite Monstrosity", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    NETMONST_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    NETMONST_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    NETMONST_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    NETMONST_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    NETMONST_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    NETMONST_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    NETMONST_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    NETMONST_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    NETMONST_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        NETMONST_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        NETMONST_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        NETMONST_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        NETMONST_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    NETMONST_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    NETMONST_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    NETMONST_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }

                // SCYLLA
                {
                    BUILDER.push("Scylla Attributes");
                    SCYLLA_SWITCH = BUILDER.worldRestart().define("Edit Scylla", false);
                    BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                    BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                    SCYLLA_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                    SCYLLA_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                    SCYLLA_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                    SCYLLA_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                    SCYLLA_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                    SCYLLA_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                    SCYLLA_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                    SCYLLA_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                    SCYLLA_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                        SCYLLA_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("endersequipment")) {
                        BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                        SCYLLA_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                        SCYLLA_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                        SCYLLA_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                    }
                    SCYLLA_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                    SCYLLA_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                    SCYLLA_ATTACK = BUILDER.worldRestart().define("Attack Damage", 9.0);
                    BUILDER.pop();
                }
            }

            // ENDER DRAGON

            {
                BUILDER.push("Ender Dragon Attributes");
                ENDER_DRAGON_SWITCH = BUILDER.worldRestart().define("Edit Ender Dragon", false);
                BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                ENDER_DRAGON_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                ENDER_DRAGON_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                ENDER_DRAGON_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                ENDER_DRAGON_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                ENDER_DRAGON_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                ENDER_DRAGON_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                ENDER_DRAGON_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                ENDER_DRAGON_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                ENDER_DRAGON_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    ENDER_DRAGON_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    ENDER_DRAGON_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    ENDER_DRAGON_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    ENDER_DRAGON_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                }
                ENDER_DRAGON_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                ENDER_DRAGON_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                ENDER_DRAGON_ATTACK = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                BUILDER.pop();
            }

            // WITHER BOSS
            {
                BUILDER.push("Wither Attributes");
                WITHER_BOSS_SWITCH = BUILDER.worldRestart().define("Edit Wither Boss", false);
                BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                WITHER_BOSS_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                WITHER_BOSS_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                WITHER_BOSS_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                WITHER_BOSS_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                WITHER_BOSS_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                WITHER_BOSS_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                WITHER_BOSS_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                WITHER_BOSS_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                WITHER_BOSS_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    WITHER_BOSS_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    WITHER_BOSS_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    WITHER_BOSS_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    WITHER_BOSS_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                }
                WITHER_BOSS_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                WITHER_BOSS_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                WITHER_BOSS_ATTACK = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                BUILDER.pop();
            }

            // WARDEN
            {
                BUILDER.push("Warden Attributes");
                WARDEN_SWITCH = BUILDER.worldRestart().define("Edit Warden", false);
                BUILDER.comment("Turning this ON [true] will disable automatic balancing");
                BUILDER.comment("All attributes will have to be defined below (default 1.0)");
                WARDEN_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.0);
                WARDEN_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.0);
                WARDEN_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.0);
                WARDEN_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.0);
                WARDEN_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 1.0);
                WARDEN_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.0);
                WARDEN_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.0);
                WARDEN_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.0);
                WARDEN_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    WARDEN_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    WARDEN_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    WARDEN_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.0);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    WARDEN_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.0);
                }
                WARDEN_ARMOR = BUILDER.worldRestart().define("Armor ", 0.0);
                WARDEN_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 0.0);
                WARDEN_ATTACK = BUILDER.worldRestart().define("Attack Damage", 10.0);
                BUILDER.pop();
            }
            BUILDER.pop();
        }
        BUILDING = BUILDER.build();
    }
}