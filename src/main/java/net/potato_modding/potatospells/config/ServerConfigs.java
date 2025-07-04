package net.potato_modding.potatospells.config;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;
    public static ModConfigSpec.ConfigValue<Integer> COOLDOWN_UNCAP;
    public static ModConfigSpec.ConfigValue<Integer> CAST_UNCAP;
    public static ModConfigSpec.ConfigValue<Integer> RESIST_UNCAP;

    public static ModConfigSpec.ConfigValue<Boolean> ISS_SWITCH;

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

    public static ModConfigSpec.ConfigValue<Double> KEEPER_ATTACK;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> KEEPER_TOUGHNESS;

    public static ModConfigSpec.ConfigValue<Boolean> CAT_SWITCH;

    public static ModConfigSpec.ConfigValue<Double> CRAB_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CRAB_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> CRAB_TOUGHNESS;

    public static ModConfigSpec.ConfigValue<Double> DEEP_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> DEEP_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> DEEP_ATTACK;

    public static ModConfigSpec.ConfigValue<Double> CORALO_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> CORALO_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> CORALO_ATTACK;

    public static ModConfigSpec.ConfigValue<Double> CORALG_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_FIRE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ICE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_HOLY_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_NATURE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_BLOOD_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ENDER_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_LIGHTNING_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ELDRITCH_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ABYSSAL_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_BLADE_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_MUSIC_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_WIND_RESIST;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ARMOR;
    public static ModConfigSpec.ConfigValue<Double> CORALG_TOUGHNESS;
    public static ModConfigSpec.ConfigValue<Double> CORALG_ATTACK;

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
            BUILDER.push("Main");
            BUILDER.comment("RE-BALANCE FORMULAS:");
            BUILDER.comment("Accept '1', '2', '3' or '4'");
            BUILDER.comment("1 = 'Default': Maximum at ~3.6 (Made for your average ISS experience)");
            BUILDER.comment("2 = 'Nerfed': Maximum at ~5.0 (Made for SMPs and PvP)");
            BUILDER.comment("3 = 'Apotheosis': Maximum at ~8.0 (Made for Apotheosis compat)");
            BUILDER.comment("4 = 'Alternative': Uncapped (For when number go big)");
            BUILDER.comment("Warning: Using 'Alternative' on anything will raise the difficulty by A LOT!");
            COOLDOWN_UNCAP = BUILDER.worldRestart().define("Cooldown Formula", 1);
            CAST_UNCAP = BUILDER.worldRestart().define("Cast Time Formula", 1);
            RESIST_UNCAP = BUILDER.worldRestart().define("Spell Resist Formula", 1);
            BUILDER.pop();
        }

        // IRONS SPELLS
        {
            BUILDER.push("ISS");
            BUILDER.comment("Turns ON/OFF automatic attributes balance for Iron's Spells mod. | Default: true");
            ISS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);
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

            // DEAD KING BOSS
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

            // LIVING ARMOR (CITADEL KEEPER)
            {
                BUILDER.push("Living Armor Attributes");
                KEEPER_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.1);
                KEEPER_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 1.5);
                KEEPER_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 0.8);
                KEEPER_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.3);
                KEEPER_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 2.0);
                KEEPER_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 2.0);
                KEEPER_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.55);
                KEEPER_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", -0.3);
                KEEPER_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    KEEPER_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 0.6);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    KEEPER_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.5);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    KEEPER_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.4);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    KEEPER_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.85);
                }
                KEEPER_ARMOR = BUILDER.worldRestart().define("Armor ", 20.0);
                KEEPER_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 10.0);
                KEEPER_ATTACK = BUILDER.worldRestart().define("Attack Damage", 13.5);
                BUILDER.pop();
            }
            BUILDER.pop();
        }

        // CATACLYSM
        {
            BUILDER.push("Cataclysm");
            BUILDER.comment("Turns ON/OFF automatic attributes balance for Cataclysm mod. | Default: true");
            CAT_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);

            // KREB
            {
                BUILDER.push("Amethyst Crab");
                CRAB_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.2);
                CRAB_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", -0.85);
                CRAB_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 0.2);
                CRAB_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 1.6);
                CRAB_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 3.25);
                CRAB_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 0.45);
                CRAB_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.3);
                CRAB_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 1.95);
                CRAB_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 0.35);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    CRAB_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 0.4);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    CRAB_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 2.0);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    CRAB_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 1.7);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    CRAB_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.35);
                }
                CRAB_ARMOR = BUILDER.worldRestart().define("Armor ", 30.0);
                CRAB_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 12.0);
                BUILDER.pop();
            }

            // DEEPLINGS
            {
                BUILDER.push("Deeplings Attributes");
                DEEP_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.2);
                DEEP_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 2.35);
                DEEP_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.85);
                DEEP_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.7);
                DEEP_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 0.8);
                DEEP_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 0.9);
                DEEP_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.65);
                DEEP_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", -0.25);
                DEEP_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.6);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    DEEP_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 3.25);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    DEEP_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 0.7);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    DEEP_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 0.75);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    DEEP_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.1);
                }
                DEEP_ARMOR = BUILDER.worldRestart().define("Armor ", 10.0);
                DEEP_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 5.0);
                DEEP_ATTACK = BUILDER.worldRestart().define("Attack Damage", 11.5);

                BUILDER.pop();
            }

            // CORALSSUS
            {
                BUILDER.push("Coralssus Miniboss");
                CORALO_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.5);
                CORALO_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 2.15);
                CORALO_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.55);
                CORALO_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.95);
                CORALO_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 0.9);
                CORALO_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.1);
                CORALO_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.75);
                CORALO_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 0.35);
                CORALO_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.8);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    CORALO_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 3.2);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    CORALO_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.65);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    CORALO_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 0.55);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    CORALO_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 0.7);
                }
                CORALO_ARMOR = BUILDER.worldRestart().define("Armor ", 30.0);
                CORALO_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 15.0);
                CORALO_ATTACK = BUILDER.worldRestart().define("Attack Damage", 12.0);
                BUILDER.pop();
            }

            // CORAL GOLEM
            {
                BUILDER.push("Coral Golem");
                CORALG_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.15);
                CORALG_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 2.15);
                CORALG_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.55);
                CORALG_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.95);
                CORALG_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 0.9);
                CORALG_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.1);
                CORALG_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.75);
                CORALG_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 0.35);
                CORALG_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 1.8);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    CORALG_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 3.2);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    CORALG_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.65);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    CORALG_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 0.55);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    CORALG_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 0.7);
                }
                CORALG_ARMOR = BUILDER.worldRestart().define("Armor ", 30.0);
                CORALG_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 15.0);
                CORALG_ATTACK = BUILDER.worldRestart().define("Attack Damage", 12.0);
                BUILDER.pop();
            }

            // CORAL GOLEM
            {
                BUILDER.push("Leviathan Boss");
                LEVIA_RESIST = BUILDER.worldRestart().define("Spell Resist", 1.25);
                LEVIA_FIRE_RESIST = BUILDER.worldRestart().define("Fire Spell Resistance", 2.3);
                LEVIA_ICE_RESIST = BUILDER.worldRestart().define("Ice Spell Resistance", 1.6);
                LEVIA_HOLY_RESIST = BUILDER.worldRestart().define("Holy Spell Resistance", 0.8);
                LEVIA_NATURE_RESIST = BUILDER.worldRestart().define("Nature Spell Resistance", 0.95);
                LEVIA_BLOOD_RESIST = BUILDER.worldRestart().define("Blood Spell Resistance", 1.05);
                LEVIA_ENDER_RESIST = BUILDER.worldRestart().define("Ender Spell Resistance", 1.9);
                LEVIA_LIGHTNING_RESIST = BUILDER.worldRestart().define("Lightning Spell Resistance", 0.65);
                LEVIA_ELDRITCH_RESIST = BUILDER.worldRestart().define("Eldritch Spell Resistance", 2.0);
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    BUILDER.comment("Only works when [Cataclysm: Spellbooks] mod is present");
                    LEVIA_ABYSSAL_RESIST = BUILDER.worldRestart().define("Abyssal Spell Resistance", 3.5);
                }
                if (ModList.get().isLoaded("endersequipment")) {
                    BUILDER.comment("Only works when [Ender's Spells and Stuff] mod is present");
                    LEVIA_BLADE_RESIST = BUILDER.worldRestart().define("Blade Spell Resistance", 1.35);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    BUILDER.comment("Only works when [Alshanex's Familiars] mod is present");
                    LEVIA_MUSIC_RESIST = BUILDER.worldRestart().define("Sound Spell Resistance", 0.85);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    BUILDER.comment("Only works when [SnackPirate's Aeromancy] mod is present");
                    LEVIA_WIND_RESIST = BUILDER.worldRestart().define("Air Spell Resistance", 1.15);
                }
                LEVIA_ARMOR = BUILDER.worldRestart().define("Armor ", 15.0);
                LEVIA_TOUGHNESS = BUILDER.worldRestart().define("Armor Toughness", 15.0);
                LEVIA_ATTACK = BUILDER.worldRestart().define("Attack Damage", 10.0);
                BUILDER.pop();
            }
            BUILDER.pop();
        }

        BUILDING = BUILDER.build();
    }
}