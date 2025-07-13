package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    // ISS Bosses
    public static final TagKey<EntityType<?>> TYROS_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/iss/tyros_boss"));
    public static final TagKey<EntityType<?>> DEADKING_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/iss/deadking_boss"));


    // ISS Mobs
    public static final TagKey<EntityType<?>> KEEPER_MOB =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/citadel_keeper"));
    public static final TagKey<EntityType<?>> ICE_SPIDER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/iss/ice_spider"));


    // Mages pre-sets
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


    // Cataclysm Entities
    public static final TagKey<EntityType<?>> NETHERITE_MONSTROSITY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/netherite_monstrosity"));
   public static final TagKey<EntityType<?>> LEVIATHAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/leviathan_boss"));


    // Not implemented yet
    public static final TagKey<MobEffect> INCOMPATIBLE_WITH_CHARGE =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "incompatible_with_charge"));
}