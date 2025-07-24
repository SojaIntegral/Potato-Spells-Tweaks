package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.items.MiracleCrystal;
import net.potato_modding.potatospells.items.MobIdentifier;
import net.potato_modding.potatospells.items.MoodCrystal;

import java.util.Collection;
import java.util.function.Supplier;

public class PotatoRegistry {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PotatoSpells.MOD_ID);

    public static final Supplier<CurioBaseItem> MOB_IDENTIFIER = ITEMS.register("mob_identifier", MobIdentifier::new);
    public static final Supplier<Item> ATTRIBUTE_REROLLER = ITEMS.register("miracle_crystal",
            () -> new MiracleCrystal(new Item.Properties().stacksTo(64).fireResistant().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.RARE)));
    public static final Supplier<Item> NATURE_REROLLER = ITEMS.register("mood_crystal",
            () -> new MoodCrystal(new Item.Properties().stacksTo(4).fireResistant().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.RARE)));



    public static Collection<DeferredHolder<Item, ? extends Item>> getPotatoItems()

    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
