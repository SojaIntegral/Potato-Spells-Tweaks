package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.spells.*;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class SpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, PotatoSpells.MOD_ID);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final Supplier<AbstractSpell> MANA_SHIELD_SPELL = registerSpell(new ManaShieldSpell());
    public static final Supplier<AbstractSpell> MANA_STEAL_SPELL = registerSpell(new ManaStealSpell());
    public static final Supplier<AbstractSpell> MANA_REND_SPELL = registerSpell(new ManaRendSpell());
    public static final Supplier<AbstractSpell> MASS_RECALL = registerSpell(new MassRecallSpell());
    public static final Supplier<AbstractSpell> ELYSIUM_SPELL = registerSpell(new ElysiumSpell());
    public static final Supplier<AbstractSpell> DAUNTLESS_SPELL = registerSpell(new DauntlessSpell());
    public static final Supplier<AbstractSpell> RAVENOUS_SPELL = registerSpell(new RavenousSpell());
    public static final Supplier<AbstractSpell> PRAXIS_SPELL = registerSpell(new PraxisSpell());
    public static final Supplier<AbstractSpell> LACERATE_SPELL = registerSpell(new LacerateSpell());
    public static final Supplier<AbstractSpell> VALIANCE_SPELL = registerSpell(new ValianceSpell());
    public static final Supplier<AbstractSpell> ARCANA_CONCERTO_SPELL = registerSpell(new ArcanaConcertoSpell());

    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }
}
