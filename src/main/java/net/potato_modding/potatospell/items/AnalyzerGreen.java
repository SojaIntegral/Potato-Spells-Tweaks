package net.potato_modding.potatospell.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.aces_spell_utils.items.curios.ImbuableCurio;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.potato_modding.potatospell.client.Keybinds;
import net.potato_modding.potatospell.registry.PotatoAttributes;
import net.potato_modding.potatospell.registry.SpellRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AnalyzerGreen extends ImbuableCurio {

    public AnalyzerGreen() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.EPIC),
                "head", SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistries.MANA_SHIELD_SPELL, 1)
                ));
    }

    @Override
    @net.neoforged.api.distmarker.OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        double attributeValue = 8 + 16 * (getAttr(mc.player, Attributes.ARMOR) + getAttr(mc.player, Attributes.ARMOR_TOUGHNESS)) / 6;
        double curioModifier = BigDecimal.valueOf(attributeValue).setScale(0, RoundingMode.HALF_UP).doubleValue();

        double currentMana = getAttr(mc.player, AttributeRegistry.MAX_MANA);
        double spellPower = getAttr(mc.player, AttributeRegistry.SPELL_POWER);
        double manaShield = getAttr(mc.player, PotatoAttributes.MANA_SHIELD) - 1;
        double rawShield = BigDecimal.valueOf((manaShield * currentMana * spellPower) / 35).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double shieldStrength = BigDecimal.valueOf(Math.min(rawShield, 90)).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double manaCost = BigDecimal.valueOf(10 * (1 - (Math.min(rawShield, 90) / 100))).setScale(2, RoundingMode.HALF_UP).doubleValue();

        tooltip.add(Component.literal("Range: " + curioModifier + " blocks | Press [")
                .append(Keybinds.OPEN_SCREEN_KEY.getTranslatedKeyMessage()).append("] to analyze")
                .withStyle(ChatFormatting.GREEN));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("Mana Shield:")
                .withStyle(ChatFormatting.GREEN));
        tooltip.add(Component.literal("Damage is reduced by the user's mana")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Current amount: [" + shieldStrength + "]%")
                .withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.literal("Cost: [" + manaCost + "] mana per damage reduced")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.SPELL_RESIST, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(Attributes.ARMOR, new AttributeModifier(id, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        attr.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return attr;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(ChatFormatting.DARK_GREEN);
    }

    public static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui_green.png");
}
