package net.potato_modding.potatospells.mixin;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.mobs.keeper.KeeperEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(KeeperEntity.class)
public class CitadelKeeperMixin {

    @Overwrite
    public static AttributeSupplier.Builder prepareAttributes() {
            return Monster.createMonsterAttributes()
                    .add(Attributes.ATTACK_DAMAGE, 13)
                    .add(Attributes.MAX_HEALTH, 90)
                    .add(Attributes.ARMOR, 12)
                    .add(Attributes.ARMOR_TOUGHNESS, 10)
                    .add(AttributeRegistry.SPELL_RESIST, 2.0)
                    .add(Attributes.FOLLOW_RANGE, 32)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                    .add(Attributes.ATTACK_KNOCKBACK, 1.5)
                    .add(Attributes.STEP_HEIGHT, 1.4)
                    .add(Attributes.ENTITY_INTERACTION_RANGE, 3.5)
                    .add(Attributes.MOVEMENT_SPEED, .220);
    }
}
