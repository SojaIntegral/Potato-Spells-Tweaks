package net.potato_modding.potatospells.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.effects.*;
import net.potato_modding.potatospells.spells.ArcanaConcertoSpell;

public class PotatoEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, PotatoSpells.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> MANA_SHIELD = MOB_EFFECTS.register("mana_shield", ManaShieldEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MANA_STEAL = MOB_EFFECTS.register("mana_steal", ManaStealEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MANA_REND = MOB_EFFECTS.register("mana_rend", ManaRendEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> ELYSIUM_EFFECT = MOB_EFFECTS.register("elysium", ElysiumEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> DAUNTLESS_EFFECT = MOB_EFFECTS.register("dauntless", DauntlessEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MANA_BURN_EFFECT = MOB_EFFECTS.register("mana_burn", ManaBurnEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> ACHERON_EFFECT = MOB_EFFECTS.register("acheron", AcheronEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> RAVENOUS_EFFECT = MOB_EFFECTS.register("ravenous", RavenousEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> RAVENOUS_DEBUFF = MOB_EFFECTS.register("hunger", RavenousDebuff::new);
    public static final DeferredHolder<MobEffect, MobEffect> PRAXIS_EFFECT = MOB_EFFECTS.register("praxis", PraxisEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SPINNING_DRAGON = MOB_EFFECTS.register("spinning_dragon", SDragonEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> LACERATE_EFFECT = MOB_EFFECTS.register("lacerate", LacerateEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> VALIANCE_EFFECT = MOB_EFFECTS.register("valiance", ValianceEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> ARCANA_CONCERTO_EFFECT = MOB_EFFECTS.register("arcana_concerto", ArcanaConcertoEffect::new);

    public static void register(IEventBus eventBus)
    {
        MOB_EFFECTS.register(eventBus);
    }
}
