package net.potato_modding.potatospells.compat.Bosses.Cataclysm;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

import java.util.Objects;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Netherite_Monstrosity {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        var mob = event.getEntity();

        if (ServerConfigs.BOSS_SWITCH.get()) {
            // Amethyst  attributes
            Resist += 1.3 * m;
            FireRes += 2.35 * m;
            IceRes -= 0.85 * m;
            HolyRes += 1.35 * m;
            NatRes += 1.85 * m;
            BldRes += 1.65 * m;
            EndRes += 0.85 * m;
            LigRes += 0.9 * m;
            EldRes += 1.25 * m;
            AbyssRes -= 0.7 * m;
            BladeRes += 2.0 * m;
            SoundRes += 1.35 * m;
            WindRes += 1.65 * m;
            Armor += 20 * (1 + m / 3.25);
            Tough += 20 * (1 + m / 3.25);
        }

        else {
            Armor = ServerConfigs.NETMONST_ARMOR.get();
            Tough = ServerConfigs.NETMONST_TOUGHNESS.get();
            Resist = ServerConfigs.NETMONST_RESIST.get();
            FireRes = ServerConfigs.NETMONST_FIRE_RESIST.get();
            NatRes = ServerConfigs.NETMONST_NATURE_RESIST.get();
            EndRes = ServerConfigs.NETMONST_ENDER_RESIST.get();
            BldRes = ServerConfigs.NETMONST_BLOOD_RESIST.get();
            IceRes = ServerConfigs.NETMONST_ICE_RESIST.get();
            LigRes = ServerConfigs.NETMONST_LIGHTNING_RESIST.get();
            EldRes = ServerConfigs.NETMONST_ELDRITCH_RESIST.get();
            HolyRes = ServerConfigs.NETMONST_HOLY_RESIST.get();
            BladeRes = ServerConfigs.NETMONST_BLADE_RESIST.get();
            AbyssRes = ServerConfigs.NETMONST_ABYSSAL_RESIST.get();
            SoundRes = ServerConfigs.NETMONST_MUSIC_RESIST.get();
            WindRes = ServerConfigs.NETMONST_WIND_RESIST.get();
        }

        if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.NETHERITE_MONSTROSITY)) {
            setIfNonNull(mob, Attributes.ARMOR, Armor);
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
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

    private static final ResourceLocation ATTR_FIX = Objects.requireNonNull(ResourceLocation.tryParse("potatospells:damage_boost"));

    @SubscribeEvent
    public static void onMobDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Mob mob)) return;

        if (Objects.requireNonNull(mob.getAttribute(Attributes.ATTACK_DAMAGE)).getModifier(ATTR_FIX) == null) {
            float originalDamage = event.getContainer().getOriginalDamage();
            event.setAmount(originalDamage * 0);

            if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.NETHERITE_MONSTROSITY)) {
                setIfNonNull(mob, Attributes.ARMOR, Armor);
                setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
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