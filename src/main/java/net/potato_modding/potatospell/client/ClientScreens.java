package net.potato_modding.potatospell.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.potato_modding.potatospell.screen.MobInteractionScreen;

@OnlyIn(Dist.CLIENT)
public class ClientScreens {
    public static void openMobInteractionScreen(
            String name, LivingEntity entity,
            float health, double armor, double attack, double mana, double resist,
            double spell, double cast, double cooldown, double crit, double critChance,
            double pierce1, double shred1, double pierce2, double shred2,
            double firstIV, double secondIV, double thirdIV, double fourthIV,
            double fifthIV, double sixthIV, double seventhIV, double eighthIV, double attackSpeed,
            double firePower, double icePower, double lightningPower, double naturePower,
            double enderPower, double holyPower, double eldritchPower, double evokerPower,
            double abyssPower, double bladePower, double songPower, double windPower, double symmetryPower,
            double dunePower, double spiritPower, double fireRes, double iceRes, double lightningRes,
            double natureRes, double enderRes, double holyRes, double eldritchRes, double evokerRes,
            double abyssRes, double bladeRes, double songRes, double windRes, double symmetryRes,
            double duneRes, double spiritRes, double rgb
    ) {
        Minecraft.getInstance().setScreen(new MobInteractionScreen(
                name, entity,
                health, armor, attack, mana, resist,
                spell, cast, cooldown, crit, critChance, pierce1,
                shred1, pierce2, shred2,
                firstIV, secondIV, thirdIV, fourthIV, fifthIV,
                sixthIV, seventhIV, eighthIV, attackSpeed,
                firePower, icePower, lightningPower, naturePower,
                enderPower, holyPower, eldritchPower, evokerPower,
                abyssPower, bladePower, songPower, windPower,
                symmetryPower, dunePower, spiritPower,
                fireRes, iceRes, lightningRes, natureRes,
                enderRes, holyRes, eldritchRes, evokerRes,
                abyssRes, bladeRes, songRes, windRes,
                symmetryRes, duneRes, spiritRes, rgb
        ));
    }
}
