package net.potato_modding.potatospells.effects;

import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatospells.PotatoSpells;

public class ManaStealEffect extends MobEffect {
    public ManaStealEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xbaf7ff);
        this.addAttributeModifier(ASAttributeRegistry.MANA_STEAL, PotatoSpells.id("mana_steal_effect"),
                ManaStealEffect.MANA_STEAL_PER_LEVEL, AttributeModifier.Operation.ADD_VALUE);
    }

    public static final float MANA_STEAL_PER_LEVEL = 0.01f;

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        return super.addAttributeModifier(attribute, id, amount, operation);
    }

    public static float getReductionAmount(int level) {
        return MANA_STEAL_PER_LEVEL * level;
    }
}
