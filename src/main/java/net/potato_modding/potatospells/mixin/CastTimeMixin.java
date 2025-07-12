package net.potato_modding.potatospells.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.potato_modding.potatospells.utils.MixinFixes;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(Utils.class)
public class CastTimeMixin {

    @WrapMethod(method = "softCapFormula")
    private static double SoftCapFormula(double x, Operation<Double> original) {
        return (float) MixinFixes.rebalanceFormula(x);
    }

}
