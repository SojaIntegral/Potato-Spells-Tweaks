package net.potato_modding.potatospells.items;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.item.UniqueSpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatospells.registry.SpellRegistries;

public class DustyBook extends UniqueSpellBook {
    public DustyBook() {
        super(SpellDataRegistryHolder.of(
                new SpellDataRegistryHolder(SpellRegistries.MASS_RECALL, 1)
        ), 5);
        withSpellbookAttributes(
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, 0.1F, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.1F, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.1F, AttributeModifier.Operation.ADD_VALUE)
        );
    }
}