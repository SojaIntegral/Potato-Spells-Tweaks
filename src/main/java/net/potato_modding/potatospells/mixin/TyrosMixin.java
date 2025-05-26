package net.potato_modding.potatospells.mixin;

import io.redspace.ironsspellbooks.entity.mobs.wizards.fire_boss.FireBossEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FireBossEntity.class)
public class TyrosMixin {

    @Overwrite
    public static AttributeSupplier.Builder prepareAttributes() {
            return LivingEntity.createLivingAttributes()
                    .add(Attributes.ATTACK_DAMAGE, 12.0)
                    .add(Attributes.ARMOR, 30)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 0.7)
                    .add(Attributes.ATTACK_KNOCKBACK, .4)
                    .add(Attributes.FOLLOW_RANGE, 48.0)
                    .add(Attributes.SCALE, 1.6)
                    .add(Attributes.GRAVITY, 0.03)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, 3.0)
                    .add(Attributes.STEP_HEIGHT, 1.5)
                    .add(Attributes.MOVEMENT_SPEED, .25);
    }
}
