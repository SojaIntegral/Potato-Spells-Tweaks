package net.potato_modding.potatospells.registry;

import net.acetheeldritchking.cataclysm_spellbooks.CataclysmSpellbooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.function.Supplier;

public class PotatoCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CataclysmSpellbooks.MOD_ID);

    public static final Supplier<CreativeModeTab> POTATO_ITEMS = CREATIVE_MODE_TAB.register("potato_tweaks_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(PotatoRegistry.BASE_ANALYZER.get()))
                    .title(Component.translatable("creative_tab.potatospellbookstweaks.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Materials
                        output.accept(PotatoRegistry.MOB_IDENTIFIER.get());
                        output.accept(PotatoRegistry.BASE_ANALYZER.get());
                        output.accept(PotatoRegistry.RED_ANALYZER.get());
                        output.accept(PotatoRegistry.GREEN_ANALYZER.get());
                        output.accept(PotatoRegistry.BLUE_ANALYZER.get());
                        output.accept(PotatoRegistry.YELLOW_ANALYZER.get());
                        output.accept(PotatoRegistry.PINK_ANALYZER.get());
                        output.accept(PotatoRegistry.BLACK_ANALYZER.get());
                        if(ServerConfigs.IV_SYSTEM.get()) output.accept(PotatoRegistry.ATTRIBUTE_REROLLER.get());
                        if(ServerConfigs.NATURE_SYSTEM.get()) output.accept(PotatoRegistry.NATURE_REROLLER.get());
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
