package net.potato_modding.potatospells.attributes;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatospells.registry.PotatoAttributes;

@EventBusSubscriber
public class ManaShield {

    @SubscribeEvent
    public static void ManaShieldAttribute(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        float original = event.getAmount();
        DamageContainer container = event.getContainer();

        var playerMana = MagicData.getPlayerMagicData(player);
        double currentMana = playerMana.getMana();
        double spellPower = player.getAttributeValue(AttributeRegistry.SPELL_POWER);
        double manaShield = player.getAttributeValue(PotatoAttributes.MANA_SHIELD) - 1;

        if ((player.getAttributeValue(PotatoAttributes.MANA_SHIELD) <= 0) || (playerMana.getMana() <= 0)) return;

        float reduction = (float) (Math.min(manaShield * (currentMana / 1000) * spellPower, 0.9));
        container.addModifier(DamageContainer.Reduction.ARMOR,
                (ct, base) -> reduction);

        event.setAmount(original * (1 - reduction));
        float cost = (float) (currentMana - ((original / (1 - reduction)) * 3));
        playerMana.setMana(cost);
        System.out.println(reduction);
        System.out.println(event.getAmount());
        System.out.println(cost);
    }
}
