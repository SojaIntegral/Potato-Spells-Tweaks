package net.potato_modding.potatospell.attributes;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatospell.registry.PotatoAttributes;

@EventBusSubscriber
public class ManaShield {

    @SubscribeEvent
    public static void manaShieldEvent(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        float original = event.getAmount();
        DamageContainer container = event.getContainer();

        var playerMana = MagicData.getPlayerMagicData(player);
        double currentMana = playerMana.getMana();
        double spellPower = player.getAttributeValue(AttributeRegistry.SPELL_POWER);
        double manaShield = player.getAttributeValue(PotatoAttributes.MANA_SHIELD) - 1;

        if ((player.getAttributeValue(PotatoAttributes.MANA_SHIELD) <= 0) || (currentMana <= 0)) return;
        if (player.isBlocking() && !event.getSource().is(DamageTypeTags.BYPASSES_SHIELD)) return;

        float reduction = (float) (Math.min(manaShield * (currentMana / 3500) * spellPower, 0.9));
        float cost = (float) (currentMana - ((original / (1 - reduction)) * 5));
        playerMana.setMana(cost);
        container.addModifier(DamageContainer.Reduction.MOB_EFFECTS,
                (ct, base) -> reduction);

        event.setAmount(original * (1 - reduction));
    }
}



