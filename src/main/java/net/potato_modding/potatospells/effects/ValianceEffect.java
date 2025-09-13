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
import net.potato_modding.potatospells.registry.PotatoAttributes;

public class ValianceEffect extends MobEffect {
    public ValianceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x38daa0);
        this.addAttributeModifier(AttributeRegistry.SPELL_POWER, PotatoSpells.id("valiance"),
                0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, PotatoSpells.id("valiance"),
                0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.MAX_HEALTH, PotatoSpells.id("valiance"),
                0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, PotatoSpells.id("valiance"),
                0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(ALObjects.Attributes.CRIT_CHANCE, PotatoSpells.id("valiance"),
                0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(ALObjects.Attributes.CRIT_DAMAGE, PotatoSpells.id("valiance"),
                0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
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
