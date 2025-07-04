package net.potato_modding.potatospells.compat.Cataclysm;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.potato_modding.potatospells.compat.CompatFormulas;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Leviathan {

    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

        double m = CompatFormulas.attrFormula;

        double Armor = 0;
        double Tough = 0;
        double Attack = 0;
        double Resist = 0;
        double FireRes = 0;
        double NatRes = 0;
        double EndRes = 0;
        double BldRes = 0;
        double IceRes = 0;
        double LigRes = 0;
        double EldRes = 0;
        double HolyRes = 0;
        double BladeRes = 0;
        double AbyssRes = 0;
        double SoundRes = 0;
        double WindRes = 0;

        if (ServerConfigs.CAT_SWITCH.get()) {
            // Amethyst  attributes
            Armor += 15 * (1 + m/4);
            Tough += 15 * (1 + m/4);
            Attack += 10 * (1 + m/4);
            Resist += Math.pow(1.3, m);
            FireRes += Math.pow(2.5, m);
            NatRes += Math.pow(0.75, m);
            EndRes += Math.pow(1.75, m);
            BldRes += Math.pow(0.8, m);
            IceRes += Math.pow(1.9, m);
            LigRes -= Math.pow(0.15, m);
            EldRes += Math.pow(2.0, m);
            HolyRes += Math.pow(0.85, m);
            BladeRes += Math.pow(0.65, m);
            AbyssRes += Math.pow(4.0, m);
            SoundRes += Math.pow(0.95, m);
            WindRes += Math.pow(1.2, m);
        }

        else {
            Armor = ServerConfigs.LEVIA_ARMOR.get();
            Tough = ServerConfigs.LEVIA_TOUGHNESS.get();
            Attack = ServerConfigs.LEVIA_ATTACK.get();
            Resist = ServerConfigs.LEVIA_RESIST.get();
            FireRes = ServerConfigs.LEVIA_FIRE_RESIST.get();
            NatRes = ServerConfigs.LEVIA_NATURE_RESIST.get();
            EndRes = ServerConfigs.LEVIA_ENDER_RESIST.get();
            BldRes = ServerConfigs.LEVIA_BLOOD_RESIST.get();
            IceRes = ServerConfigs.LEVIA_ICE_RESIST.get();
            LigRes = ServerConfigs.LEVIA_LIGHTNING_RESIST.get();
            EldRes = ServerConfigs.LEVIA_ELDRITCH_RESIST.get();
            HolyRes = ServerConfigs.LEVIA_HOLY_RESIST.get();
            BladeRes = ServerConfigs.LEVIA_BLADE_RESIST.get();
            AbyssRes = ServerConfigs.LEVIA_ABYSSAL_RESIST.get();
            SoundRes = ServerConfigs.LEVIA_MUSIC_RESIST.get();
            WindRes = ServerConfigs.LEVIA_WIND_RESIST.get();
        }

        if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.LEVIATHAN)) {
            setIfNonNull(mob, Attributes.ARMOR, Armor);
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
            setIfNonNull(mob, Attributes.ATTACK_DAMAGE, Attack);
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