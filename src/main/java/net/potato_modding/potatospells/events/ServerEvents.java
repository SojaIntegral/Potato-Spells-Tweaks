package net.potato_modding.potatospells.events;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import com.snackpirate.aeromancy.spells.AASpells;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

import java.util.Objects;

@SuppressWarnings("unused")
@EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

        // Tyros Modifiers
        if (mob.getType().is(PotatoTags.TYROS_BOSS)) {
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, ServerConfigs.TYROS_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, ServerConfigs.TYROS_FIRE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, ServerConfigs.TYROS_NATURE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, ServerConfigs.TYROS_ENDER_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, ServerConfigs.TYROS_BLOOD_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, ServerConfigs.TYROS_ICE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, ServerConfigs.TYROS_LIGHTNING_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, ServerConfigs.TYROS_ELDRITCH_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, ServerConfigs.TYROS_HOLY_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.SPELL_POWER, ServerConfigs.TYROS_POWER.get());
            setIfNonNull(mob, AttributeRegistry.FIRE_SPELL_POWER, ServerConfigs.TYROS_FIRE_SPELL.get());
            setIfNonNull(mob, EEAttributeRegistry.BLADE_MAGIC_RESIST, ServerConfigs.TYROS_BLADE_RESIST.get());
            setIfNonNull(mob, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, ServerConfigs.TYROS_ABYSSAL_RESIST.get());
            setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, ServerConfigs.TYROS_MUSIC_RESIST.get());
            setIfNonNull(mob, AASpells.Attributes.WIND_MAGIC_RESIST, ServerConfigs.TYROS_WIND_RESIST.get());
            setIfNonNull(mob, Attributes.ARMOR, ServerConfigs.TYROS_ARMOR.get());
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, ServerConfigs.TYROS_TOUGHNESS.get());
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, ServerConfigs.TYROS_ATTACK.get());

        }

        // Dead King modifiers
        if (mob.getType().is(PotatoTags.DEADKING_BOSS)) {
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, ServerConfigs.DEAD_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, ServerConfigs.DEAD_FIRE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, ServerConfigs.DEAD_NATURE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, ServerConfigs.DEAD_ENDER_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, ServerConfigs.DEAD_BLOOD_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, ServerConfigs.DEAD_ICE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, ServerConfigs.DEAD_LIGHTNING_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, ServerConfigs.DEAD_ELDRITCH_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, ServerConfigs.DEAD_HOLY_RESIST.get());
            setIfNonNull(mob, EEAttributeRegistry.BLADE_MAGIC_RESIST, ServerConfigs.DEAD_BLADE_RESIST.get());
            setIfNonNull(mob, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, ServerConfigs.DEAD_ABYSSAL_RESIST.get());
            setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, ServerConfigs.DEAD_MUSIC_RESIST.get());
            setIfNonNull(mob, AASpells.Attributes.WIND_MAGIC_RESIST, ServerConfigs.DEAD_WIND_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.SPELL_POWER, ServerConfigs.DEAD_POWER.get());
            setIfNonNull(mob, AttributeRegistry.BLOOD_SPELL_POWER, ServerConfigs.DEAD_BLOOD_SPELL.get());
            setIfNonNull(mob, Attributes.ARMOR, ServerConfigs.DEAD_ARMOR.get());
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, ServerConfigs.DEAD_TOUGHNESS.get());
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, ServerConfigs.DEAD_ATTACK.get());
        }

        // Living Armor modifiers
        if (mob.getType().is(PotatoTags.KEEPER_MOB)) {
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, ServerConfigs.KEEPER_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, ServerConfigs.KEEPER_FIRE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, ServerConfigs.KEEPER_NATURE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, ServerConfigs.KEEPER_ENDER_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, ServerConfigs.KEEPER_BLOOD_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, ServerConfigs.KEEPER_ICE_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, ServerConfigs.KEEPER_LIGHTNING_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, ServerConfigs.KEEPER_ELDRITCH_RESIST.get());
            setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, ServerConfigs.KEEPER_HOLY_RESIST.get());
            setIfNonNull(mob, EEAttributeRegistry.BLADE_MAGIC_RESIST, ServerConfigs.KEEPER_BLADE_RESIST.get());
            setIfNonNull(mob, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, ServerConfigs.KEEPER_ABYSSAL_RESIST.get());
            setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, ServerConfigs.KEEPER_MUSIC_RESIST.get());
            setIfNonNull(mob, AASpells.Attributes.WIND_MAGIC_RESIST, ServerConfigs.KEEPER_WIND_RESIST.get());
            setIfNonNull(mob, Attributes.ARMOR, ServerConfigs.KEEPER_ARMOR.get());
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, ServerConfigs.KEEPER_TOUGHNESS.get());
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, ServerConfigs.KEEPER_ATTACK.get());
        }

        // Give summoned mobs some spell resistance so they don't get nuked
        // Spell resistance depends on the spell resist formula
        if (mob.getType().is(PotatoTags.SUMMONS)) {
            if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "3")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 4.0);

            } else if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "2")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 2.2);

            } else if (Objects.equals(ServerConfigs.RESIST_UNCAP.get(), "1")) {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1.35);

            } else {
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1.45);
            }
        }
    }


    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }
}