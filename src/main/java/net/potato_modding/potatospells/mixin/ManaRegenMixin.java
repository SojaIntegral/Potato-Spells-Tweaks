package net.potato_modding.potatospells.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nullable;

@Mixin(AbstractSpell.class)
public class ManaRegenMixin extends AbstractSpell {

    @Override
    public ResourceLocation getSpellResource() {
        return null;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return null;
    }

    @Override
    public CastType getCastType() {
        return null;
    }

    @WrapMethod(method = "getManaCost")
    public int getManaCost(int level, @Nullable Entity sourceEntity) {
        double manaCost = (sourceEntity instanceof Player player) ? player.getAttributeValue(PotatoAttributes.MANA_COST) : 1;
        return (int) ((baseManaCost + manaCostPerLevel * (level - 1))
                * ServerConfigs.getSpellConfig(this).manaMultiplier() * manaCost);
    }
}
