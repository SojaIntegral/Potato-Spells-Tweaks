package net.potato_modding.potatospells.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.potato_modding.potatospells.PotatoSpells;
import net.potato_modding.potatospells.registry.PotatoEffects;
import net.potato_modding.potatospells.registry.PotatoSchool;

import javax.annotation.Nullable;
import java.util.List;

@AutoSpellConfig
public class ManaStealSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "mana_steal_spell");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 4, 0)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation((int) (getSpellPower(spellLevel, caster)  * 0.25), 0), Component.translatable("potatospellbookstweaks.mana_steal"))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(PotatoSchool.GENERIC_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(47.5)
            .setAllowCrafting(false)
            .build();

    public ManaStealSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 50;
        this.spellPowerPerLevel = 0;
        this.castTime = 20;
        this.baseManaCost = 100;
    }

    @Override
    public float getSpellPower(int spellLevel, @Nullable Entity sourceEntity) {
        var entitySpellPowerModifier = (float) ((sourceEntity instanceof LivingEntity entity) ? entity.getAttributeValue(AttributeRegistry.SPELL_POWER) : 1);
        float configPowerModifier = (float) ServerConfigs.getSpellConfig(this).powerMultiplier();
        double manaPower = (sourceEntity instanceof Player player) ? player.getAttributeValue(AttributeRegistry.MAX_MANA) * 0.001 : 1;
        return (float) ((baseSpellPower + spellPowerPerLevel * (spellLevel - 1)) * entitySpellPowerModifier * configPowerModifier * manaPower);
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(PotatoEffects.MANA_STEAL,
                (int) getSpellPower(spellLevel, entity) * 4,
                (int) ((getSpellPower(spellLevel, entity) - 1) * 0.25),
                false,
                false,
                true));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }
}