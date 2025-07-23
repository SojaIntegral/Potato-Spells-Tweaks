package net.potato_modding.potatospells.utils;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.potato_modding.potatospells.tags.PotatoTags;

import java.util.Objects;

@SuppressWarnings("unused")
@EventBusSubscriber
public class BurningFix {

    // Fixes entities burning while immune to fire
    // Works by checking if they got fire resistance and setting burning time to zero and clearing their fire

    @SubscribeEvent
    public static void fireResistanceDoesNotBurn(MobEffectEvent.Added event) {
        var mob = event.getEntity();
        if (mob.getType().is(PotatoTags.PLAYER)) {
            if (event.getEffectInstance().getEffect().equals(MobEffects.FIRE_RESISTANCE) && mob.isOnFire()) {
                setIfNonNull(mob, 0);
                event.getEntity().clearFire();
            }
        }
    }

    @SubscribeEvent
    public static void fireResistanceBurnRemove(MobEffectEvent.Remove event) {
        var mob = event.getEntity();
        if (mob.getType().is(PotatoTags.PLAYER)) {
            double burn = Objects.requireNonNull(mob.getAttribute(Attributes.BURNING_TIME)).getValue();
            assert event.getEffectInstance() != null;
            if (event.getEffectInstance().getEffect().equals(MobEffects.FIRE_RESISTANCE)) {
                if (!mob.fireImmune()) {
                    setIfNonNull(mob, 1);
                } else if ((mob.fireImmune()) && burn > 0 && mob.isOnFire()) {
                    setIfNonNull(mob, 0);
                    event.getEntity().clearFire();
                }
            }
        }
    }

    @SubscribeEvent
    public static void fireResistanceBurnExpire(MobEffectEvent.Expired event) {
        var mob = event.getEntity();
        if (mob.getType().is(PotatoTags.PLAYER)) {
            double burn = Objects.requireNonNull(mob.getAttribute(Attributes.BURNING_TIME)).getValue();
            assert event.getEffectInstance() != null;
            if (event.getEffectInstance().getEffect().equals(MobEffects.FIRE_RESISTANCE)) {
                if (!mob.fireImmune()) {
                    setIfNonNull(mob, 1);
                } else if ((mob.fireImmune()) && burn > 0 && mob.isOnFire()) {
                    setIfNonNull(mob, 0);
                    event.getEntity().clearFire();
                }
            }
        }
    }

    private static void setIfNonNull(LivingEntity mob, double value) {
        var instance = mob.getAttributes().getInstance(Attributes.BURNING_TIME);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }
}
