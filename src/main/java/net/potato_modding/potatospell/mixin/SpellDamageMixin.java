package net.potato_modding.potatospell.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.entity.NoKnockbackProjectile;
import io.redspace.ironsspellbooks.api.events.SpellDamageEvent;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;
import net.potato_modding.potatospell.registry.PotatoAttributes;
import net.potato_modding.potatospell.utils.RebalanceHandler;
import org.spongepowered.asm.mixin.Mixin;

import static io.redspace.ironsspellbooks.damage.DamageSources.*;

@SuppressWarnings("unused")
@Mixin(DamageSources.class)
public class SpellDamageMixin {

    @WrapMethod(method = "applyDamage")
    private static boolean applyDamage(Entity target, float baseAmount, DamageSource damageSource, Operation<Integer> original) {
        if (target instanceof LivingEntity livingTarget && damageSource instanceof SpellDamageSource spellDamageSource) {
            var e = new SpellDamageEvent(livingTarget, baseAmount, spellDamageSource);

            Class<?> clazz = spellDamageSource.getClass();
            try {
                var f = clazz.getDeclaredField("spell");
                AbstractSpell spell = (AbstractSpell) f.get(spellDamageSource);
            }
            catch (NoSuchFieldException | IllegalAccessException exception) {
                throw new RuntimeException(exception);
            }

            if (NeoForge.EVENT_BUS.post(e).isCanceled()) {
                return false;
            }
            baseAmount = e.getAmount();
            Entity caster = damageSource.getEntity();
            if(!(caster instanceof LivingEntity livingCaster)) return false;
            double piercingAttr;
            double piercedAmount;
            double spellShred = 0;
            double spellPierce = 0;
            double resistPierce = livingCaster.getAttributeValue(PotatoAttributes.SPELL_RESIST_PIERCE) + livingCaster.getAttributeValue(PotatoAttributes.SPELL_RESIST_SHRED);
            if(livingCaster instanceof Player player) {
                piercingAttr = livingCaster.getAttributeValue(PotatoAttributes.SPELL_RESIST_SHRED);
                piercedAmount = getResist(livingTarget, spellDamageSource.spell().getSchoolType()) - 1;
                if(piercedAmount <= 0) piercingAttr = 0;

                spellShred = Math.abs(piercedAmount * piercingAttr);

                spellPierce = RebalanceHandler.rebalanceFormula(resistPierce) - RebalanceHandler.rebalanceFormula(0);
            }
            float adjustedDamage = (float)(baseAmount * (getResist(livingTarget, spellDamageSource.spell().getSchoolType()) + spellShred + spellPierce));
            if (damageSource.getDirectEntity() instanceof NoKnockbackProjectile) {
                ignoreNextKnockback(livingTarget);
            }
            if (damageSource.getEntity() instanceof LivingEntity livingAttacker) {
                if (isFriendlyFireBetween(livingAttacker, livingTarget)) {
                    return false;
                }
                livingAttacker.setLastHurtMob(target);
            }
            return livingTarget.hurt(damageSource, adjustedDamage);
        }
        else {
            return target.hurt(damageSource, baseAmount);
        }
    }
}
