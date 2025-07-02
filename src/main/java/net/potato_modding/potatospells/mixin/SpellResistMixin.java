package net.potato_modding.potatospells.mixin;

import net.potato_modding.potatospells.utils.PotatoUtils;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DamageSources.class)
public class SpellResistMixin {

    @WrapMethod(method = "getResist")
    private static float getResist(LivingEntity entity, SchoolType damageSchool, Operation<Float> original) {
        var baseResist = entity.getAttributeValue(AttributeRegistry.SPELL_RESIST);

        if (damageSchool == null)
            return 2 - (float) PotatoUtils.resistsoftcap(baseResist);
        else
            return 2 - (float) PotatoUtils.resistsoftcap(damageSchool.getResistanceFor(entity) * baseResist);
    }
}
