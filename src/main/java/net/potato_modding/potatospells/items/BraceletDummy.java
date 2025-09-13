package net.potato_modding.potatospells.items;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
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
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatospells.client.Keybinds;
import net.potato_modding.potatospells.registry.SpellRegistries;
import net.potato_modding.potatospells.utils.ImbuableCurio;
import top.theillusivec4.curios.api.SlotContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BraceletDummy extends ImbuableCurio {

    public BraceletDummy() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.EPIC),
                "bracelet", SpellDataRegistryHolder.of(
                        new SpellDataRegistryHolder(SpellRegistries.MANA_STEAL_SPELL, 1)
                ));
    }

    @Override
    @net.neoforged.api.distmarker.OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(AttributeRegistry.SPELL_RESIST, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(AttributeRegistry.MAX_MANA, new AttributeModifier(id, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(AttributeRegistry.MANA_REGEN, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return attr;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(ChatFormatting.DARK_AQUA);
    }
}
