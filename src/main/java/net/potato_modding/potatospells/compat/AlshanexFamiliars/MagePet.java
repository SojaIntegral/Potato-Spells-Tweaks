package net.potato_modding.potatospells.compat.AlshanexFamiliars;

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
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class MagePet {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttribute(FinalizeSpawnEvent event) {
        var mob = event.getEntity();
        var typeKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());

        if (typeKey.getNamespace().equals("alshanex_familiars") && typeKey.getPath().equals("mage_pet")) {

            // Adds +- 30% to Familiars' attributes
            double[] attrVar = new double[10];
            for (int i = 0; i < attrVar.length; i++) {
                attrVar[i] = randMin + Math.random() * randMax;
            }

            // Amethyst  attributes
            SpellPower += 1.35 * mob_mod * attrVar[0];
            Resist += 1.15 * mob_mod * attrVar[1];
            FireRes += 1.1 * mob_mod * attrVar[2];
            IceRes += 1.1 * mob_mod * attrVar[2];
            HolyRes += 1.1 * mob_mod * attrVar[2];
            NatRes += 1.1 * mob_mod * attrVar[2];
            BloodRes += 1.1 * mob_mod * attrVar[2];
            EndRes += 1.1 * mob_mod * attrVar[2];
            LigRes += 1.1 * mob_mod * attrVar[2];
            EldRes += 1.1 * mob_mod * attrVar[2];
            AbyssRes += 1.1 * mob_mod * attrVar[2];
            BladeRes += 1.1 * mob_mod * attrVar[2];
            SoundRes += 1.1 * mob_mod * attrVar[2];
            WindRes += 1.1 * mob_mod * attrVar[2];
            Armor += 9 * spec_mod * attrVar[3];
            Tough += 9 * spec_mod * attrVar[4];
            Attack += 8.0 * spec_mod * attrVar[5];

            // Updates mob attributes
            {
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR, Armor);
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR_TOUGHNESS, Tough);
                setIfNonNull((LivingEntity) mob, Attributes.ATTACK_DAMAGE, Attack);
                setIfNonNull((LivingEntity) mob, AttributeRegistry.SPELL_POWER, SpellPower);
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
                setIfNonNull((LivingEntity) mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, SoundRes);
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull((LivingEntity) mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, WindRes);
                }
                // Fixed Attributes
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_PIERCE, mob_armor_pen * attrVar[6]);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_SHRED, mob_armor_shred * attrVar[6]);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_PIERCE, mob_prot_pen * attrVar[7]);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_SHRED, mob_prot_shred * attrVar[7]);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.CRIT_CHANCE, 25 * attrVar[8]);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.CRIT_DAMAGE, 150 * attrVar[9]);
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