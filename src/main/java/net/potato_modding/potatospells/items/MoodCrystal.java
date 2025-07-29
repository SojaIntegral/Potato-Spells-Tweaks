package net.potato_modding.potatospells.items;

import net.alshanex.familiarslib.entity.AbstractSpellCastingPet;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.resistances.core.PotatoNaturesHandler;
import net.potato_modding.potatospells.tags.PotatoTags;

import java.util.List;
import java.util.UUID;

public class MoodCrystal extends Item {

    public MoodCrystal(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if(player.getCooldowns().isOnCooldown(stack.getItem())) {
            player.displayClientMessage(
                    Component.literal("Item on Cooldown").withStyle(ChatFormatting.DARK_RED), true
            );
            return InteractionResult.FAIL;
        }

        if(!player.level().isClientSide && target instanceof AbstractSpellCastingPet familiar) {
            if (familiar.getSummoner() != null && familiar.getSummoner().is(player)) {

                return applyNatureCrystal(stack, player, target).getResult();
            }
        }
        player.getCooldowns().addCooldown(stack.getItem(), 20);

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Use on a mob to reroll Natures")
                .withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("Hold shift to use on yourself")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("WARNING: Only works on Familiars by default")
                .withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player.isShiftKeyDown()) {
            return applyNatureCrystal(player.getItemInHand(hand), player, player); // Use on self
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    public InteractionResultHolder<ItemStack> applyNatureCrystal(ItemStack stack, Player player, LivingEntity target) {
        if (!player.level().isClientSide) {

            if(!ServerConfigs.NATURE_SYSTEM.get()) {
                player.displayClientMessage(
                        Component.literal("This item has been disabled").withStyle(ChatFormatting.DARK_RED), true
                );
                return InteractionResultHolder.fail(stack);
            }

            if(player.getCooldowns().isOnCooldown(stack.getItem())) {
                player.displayClientMessage(
                        Component.literal("Item on Cooldown").withStyle(ChatFormatting.DARK_RED), true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (target.getType().is(PotatoTags.HAS_NATURE)) {
                if (!player.getAbilities().instabuild) { // Not in creative
                    if (stack.getCount() != 0) {
                        stack.shrink(1);
                        player.getCooldowns().addCooldown(stack.getItem(), 20);
                    }
                }

                // Remove existing nature modifiers first
                PotatoNaturesHandler.removeNatures(target, PotatoNaturesHandler.NATURE_IDS);
                // Apply new nature
                PotatoNaturesHandler.applySpawnModifiers(target);
            }
            else{
                player.getCooldowns().addCooldown(stack.getItem(), 20);
                player.displayClientMessage(
                        Component.literal("Target cannot have Nature").withStyle(ChatFormatting.DARK_RED), true
                );
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide);
    }
}
