package net.potato_modding.potatospells.attributes;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.potato_modding.potatospells.registry.PotatoAttributes;

@EventBusSubscriber
public class SlayerAttribute {

    @SubscribeEvent
    public static void slayerAttributeEvent(LivingIncomingDamageEvent event) {
        var target = event.getEntity();
        var source = event.getSource().getEntity();

        if(!(source instanceof LivingEntity attacker)) return;

        var bossSlayer = attacker.getAttribute(PotatoAttributes.BOSS_SLAYER);
        var monsterSlayer = attacker.getAttribute(PotatoAttributes.MONSTER_SLAYER);
        var playerSlayer = attacker.getAttribute(PotatoAttributes.PLAYER_SLAYER);

        double bossBonus = 0;
        double monsterBonus = 0;
        double playerBonus = 0;

        if(bossSlayer != null) bossBonus = bossSlayer.getValue();
        if(monsterSlayer != null) monsterBonus = monsterSlayer.getValue();
        if(playerSlayer != null) playerBonus = playerSlayer.getValue();

        double mod;
        var targetTag = target.getType();

        if(targetTag.is(PotatoTags.NORMAL)) mod = 1 + monsterBonus;
        else if(targetTag.is(PotatoTags.PLAYER)) mod = 1 + playerBonus;
        else if(targetTag.is(PotatoTags.SUMMON)) mod = 1 + (monsterBonus / 2) + (playerBonus / 2);
        else if(targetTag.is(PotatoTags.MINIBOSS)) mod = 1 + (bossBonus / 2) + (monsterBonus / 2);
        else if(targetTag.is(PotatoTags.BOSS)) mod = 1 + bossBonus + (monsterBonus / 4);
        else mod = 1 + monsterBonus;

        event.setAmount((float) (event.getAmount() * mod));
    }
}
