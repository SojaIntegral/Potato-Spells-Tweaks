package net.potato_modding.potatospells.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

@SuppressWarnings("deprecation")
public class CurioUtil {

    public static boolean hasCurio(Player player, Item item)
    {
        return CuriosApi.getCuriosHelper().findEquippedCurio(item, player).isPresent();
    }
}
