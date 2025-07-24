package net.potato_modding.potatospells.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.potato_modding.potatospells.client.ClientScreens;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;
import top.theillusivec4.curios.api.SlotContext;


public class MobIdentifier extends CurioBaseItem {

    public MobIdentifier() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(Rarity.EPIC));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(ALObjects.Attributes.CRIT_CHANCE, new AttributeModifier(id, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attr.put(AttributeRegistry.SPELL_POWER, new AttributeModifier(id, 0.05, AttributeModifier.Operation.ADD_VALUE));
        return attr;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (player.level().isClientSide) {
            if (player.isShiftKeyDown()) {
                target = player; // View self
            }

            float targetHealth = target.getMaxHealth();
            double targetArmor = getAttr(target, Attributes.ARMOR);
            double targetAttack = getAttr(target, Attributes.ATTACK_DAMAGE);
            double targetMana = getAttr(target, AttributeRegistry.MAX_MANA);
            double targetResist = getAttr(target, AttributeRegistry.SPELL_RESIST);
            double targetSpell = getAttr(target, AttributeRegistry.SPELL_POWER);
            double targetCast = getAttr(target, AttributeRegistry.CAST_TIME_REDUCTION);
            double targetCooldown = getAttr(target, AttributeRegistry.COOLDOWN_REDUCTION);
            double targetCrit = getAttr(target, ALObjects.Attributes.CRIT_DAMAGE);
            double targetCritChance = getAttr(target, ALObjects.Attributes.CRIT_CHANCE);
            double targetPierce1 = getAttr(target, ALObjects.Attributes.ARMOR_PIERCE);
            double targetShred1 = getAttr(target, ALObjects.Attributes.ARMOR_SHRED);
            double targetPierce2 = getAttr(target, ALObjects.Attributes.PROT_PIERCE);
            double targetShred2 = getAttr(target, ALObjects.Attributes.PROT_SHRED);

            double firstIV = getAttr(target, PotatoAttributes.ATTACK_IV);
            double secondIV = getAttr(target, PotatoAttributes.ARMOR_IV);
            double thirdIV = getAttr(target, PotatoAttributes.POWER_IV);
            double fourthIV = getAttr(target, PotatoAttributes.RESIST_IV);
            double fifthIV = getAttr(target, PotatoAttributes.CAST_IV);
            double sixthIV = getAttr(target, PotatoAttributes.ARMOR_PEN_IV);
            double seventhIV = getAttr(target, PotatoAttributes.PROT_PEN_IV);
            double eighthIV = getAttr(target, PotatoAttributes.CRIT_IV);

            // Call the client screen opener
            ClientScreens.openMobInteractionScreen(
                    target.getName().getString(), target,
                    targetHealth, targetArmor, targetAttack, targetMana, targetResist,
                    targetSpell, targetCast, targetCooldown, targetCrit, targetCritChance, targetPierce1,
                    targetShred1, targetPierce2, targetShred2,
                    firstIV, secondIV, thirdIV, fourthIV, fifthIV,
                    sixthIV, seventhIV, eighthIV
            );
        }
        return InteractionResult.SUCCESS;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }
}
