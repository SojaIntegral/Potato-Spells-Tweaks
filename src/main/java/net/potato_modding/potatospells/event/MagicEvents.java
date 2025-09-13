package net.potato_modding.potatospells.event;

import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.registry.PotatoDamageTypes;
import net.potato_modding.potatospells.registry.PotatoEffects;

@EventBusSubscriber
public class MagicEvents {

    @SubscribeEvent
    private static void manaBurnEvent(PlayerTickEvent.Post event) {
        var entity = event.getEntity();

        if(!entity.hasEffect(PotatoEffects.MANA_BURN_EFFECT)) return;

        if(entity.isCreative()) return;
        if(entity.isSpectator()) return;

        int tickCount = entity.tickCount;
        boolean doManaBurn = tickCount % 20 == 0;
        var grabMana = MagicData.getPlayerMagicData(entity);
        float manaBurned = (float)Math.min(entity.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.025f, grabMana.getMana());

        if(doManaBurn && grabMana.getMana() > 0) {
            grabMana.setMana(grabMana.getMana() - manaBurned);
        }
    }

    private static DamageSource damageSource;

    @SubscribeEvent
    private static void acheronEvent(PlayerTickEvent.Post event) {
        var entity = event.getEntity();

        if(!entity.hasEffect(PotatoEffects.ACHERON_EFFECT)) return;

        if(entity.isCreative()) return;
        if(entity.isSpectator()) return;
        if(entity.level().isClientSide()) return;

        int tickCount = entity.tickCount;
        boolean doBurn = tickCount % 35 == 0;
        var grabHealth = entity.getHealth();

        double damage = (1 + entity.getAttributeValue(PotatoAttributes.SLAYER)) * entity.getAttributeValue(AttributeRegistry.SPELL_POWER) * (1 - entity.getAttributeValue(PotatoAttributes.RESISTANCE));

        float healthBurned = (float)Math.min(damage, grabHealth);

        if (damageSource == null) {
            damageSource = new DamageSource(DamageSources.getHolderFromResource(entity, PotatoDamageTypes.ACHERON_DAMAGE));
        }

        if(doBurn && grabHealth > 0) {
            entity.hurt(damageSource, healthBurned);
        }
    }
}
