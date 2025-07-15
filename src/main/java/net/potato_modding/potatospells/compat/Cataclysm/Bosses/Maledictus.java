package net.potato_modding.potatospells.compat.Cataclysm.Bosses;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.potato_modding.potatospells.config.ServerConfigs;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Maledictus {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributeCataclysm(EntityJoinLevelEvent event) {
        var mob = event.getEntity();
        var typeKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());

        if (typeKey.getNamespace().equals("cataclysm") && typeKey.getPath().equals("maledictus")) {

            if (!ServerConfigs.MALEDICTUS_SWITCH.get()) {
                // Amethyst  attributes
                Resist += 1.3 * boss_mod;
                FireRes += 0.65 * boss_mod;
                IceRes += 1.5 * boss_mod;
                HolyRes += 0.9 * boss_mod;
                NatRes += 1.25 * boss_mod;
                BloodRes += 1.35 * boss_mod;
                EndRes += 1.1 * boss_mod;
                LigRes += 0.8 * boss_mod;
                EldRes += 1.2 * boss_mod;
                AbyssRes += 1.35 * boss_mod;
                BladeRes += 0.95 * boss_mod;
                SoundRes += 1.1 * boss_mod;
                WindRes += 1.3 * boss_mod;
                Armor += 16 * spec_mod;
                Tough += 13 * spec_mod;
                Attack += 11 * spec_mod;
            }

            else {
                Armor = ServerConfigs.MALEDICTUS_ARMOR.get();
                Tough = ServerConfigs.MALEDICTUS_TOUGHNESS.get();
                Attack = ServerConfigs.MALEDICTUS_ATTACK.get();
                Resist = ServerConfigs.MALEDICTUS_RESIST.get();
                FireRes = ServerConfigs.MALEDICTUS_FIRE_RESIST.get();
                NatRes = ServerConfigs.MALEDICTUS_NATURE_RESIST.get();
                EndRes = ServerConfigs.MALEDICTUS_ENDER_RESIST.get();
                BloodRes = ServerConfigs.MALEDICTUS_BLOOD_RESIST.get();
                IceRes = ServerConfigs.MALEDICTUS_ICE_RESIST.get();
                LigRes = ServerConfigs.MALEDICTUS_LIGHTNING_RESIST.get();
                EldRes = ServerConfigs.MALEDICTUS_ELDRITCH_RESIST.get();
                HolyRes = ServerConfigs.MALEDICTUS_HOLY_RESIST.get();
                BladeRes = ServerConfigs.MALEDICTUS_BLADE_RESIST.get();
                AbyssRes = ServerConfigs.MALEDICTUS_ABYSSAL_RESIST.get();
                SoundRes = ServerConfigs.MALEDICTUS_MUSIC_RESIST.get();
                WindRes = ServerConfigs.MALEDICTUS_WIND_RESIST.get();
            }

            // Updates mob attributes
            {
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR, Armor);
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR_TOUGHNESS, Tough);
                setIfNonNull((LivingEntity) mob, Attributes.ATTACK_DAMAGE, Attack);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.SPELL_RESIST, Resist);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.FIRE_MAGIC_RESIST, FireRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.NATURE_MAGIC_RESIST, NatRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.ENDER_MAGIC_RESIST, EndRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.BLOOD_MAGIC_RESIST, BloodRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.ICE_MAGIC_RESIST, IceRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, LigRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, EldRes);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.HOLY_MAGIC_RESIST, HolyRes);
                // This needs to be conditional or the game shits itself if the mod is not present
                if (ModList.get().isLoaded("endersequipment")) {
                    setIfNonNull((LivingEntity) mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, BladeRes);
                }
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    setIfNonNull((LivingEntity) mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, AbyssRes);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    setIfNonNull((LivingEntity) mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, SoundRes);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull((LivingEntity) mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, WindRes);
                }
                // Fixed Attributes
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_PIERCE, boss_armor_pen);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_SHRED, boss_armor_shred);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_PIERCE, boss_prot_pen);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_SHRED, boss_prot_shred);
            }

            // We reset this stuff so it doesn't make other mobs go crazy
            {
                SpellPower = 0;
                SchoolPower = 0;
                Resist = 0;
                FireRes = 0;
                IceRes = 0;
                HolyRes = 0;
                NatRes = 0;
                BloodRes = 0;
                EndRes = 0;
                LigRes = 0;
                EldRes = 0;
                AbyssRes = 0;
                BladeRes = 0;
                SoundRes = 0;
                WindRes = 0;
                Armor = 0;
                Tough = 0;
                Attack = 0;
            }
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