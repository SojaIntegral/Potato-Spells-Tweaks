package net.potato_modding.potatospells.effects;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatospells.PotatoSpells;

public class ManaShieldEffect extends MobEffect {
    public ManaShieldEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xbaf7ff);
        this.addAttributeModifier(PotatoEssentialsAttributes.MANA_SHIELD, PotatoSpells.id("mana_shield_effect"),
                ManaShieldEffect.MANA_SHIELD_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    public static final float MANA_SHIELD_PER_LEVEL = 0.01f;

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        return super.addAttributeModifier(attribute, id, amount, operation);
    }
}
