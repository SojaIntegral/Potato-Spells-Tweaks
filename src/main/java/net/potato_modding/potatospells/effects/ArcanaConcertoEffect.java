package net.potato_modding.potatospells.effects;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
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

public class ArcanaConcertoEffect extends MobEffect {
    public ArcanaConcertoEffect() {
        super(MobEffectCategory.HARMFUL, 0x38daa0);
        this.addAttributeModifier(AttributeRegistry.SPELL_RESIST, PotatoSpells.id("valiance"),
                -0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ARMOR, PotatoSpells.id("valiance"),
                -0.01, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
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
