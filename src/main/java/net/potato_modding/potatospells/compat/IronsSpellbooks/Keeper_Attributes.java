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
import net.potato_modding.potatospells.compat.CompatFormulas;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

import static net.neoforged.bus.api.EventPriority.LOWEST;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Keeper_Attributes {

    @SubscribeEvent(priority = LOWEST)
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
            Armor += 20 * (1 + m/4);
            Tough += 10 * (1 + m/4);
            Attack += 10.5 * (1 + m/4);
            Resist += Math.pow(1.2, m);
            FireRes += Math.pow(1.75, m);
            NatRes += Math.pow(1.5, m);
            EndRes += Math.pow(1.2, m);
            BldRes += Math.pow(1.9, m);
            IceRes += Math.pow(0.3, m);
            LigRes += Math.pow(2.0, m);
            EldRes += Math.pow(1.7, m);
            HolyRes -= Math.pow(0.5, m);
            BladeRes += Math.pow(1.65, m);
            AbyssRes += Math.pow(1.15, m);
            SoundRes += Math.pow(1.4, m);
            WindRes += Math.pow(1.35, m);
        }

        if (!ServerConfigs.CAT_SWITCH.get()) {
            Armor = ServerConfigs.KEEPER_ARMOR.get();
            Tough = ServerConfigs.KEEPER_TOUGHNESS.get();
            Attack = ServerConfigs.KEEPER_ATTACK.get();
            Resist = ServerConfigs.KEEPER_RESIST.get();
            FireRes = ServerConfigs.KEEPER_FIRE_RESIST.get();
            NatRes = ServerConfigs.KEEPER_NATURE_RESIST.get();
            EndRes = ServerConfigs.KEEPER_ENDER_RESIST.get();
            BldRes = ServerConfigs.KEEPER_BLOOD_RESIST.get();
            IceRes = ServerConfigs.KEEPER_ICE_RESIST.get();
            LigRes = ServerConfigs.KEEPER_LIGHTNING_RESIST.get();
            EldRes = ServerConfigs.KEEPER_ELDRITCH_RESIST.get();
            HolyRes = ServerConfigs.KEEPER_HOLY_RESIST.get();
            BladeRes = ServerConfigs.KEEPER_BLADE_RESIST.get();
            AbyssRes = ServerConfigs.KEEPER_ABYSSAL_RESIST.get();
            SoundRes = ServerConfigs.KEEPER_MUSIC_RESIST.get();
            WindRes = ServerConfigs.KEEPER_WIND_RESIST.get();
        }

        if (mob.getType().is(PotatoTags.KEEPER_MOB)) {
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