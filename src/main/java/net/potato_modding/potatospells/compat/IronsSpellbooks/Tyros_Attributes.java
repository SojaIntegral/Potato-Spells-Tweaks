package net.potato_modding.potatospells.compat.IronsSpellbooks;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Tyros_Attributes {

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
            // This needs to be conditional or the game shits itself if the mod is not present
            if (ModList.get().isLoaded("endersequipment")) {
                setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, ServerConfigs.TYROS_BLADE_RESIST.get());
            }
            if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, ServerConfigs.TYROS_ABYSSAL_RESIST.get());
            }
            if (ModList.get().isLoaded("alshanex_familiars")) {
                setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, ServerConfigs.TYROS_MUSIC_RESIST.get());
            }
            if (ModList.get().isLoaded("aero_additions")) {
                setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, ServerConfigs.TYROS_WIND_RESIST.get());
            }
            setIfNonNull(mob, Attributes.ARMOR, ServerConfigs.TYROS_ARMOR.get());
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, ServerConfigs.TYROS_TOUGHNESS.get());
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, ServerConfigs.TYROS_ATTACK.get());
        }
    }
    // Actually sets the attributes
    private static void setIfNonNull(LivingEntity entity, Holder< Attribute > attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }
}
