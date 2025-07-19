package net.potato_modding.potatospells.screen;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AttributesGUI extends Item {

    public AttributesGUI(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if(player.isShiftKeyDown()) {
            target = player;
        }
        if (player.level().isClientSide) {
            float targetHealth = target.getMaxHealth();
            double targetArmor = 0;
            double targetAttack = 0;
            double targetMana = 0;
            double targetResist = 0;
            double targetSpell = 0;
            double targetCast = 0;
            double targetCrit = 0;
            double targetPierce1 = 0;
            double targetPierce2 = 0;

            var armorAttr = target.getAttribute(Attributes.ARMOR);
            if (armorAttr != null) targetArmor = armorAttr.getValue();

            var attackAttr = target.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null) targetAttack = attackAttr.getValue();

            var manaAttr = target.getAttribute(AttributeRegistry.MAX_MANA);
            if(manaAttr != null) targetMana = manaAttr.getValue();

            var resAttr = target.getAttribute(AttributeRegistry.SPELL_RESIST);
            if(resAttr != null) targetResist = resAttr.getValue();

            var spellAttr = target.getAttribute(AttributeRegistry.SPELL_POWER);
            if(spellAttr != null) targetSpell = spellAttr.getValue();

            Minecraft.getInstance().setScreen(new MobInteractionScreen(target.getName().getString(), target,
                    targetHealth, targetArmor, targetAttack, targetMana, targetResist,
                    targetSpell, targetCast, targetCrit, targetPierce1, targetPierce2));
        }
        return InteractionResult.SUCCESS;
    }
}
