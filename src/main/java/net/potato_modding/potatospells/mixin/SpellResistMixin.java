package net.potato_modding.potatospells.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.potato_modding.potatospells.utils.RebalanceHandler;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(DamageSources.class)
public class SpellResistMixin {

    @WrapMethod(method = "getResist")
    private static float getResist(LivingEntity entity, SchoolType damageSchool, Operation<Integer> original) {
        var baseResist = entity.getAttributeValue(AttributeRegistry.SPELL_RESIST);

        if (damageSchool == null) {
            return 2 - (float) RebalanceHandler.rebalanceFormula(baseResist);
        }
        else {
            return (float) ((2 - RebalanceHandler.rebalanceFormula(baseResist)) * (2 - RebalanceHandler.rebalanceFormula(damageSchool.getResistanceFor(entity))));
        }
    }
}