package net.potato_modding.potatospells.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.potato_modding.potatospells.resistances.core.PotatoNaturesHandler;

public class MoodCrystal extends Item {

    public MoodCrystal(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        return applyNatureCrystal(stack, player, target).getResult(); // Use on self
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player.isShiftKeyDown()) {
            return applyNatureCrystal(player.getItemInHand(hand), player, player); // Use on self
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private InteractionResultHolder<ItemStack> applyNatureCrystal(ItemStack stack, Player player, LivingEntity target) {
        if (!player.level().isClientSide) {

            // Remove existing nature modifiers first
            PotatoNaturesHandler.removeNatures(target, PotatoNaturesHandler.NATURE_IDS);

            if (!player.getAbilities().instabuild) { // Not in creative
                stack.shrink(1);
            }
            PotatoNaturesHandler.applySpawnModifiers(target);
        }
        return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide);
    }

}
