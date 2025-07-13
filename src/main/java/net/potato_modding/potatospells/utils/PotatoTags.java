package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    // Minecraft Bosses
    public static final TagKey<EntityType<?>> ENDER_DRAGON =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/minecraft/ender_dragon"));
    public static final TagKey<EntityType<?>> WARDEN_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/minecraft/warden"));
    public static final TagKey<EntityType<?>> WITHER_BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/minecraft/wither_boss"));


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
    // Bosses
    public static final TagKey<EntityType<?>> ANCIENT_REMNANT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/ancient_remnant"));
    public static final TagKey<EntityType<?>> HARBINGER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/the_harbinger"));
    public static final TagKey<EntityType<?>> IGNIS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/ignis"));
    public static final TagKey<EntityType<?>> LEVIATHAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/leviathan_boss"));
    public static final TagKey<EntityType<?>> MALEDICTUS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/maledictus"));
    public static final TagKey<EntityType<?>> NETHERITE_MONSTROSITY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/netherite_monstrosity"));
    public static final TagKey<EntityType<?>> SCYLLA =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "bosses/cataclysm/scylla"));

    // Minibosses
    public static final TagKey<EntityType<?>> AMETHYST_CRAB =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/amethyst_crab"));
    public static final TagKey<EntityType<?>> APTRGANGR =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/aptrgangr"));
    public static final TagKey<EntityType<?>> CLAWDIAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/clawdian"));
    public static final TagKey<EntityType<?>> CORAL_GOLEM =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/coral_golem"));
    public static final TagKey<EntityType<?>> CORALSSUS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/coralssus"));
    public static final TagKey<EntityType<?>> ENDER_GOLEM =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/ender_golem"));
    public static final TagKey<EntityType<?>> ENDER_GUARDIAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/ender_guardian"));
    public static final TagKey<EntityType<?>> KOBOLEDIATOR =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/kobolediator"));
    public static final TagKey<EntityType<?>> WADJET =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "miniboss/cataclysm/wadjet"));

    // Normal Mobs
    public static final TagKey<EntityType<?>> ENDERMAPTERA =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/cataclysm/endermaptera"));
    public static final TagKey<EntityType<?>> DUMMY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mobs/cataclysm/endermaptera"));


    // Effects Blacklists (prevent buff / debuff stacking from getting out of hand)
    public static final TagKey<MobEffect> POSITIVE_BUFFS =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/buff_replace"));
    public static final TagKey<MobEffect> POSITIVE_BUFFS2 =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/buff_not_apply"));
    public static final TagKey<MobEffect> DEFENSIVE_BUFFS =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/def_replace"));
    public static final TagKey<MobEffect> DEFENSIVE_BUFFS2 =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/def_not_apply"));
    public static final TagKey<MobEffect> DAMAGE_BUFFS =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/dmg_replace"));
    public static final TagKey<MobEffect> DAMAGE_BUFFS2 =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/dmg_not_apply"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/debuff_replace"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK2 =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/debuff_replace_2"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK3 =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "blacklist/debuff_not_apply"));

    public static final TagKey<MobEffect> POSITIVE_BUFFS_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/buff_replace_check"));
    public static final TagKey<MobEffect> POSITIVE_BUFFS2_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/buff_not_apply_check"));
    public static final TagKey<MobEffect> DEFENSIVE_BUFFS_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/def_replace_check"));
    public static final TagKey<MobEffect> DEFENSIVE_BUFFS2_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/def_not_apply_check"));
    public static final TagKey<MobEffect> DAMAGE_BUFFS_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/dmg_replace_check"));
    public static final TagKey<MobEffect> DAMAGE_BUFFS2_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/dmg_not_apply_check"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/debuff_replace_check"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK2_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/debuff_replace_2_check"));
    public static final TagKey<MobEffect> DEBUFF_BLOCK3_CHECK =
            TagKey.create(Registries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "check/debuff_not_apply_check"));
}