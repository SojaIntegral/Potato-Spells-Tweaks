package net.potato_modding.potatospells.compat.Cataclysm.Bosses;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Netherite_Monstrosity {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributeCataclysm(EntityJoinLevelEvent event) {
        var mob = event.getEntity();

        if (ModList.get().isLoaded("cataclysm") && mob.getType().is(PotatoTags.NETHERITE_MONSTROSITY)) {

            if (!ServerConfigs.NETMONST_SWITCH.get()) {
                // Amethyst  attributes
                Resist += 1.15 * boss_mod;
                FireRes += 2.15 * boss_mod;
                IceRes -= 0.85 * boss_mod;
                HolyRes += 1.35 * boss_mod;
                NatRes += 1.85 * boss_mod;
                BloodRes += 1.65 * boss_mod;
                EndRes += 0.85 * boss_mod;
                LigRes += 0.9 * boss_mod;
                EldRes += 1.25 * boss_mod;
                AbyssRes -= 0.7 * boss_mod;
                BladeRes += 2.0 * boss_mod;
                SoundRes += 1.35 * boss_mod;
                WindRes += 1.65 * boss_mod;
                Armor += 30 * spec_mod;
                Tough += 20 * spec_mod;
                Attack += 9 * spec_mod;
            }

            else {
                Armor = ServerConfigs.NETMONST_ARMOR.get();
                Tough = ServerConfigs.NETMONST_TOUGHNESS.get();
                Attack = ServerConfigs.NETMONST_ATTACK.get();
                Resist = ServerConfigs.NETMONST_RESIST.get();
                FireRes = ServerConfigs.NETMONST_FIRE_RESIST.get();
                NatRes = ServerConfigs.NETMONST_NATURE_RESIST.get();
                EndRes = ServerConfigs.NETMONST_ENDER_RESIST.get();
                BloodRes = ServerConfigs.NETMONST_BLOOD_RESIST.get();
                IceRes = ServerConfigs.NETMONST_ICE_RESIST.get();
                LigRes = ServerConfigs.NETMONST_LIGHTNING_RESIST.get();
                EldRes = ServerConfigs.NETMONST_ELDRITCH_RESIST.get();
                HolyRes = ServerConfigs.NETMONST_HOLY_RESIST.get();
                BladeRes = ServerConfigs.NETMONST_BLADE_RESIST.get();
                AbyssRes = ServerConfigs.NETMONST_ABYSSAL_RESIST.get();
                SoundRes = ServerConfigs.NETMONST_MUSIC_RESIST.get();
                WindRes = ServerConfigs.NETMONST_WIND_RESIST.get();
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