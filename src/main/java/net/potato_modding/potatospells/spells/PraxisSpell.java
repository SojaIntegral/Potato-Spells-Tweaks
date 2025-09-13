package net.potato_modding.potatospells.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.FlameStrikeParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoEffects;
import net.potato_modding.potatospells.registry.PotatoSchool;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class PraxisSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "praxis");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", String.format("%.1f", getDamage(spellLevel, caster)) + " to " + String.format("%.1f", getDamage(spellLevel, caster) + getDamage(spellLevel, caster) * 25.6)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(30, 1)),
                Component.literal("Negates the next attack from the target")
                );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(PotatoSchool.GENERIC_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(2.5)
            .setAllowCrafting(false)
            .build();

    public PraxisSpell()
    {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = 10;
        this.baseManaCost = 55;
    }

    @Override
    public boolean canBeCraftedBy(Player player) {
        return false;
    }

    @Override
    public boolean allowLooting() {
        return false;
    }

    @Override
    public boolean allowCrafting() {
        return false;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public boolean canBeInterrupted(@Nullable Player player) {
        return false;
    }

    @Override
    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return getCastTime(spellLevel);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ONE_HANDED_HORIZONTAL_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        float radius = 3.5f;
        float distance = 2.15f;
        Vec3 forward = entity.getForward();
        Vec3 hitLocation = entity.position().add(0, entity.getBbHeight() * .3f, 0).add(forward.scale(distance));
        var entities = level.getEntities(entity, AABB.ofSize(hitLocation, radius * 2, radius, radius * 2));
        var damageSource = this.getDamageSource(entity);
        for (Entity targetEntity : entities) {
            if (targetEntity instanceof LivingEntity && targetEntity.isAlive() && entity.isPickable() && targetEntity.position().subtract(entity.getEyePosition()).dot(forward) >= 0 && entity.distanceToSqr(targetEntity) < radius * radius && Utils.hasLineOfSight(level, entity.getEyePosition(), targetEntity.getBoundingBox().getCenter(), true)) {
                Vec3 offsetVector = targetEntity.getBoundingBox().getCenter().subtract(entity.getEyePosition());
                if (offsetVector.dot(forward) >= 0) {

                    double praxisStacks = 1;
                    if(((LivingEntity) targetEntity).hasEffect(PotatoEffects.PRAXIS_EFFECT)) praxisStacks =  1 + (0.1 * (1 + ((LivingEntity) targetEntity).getEffect(PotatoEffects.PRAXIS_EFFECT).getAmplifier()));

                    if (DamageSources.applyDamage(targetEntity, (float)(getDamage(spellLevel, entity) * praxisStacks), damageSource)) {
                        MagicManager.spawnParticles(level, ParticleHelper.FIERY_SPARKS, targetEntity.getX(), targetEntity.getY() + targetEntity.getBbHeight() * .5f, targetEntity.getZ(), 30, targetEntity.getBbWidth() * .5f, targetEntity.getBbHeight() * .5f, targetEntity.getBbWidth() * .5f, .03, false);
                        EnchantmentHelper.doPostAttackEffects((ServerLevel) level, targetEntity, damageSource);

                        if(!(((LivingEntity) targetEntity).hasEffect(PotatoEffects.PRAXIS_EFFECT))) {
                            ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(PotatoEffects.SPINNING_DRAGON,
                                    30,
                                    0,
                                    true,
                                    true,
                                    true));
                        }

                        int praxisMax = 0;
                        if(((LivingEntity) targetEntity).hasEffect(PotatoEffects.PRAXIS_EFFECT)) {
                            praxisMax = Math.min(((LivingEntity) targetEntity).getEffect(PotatoEffects.PRAXIS_EFFECT).getAmplifier() + 1, 255);
                        }
                        ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(PotatoEffects.PRAXIS_EFFECT,
                                100,
                                praxisMax,
                                true,
                                true,
                                true));
                    }
                }
            }
        }
        boolean mirrored = playerMagicData.getCastingEquipmentSlot().equals(SpellSelectionManager.OFFHAND);
        MagicManager.spawnParticles(level, new FlameStrikeParticleOptions((float) forward.x, (float) forward.y, (float) forward.z, mirrored, false, 1f), hitLocation.x, hitLocation.y+.3, hitLocation.z, 1, 0, 0, 0, 0, true);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            double physicalDamage = caster.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.075;
            double magicalDamage = getSpellPower(spellLevel, caster) * 0.075;

            return (float) (getSpellPower(spellLevel, caster) * (1 + magicalDamage + physicalDamage) * (1 + caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.001f));
        }
        else return 0;
    }

    private int getRange(int spellLevel, LivingEntity caster) {
        return Math.max(3 + (int)((getSpellPower(spellLevel, caster) * getEntityPowerMultiplier(caster)) / (1 + getEntityPowerMultiplier(caster))), 20);
    }

    private void doKnockback(LivingEntity target, LivingEntity caster, double x, double y)
    {
        double diffX = target.getX() - caster.getX();
        double diffZ = target.getZ() - caster.getZ();
        double power = Math.max(diffX * diffX + diffZ * diffZ, 0.001);
        target.push(diffX / diffZ * x, y, diffZ / power * x);
    }
}
