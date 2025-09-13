package net.potato_modding.potatospells.attributes;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.registry.PotatoEffects;

@EventBusSubscriber
public class SlayerAttribute {

    private static double bossBonus = 0;
    private static double monsterBonus = 0;
    private static double playerBonus = 0;
    private static double slayer = 0;

    @SubscribeEvent
    public static void slayerAttributeEvent(LivingIncomingDamageEvent event) {
        var target = event.getEntity();
        var source = event.getSource().getEntity();

        if(!(source instanceof LivingEntity attacker)) return;

        var bossSlayer = attacker.getAttribute(PotatoAttributes.BOSS_SLAYER);
        var monsterSlayer = attacker.getAttribute(PotatoAttributes.MONSTER_SLAYER);
        var playerSlayer = attacker.getAttribute(PotatoAttributes.PLAYER_SLAYER);
        slayer = (attacker.getAttribute(PotatoAttributes.SLAYER) == null) ?
                0 : attacker.getAttribute(PotatoAttributes.SLAYER).getValue();

        if(bossSlayer != null) bossBonus = bossSlayer.getValue();
        if(monsterSlayer != null) monsterBonus = monsterSlayer.getValue();
        if(playerSlayer != null) playerBonus = playerSlayer.getValue();

        double mod = getMod(target);
        double resist = Math.max(1 - target.getAttributeValue(PotatoAttributes.RESISTANCE), 0.2);

        double ravenous = 1;
        if(attacker.hasEffect(PotatoEffects.RAVENOUS_EFFECT)) {
            double maxHealth = target.getMaxHealth();
            double healthCap = maxHealth * 0.1;
            double currentHealth = target.getHealth();
            double minHealth = Math.max(currentHealth, healthCap);
            ravenous = 1 + (maxHealth / minHealth) * 0.06;
        }

        if(!attacker.hasEffect(PotatoEffects.SPINNING_DRAGON)) {
            event.setAmount((float) (event.getOriginalAmount() * mod * ravenous * resist));
        }
        else {
            event.setAmount(0.0f);
            attacker.removeEffect(PotatoEffects.SPINNING_DRAGON);
        }
    }

    private static double getMod(LivingEntity target) {
        var targetTag = target.getType();

        double preMod = 0;
        if(targetTag.is(PotatoTags.PLAYER)) preMod = 1 + playerBonus + slayer;
        else if(targetTag.is(PotatoTags.SUMMON)) preMod = 1 + (monsterBonus * 0.66) + (playerBonus * 0.34) + slayer;
        else if(targetTag.is(PotatoTags.MINIBOSS)) preMod = 1 + (bossBonus * 0.75) + (monsterBonus * 0.25) + slayer;
        else if(targetTag.is(PotatoTags.BOSS)) preMod = 1 + bossBonus + slayer;
        else preMod = 1 + monsterBonus + slayer;
        return preMod;
    }
}
