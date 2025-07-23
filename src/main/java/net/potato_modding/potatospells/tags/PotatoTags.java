package net.potato_modding.potatospells.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    public static final TagKey<Item> DROP_ON_DEATH = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "drop_on_death")
    );

    // For familiars' natures
    public static final TagKey<EntityType<?>> HAS_NATURE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "natures/mobs_with_natures"));
    public static final TagKey<EntityType<?>> MOB_ENABLED =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "potato_whitelist"));

    // Mobs types
    public static final TagKey<EntityType<?>> BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "rank/boss"));
    public static final TagKey<EntityType<?>> MINIBOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "rank/miniboss"));
    public static final TagKey<EntityType<?>> NORMAL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "rank/normal"));
    public static final TagKey<EntityType<?>> SUMMON =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "rank/summon"));
    public static final TagKey<EntityType<?>> PLAYER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "rank/player"));

    // Mobs elements
    // Base
    public static final TagKey<EntityType<?>> TYPE_NEUTRAL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_neutral"));
    public static final TagKey<EntityType<?>> TYPE_BLOOD =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_blood"));
    public static final TagKey<EntityType<?>> TYPE_ELDRITCH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_eldritch"));
    public static final TagKey<EntityType<?>> TYPE_ENDER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_ender"));
    public static final TagKey<EntityType<?>> TYPE_EVOKATION =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_evokation"));
    public static final TagKey<EntityType<?>> TYPE_FIRE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_fire"));
    public static final TagKey<EntityType<?>> TYPE_HOLY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_holy"));
    public static final TagKey<EntityType<?>> TYPE_ICE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_ice"));
    public static final TagKey<EntityType<?>> TYPE_LIGHTNING =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_lightning"));
    public static final TagKey<EntityType<?>> TYPE_NATURE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_nature"));
    // Aeromancy
    public static final TagKey<EntityType<?>> TYPE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_wind"));
    // CAT:S
    public static final TagKey<EntityType<?>> TYPE_ABYSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_abyss"));
    public static final TagKey<EntityType<?>> TYPE_TECHNOMANCY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_techno"));
    // Ender's
    public static final TagKey<EntityType<?>> TYPE_BLADE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_blade"));
    public static final TagKey<EntityType<?>> TYPE_MIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_mind"));
    // Familiars
    public static final TagKey<EntityType<?>> TYPE_SOUND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_sound"));
    // Magic from the East
    public static final TagKey<EntityType<?>> TYPE_SOUL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_soul"));
    public static final TagKey<EntityType<?>> TYPE_SYM =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_sym"));
    public static final TagKey<EntityType<?>> TYPE_DUNE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_dune"));
    // TO Tweaks
    public static final TagKey<EntityType<?>> TYPE_AQUA =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elemental_system/type_aqua"));

    // Races
    public static final TagKey<EntityType<?>> RACE_HUMAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/human"));
    public static final TagKey<EntityType<?>> RACE_HUMANOID =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/humanoid"));
    public static final TagKey<EntityType<?>> RACE_UNDEAD =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/undead"));
    public static final TagKey<EntityType<?>> RACE_BRUTE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/brute"));
    public static final TagKey<EntityType<?>> RACE_INSECT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/insect"));
    public static final TagKey<EntityType<?>> RACE_FLYING =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/flying"));
    public static final TagKey<EntityType<?>> RACE_GOLEM =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/golem"));
    public static final TagKey<EntityType<?>> RACE_CONSTRUCT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/construct"));
    public static final TagKey<EntityType<?>> RACE_FISH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/fish"));
    public static final TagKey<EntityType<?>> RACE_SPIRIT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/spirit"));
    public static final TagKey<EntityType<?>> RACE_AMORPH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/amorph"));
    public static final TagKey<EntityType<?>> RACE_DRAGON =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/dragon"));
    public static final TagKey<EntityType<?>> RACE_DRAGONBORN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/dragonborn"));
    public static final TagKey<EntityType<?>> RACE_PLAYER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "race_system/player"));






    public static final TagKey<EntityType<?>> CRASH_FIX =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "crash_fix/culprits"));

}
