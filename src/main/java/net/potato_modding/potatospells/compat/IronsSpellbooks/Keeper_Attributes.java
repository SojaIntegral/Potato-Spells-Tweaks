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
public class Keeper_Attributes {

    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

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
            // This needs to be conditional or the game shits itself if the mod is not present
            if (ModList.get().isLoaded("endersequipment")) {
                setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, ServerConfigs.KEEPER_BLADE_RESIST.get());
            }
            if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, ServerConfigs.KEEPER_ABYSSAL_RESIST.get());
            }
            if (ModList.get().isLoaded("alshanex_familiars")) {
                setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, ServerConfigs.KEEPER_MUSIC_RESIST.get());
            }
            if (ModList.get().isLoaded("aero_additions")) {
                setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, ServerConfigs.KEEPER_WIND_RESIST.get());
            }
            setIfNonNull(mob, Attributes.ARMOR, ServerConfigs.KEEPER_ARMOR.get());
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, ServerConfigs.KEEPER_TOUGHNESS.get());
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, ServerConfigs.KEEPER_ATTACK.get());
        }
    }

    // Actually sets the attributes
    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }
}