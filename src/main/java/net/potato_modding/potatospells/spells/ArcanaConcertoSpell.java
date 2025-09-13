package net.potato_modding.potatospells.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.blood_needle.BloodNeedle;
import io.redspace.ironsspellbooks.entity.spells.firebolt.FireboltProjectile;
import io.redspace.ironsspellbooks.particle.FlameStrikeParticleOptions;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.potato_modding.potatoessentials.config.ServerConfigs;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoEffects;
import net.potato_modding.potatospells.registry.PotatoSchool;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.potato_modding.potatoessentials.utils.ConfigFormulas.randMax;
import static net.potato_modding.potatoessentials.utils.ConfigFormulas.shinyChanceModifier;

@AutoSpellConfig
public class ArcanaConcertoSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "arcana_concerto");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getRange(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.projectile_count", getCount(spellLevel, caster)),
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(440, 1)),
                Component.translatable("attribute.modifier.take.1", Utils.stringTruncation(Math.min(getCount(spellLevel, caster), 22), 0), Component.translatable("potatospellbookstweaks.arcana_1")).withStyle(ChatFormatting.RED)
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(PotatoSchool.GENERIC_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(2.2)
            .setAllowCrafting(false)
            .build();

    public ArcanaConcertoSpell()
    {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 0;
        this.castTime = (int)4.4;
        this.baseManaCost = 22;
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
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, getRange(spellLevel, entity), .15f);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData targetData) {
            var targetEntity = targetData.getTarget((ServerLevel) world);
            if (targetEntity != null) {
                int count = getCount(spellLevel, entity);
                float damage = getDamage(spellLevel, entity);
                Vec3 center = targetEntity.position().add(0, targetEntity.getEyeHeight() / 2, 0);
                float degreesPerNeedle = 360f / count;
                for (int i = 0; i < count; i++) {
                    Vec3 offset = new Vec3(0, Math.random(), .55).normalize().scale(targetEntity.getBbWidth() + 2.75f).yRot(degreesPerNeedle * i * Mth.DEG_TO_RAD);
                    Vec3 spawn = center.add(offset);
                    Vec3 motion = center.subtract(spawn).normalize();

                    BloodNeedle needle = new BloodNeedle(world, entity);
                    needle.moveTo(spawn);
                    needle.shoot(motion.scale(.35f));
                    needle.setDamage(damage);
                    world.addFreshEntity(needle);

                    int[] arcanaVar = new int[1];
                    Arrays.fill(arcanaVar, (getCount(spellLevel, entity) -1));

                    int arcanaMax = arcanaVar[0];
                    if(((LivingEntity) targetEntity).hasEffect(PotatoEffects.ARCANA_CONCERTO_EFFECT)) {
                        arcanaMax = Math.min(((LivingEntity) targetEntity).getEffect(PotatoEffects.ARCANA_CONCERTO_EFFECT).getAmplifier() + arcanaVar[0], 21);
                    }
                    if (ThreadLocalRandom.current().nextInt(2) == 0) {
                        ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(PotatoEffects.ARCANA_CONCERTO_EFFECT,
                                440,
                                arcanaMax,
                                true,
                                true,
                                true));
                    }
                }
            }
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster) {
        if(caster != null) {
            double physicalDamage = 2.2 + (caster.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.22);
            double magicalDamage = (caster.getAttributeValue(AttributeRegistry.COOLDOWN_REDUCTION) * 0.22)
                    * (getSpellPower(spellLevel, caster) * 0.22)
                    * (caster.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.0022);

            return (float) (magicalDamage * physicalDamage);
        }
        else return 0;
    }

    private int getCount(int spellLevel, LivingEntity caster) {
        return BigDecimal.valueOf(Math.max(getDamage(spellLevel, caster) * (0.22 + 0.22 * getSpellPower(spellLevel, caster)) * 2.2, 1)).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    private int getRange(int spellLevel, LivingEntity caster) {
        return Math.min(8 + (int)(getDamage(spellLevel, caster) * getSpellPower(spellLevel, caster) * getEntityPowerMultiplier(caster)), 64);
    }

    private void doKnockback(LivingEntity target, LivingEntity caster, double x, double y)
    {
        double diffX = target.getX() - caster.getX();
        double diffZ = target.getZ() - caster.getZ();
        double power = Math.max(diffX * diffX + diffZ * diffZ, 0.001);
        target.push(diffX / diffZ * x, y, diffZ / power * x);
    }
}
