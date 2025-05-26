package net.potato_modding.potatospells.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import io.redspace.ironsspellbooks.entity.mobs.dead_king_boss.DeadKingBoss;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DeadKingBoss.class)
public class DeadKingMixin {

    @Overwrite
    public static AttributeSupplier.Builder prepareAttributes() {
            return LivingEntity.createLivingAttributes()
                    .add(Attributes.ATTACK_DAMAGE, 12)
                    .add(Attributes.ARMOR, 30)
                    .add(Attributes.ARMOR_TOUGHNESS, 18)
                    .add(Attributes.MAX_HEALTH, 850)
                    .add(Attributes.KNOCKBACK_RESISTANCE, .9)
                    .add(Attributes.ATTACK_KNOCKBACK, .45)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, 4)
                    .add(Attributes.FOLLOW_RANGE, 32)
                    .add(Attributes.FLYING_SPEED, .200)
                    .add(Attributes.MOVEMENT_SPEED, .175);
    }
}
