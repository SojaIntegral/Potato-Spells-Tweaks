package net.potato_modding.potatospells.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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

public class LacerateEffect extends MobEffect {
    public LacerateEffect() {
        super(MobEffectCategory.HARMFUL, 0x38daa0);
        this.addAttributeModifier(AttributeRegistry.COOLDOWN_REDUCTION, PotatoSpells.id("lacerate"),
                -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(AttributeRegistry.MANA_REGEN, PotatoSpells.id("lacerate"),
                -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, PotatoSpells.id("lacerate"),
                -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, PotatoSpells.id("lacerate"),
                -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, PotatoSpells.id("lacerate"),
                -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        return super.addAttributeModifier(attribute, id, amount, operation);
    }
}
