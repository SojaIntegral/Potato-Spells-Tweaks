package net.potato_modding.potatospells.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
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
}
