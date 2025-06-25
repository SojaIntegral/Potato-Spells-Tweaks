package net.potato_modding.potatospells.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.ChargeEffect;
import io.redspace.ironsspellbooks.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

@EventBusSubscriber
public class ServerEvents {
    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event)
    {
        //System.out.println("Event");
        var mob = event.getEntity();

        if (mob.getType().is(PotatoTags.SPELL_RES_ENTITIES))
        {
            // Any mob added here has more spell res
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1.5);

            //System.out.println("Mobs: " + PotatoTags.SPELL_RES_ENTITIES.toString());
            //System.out.println("Attributes: " + mob.getAttributes().toString());
        }
    }

    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }

    @SubscribeEvent
    public static void onEffectGainEvent(MobEffectEvent.Added event)
    {
        // Control this through a config
        if (ServerConfigs.MUTUAL_EFFECTS.get())
        {
            Entity entity = event.getEntity();
            MobEffect mobEffect = event.getEffectInstance().getEffect().value();

            if (entity instanceof LivingEntity livingEntity)
            {
                if (mobEffect instanceof ChargeEffect)
                {
                    // I'm hard coding this for now cause I need extra time to figure out tags for this
                    livingEntity.removeEffect(MobEffects.DAMAGE_BOOST);
                }
            }
        }
    }
}
