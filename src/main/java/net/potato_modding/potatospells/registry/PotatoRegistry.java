package net.potato_modding.potatospells.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.potato_modding.potatospells.screen.AttributesGUI;

public class PotatoRegistry {

    public static Item MOB_INTERACT_ITEM;

    public static void register(IEventBus modBus) {
        modBus.addListener(PotatoRegistry::onRegisterItems);
    }

    private static void onRegisterItems(RegisterEvent event) {
        event.register(Registries.ITEM, helper -> {
            MOB_INTERACT_ITEM = new AttributesGUI(new Item.Properties());
            helper.register(
                    ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "mob_identifier"),
                    MOB_INTERACT_ITEM
            );
        });

        event.register(Registries.CREATIVE_MODE_TAB, helper -> {
            helper.register(
                    ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "mob_identifier"),
                    CreativeModeTab.builder()
                            .title(Component.literal("P.S.T."))
                            .icon(() -> new ItemStack(Items.STICK)) // Replace with your icon
                            .displayItems((flags, output) -> {
                                output.accept(MOB_INTERACT_ITEM);
                            })
                            .build());
        });
    }
}
