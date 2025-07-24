package net.potato_modding.potatospells.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.potato_modding.potatospells.screen.MobInteractionScreen;

@OnlyIn(Dist.CLIENT)
public class ClientScreens {
    public static void openMobInteractionScreen(
            String name, LivingEntity entity,
            float health, double armor, double attack, double mana, double resist,
            double spell, double cast, double cooldown, double crit, double critChance,
            double pierce1, double shred1, double pierce2, double shred2,
            double firstIV, double secondIV, double thirdIV, double fourthIV,
            double fifthIV, double sixthIV, double seventhIV, double eighthIV
    ) {
        Minecraft.getInstance().setScreen(new MobInteractionScreen(
                name, entity,
                health, armor, attack, mana, resist,
                spell, cast, cooldown, crit, critChance, pierce1,
                shred1, pierce2, shred2,
                firstIV, secondIV, thirdIV, fourthIV, fifthIV,
                sixthIV, seventhIV, eighthIV
        ));
    }
}
