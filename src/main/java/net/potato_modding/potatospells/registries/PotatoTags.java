package net.potato_modding.potatospells.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    // Spellcasting entities tags
    // These are meant to be used as "default mage profiles"
    // But are also used for ISS and other mods mages by default
    public static final TagKey<EntityType<?>> MAGE_ABYSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_abyss"));
    public static final TagKey<EntityType<?>> MAGE_BLADE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_blade"));
    public static final TagKey<EntityType<?>> MAGE_BLOOD =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_blood"));
    public static final TagKey<EntityType<?>> MAGE_ELDRITCH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_eldritch"));
    public static final TagKey<EntityType<?>> MAGE_ENDER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_ender"));
    public static final TagKey<EntityType<?>> MAGE_EVOKE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_evoke"));
    public static final TagKey<EntityType<?>> MAGE_FIRE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_fire"));
    public static final TagKey<EntityType<?>> MAGE_HOLY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_holy"));
    public static final TagKey<EntityType<?>> MAGE_ICE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_ice"));
    public static final TagKey<EntityType<?>> MAGE_NATURE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_nature"));
    public static final TagKey<EntityType<?>> MAGE_SOUND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_sound"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));

    // Familiar Natures
    public static final TagKey<EntityType<?>> NATURE_HARDY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/familiars/natures/hardy"));
    /*
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
    public static final TagKey<EntityType<?>> MAGE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/mage_wind"));
     */
}
