package net.potato_modding.potatospells.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.effects.ManaShieldEffect;
import net.potato_modding.potatospells.effects.ManaStealEffect;

public class PotatoEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, PotatoSpells.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> MANA_SHIELD =
            MOB_EFFECTS.register("mana_shield", ManaShieldEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MANA_STEAL =
            MOB_EFFECTS.register("mana_steal", ManaStealEffect::new);

    public static void register(IEventBus eventBus)
    {
        MOB_EFFECTS.register(eventBus);
    }
}
