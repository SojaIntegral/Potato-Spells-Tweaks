package net.potato_modding.potatospells.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
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
import net.potato_modding.potatospells.client.Keybinds;
import top.theillusivec4.curios.api.SlotContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AnalyzerPink extends CurioBaseItem {

    public AnalyzerPink() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    @net.neoforged.api.distmarker.OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        double attributeValue = 8 + 16 * (getAttr(mc.player, AttributeRegistry.CAST_TIME_REDUCTION)
                * getAttr(mc.player, AttributeRegistry.COOLDOWN_REDUCTION));
        double curioModifier = BigDecimal.valueOf(attributeValue).setScale(0, RoundingMode.HALF_UP).doubleValue();

            tooltip.add(Component.literal("Range: " + curioModifier + " blocks | Press [")
                    .append(Keybinds.OPEN_SCREEN_KEY.getTranslatedKeyMessage()).append("] to analyze")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.SPELL_RESIST, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(AttributeRegistry.CAST_TIME_REDUCTION, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_VALUE));
        attr.put(AttributeRegistry.COOLDOWN_REDUCTION, new AttributeModifier(id, 0.05, AttributeModifier.Operation.ADD_VALUE));
        return attr;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(ChatFormatting.DARK_PURPLE);
    }

    public static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui_pink.png");
}
