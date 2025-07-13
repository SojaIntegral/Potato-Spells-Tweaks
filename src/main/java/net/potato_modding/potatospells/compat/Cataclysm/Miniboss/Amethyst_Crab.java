package net.potato_modding.potatospells.compat.Cataclysm.Miniboss;

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
import net.potato_modding.potatospells.utils.PotatoTags;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Amethyst_Crab {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributeCataclysm(EntityJoinLevelEvent event) {
        var mob = event.getEntity();

        if (ModList.get().isLoaded("cataclysm") && mob.getType().is(PotatoTags.AMETHYST_CRAB)) {

            // Amethyst  attributes
            Resist += 1.3 * mini_mod;
            FireRes += 0.85 * mini_mod;
            IceRes += 0.7 * mini_mod;
            HolyRes += 1.15 * mini_mod;
            NatRes += 1.3 * mini_mod;
            BldRes += 1.45 * mini_mod;
            EndRes += 1.2 * mini_mod;
            LigRes += 1.25 * mini_mod;
            EldRes += 0.5 * mini_mod;
            AbyssRes += 1.4 * mini_mod;
            BladeRes += 1.5 * mini_mod;
            SoundRes += 0.9 * mini_mod;
            WindRes += 1.05 * mini_mod;
            Armor += 15 * spec_mod;
            Tough += 15 * spec_mod;
            Attack += 9.5 * spec_mod;

            // Updates mob attributes
            {
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR, Armor);
                setIfNonNull((LivingEntity) mob, Attributes.ARMOR_TOUGHNESS, Tough);
                setIfNonNull((LivingEntity) mob, Attributes.ATTACK_DAMAGE, Attack);
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
                // Fixed Attributes
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_PIERCE, mini_armor_pen);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.ARMOR_SHRED, mini_armor_shred);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_PIERCE, mini_prot_pen);
                setIfNonNull((LivingEntity) mob, ALObjects.Attributes.PROT_SHRED, mini_prot_shred);
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