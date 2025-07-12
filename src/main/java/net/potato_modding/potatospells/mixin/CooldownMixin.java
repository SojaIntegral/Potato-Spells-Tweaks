package net.potato_modding.potatospells.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import net.potato_modding.potatospells.utils.MixinFixes;
import org.spongepowered.asm.mixin.Mixin;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraft.world.entity.player.Player;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.COOLDOWN_REDUCTION;

@SuppressWarnings("unused")
@Mixin(MagicManager.class)
public class CooldownMixin {

    @WrapMethod(method = "getEffectiveSpellCooldown")
    private static int getEffectiveSpellCooldown(AbstractSpell spell, Player player, CastSource castSource, Operation<Integer> original) {
        double playerCooldownModifier = player.getAttributeValue(COOLDOWN_REDUCTION);

        float itemCoolDownModifer = 1;
        if (castSource == CastSource.SWORD) {
            itemCoolDownModifer = ServerConfigs.SWORDS_CD_MULTIPLIER.get().floatValue();
        }
        return (int) (spell.getSpellCooldown() * (2 - MixinFixes.rebalanceFormula(playerCooldownModifier)) * itemCoolDownModifer);
    }
}