package net.potato_modding.potatospells.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class CurioUtil {

    public static boolean hasCurio(Player player, Item item) {
        return CuriosApi.getCuriosHelper().findEquippedCurio(item, player).isPresent();
    }

    public static ItemStack getCurio(Player player, Item item) {
        // findEquippedCurio returns Optional<ImmutableTriple<String, Integer, ItemStack>>
        Optional<ImmutableTriple<String, Integer, ItemStack>> optionalStack =
                CuriosApi.getCuriosHelper().findEquippedCurio(item, player);
        return optionalStack.map(ImmutableTriple::getRight).orElse(ItemStack.EMPTY);
    }

    public static ItemStack getCurioPersistent(Player player, Item item) {
        return CuriosApi.getCuriosHelper()
                .findEquippedCurio(stack -> stack.getItem() == item, player)
                .map(triple -> triple.getRight())
                .orElse(ItemStack.EMPTY);
    }
}
