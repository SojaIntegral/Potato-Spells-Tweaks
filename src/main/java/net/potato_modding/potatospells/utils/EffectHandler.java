package net.potato_modding.potatospells.utils;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.bus.api.SubscribeEvent;

@SuppressWarnings("unused")
@EventBusSubscriber
public class EffectHandler {

    @SubscribeEvent
    public static void positiveBuff(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.POSITIVE_BUFFS);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.POSITIVE_BUFFS));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void positiveBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.POSITIVE_BUFFS2);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.POSITIVE_BUFFS2));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void damageBuff(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DAMAGE_BUFFS);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DAMAGE_BUFFS));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void damageBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DAMAGE_BUFFS2);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DAMAGE_BUFFS2));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void defensiveBuff(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DEFENSIVE_BUFFS);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEFENSIVE_BUFFS));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void defensiveBuff2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DEFENSIVE_BUFFS2);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEFENSIVE_BUFFS2));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void debuffBlock(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DEBUFF_BLOCK);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEBUFF_BLOCK));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void debuffBlock2(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DEBUFF_BLOCK2);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEBUFF_BLOCK2));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }

    @SubscribeEvent
    public static void debuffBlock3(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        var applyingEffect = event.getEffectInstance().getEffect().is(PotatoTags.DEBUFF_BLOCK3);

        boolean hasBlockingEffect = entity.getActiveEffects()
                .stream()
                .anyMatch(e -> e.getEffect().is(PotatoTags.DEBUFF_BLOCK3));

        if (hasBlockingEffect && applyingEffect)
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
    }
}
