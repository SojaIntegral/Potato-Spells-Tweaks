package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.potato_modding.potatospells.PotatoSpells;

public class PotatoTags {
    // Items

    // Blocks

    // Entities
    public static final TagKey<EntityType<?>> SPELL_RES_ENTITIES = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "spell_res_entities"));

    // Potion Effects
    public static final TagKey<MobEffect> INCOMPATIBLE_WITH_CHARGE = TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "incompatible_with_charge"));
}
