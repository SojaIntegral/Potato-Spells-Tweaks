package net.potato_modding.potatospells.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.potato_modding.potatospells.PotatoSpells;

public class PotatoFocuses {
    public static final TagKey<Item> GENERIC_FOCUS = ItemTags.create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "generic_focus.json").toString()));
}
