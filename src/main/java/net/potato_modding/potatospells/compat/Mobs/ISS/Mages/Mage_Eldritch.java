package net.potato_modding.potatospells.compat.Mobs.ISS.Mages;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.potato_modding.potatospells.utils.PotatoTags;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Mage_Eldritch {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributePreset(EntityJoinLevelEvent event) {
        var mob = event.getEntity();

            // Amethyst  attributes
            Armor += 6 * (1 + m/3.25);
            Tough += 9 * (1 + m/3.25);
            Attack += 9.0 * (1 + m/3.25);
            SpellPower += 0.25 * m;
            SchoolPower += 6.66 * m;
            Resist += 2.0 * m;
            FireRes += 0.65 * m;
            NatRes += 1.25 * m;
            EndRes += 1.4 * m;
            BldRes += 1.35 * m;
            IceRes += 1.2 * m;
            LigRes += 1.1 * m;
            EldRes += 1.5 * m;
            HolyRes -= 1.25 * m;
            BladeRes += 0.55 * m;
            AbyssRes += 1.4 * m;
            SoundRes += 0.1 * m;
            WindRes += 0.8 * m;

        if (mob.getType().is(PotatoTags.MAGE_NATURE)) {
            setIfNonNull((LivingEntity) mob, Attributes.ARMOR, Armor);
            setIfNonNull((LivingEntity) mob, Attributes.ARMOR_TOUGHNESS, Tough);
            setIfNonNull((LivingEntity) mob, Attributes.ATTACK_DAMAGE, Attack);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.SPELL_POWER, SpellPower);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.NATURE_SPELL_POWER, SchoolPower);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.SPELL_RESIST, Resist);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.FIRE_MAGIC_RESIST, FireRes);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.NATURE_MAGIC_RESIST, NatRes);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.ENDER_MAGIC_RESIST, EndRes);
            setIfNonNull((LivingEntity) mob, AttributeRegistry.BLOOD_MAGIC_RESIST, BldRes);
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
    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }
}