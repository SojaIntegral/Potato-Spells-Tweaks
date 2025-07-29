package net.potato_modding.potatospells.client;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.utils.ASUtils;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.registry.PotatoRegistry;
import net.potato_modding.potatospells.tags.PotatoTags;
import net.potato_modding.potatospells.utils.RebalanceHandler;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.util.List;

import static net.potato_modding.potatospells.utils.ConfigFormulas.mobType;

@EventBusSubscriber(value = Dist.CLIENT)
public class ScreenTrigger {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        int rgb = 0;

        boolean hasIdentifier =
                ASUtils.hasCurio(mc.player, PotatoRegistry.BASE_ANALYZER.get()) ||
                        ASUtils.hasCurio(mc.player, PotatoRegistry.RED_ANALYZER.get()) ||
                        ASUtils.hasCurio(mc.player, PotatoRegistry.GREEN_ANALYZER.get()) ||
                        ASUtils.hasCurio(mc.player, PotatoRegistry.BLUE_ANALYZER.get()) ||
                        ASUtils.hasCurio(mc.player, PotatoRegistry.YELLOW_ANALYZER.get()) ||
                        ASUtils.hasCurio(mc.player, PotatoRegistry.PINK_ANALYZER.get());

        if (!Keybinds.OPEN_SCREEN_KEY.consumeClick() || !hasIdentifier) return;

        double curioModifier = 0;
        if (ASUtils.hasCurio(mc.player, PotatoRegistry.RED_ANALYZER.get())) {
            curioModifier = Math.pow(getAttr(mc.player, ALObjects.Attributes.CRIT_DAMAGE), 2)
                    * getAttr(mc.player, ALObjects.Attributes.CRIT_CHANCE);
            rgb = 1;
        }
        if (ASUtils.hasCurio(mc.player, PotatoRegistry.GREEN_ANALYZER.get())) {
            curioModifier = 8 + 16 * (getAttr(mc.player, Attributes.ARMOR)
                    + getAttr(mc.player, Attributes.ARMOR_TOUGHNESS)) / 6;
            rgb = 2;
        }
        if (ASUtils.hasCurio(mc.player, PotatoRegistry.BLUE_ANALYZER.get())) {
            curioModifier = pow2(getAttr(mc.player, AttributeRegistry.SPELL_POWER));
            rgb = 3;
        }
        if (ASUtils.hasCurio(mc.player, PotatoRegistry.YELLOW_ANALYZER.get())) {
            curioModifier = (10 * getAttr(mc.player, Attributes.MOVEMENT_SPEED)
                    + getAttr(mc.player, Attributes.ATTACK_SPEED) / 2)
                    * (getAttr(mc.player, Attributes.SNEAKING_SPEED));
            rgb = 4;
        }
        if (ASUtils.hasCurio(mc.player, PotatoRegistry.PINK_ANALYZER.get())) {
            curioModifier = (getAttr(mc.player, AttributeRegistry.CAST_TIME_REDUCTION)
                    * getAttr(mc.player, AttributeRegistry.COOLDOWN_REDUCTION));
            rgb = 5;
        }
        LivingEntity target = mc.player.isShiftKeyDown()
                ? mc.player
                : getTargetedEntity(8 + 16 * curioModifier);

        if (target == null) return;

        ClientScreens.openMobInteractionScreen(
                target.getName().getString(), target,
                target.getMaxHealth(),
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
                getAttr(target, PotatoAttributes.CRIT_IV),
                getAttr(target, Attributes.ATTACK_SPEED),

                getAttr(target, AttributeRegistry.FIRE_SPELL_POWER),
                getAttr(target, AttributeRegistry.ICE_SPELL_POWER),
                getAttr(target, AttributeRegistry.LIGHTNING_SPELL_POWER),
                getAttr(target, AttributeRegistry.NATURE_SPELL_POWER),
                getAttr(target, AttributeRegistry.ENDER_SPELL_POWER),
                getAttr(target, AttributeRegistry.HOLY_SPELL_POWER),
                getAttr(target, AttributeRegistry.ELDRITCH_SPELL_POWER),
                getAttr(target, AttributeRegistry.EVOCATION_SPELL_POWER),
                (ModList.get().isLoaded("cataclysm_spellbooks")) ? getAttr(target, CSAttributeRegistry.ABYSSAL_MAGIC_POWER) : 1,
                (ModList.get().isLoaded("endersequipment")) ? getAttr(target, EEAttributeRegistry.BLADE_SPELL_POWER) : 1,
                (ModList.get().isLoaded("alshanex_familiars")) ? getAttr(target, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_SPELL_POWER) : 1,
                (ModList.get().isLoaded("aero_additions")) ? getAttr(target, AASpells.Attributes.WIND_SPELL_POWER) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SYMMETRY_SPELL_POWER) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.DUNE_SPELL_POWER) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SPIRIT_SPELL_POWER) : 1,
                getAttr(target, AttributeRegistry.FIRE_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.ICE_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.LIGHTNING_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.NATURE_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.ENDER_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.HOLY_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.ELDRITCH_MAGIC_RESIST),
                getAttr(target, AttributeRegistry.EVOCATION_MAGIC_RESIST),
                (ModList.get().isLoaded("cataclysm_spellbooks")) ? getAttr(target, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("endersequipment")) ? getAttr(target, EEAttributeRegistry.BLADE_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("alshanex_familiars")) ? getAttr(target, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("aero_additions")) ? getAttr(target, AASpells.Attributes.WIND_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.DUNE_MAGIC_RESIST) : 1,
                (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST) : 1,

                rgb
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
