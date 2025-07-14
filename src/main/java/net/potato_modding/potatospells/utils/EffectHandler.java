package net.potato_modding.potatospells.utils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.bus.api.SubscribeEvent;

@SuppressWarnings("unused")
@EventBusSubscriber
public class EffectHandler {

    // This one replaces buffs
    @SubscribeEvent
    public static void positiveBuff(MobEffectEvent.Added event) {
        MobEffectInstance addedEffect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if (effect.is(PotatoTags.POSITIVE_BUFFS)) {
            for (MobEffectInstance current : entity.getActiveEffects()) {
                if (!current.getEffect().equals(effect) && current.getEffect().is(PotatoTags.POSITIVE_BUFFS_CHECK)) {
                    entity.removeEffect(current.getEffect());
                }
            }
        }
    }

    // This one prevents buffs from applying instead of replacing
    @SubscribeEvent
    public static void positiveBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect();

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.POSITIVE_BUFFS2_CHECK));

        if (hasBlockingEffect && applyingEffect.is(PotatoTags.POSITIVE_BUFFS2)) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    // This one replaces buffs
    @SubscribeEvent
    public static void damageBuff(MobEffectEvent.Added event) {
        MobEffectInstance addedEffect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if (effect.is(PotatoTags.DAMAGE_BUFFS)) {
            for (MobEffectInstance current : entity.getActiveEffects()) {
                if (!current.getEffect().equals(effect) && current.getEffect().is(PotatoTags.DAMAGE_BUFFS_CHECK)) {
                    entity.removeEffect(current.getEffect());
                }
            }
        }
    }

    // This one prevents buffs from applying instead of replacing
    @SubscribeEvent
    public static void damageBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect();

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DAMAGE_BUFFS2_CHECK));

        if (hasBlockingEffect && applyingEffect.is(PotatoTags.DAMAGE_BUFFS2)) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    // This one replaces buffs
    @SubscribeEvent
    public static void defensiveBuff(MobEffectEvent.Added event) {
        MobEffectInstance addedEffect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if (effect.is(PotatoTags.DEFENSIVE_BUFFS)) {
            for (MobEffectInstance current : entity.getActiveEffects()) {
                if (!current.getEffect().equals(effect) && current.getEffect().is(PotatoTags.DEFENSIVE_BUFFS_CHECK)) {
                    entity.removeEffect(current.getEffect());
                }
            }
        }
    }

    // This one prevents buffs from applying instead of replacing
    @SubscribeEvent
    public static void defensiveBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect();

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEFENSIVE_BUFFS2_CHECK));

        if (hasBlockingEffect && applyingEffect.is(PotatoTags.DEFENSIVE_BUFFS2)) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    // This one replaces buffs
    @SubscribeEvent
    public static void debuffBlock(MobEffectEvent.Added event) {
        MobEffectInstance addedEffect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if (effect.is(PotatoTags.DEBUFF_BLOCK)) {
            for (MobEffectInstance current : entity.getActiveEffects()) {
                if (!current.getEffect().equals(effect) && current.getEffect().is(PotatoTags.DEBUFF_BLOCK_CHECK)) {
                    entity.removeEffect(current.getEffect());
                }
            }
        }
    }

    // This one replaces buffs
    @SubscribeEvent
    public static void debuffBlock2(MobEffectEvent.Added event) {
        MobEffectInstance addedEffect = event.getEffectInstance();
        LivingEntity entity = event.getEntity();
        var effect = addedEffect.getEffect();

        if (effect.is(PotatoTags.DEBUFF_BLOCK2)) {
            for (MobEffectInstance current : entity.getActiveEffects()) {
                if (!current.getEffect().equals(effect) && current.getEffect().is(PotatoTags.DEBUFF_BLOCK2_CHECK)) {
                    entity.removeEffect(current.getEffect());
                }
            }
        }
    }

    // This one prevents buffs from applying instead of replacing
    @SubscribeEvent
    public static void debuffBlock3(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect();

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEBUFF_BLOCK3_CHECK));

        if (hasBlockingEffect && applyingEffect.is(PotatoTags.DEBUFF_BLOCK3)) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }
}
