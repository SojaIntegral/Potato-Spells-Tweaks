package net.potato_modding.potatospells.client;


import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.potato_modding.potatospells.config.ClientConfigs;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class ShinyParticlesHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (!ClientConfigs.SHINY_GLOW.get()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        if (!mc.isWindowActive() || mc.isPaused()) return;

        if (mc.level.getGameTime() % 5 != 0) return;

        Vec3 playerPos = mc.player.position();
        AABB searchBox = new AABB(
                playerPos.x - 60, playerPos.y - 60, playerPos.z - 60,
                playerPos.x + 60, playerPos.y + 60, playerPos.z + 60
        );

        List<LivingEntity> nearbyEntities = mc.level.getEntitiesOfClass(LivingEntity.class, searchBox, entity -> {
            CompoundTag potatoData = entity.getPersistentData().getCompound("PotatoData");

            return potatoData.getBoolean("shiny");
        });

        for (LivingEntity entity : nearbyEntities) {
            AABB bounds = entity.getBoundingBox();
            double width = bounds.maxX - bounds.minX;
            double x = entity.getX();
            double y = bounds.minY; // Feet level
            double z = entity.getZ();

            if(entity == mc.player && !ClientConfigs.PLAYER_GLOW.get()) return;

            for (int i = 0; i < 2; i++) {
                double offsetX = (mc.level.random.nextDouble() - 0.5) * width;
                double offsetZ = (mc.level.random.nextDouble() - 0.5) * width;

                double velocityX = offsetX * 0.11;
                double velocityY = 0.05 + mc.level.random.nextDouble() * 1.5;
                double velocityZ = offsetZ * 0.11;

                mc.level.addParticle(ParticleTypes.GLOW,
                        x + offsetX, y, z + offsetZ,
                        velocityX, velocityY, velocityZ);
            }
        }
    }
}
