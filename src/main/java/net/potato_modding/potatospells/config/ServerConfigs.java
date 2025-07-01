package net.potato_modding.potatospells.config;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;
    public static ModConfigSpec.ConfigValue<String> COOLDOWN_UNCAP;
    public static ModConfigSpec.ConfigValue<String> CAST_UNCAP;
    public static ModConfigSpec.ConfigValue<String> RESIST_UNCAP;
    public static ModConfigSpec.ConfigValue<Boolean> MUTUAL_EFFECTS;

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

    static {
        {
            BUILDER.push("Potato Spells Utils");
            BUILDER.comment("RE-BALANCE FORMULAS:");
            BUILDER.comment("Accept '1', '2' or '3'");
            BUILDER.comment("0 = 'Alternative': Uncapped (Alternative for classical ISS formula)");
            BUILDER.comment("1 = 'Default': Maximum at ~3.6 (Made for your average ISS experience)");
            BUILDER.comment("2 = 'Nerfed': Maximum at ~5.0 (Made for SMPs and PvP)");
            BUILDER.comment("3 = 'Apotheosis': Maximum at ~8.0 (Made for Apotheosis compat)");
            COOLDOWN_UNCAP = BUILDER.worldRestart().define("Cooldown Formula", "1");
            CAST_UNCAP = BUILDER.worldRestart().define("Cast Time Formula", "1");
            RESIST_UNCAP = BUILDER.worldRestart().define("Spell Resist Formula", "1");
            BUILDER.comment("Not yet implemented!");
            MUTUAL_EFFECTS = BUILDER.worldRestart().define("Should Charge and Haste be mutually exclusive to other potion effects (determined by mob effect tags): ", true);
            BUILDER.pop();
        }

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
        BUILDING = BUILDER.build();
    }
}