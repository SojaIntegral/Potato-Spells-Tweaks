package net.potato_modding.potatospells.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.potato_modding.potatospells.PotatoSpells;

@SuppressWarnings("unused")
public class PotatoTags {

    public static final TagKey<Item> CURIO_ANALYZER =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzers"));
    public static final TagKey<Item> ANALYZER_BASE =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "base_analyzer"));
    public static final TagKey<Item> ANALYZER_RED =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzer_red"));
    public static final TagKey<Item> ANALYZER_GREEN =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzer_green"));
    public static final TagKey<Item> ANALYZER_BLUE =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzer_blue"));
    public static final TagKey<Item> ANALYZER_YELLOW =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzer_yellow"));
    public static final TagKey<Item> ANALYZER_PINK =
            ItemTags.create(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "analyzer_pink"));
}
