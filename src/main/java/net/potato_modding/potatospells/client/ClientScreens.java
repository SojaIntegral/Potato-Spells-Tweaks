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
            boolean fire, boolean ice, boolean holy, boolean nature, boolean evoke,
            boolean blood, boolean ender, boolean lightning, boolean eldritch,
            boolean abyss, boolean techno, boolean blade, boolean mind,
            boolean sound, boolean wind, boolean sym, boolean soul, boolean dune, boolean aqua
    ) {
        Minecraft.getInstance().setScreen(new MobInteractionScreen(
                name, entity,
                health, armor, attack, mana, resist,
                spell, cast, cooldown, crit, critChance, pierce1,
                shred1, pierce2, shred2,
                fire, ice, holy, nature, evoke,
                blood, ender, lightning, eldritch,
                abyss, techno, blade, mind,
                sound, wind, sym, soul, dune, aqua
        ));
    }
}
