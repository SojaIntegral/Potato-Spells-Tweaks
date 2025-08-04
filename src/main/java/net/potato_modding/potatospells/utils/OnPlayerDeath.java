package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class OnPlayerDeath {
    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        var oldPlayer = event.getOriginal();
        var newPlayer = event.getEntity();

        var registry = newPlayer.level().registryAccess().registryOrThrow(Registries.ATTRIBUTE);

        for (var attributeKey : registry.keySet()) {
            registry.getHolder(attributeKey).ifPresent(holder -> {
                var oldAttr = oldPlayer.getAttribute(holder);
                var newAttr = newPlayer.getAttribute(holder);

                if (oldAttr != null && newAttr != null) {
                    for (AttributeModifier mod : oldAttr.getModifiers()) {
                        if (mod.id().getNamespace().equals("potatospellbookstweaks")) {
                            if (newAttr.getModifier(mod.id()) == null) {
                                newAttr.addPermanentModifier(mod);
                            }
                        }
                    }
                }
            });
        }
    }
}