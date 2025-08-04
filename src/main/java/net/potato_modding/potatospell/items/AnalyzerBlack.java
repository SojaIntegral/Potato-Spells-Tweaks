package net.potato_modding.potatospell.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.potato_modding.potatospell.client.Keybinds;
import net.potato_modding.potatospell.registry.PotatoAttributes;
import top.theillusivec4.curios.api.SlotContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AnalyzerBlack extends CurioBaseItem {

    public AnalyzerBlack() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    @net.neoforged.api.distmarker.OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        double modifier = (1 + getAttr(mc.player, PotatoAttributes.SPELL_RESIST_PIERCE)) * (1 + getAttr(mc.player, PotatoAttributes.SPELL_RESIST_SHRED));
        double attributeValue = 8 + 16 * modifier * 1.25;
        double curioModifier = BigDecimal.valueOf(attributeValue).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double resistShred = BigDecimal.valueOf(mc.player.getAttributeValue(PotatoAttributes.SPELL_RESIST_SHRED) * 100).setScale(0, RoundingMode.HALF_UP).doubleValue();

        tooltip.add(Component.literal("Range: " + curioModifier + " blocks | Press [")
                .append(Keybinds.OPEN_SCREEN_KEY.getTranslatedKeyMessage()).append("] to analyze")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("Spell Resistance Shred:")
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("Ignores [" + resistShred + "]% of Spell Resistance")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.SPELL_RESIST, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(PotatoAttributes.SPELL_RESIST_SHRED, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_VALUE));
        attr.put(PotatoAttributes.SPELL_RESIST_PIERCE, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_VALUE));
        return attr;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(ChatFormatting.DARK_GRAY);
    }

    public static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui.png");
}
