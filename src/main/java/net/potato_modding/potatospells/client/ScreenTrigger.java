package net.potato_modding.potatospells.client;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.registry.PotatoRegistry;
import net.potato_modding.potatospells.tags.PotatoTags;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class ScreenTrigger {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        boolean hasIdentifier = ASUtils.hasCurio(mc.player, PotatoRegistry.MOB_IDENTIFIER.get());

        if (!Keybinds.OPEN_SCREEN_KEY.consumeClick() || !hasIdentifier) return;

        LivingEntity target = mc.player.isShiftKeyDown()
                ? mc.player
                : getTargetedEntity(16.0 * pow2(getAttr(mc.player, AttributeRegistry.SPELL_POWER)));

        if (target == null) return;

        ClientScreens.openMobInteractionScreen(
                target.getName().getString(), target,
                (float) target.getMaxHealth(),
                getAttr(target, Attributes.ARMOR),
                getAttr(target, Attributes.ATTACK_DAMAGE),
                getAttr(target, AttributeRegistry.MAX_MANA),
                getAttr(target, AttributeRegistry.SPELL_RESIST),
                getAttr(target, AttributeRegistry.SPELL_POWER),
                getAttr(target, AttributeRegistry.CAST_TIME_REDUCTION),
                getAttr(target, AttributeRegistry.COOLDOWN_REDUCTION),
                getAttr(target, ALObjects.Attributes.CRIT_DAMAGE),
                getAttr(target, ALObjects.Attributes.CRIT_CHANCE),
                getAttr(target, ALObjects.Attributes.ARMOR_PIERCE),
                getAttr(target, ALObjects.Attributes.ARMOR_SHRED),
                getAttr(target, ALObjects.Attributes.PROT_PIERCE),
                getAttr(target, ALObjects.Attributes.PROT_SHRED),
                getAttr(target, PotatoAttributes.ATTACK_IV),
                getAttr(target, PotatoAttributes.ARMOR_IV),
                getAttr(target, PotatoAttributes.POWER_IV),
                getAttr(target, PotatoAttributes.RESIST_IV),
                getAttr(target, PotatoAttributes.CAST_IV),
                getAttr(target, PotatoAttributes.ARMOR_PEN_IV),
                getAttr(target, PotatoAttributes.PROT_PEN_IV),
                getAttr(target, PotatoAttributes.CRIT_IV)
        );
    }

    private static double getAttr(LivingEntity entity, Holder<Attribute> attribute) {
        var instance = entity.getAttribute(attribute);
        return instance != null ? instance.getValue() : 0.0;
    }

    private static double pow2(double value) {
        return value * value;
    }

    private static LivingEntity getTargetedEntity(double maxDistance) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.getCameraEntity() == null)
            return null;

        Vec3 eye = mc.getCameraEntity().getEyePosition(1.0F);
        Vec3 view = mc.getCameraEntity().getViewVector(1.0F);
        Vec3 reach = eye.add(view.scale(maxDistance));
        AABB box = mc.getCameraEntity().getBoundingBox().expandTowards(view.scale(maxDistance)).inflate(1.0D);

        List<Entity> entities = mc.level.getEntities(mc.getCameraEntity(), box,
                e -> e instanceof LivingEntity le && le.isPickable() && le.isAlive());

        EntityHitResult closest = null;
        double bestDist = maxDistance * maxDistance;

        for (Entity entity : entities) {
            var bb = entity.getBoundingBox().inflate(0.3D);
            var hit = bb.clip(eye, reach);
            if (hit.isPresent()) {
                double distSqr = eye.distanceToSqr(hit.get());
                if (distSqr < bestDist) {
                    closest = new EntityHitResult(entity, hit.get());
                    bestDist = distSqr;
                }
            }
        }

        return closest != null ? (LivingEntity) closest.getEntity() : null;
    }
}
