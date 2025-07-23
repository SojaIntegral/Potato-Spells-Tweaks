package net.potato_modding.potatospells.client;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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
import net.potato_modding.potatospells.tags.PotatoTags;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class ScreenTrigger {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && getAttr(mc.player, PotatoAttributes.CAN_IDENTIFY) == 1) {
            if (mc.player == null || mc.level == null) return;

            if (!Keybinds.OPEN_SCREEN_KEY.consumeClick()) return;

            LivingEntity target = mc.player.isShiftKeyDown()
                    ? mc.player
                    // Scales with the square of the spell power
                    : getTargetedEntity(16.0 * getAttr(mc.player, AttributeRegistry.SPELL_POWER) * getAttr(mc.player, AttributeRegistry.SPELL_POWER));

            if (target == null) return;

            float targetHealth = target.getMaxHealth();
            double targetArmor = getAttr(target, Attributes.ARMOR);
            double targetAttack = getAttr(target, Attributes.ATTACK_DAMAGE);
            double targetMana = getAttr(target, AttributeRegistry.MAX_MANA);
            double targetResist = getAttr(target, AttributeRegistry.SPELL_RESIST);
            double targetSpell = getAttr(target, AttributeRegistry.SPELL_POWER);
            double targetCast = getAttr(target, AttributeRegistry.CAST_TIME_REDUCTION);
            double targetCooldown = getAttr(target, AttributeRegistry.COOLDOWN_REDUCTION);
            double targetCrit = getAttr(target, ALObjects.Attributes.CRIT_DAMAGE);
            double targetCritChance = getAttr(target, ALObjects.Attributes.CRIT_CHANCE);
            double targetPierce1 = getAttr(target, ALObjects.Attributes.ARMOR_PIERCE);
            double targetShred1 = getAttr(target, ALObjects.Attributes.ARMOR_SHRED);
            double targetPierce2 = getAttr(target, ALObjects.Attributes.PROT_PIERCE);
            double targetShred2 = getAttr(target, ALObjects.Attributes.PROT_SHRED);

            boolean schoolFire = target.getType().is(PotatoTags.TYPE_FIRE);
            boolean schoolIce = target.getType().is(PotatoTags.TYPE_ICE);
            boolean schoolHoly = target.getType().is(PotatoTags.TYPE_HOLY);
            boolean schoolNature = target.getType().is(PotatoTags.TYPE_NATURE);
            boolean schoolEvoke = target.getType().is(PotatoTags.TYPE_EVOKATION);
            boolean schoolBlood = target.getType().is(PotatoTags.TYPE_BLOOD);
            boolean schoolEnder = target.getType().is(PotatoTags.TYPE_ENDER);
            boolean schoolLightning = target.getType().is(PotatoTags.TYPE_LIGHTNING);
            boolean schoolEldritch = target.getType().is(PotatoTags.TYPE_ELDRITCH);
            boolean schoolAbyss = target.getType().is(PotatoTags.TYPE_ABYSS);
            boolean schoolTechno = target.getType().is(PotatoTags.TYPE_TECHNOMANCY);
            boolean schoolBlade = target.getType().is(PotatoTags.TYPE_BLADE);
            boolean schoolMind = target.getType().is(PotatoTags.TYPE_MIND);
            boolean schoolSound = target.getType().is(PotatoTags.TYPE_SOUND);
            boolean schoolWind = target.getType().is(PotatoTags.TYPE_WIND);
            boolean schoolSym = target.getType().is(PotatoTags.TYPE_SYM);
            boolean schoolSoul = target.getType().is(PotatoTags.TYPE_SOUL);
            boolean schoolDune = target.getType().is(PotatoTags.TYPE_DUNE);
            boolean schoolAqua = target.getType().is(PotatoTags.TYPE_AQUA);

            ClientScreens.openMobInteractionScreen(
                    target.getName().getString(), target,
                    targetHealth, targetArmor, targetAttack, targetMana, targetResist,
                    targetSpell, targetCast, targetCooldown, targetCrit, targetCritChance, targetPierce1,
                    targetShred1, targetPierce2, targetShred2,
                    schoolFire, schoolIce, schoolHoly, schoolNature, schoolEvoke,
                    schoolBlood, schoolEnder, schoolLightning, schoolEldritch,
                    schoolAbyss, schoolTechno, schoolBlade, schoolMind,
                    schoolSound, schoolWind, schoolSym, schoolSoul, schoolDune, schoolAqua
            );
        }
    }
    private static double getAttr(LivingEntity target, Holder<Attribute> holder) {
        var attrInstance = target.getAttribute(holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }

    private static LivingEntity getTargetedEntity(double maxDistance) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null || mc.getCameraEntity() == null)
            return null;

        Vec3 eyePos = mc.getCameraEntity().getEyePosition(1.0F);
        Vec3 viewVec = mc.getCameraEntity().getViewVector(1.0F);
        Vec3 reachVec = eyePos.add(viewVec.scale(maxDistance));
        AABB aabb = mc.getCameraEntity().getBoundingBox().expandTowards(viewVec.scale(maxDistance)).inflate(1.0D);

        List<Entity> entities = mc.level.getEntities(mc.getCameraEntity(), aabb, e ->
                e instanceof LivingEntity && e.isPickable() && e.isAlive()
        );

        EntityHitResult closestHit = null;
        double closestDistance = maxDistance * maxDistance;

        for (Entity entity : entities) {
            AABB entityBB = entity.getBoundingBox().inflate(0.3D);
            var optional = entityBB.clip(eyePos, reachVec);
            if (optional.isPresent()) {
                double distSqr = eyePos.distanceToSqr(optional.get());
                if (distSqr < closestDistance) {
                    closestHit = new EntityHitResult(entity, optional.get());
                    closestDistance = distSqr;
                }
            }
        }

        return closestHit != null ? (LivingEntity) closestHit.getEntity() : null;
    }
}
