/*
 * MIT License
 * Copyright (c) 2025 Sofia Bergi
 * See the LICENSE file for more information.
 */

package net.potato_modding.potatospells;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.potato_modding.potatospells.config.ClientConfigs;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.entity.render.items.AnalyzerCurioRenderer;
import net.potato_modding.potatospells.registry.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@SuppressWarnings("unused")
@Mod(PotatoSpells.MOD_ID)
public class PotatoSpells {
    public static final String MOD_ID = "potatospellbookstweaks";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public PotatoSpells(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to spawn_armor.json creative tab
        PotatoSchool.register(modEventBus);
        SpellRegistries.register(modEventBus);
        PotatoEffects.register(modEventBus);
        PotatoCreativeTab.register(modEventBus);
        PotatoRegistry.register(modEventBus);
        PotatoAttributes.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfigs.BUILDING, String.format("%s-server.toml", PotatoSpells.MOD_ID));
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfigs.BUILDING, String.format("%s-client.toml", PotatoSpells.MOD_ID));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    private void onReloadListeners(AddReloadListenerEvent event) {
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                CuriosRendererRegistry.register(PotatoRegistry.BASE_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.RED_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.GREEN_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.BLUE_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.YELLOW_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.PINK_ANALYZER.get(), AnalyzerCurioRenderer::new);
                CuriosRendererRegistry.register(PotatoRegistry.BLACK_ANALYZER.get(), AnalyzerCurioRenderer::new);
            });
        }
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, path);
    }
}
