package net.potato_modding.potatospells.utils;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Objects;

@EventBusSubscriber
public class AttributeFix {

    private static final ResourceLocation ATTR_FIX = Objects.requireNonNull(ResourceLocation.tryParse("potatospells:damage_boost"));

    @SubscribeEvent
    public static void onMobDamaged(LivingDamageEvent.Post event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Mob mob)) return;
        if (mob.getType().is(PotatoTags.NETHERITE_MONSTROSITY)) {

            AttributeInstance attackAttr = mob.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr == null) return;

            // Check if modifier is already present:
            if (attackAttr.getModifier(ATTR_FIX) == null) {
                attackAttr.addPermanentModifier(new AttributeModifier(
                        ATTR_FIX,
                        0,
                        AttributeModifier.Operation.ADD_VALUE
                ));
            }
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
