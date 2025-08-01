package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.spells.ManaShieldSpell;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class SpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, PotatoSpells.MOD_ID);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final Supplier<AbstractSpell> MANA_SHIELD_SPELL = registerSpell(new ManaShieldSpell());

    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
