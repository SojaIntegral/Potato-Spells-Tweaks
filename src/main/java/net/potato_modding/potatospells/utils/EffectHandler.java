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
    public static void effectStackingEvent(MobEffectEvent.Added event) {
        var addedNewEffect = event.getEffectInstance();
        var getEffectEntity = event.getEntity();
        var appliedEffect = addedNewEffect.getEffect();

        if(ServerConfigs.BUFF_STACKING.get()) {
            //System.out.println("Buff system on");

            BuiltInRegistries.MOB_EFFECT.getTags().forEach(tagPair -> {
                var tagKey = tagPair.getFirst();
                var tagPath = tagKey.location().getPath();
                //System.out.println("Checking tags");

                // Only handle tags in replaced_effects/
                if (!tagPath.startsWith("effect_replacing/replaced_effects/")) return;
                //System.out.println("Folder check");

                String tagName = tagPath.substring("effect_replacing/replaced_effects/".length());
                //System.out.println("Checked tags");

                // Registers any new tags in the target folders
                var checkTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "effect_replacing/replace_with/" + tagName));
                //System.out.println("Reading Replacing");
                var blacklistTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "effect_replacing/replaced_effects/" + tagName));
                //System.out.println("Reading Replaced");
                //System.out.println("CheckTag exists: " + BuiltInRegistries.MOB_EFFECT.getTag(checkTag).isPresent());
                //System.out.println("BlacklistTag exists: " + BuiltInRegistries.MOB_EFFECT.getTag(blacklistTag).isPresent());

                // Retrieving and removing conflicting effects
                if (appliedEffect.is(checkTag)) {
                    List<MobEffectInstance> effects = List.copyOf(getEffectEntity.getActiveEffects());
                    //System.out.println("Grabbing list");
                    for (MobEffectInstance current : effects) {
                        //System.out.println("Running Blacklist");
                        if (!current.getEffect().equals(appliedEffect) && current.getEffect().is(blacklistTag)) {
                            //System.out.println("Replacing");
                            getEffectEntity.removeEffect(current.getEffect());
                            //System.out.println("Replaced");
                        }
                    }
                }
            });
        }
    }

    // Doesn't apply X effect when Y effect is active
    @SubscribeEvent
    public static void effectNotApply(MobEffectEvent.Applicable event) {
        LivingEntity buffedBlockEntity = event.getEntity();
        var notApplyingEffect = event.getEffectInstance().getEffect();
        //System.out.println("Running Blocker");

        if (ServerConfigs.BUFF_STACKING.get()) {
            //System.out.println("Blocker - Checking Config");

            BuiltInRegistries.MOB_EFFECT.getTags().forEach(tagPair -> {
                var tagKey = tagPair.getFirst();
                var tagPath = tagKey.location().getPath();
                //System.out.println("Blocker - Checking Tags");

                // Only handle tags in replaced_effects/
                if (!tagPath.startsWith("effect_not_applying/active_effects/")) return;
                //System.out.println("Blocker - Looping Lists");

                String tagName = tagPath.substring("effect_not_applying/active_effects/".length());
                //System.out.println("Blocker - Grabbing Lists");

                var blockTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks",
                                "effect_not_applying/active_effects/" + tagName));
                //System.out.println("Blocker - Registered Active");
                var checkBlockTag = TagKey.create(Registries.MOB_EFFECT,
                        ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks",
                                "effect_not_applying/cannot_apply/" + tagName));
                //System.out.println("Blocker - Registered Blacklist");

                boolean hasBlockingEffect = buffedBlockEntity.getActiveEffects()
                        .stream()
                        .anyMatch(e -> e.getEffect().is(blockTag));

                //System.out.println("Blocker - Initializing blocker");
                if (hasBlockingEffect && notApplyingEffect.is(checkBlockTag)) {
                    //System.out.println("Blocker - Running Blocker");
                    event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
                    //System.out.println("BLOCKED");
                }
            });
        }
    }
}