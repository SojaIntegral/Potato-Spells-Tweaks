package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    // ISS Entities
    public static final TagKey<EntityType<?>> TYROS_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "tyros_boss"));
    public static final TagKey<EntityType<?>> DEADKING_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "deadking_boss"));
    public static final TagKey<EntityType<?>> KEEPER_MOB =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "citadel_keeper"));

    // Cataclysm Entities
    public static final TagKey<EntityType<?>> NETHERITE_MONSTROSITY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "netherite_monstrosity"));
   public static final TagKey<EntityType<?>> LEVIATHAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "leviathan_boss"));

    // Not implemented yet
    public static final TagKey<MobEffect> INCOMPATIBLE_WITH_CHARGE =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "incompatible_with_charge"));
}