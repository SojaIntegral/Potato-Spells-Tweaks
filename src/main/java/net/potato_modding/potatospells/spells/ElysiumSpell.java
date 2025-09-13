package net.potato_modding.potatospells.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import io.redspace.ironsspellbooks.entity.spells.EarthquakeAoe;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoEffects;
import net.potato_modding.potatospells.registry.PotatoSchool;
import net.potato_modding.potatospells.registry.SpellRegistries;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class ElysiumSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "elysium");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", String.format("%.1f", damageMin(spellLevel, caster)) + " to " + String.format("%.1f", damageMax(spellLevel, caster))),
                Component.literal("Damage increased with missing mana"),
                Component.literal("Gain Elysian for 5s")
                );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(PotatoSchool.GENERIC_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(6)
            .setAllowCrafting(false)
            .build();

    public ElysiumSpell()
    {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 0;
        this.castTime = 5;
        this.baseManaCost = 0;
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
        return SpellAnimations.STOMP;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        List<LivingEntity> entitiesNearby = level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(getRange(spellLevel, entity)));
        for (LivingEntity targets : entitiesNearby)
        {
            var damageSource = this.getDamageSource(entity);
            if (DamageSources.applyDamage(targets, getDamage(spellLevel, entity), damageSource));

            entity.addEffect(new MobEffectInstance(PotatoEffects.ELYSIUM_EFFECT,
                        100,
                        0,
                        true,
                        false,
                        true));
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,
                        100,
                        0,
                        true,
                        false,
                        true));
        }

        // Temp
        EarthquakeAoe aoe = new EarthquakeAoe(level);
        aoe.moveTo(entity.position());
        aoe.setOwner(entity);
        aoe.setCircular();
        aoe.setRadius(getRange(spellLevel, entity));
        aoe.setDuration(20);
        aoe.setDamage(0);

        level.addFreshEntity(aoe);

        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(PotatoSchool.GENERIC.get().getTargetingColor(), getRange(spellLevel, entity)), entity.getX(), entity.getY() + 0.5F, entity.getZ(), 1, 0, 0, 0, -0.5, false);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        if(caster != null) {
            double currentMana = MagicData.getPlayerMagicData(caster).getMana();
            double maxMana = caster.getAttributeValue(AttributeRegistry.MAX_MANA);
            return (float) ((0.2 * (maxMana / Math.max(currentMana, maxMana * 0.0666)) + 0.5)
                    * getSpellPower(spellLevel, caster) * (1 + caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.002f));
        }
        else return 0;
    }

    private float damageMin(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            double currentMana = caster.getAttributeValue(AttributeRegistry.MAX_MANA);
            double maxMana = caster.getAttributeValue(AttributeRegistry.MAX_MANA);
            return (float) ((0.3 * (maxMana / Math.max(currentMana, maxMana * 0.075)) + 0.5)
                    * getSpellPower(spellLevel, caster) * (1 + caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.001f));
        }
        else return 0;
    }

    private float damageMax(int spellLevel, LivingEntity caster) {
        if (caster != null) {
            double currentMana = caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.075;
            double maxMana = caster.getAttributeValue(AttributeRegistry.MAX_MANA);
            return (float) ((0.3 * (maxMana / Math.max(currentMana, maxMana * 0.075)) + 0.5)
                    * getSpellPower(spellLevel, caster) * (1 + caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.001f));
        }
        else return 0;
    }

    private int getRange(int spellLevel, LivingEntity caster) {
        return Math.max(3 + (int)((getSpellPower(spellLevel, caster) * getEntityPowerMultiplier(caster)) / (1 + getEntityPowerMultiplier(caster))), 20);
    }
}
