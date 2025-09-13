package net.potato_modding.potatospells.effects;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatospells.PotatoSpells;

public class ManaBurnEffect extends MobEffect {
    public ManaBurnEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x38daa0);
        this.addAttributeModifier(AttributeRegistry.MANA_REGEN, PotatoSpells.id("mana_burn"),
                -100, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    public static final float MANA_BURN_PER_LEVEL = 0.01f;

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }

    @Override
    public MobEffect addAttributeModifier(Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        return super.addAttributeModifier(attribute, id, amount, operation);
    }
}
