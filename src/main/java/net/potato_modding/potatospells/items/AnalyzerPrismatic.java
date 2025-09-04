package net.potato_modding.potatospells.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.potato_modding.potatospells.client.Keybinds;
import net.potato_modding.potatospells.utils.ImbuableCurio;
import top.theillusivec4.curios.api.SlotContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AnalyzerPrismatic extends ImbuableCurio {

    public AnalyzerPrismatic() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.EPIC),
                "head", SpellDataRegistryHolder.of(
                ));
    }

    @Override
    @net.neoforged.api.distmarker.OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        double attributeValue = 128;
        double curioModifier = BigDecimal.valueOf(attributeValue).setScale(0, RoundingMode.HALF_UP).doubleValue();

        tooltip.add(Component.literal("Range: " + curioModifier + " blocks | Press [")
                .append(Keybinds.OPEN_SCREEN_KEY.getTranslatedKeyMessage()).append("] to analyze")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        return attr;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    @Override
    public Component getName(ItemStack stack) {
        String baseName = super.getName(stack).getString();

        MutableComponent rainbowName = Component.literal("");

        Minecraft mc = Minecraft.getInstance();
        long ticks = (mc.level != null) ? mc.level.getGameTime() : 0;

        for (int i = 0; i < baseName.length(); i++) {
            float hue = ((i * 40) + (ticks * 2.25f)) % 360 / 360f;

            int rgb = java.awt.Color.HSBtoRGB(hue, 1f, 1f);
            String hex = String.format("#%06X", (0xFFFFFF & rgb));

            TextColor color = TextColor.parseColor(hex).getOrThrow();

            rainbowName = rainbowName.append(
                    Component.literal(String.valueOf(baseName.charAt(i)))
                            .withStyle(style -> style.withColor(color))
            );
        }

        return rainbowName;
    }

    public static final List<ResourceLocation> OVERLAY_TEXTURE = List.of(
            Analyzer.OVERLAY_TEXTURE,
            AnalyzerRed.OVERLAY_TEXTURE,
            AnalyzerOrange.OVERLAY_TEXTURE,
            AnalyzerYellow.OVERLAY_TEXTURE,
            AnalyzerGreen.OVERLAY_TEXTURE,
            AnalyzerBlue.OVERLAY_TEXTURE,
            AnalyzerPink.OVERLAY_TEXTURE,
            AnalyzerPurple.OVERLAY_TEXTURE
    );
    public static void setOverlayIndex(ItemStack stack, int index) {
        CustomData existing = stack.get(DataComponents.CUSTOM_DATA);
        CustomData customData = CustomData.of(existing != null ? existing.copyTag() : CustomData.EMPTY.copyTag());
        customData.getUnsafe().putInt("OverlayIndex", index);
        stack.set(DataComponents.CUSTOM_DATA, customData);
    }

    public static int getOverlayIndex(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null && customData.getUnsafe().contains("OverlayIndex")) {
            return customData.getUnsafe().getInt("OverlayIndex");
        }
        return 0; // default
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
    }
}

