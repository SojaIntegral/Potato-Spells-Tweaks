package net.potato_modding.potatospells.effects;

import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoAttributes;

public class ManaShieldEffect extends MagicMobEffect {
    public ManaShieldEffect() {
        super(MobEffectCategory.BENEFICIAL, 5984177);
        this.addAttributeModifier(PotatoAttributes.MANA_SHIELD, PotatoSpells.id("mana_shield_effect"),
                ManaShieldEffect.MANA_SHIELD_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE);
    }

    // Base buffs, deal more damage when in water
    public static final float MANA_SHIELD_PER_LEVEL = 0.05f;

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        return super.addAttributeModifier(attribute, id, amount, operation);
    }
}
