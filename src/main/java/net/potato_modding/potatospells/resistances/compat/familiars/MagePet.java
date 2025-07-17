package net.potato_modding.potatospells.resistances.compat.familiars;

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
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.FamiliarsNaturesHandler;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class MagePet {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttribute(FinalizeSpawnEvent event) {
        var mob = event.getEntity();
        var typeKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());

        if (typeKey.getNamespace().equals("alshanex_familiars") && typeKey.getPath().equals("mage_pet")
                && ServerConfigs.FAMILIAR_TOGGLE.get()) {

            if (ServerConfigs.FAMILIAR_NATURE.get()) {
                FamiliarsNaturesHandler.applySpawnModifiers(mob);
            }

            // Adds +- 30% to Familiars' attributes
            double[] attrVar = new double[10];
            for (int i = 0; i < attrVar.length; i++) {
                attrVar[i] = 1 + Math.random() * randMax;
            }

            SpellPower += 0.85 * mob_mod * attrVar[0];
            Resist += 1.2 * mob_mod * attrVar[1];
            FireRes += 1.2 * mob_mod * attrVar[2];
            IceRes += 1.5 * mob_mod * attrVar[2];
            HolyRes += mob_mod * attrVar[2];
            NatRes += 1.2 * mob_mod * attrVar[2];
            EvokeRes += mob_mod * attrVar[2];
            BloodRes += 1.2 * mob_mod * attrVar[2];
            EndRes += mob_mod * attrVar[2];
            LigRes += 1.5 * mob_mod * attrVar[2];
            EldRes += 0.8 * mob_mod * attrVar[2];
            AbyssRes += 0.5 * mob_mod * attrVar[2];
            BladeRes += 0.5 * mob_mod * attrVar[2];
            SoundRes += mob_mod * attrVar[2];
            WindRes += mob_mod * attrVar[2];
            Armor += 0.5 * spec_mod * attrVar[3];
            Tough += 0.5 * spec_mod * attrVar[4];

            // Updates mob attributes
            {
                setIfNonNull(mob, Attributes.ARMOR, Armor);
                setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
                setIfNonNull(mob, AttributeRegistry.SPELL_POWER, SpellPower);
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, Resist);
                setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, FireRes);
                setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, NatRes);
                setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, EndRes);
                setIfNonNull(mob, AttributeRegistry.EVOCATION_MAGIC_RESIST, EvokeRes);
                setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, BloodRes);
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
                setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, SoundRes);
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, WindRes);
                }
                // Fixed Attributes
                setIfNonNull(mob, ALObjects.Attributes.ARMOR_PIERCE, mob_armor_pen * attrVar[6]);
                setIfNonNull(mob, ALObjects.Attributes.ARMOR_SHRED, mob_armor_shred * attrVar[6]);
                setIfNonNull(mob, ALObjects.Attributes.PROT_PIERCE, mob_prot_pen * attrVar[7]);
                setIfNonNull(mob, ALObjects.Attributes.PROT_SHRED, mob_prot_shred * attrVar[7]);
                setIfNonNull(mob, ALObjects.Attributes.CRIT_CHANCE, 0.3 * attrVar[8]);
                setIfNonNull(mob, ALObjects.Attributes.CRIT_DAMAGE, 1.75 * attrVar[9]);
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
                EvokeRes = 0;
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
    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }
}