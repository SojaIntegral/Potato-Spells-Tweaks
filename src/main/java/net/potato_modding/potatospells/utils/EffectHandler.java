package net.potato_modding.potatospells.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.potato_modding.potatospells.config.ServerConfigs;

import java.util.List;

@SuppressWarnings("unused")
@EventBusSubscriber
public class EffectHandler {

    // Replaces X effect (currently active) for Y
    @SubscribeEvent
    public static void effectStackingHandler(MobEffectEvent.Added event) {
        var addedEffect = event.getEffectInstance();
        var entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if(ServerConfigs.BUFF_STACKING.get()) {

            BuiltInRegistries.MOB_EFFECT.getTags().forEach(tagPair -> {
                var tagKey = tagPair.getFirst();
                var tagPath = tagKey.location().getPath();

                // Only handle tags in replaced_effects/
                if (!tagPath.startsWith("effect_replacing/replaced_effects/")) return;

                String tagName = tagPath.substring("effect_replacing/replaced_effects/".length());

                // Registers any new tags in the target folders
                var checkTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "effect_replacing/replace_with/" + tagName));
                var blacklistTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "effect_replacing/replaced_effects/" + tagName));

                // Retrieving and removing conflicting effects
                if (effect.is(checkTag)) {
                    List<MobEffectInstance> effects = List.copyOf(entity.getActiveEffects());
                    for (MobEffectInstance current : effects) {
                        if (!current.getEffect().equals(effect) && current.getEffect().is(blacklistTag)) {
                            entity.removeEffect(current.getEffect());
                        }
                    }
                }
            });
        }
    }

    // Doesn't apply X effect when Y effect is active
    @SubscribeEvent
    public static void effectNotApply(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect();

        if (ServerConfigs.BUFF_STACKING.get()) {

            BuiltInRegistries.MOB_EFFECT.getTags().forEach(tagPair -> {
                var tagKey = tagPair.getFirst();
                var tagPath = tagKey.location().getPath();

                // Only handle tags in replaced_effects/
                if (!tagPath.startsWith("effect_not_applying/active_effects/")) return;

                String tagName = tagPath.substring("effect_not_applying/active_effects/".length());

                var checkTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks",
                                "effect_not_applying/active_effects/" + tagName));
                var blockTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks",
                                "effect_not_applying/cannot_apply/" + tagName));

                boolean hasBlockingEffect = entity.getActiveEffects()
                        .stream()
                        .anyMatch(e -> e.getEffect().is(blockTag));

                if (hasBlockingEffect && applyingEffect.is(checkTag)) {
                    event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
                }
            });
        }
    }
}