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
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoAttributes;

public class AcheronEffect extends MobEffect {
    public AcheronEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x38daa0);
        this.addAttributeModifier(PotatoAttributes.SLAYER, PotatoSpells.id("acheron"),
                0.3, AttributeModifier.Operation.ADD_VALUE);
        this.addAttributeModifier(ALObjects.Attributes.ARMOR_PIERCE, PotatoSpells.id("acheron"),
                2.5, AttributeModifier.Operation.ADD_VALUE);
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
