package net.potato_modding.potatospells.compat.IronsSpellbooks;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

import java.util.Objects;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Summon_Attributes {

    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

        // Give summoned mobs some spell resistance so they don't get nuked
        // Spell resistance depends on the spell resist formula
        if (mob.getType().is(PotatoTags.SUMMONS)) {
            if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "3")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 4.25);

            } else if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "2")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 2.35);

            } else if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "1")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1.4);

            } else {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1.55);
            }
        }
    }

    // Actually sets the attributes
    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }
}