package net.potato_modding.potatospells.compat.Bosses.ISS;

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

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Tyros {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        var mob = event.getEntity();

        if (ServerConfigs.BOSS_SWITCH.get()) {
            // Amethyst  attributes
            Armor += 25 * (1 + m/4);
            Tough += 10 * (1 + m/4);
            Attack += 11 * (1 + m/4);
            SpellPower += Math.pow(1.2, m);
            SchoolPower += Math.pow(1.65, m);
            Resist += Math.pow(1.3, m);
            FireRes += Math.pow(2.0, m);
            NatRes += Math.pow(1.5, m);
            EndRes += Math.pow(0.8, m);
            BldRes += Math.pow(1.35, m);
            IceRes += Math.pow(0.3, m);
            LigRes += Math.pow(1.2, m);
            EldRes += Math.pow(1.0, m);
            HolyRes += Math.pow(0.6, m);
            BladeRes += Math.pow(1.1, m);
            AbyssRes += Math.pow(0.25, m);
            SoundRes += Math.pow(0.75, m);
            WindRes += Math.pow(0.8, m);
        }

        else {
            Armor = ServerConfigs.TYROS_ARMOR.get();
            Tough = ServerConfigs.TYROS_TOUGHNESS.get();
            Attack = ServerConfigs.TYROS_ATTACK.get();
            Resist = ServerConfigs.TYROS_RESIST.get();
            SpellPower = ServerConfigs.TYROS_POWER.get();
            SchoolPower = ServerConfigs.TYROS_FIRE_SPELL.get();
            FireRes = ServerConfigs.TYROS_FIRE_RESIST.get();
            NatRes = ServerConfigs.TYROS_NATURE_RESIST.get();
            EndRes = ServerConfigs.TYROS_ENDER_RESIST.get();
            BldRes = ServerConfigs.TYROS_BLOOD_RESIST.get();
            IceRes = ServerConfigs.TYROS_ICE_RESIST.get();
            LigRes = ServerConfigs.TYROS_LIGHTNING_RESIST.get();
            EldRes = ServerConfigs.TYROS_ELDRITCH_RESIST.get();
            HolyRes = ServerConfigs.TYROS_HOLY_RESIST.get();
            BladeRes = ServerConfigs.TYROS_BLADE_RESIST.get();
            AbyssRes = ServerConfigs.TYROS_ABYSSAL_RESIST.get();
            SoundRes = ServerConfigs.TYROS_MUSIC_RESIST.get();
            WindRes = ServerConfigs.TYROS_WIND_RESIST.get();
        }

        if (mob.getType().is(PotatoTags.TYROS_BOSS)) {
            setIfNonNull(mob, Attributes.ARMOR, Armor);
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, Attack);
            setIfNonNull(mob, AttributeRegistry.SPELL_POWER, SpellPower);
            setIfNonNull(mob, AttributeRegistry.FIRE_SPELL_POWER, SchoolPower);
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, Resist);
            setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, FireRes);
            setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, NatRes);
            setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, EndRes);
            setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, BldRes);
            setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, IceRes);
            setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, LigRes);
            setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, EldRes);
            setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, HolyRes);
            // This needs to be conditional or the game shits itself if the mod is not present
            if (ModList.get().isLoaded("endersequipment")) {
                setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, BladeRes);
            }
            if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, AbyssRes);
            }
            if (ModList.get().isLoaded("alshanex_familiars")) {
                setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, SoundRes);
            }
            if (ModList.get().isLoaded("aero_additions")) {
                setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, WindRes);
            }
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
            BldRes = 0;
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
