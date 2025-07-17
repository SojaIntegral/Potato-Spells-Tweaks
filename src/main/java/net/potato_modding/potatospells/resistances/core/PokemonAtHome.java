package net.potato_modding.potatospells.resistances.core;

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
import net.potato_modding.potatospells.registries.PotatoTags;
import net.potato_modding.potatospells.utils.FamiliarsNaturesHandler;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class PokemonAtHome {

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttribute(FinalizeSpawnEvent event) {
        var mob = event.getEntity();
        var typeKey = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());

        if (mob.getType().is(PotatoTags.TYPE_BLOOD)) {

            if(ServerConfigs.FAMILIAR_NATURE.get() && mob.getType().is(PotatoTags.HAS_NATURE)) {
                FamiliarsNaturesHandler.applySpawnModifiers(mob);
            }

            // Adds + 0~15% to Familiars' attributes
            double[] attrVar = new double[10];
            for (int i = 0; i < attrVar.length; i++) {
                attrVar[i] = Math.random() * randMax;
            }

            // Class modifier
            double mobType = 0;
            if(mob.getType().is(PotatoTags.BOSS)) {
                mobType = boss_mod;
                Armor += 20 * (1 + attrVar[0]);
                Tough += 15 * (1 + attrVar[1]);
                Attack = 9 * (1 + attrVar[1]);
            }
            if(mob.getType().is(PotatoTags.MINIBOSS)) {
                mobType = mini_mod;
                Armor += 12 * (1 + attrVar[0]);
                Tough += 10 * (1 + attrVar[1]);
                Attack = 7.5 * (1 + attrVar[1]);
            }
            if(mob.getType().is(PotatoTags.NORMAL)) {
                mobType = mob_mod;
                Armor += 5 * (1 + attrVar[0]);
                Tough += 4 * (1 + attrVar[1]);
                Attack = 5 * (1 + attrVar[1]);
            }
            if(mob.getType().is(PotatoTags.SUMMON)) {
                mobType = summon_mod;
                Armor += 6 * (1 + attrVar[0]);
                Tough += 6 * (1 + attrVar[1]);
                Attack = 6 * (1 + attrVar[1]);
            }

            // Type Modifiers
            if(mob.getType().is(PotatoTags.RACE_HUMAN)) {
                SpellPower = 1.1 + attrVar[2];
                CastReduction = 1.1 + attrVar[3];
                Resist = 1.2 + attrVar[4];
                FireRes += 0 + attrVar[4];
                IceRes -= 0.1 + attrVar[4];
                HolyRes += 0.1 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes += 0 + attrVar[4];
                BloodRes -= 0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes -= 0.1 + attrVar[4];
                EldRes -= 0.1 + attrVar[4];
                AbyssRes -= 0.1 + attrVar[4];
                TechRes += 0.1 + attrVar[4];
                BladeRes -= 0.1 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes += 0.1 + attrVar[4];
                WindRes += 0 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes -= 0.1 + attrVar[4];
                DuneRes += 0.1 + attrVar[4];
                AquaRes += 0.1 + attrVar[4];
                ArmorPierce = 3 + attrVar[5];
                ArmorShred = 0.2 + attrVar[5];
                ProtPierce = 1.5 + attrVar[6];
                ProtShred = 0.1 + attrVar[6];
                CritDmg = 0.2 + attrVar[7];
                Crit = 1.5 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_UNDEAD)) {
                SpellPower = 1.2 + attrVar[2];
                CastReduction = 0.9 + attrVar[3];
                Resist = 1.1 + attrVar[4];
                FireRes -= 0.1 + attrVar[4];
                IceRes += 0.1 + attrVar[4];
                HolyRes -= 0.1 + attrVar[4];
                NatRes -= 0.1 + attrVar[4];
                EvokeRes += 0.1 + attrVar[4];
                BloodRes += 0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes += 0.1 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0.1 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes -= 0.1 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes -= 0.1 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes -= 0.1 + attrVar[4];
                SpiritRes += 0.1 + attrVar[4];
                DuneRes += 0 + attrVar[4];
                AquaRes += 0 + attrVar[4];
                ArmorPierce = 2 + attrVar[5];
                ArmorShred = 0.15 + attrVar[5];
                ProtPierce = 2 + attrVar[6];
                ProtShred = 0.1 + attrVar[6];
                CritDmg = 1.35 + attrVar[7];
                Crit = 0.15 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_BRUTE)) {
                SpellPower = 1 + attrVar[2];
                CastReduction = 0.9 + attrVar[3];
                Resist = 1.2 + attrVar[4];
                FireRes += 0 + attrVar[4];
                IceRes -= 0.1 + attrVar[4];
                HolyRes += 0 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes += 0 + attrVar[4];
                BloodRes -= 0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes -= 0.1 + attrVar[4];
                EldRes += 0 + attrVar[4];
                AbyssRes -= 0.1 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes += 0.1 + attrVar[4];
                MindRes += 0 + attrVar[4];
                SoundRes += 0 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0 + attrVar[4];
                DuneRes += 0.1 + attrVar[4];
                AquaRes += 0.1 + attrVar[4];
                ArmorPierce = 5 + attrVar[5];
                ArmorShred = 0.1 + attrVar[5];
                ProtPierce = 1 + attrVar[6];
                ProtShred = 1.5 + attrVar[6];
                CritDmg = 1.4 + attrVar[7];
                Crit = 0.25 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_INSECT)) {
                SpellPower = 0.9 + attrVar[2];
                CastReduction = 1 + attrVar[3];
                Resist = 1 + attrVar[4];
                FireRes -= 0.1 + attrVar[4];
                IceRes -= 0.1 + attrVar[4];
                HolyRes += 0 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes += 0 + attrVar[4];
                BloodRes += 0 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes += 0 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0.1 + attrVar[4];
                TechRes += 0.1 + attrVar[4];
                BladeRes += 0 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes += 0 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0 + attrVar[4];
                DuneRes += 0.1 + attrVar[4];
                AquaRes -= 0.1 + attrVar[4];
                ArmorPierce = 1 + attrVar[5];
                ArmorShred = 0.25 + attrVar[5];
                ProtPierce = 1 + attrVar[6];
                ProtShred = 0.15 + attrVar[6];
                CritDmg = 1.4 + attrVar[7];
                Crit = 0.25 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_FLYING)) {
                SpellPower = 0.95 + attrVar[2];
                CastReduction = 1.25 + attrVar[3];
                Resist = 0.9 + attrVar[4];
                FireRes += 0 + attrVar[4];
                IceRes -= 0.1 + attrVar[4];
                HolyRes += 0 + attrVar[4];
                NatRes += 0 + attrVar[4];
                EvokeRes += 0 + attrVar[4];
                BloodRes -= -0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes -= 0.1 + attrVar[4];
                EldRes += 0 + attrVar[4];
                AbyssRes += 0 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes += 0.1 + attrVar[4];
                MindRes += 0 + attrVar[4];
                SoundRes -= 0.1 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0 + attrVar[4];
                DuneRes += 0 + attrVar[4];
                AquaRes -= 0.1 + attrVar[4];
                ArmorPierce = 1 + attrVar[5];
                ArmorShred = 0.1 + attrVar[5];
                ProtPierce = 1 + attrVar[6];
                ProtShred = 0.1 + attrVar[6];
                CritDmg = 1.35 + attrVar[7];
                Crit = 0.4 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_GOLEM)) {
                SpellPower = 1.1 + attrVar[2];
                CastReduction = 0.8 + attrVar[3];
                Resist = 1.3 + attrVar[4];
                FireRes += 0.1 + attrVar[4];
                IceRes += 0.1 + attrVar[4];
                HolyRes -= 0.1 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes += 0.1 + attrVar[4];
                BloodRes += 0.1 + attrVar[4];
                EndRes -= 0.1 + attrVar[4];
                LigRes += 0.1 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0.1 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes += 0.1 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes -= 0.1 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes -= 0.1 + attrVar[4];
                SpiritRes += 0.1 + attrVar[4];
                DuneRes -= 0.1 + attrVar[4];
                AquaRes += 0 + attrVar[4];
                ArmorPierce = 3 + attrVar[5];
                ArmorShred = 0.15 + attrVar[5];
                ProtPierce = 1 + attrVar[6];
                ProtShred = 0.15 + attrVar[6];
                CritDmg = 1.6 + attrVar[7];
                Crit = 0.1 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_CONSTRUCT)) {
                SpellPower = 0.8 + attrVar[2];
                CastReduction = 1.5 + attrVar[3];
                Resist = 1.25 + attrVar[4];
                FireRes += 0.1 + attrVar[4];
                IceRes += 0.1 + attrVar[4];
                HolyRes += 0 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes += 0.1 + attrVar[4];
                BloodRes += 0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes -= 0.1 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0 + attrVar[4];
                TechRes += 0.1 + attrVar[4];
                BladeRes += 0 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes += 0 + attrVar[4];
                WindRes += 0.1 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0.1 + attrVar[4];
                DuneRes -= 0.1 + attrVar[4];
                AquaRes -= 0.1 + attrVar[4];
                ArmorPierce = 2 + attrVar[5];
                ArmorShred = 0.2 + attrVar[5];
                ProtPierce = 2 + attrVar[6];
                ProtShred = 0.2 + attrVar[6];
                CritDmg = 1.25 + attrVar[7];
                Crit = 1 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_FISH)) {
                SpellPower = 0.95 + attrVar[2];
                CastReduction = 1.2 + attrVar[3];
                Resist = 0.9 + attrVar[4];
                FireRes += 0.1 + attrVar[4];
                IceRes += 0.1 + attrVar[4];
                HolyRes += 0 + attrVar[4];
                NatRes += 0.1 + attrVar[4];
                EvokeRes -= 0.1 + attrVar[4];
                BloodRes -= 0.1 + attrVar[4];
                EndRes += 0 + attrVar[4];
                LigRes -= 0.1 + attrVar[4];
                EldRes += 0 + attrVar[4];
                AbyssRes += 0.1 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes -= 0.1 + attrVar[4];
                MindRes += 0 + attrVar[4];
                SoundRes -= 0.1 + attrVar[4];
                WindRes += 0 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0 + attrVar[4];
                DuneRes -= 0.1 + attrVar[4];
                AquaRes += 0.1 + attrVar[4];
                ArmorPierce = 1.5 + attrVar[5];
                ArmorShred = 0.15 + attrVar[5];
                ProtPierce = 1.5 + attrVar[6];
                ProtShred = 0.15 + attrVar[6];
                CritDmg = 1.45 + attrVar[7];
                Crit = 0.15 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_SPIRIT)) {
                SpellPower = 1.25 + attrVar[2];
                CastReduction = 1.25 + attrVar[3];
                Resist = 1.1 + attrVar[4];
                FireRes += 0 + attrVar[4];
                IceRes += 0 + attrVar[4];
                HolyRes -= 0.1 + attrVar[4];
                NatRes += 0 + attrVar[4];
                EvokeRes += 0.1 + attrVar[4];
                BloodRes -= 0.1 + attrVar[4];
                EndRes -= 0.1 + attrVar[4];
                LigRes += 0 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes += 0.1 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes += 0 + attrVar[4];
                WindRes += 0 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes -= 0.1 + attrVar[4];
                DuneRes += 0 + attrVar[4];
                AquaRes += 0 + attrVar[4];
                ArmorPierce = 3 + attrVar[5];
                ArmorShred = 0.25 + attrVar[5];
                ProtPierce = 3 + attrVar[6];
                ProtShred = 0.25 + attrVar[6];
                CritDmg = 1.15 + attrVar[7];
                Crit = 0.1 + attrVar[7];
            }
            if(mob.getType().is(PotatoTags.RACE_AMORPH)) {
                SpellPower = 1.05 + attrVar[2];
                CastReduction = 1.05 + attrVar[3];
                Resist = 1 + attrVar[4];
                FireRes += 0 + attrVar[4];
                IceRes += 0 + attrVar[4];
                HolyRes -= 0.1 + attrVar[4];
                NatRes -= 0.1 + attrVar[4];
                EvokeRes += 0.1 + attrVar[4];
                BloodRes += 0.1 + attrVar[4];
                EndRes += 0.1 + attrVar[4];
                LigRes += 0.1 + attrVar[4];
                EldRes += 0.1 + attrVar[4];
                AbyssRes += 0.1 + attrVar[4];
                TechRes -= 0.1 + attrVar[4];
                BladeRes -= 0.1 + attrVar[4];
                MindRes += 0.1 + attrVar[4];
                SoundRes -= 0.1 + attrVar[4];
                WindRes += 0 + attrVar[4];
                SymRes += 0 + attrVar[4];
                SpiritRes += 0.1 + attrVar[4];
                DuneRes += 0 + attrVar[4];
                AquaRes += 0 + attrVar[4];
                ArmorPierce = 1 + attrVar[5];
                ArmorShred = 0.1 + attrVar[5];
                ProtPierce = 2 + attrVar[6];
                ProtShred = 0.15 + attrVar[6];
                CritDmg = 1.55 + attrVar[7];
                Crit = 0.2 + attrVar[7];
            }

            // School Modifiers
            if(mob.getType().is(PotatoTags.TYPE_BLOOD)) {
                FireRes *= 0.4 * mobType;
                IceRes *= 1.25 * mobType;
                HolyRes *= 0.4 * mobType;
                NatRes *= 1.2 * mobType;
                EvokeRes *= 1.6 * mobType;
                BloodRes *= 1.6 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 1.2 * mobType;
                AbyssRes *= 1.6 * mobType;
                TechRes *= 1.2 * mobType;
                BladeRes *= 0.4 * mobType;
                MindRes *= 1.6 * mobType;
                SoundRes *= 0.4 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 0.4 * mobType;
                SpiritRes *= 1.6 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 1.6 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_ELDRITCH)) {
                FireRes *= 0.8 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 0.4 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1.6 * mobType;
                BloodRes *= 1 * mobType;
                EndRes *= 1.2 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 1.6 * mobType;
                AbyssRes *= 1 * mobType;
                TechRes *= 0.4 * mobType;
                BladeRes *= 0.4 * mobType;
                MindRes *= 1.6 * mobType;
                SoundRes *= 0.4 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 1.6 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_ENDER)) {
                FireRes *= 1 * mobType;
                IceRes *= 1 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 1 * mobType;
                EvokeRes *= 1.6 * mobType;
                BloodRes *= 1 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1 * mobType;
                EldRes *= 0.8 * mobType;
                AbyssRes *= 1.2 * mobType;
                TechRes *= 1 * mobType;
                BladeRes *= 1.2 * mobType;
                MindRes *= 1.2 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 1.2 * mobType;
                SpiritRes *= 1 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 1 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_EVOKATION)) {
                FireRes *= 0.8 * mobType;
                IceRes *= 1 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 1 * mobType;
                EvokeRes *= 1.6 * mobType;
                BloodRes *= 0.4 * mobType;
                EndRes *= 1.2 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 0.8 * mobType;
                TechRes *= 0.8 * mobType;
                BladeRes *= 0.4 * mobType;
                MindRes *= 1.2 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 1.2 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 0.4 * mobType;
                DuneRes *= 1.6 * mobType;
                AquaRes *= 1 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_FIRE)) {
                FireRes *= 1.6 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 1.2 * mobType;
                AbyssRes *= 0.8 * mobType;
                TechRes *= 1.2 * mobType;
                BladeRes *= 1 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 0.8 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 0.8 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 0.4 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_HOLY)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1.6 * mobType;
                NatRes *= 1 * mobType;
                EvokeRes *= 1.2 * mobType;
                BloodRes *= 1.6 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 1.6 * mobType;
                AbyssRes *= 1.2 * mobType;
                TechRes *= 0.4 * mobType;
                BladeRes *= 0.4 * mobType;
                MindRes *= 0.4 * mobType;
                SoundRes *= 1.6 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 1 * mobType;
                SpiritRes *= 1.6 * mobType;
                DuneRes *= 0.8 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_ICE)) {
                FireRes *= 0.8 * mobType;
                IceRes *= 1.6 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 0.8 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1.2 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 0.8 * mobType;
                AbyssRes *= 1 * mobType;
                TechRes *= 0.8 * mobType;
                BladeRes *= 0.8 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 0.8 * mobType;
                WindRes *= 1.6 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 1.2 * mobType;
                DuneRes *= 0.8 * mobType;
                AquaRes *= 0.8 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_LIGHTNING)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.6 * mobType;
                HolyRes *= 1 * mobType;
                NatRes *= 1.2 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1.2 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1.6 * mobType;
                EldRes *= 0.8 * mobType;
                AbyssRes *= 1.6 * mobType;
                TechRes *= 1.6 * mobType;
                BladeRes *= 1.6 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 1 * mobType;
                SpiritRes *= 1 * mobType;
                DuneRes *= 0.4 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_NATURE)) {
                FireRes *= 0.4 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1 * mobType;
                NatRes *= 1 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 0.4 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 1.2 * mobType;
                TechRes *= 0.4 * mobType;
                BladeRes *= 0.8 * mobType;
                MindRes *= 1.2 * mobType;
                SoundRes *= 1.2 * mobType;
                WindRes *= 1.6 * mobType;
                SymRes *= 1.2 * mobType;
                SpiritRes *= 0.8 * mobType;
                DuneRes *= 0.4 * mobType;
                AquaRes *= 1.6 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_WIND)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 0.4 * mobType;
                HolyRes *= 1 * mobType;
                NatRes *= 0.4 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 0.8 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.4 * mobType;
                EldRes *= 0.8 * mobType;
                AbyssRes *= 1.6 * mobType;
                TechRes *= 1.2 * mobType;
                BladeRes *= 1.2 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 1.2 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 1 * mobType;
                SpiritRes *= 1 * mobType;
                DuneRes *= 0.4 * mobType;
                AquaRes *= 0.8 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_ABYSS)) {
                FireRes *= 1.6 * mobType;
                IceRes *= 1 * mobType;
                HolyRes *= 1.2 * mobType;
                NatRes *= 1.2 * mobType;
                EvokeRes *= 1.2 * mobType;
                BloodRes *= 1.2 * mobType;
                EndRes *= 1.2 * mobType;
                LigRes *= 0.4 * mobType;
                EldRes *= 1.2 * mobType;
                AbyssRes *= 0 * mobType;
                TechRes *= 1.6 * mobType;
                BladeRes *= 1.6 * mobType;
                MindRes *= 1.6 * mobType;
                SoundRes *= 0.8 * mobType;
                WindRes *= 0.4 * mobType;
                SymRes *= 0.4 * mobType;
                SpiritRes *= 1.2 * mobType;
                DuneRes *= 0.4 * mobType;
                AquaRes *= 1.6 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_TECHNOMANCY)) {
                FireRes *= 0.8 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1.6 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1.2 * mobType;
                BloodRes *= 0.8 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.4 * mobType;
                EldRes *= 1.6 * mobType;
                AbyssRes *= 0.4 * mobType;
                TechRes *= 1 * mobType;
                BladeRes *= 0.8 * mobType;
                MindRes *= 1.2 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 0.8 * mobType;
                SymRes *= 1.6 * mobType;
                SpiritRes *= 0.8 * mobType;
                DuneRes *= 0.8 * mobType;
                AquaRes *= 0.4 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_BLADE)) {
                FireRes *= 1 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1.2 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1.2 * mobType;
                EndRes *= 0.8 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 0.8 * mobType;
                AbyssRes *= 1.2 * mobType;
                TechRes *= 1.2 * mobType;
                BladeRes *= 1 * mobType;
                MindRes *= 1 * mobType;
                SoundRes *= 0.8 * mobType;
                WindRes *= 0.8 * mobType;
                SymRes *= 1.2 * mobType;
                SpiritRes *= 0.4 * mobType;
                DuneRes *= 1.6 * mobType;
                AquaRes *= 0.4 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_MIND)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1.6 * mobType;
                NatRes *= 0.8 * mobType;
                EvokeRes *= 0.8 * mobType;
                BloodRes *= 0.4 * mobType;
                EndRes *= 0.8 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 0.4 * mobType;
                TechRes *= 0.8 * mobType;
                BladeRes *= 1.6 * mobType;
                MindRes *= 1 * mobType;
                SoundRes *= 1.2 * mobType;
                WindRes *= 1.2 * mobType;
                SymRes *= 1.6 * mobType;
                SpiritRes *= 0.4 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_SOUND)) {
                FireRes *= 1 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 1.6 * mobType;
                NatRes *= 0.4 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1.6 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 0.4 * mobType;
                TechRes *= 0.8 * mobType;
                BladeRes *= 1.6 * mobType;
                MindRes *=  0.8 * mobType;
                SoundRes *= 1.6 * mobType;
                WindRes *= 0.8 * mobType;
                SymRes *= 1 * mobType;
                SpiritRes *= 1.2 * mobType;
                DuneRes *= 1 * mobType;
                AquaRes *= 0.4 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_DUNE)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.6 * mobType;
                HolyRes *= 1.2 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 1.2 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 1.2 * mobType;
                EldRes *= 1.2 * mobType;
                AbyssRes *= 0.4 * mobType;
                TechRes *= 1.2 * mobType;
                BladeRes *= 0.8 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 0.8 * mobType;
                WindRes *= 1.6 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 0.8 * mobType;
                DuneRes *= 1 * mobType;
                AquaRes *= 0.4 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_SOUL)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.6 * mobType;
                HolyRes *= 0.4 * mobType;
                NatRes *= 0.8 * mobType;
                EvokeRes *= 1.2 * mobType;
                BloodRes *= 0.4 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.8 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 0.8 * mobType;
                TechRes *= 0.8 * mobType;
                BladeRes *= 1.6 * mobType;
                MindRes *= 1.2 * mobType;
                SoundRes *= 1 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 0.4 * mobType;
                SpiritRes *= 0 * mobType;
                DuneRes *= 0.8 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_SYM)) {
                FireRes *= 1.2 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 1.2 * mobType;
                EvokeRes *= 1.2 * mobType;
                BloodRes *= 1 * mobType;
                EndRes *= 0.8 * mobType;
                LigRes *= 1 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 1.6 * mobType;
                TechRes *= 0.4 * mobType;
                BladeRes *= 0.8 * mobType;
                MindRes *= 0.4 * mobType;
                SoundRes *= 1.6 * mobType;
                WindRes *= 1 * mobType;
                SymRes *= 1.2 * mobType;
                SpiritRes *= 1.6 * mobType;
                DuneRes *= 1.2 * mobType;
                AquaRes *= 1.2 * mobType;
            }
            if(mob.getType().is(PotatoTags.TYPE_AQUA)) {
                FireRes *= 1.6 * mobType;
                IceRes *= 1.2 * mobType;
                HolyRes *= 0.8 * mobType;
                NatRes *= 1.6 * mobType;
                EvokeRes *= 1 * mobType;
                BloodRes *= 0.8 * mobType;
                EndRes *= 1 * mobType;
                LigRes *= 0.4 * mobType;
                EldRes *= 0.4 * mobType;
                AbyssRes *= 1.6 * mobType;
                TechRes *= 1.6 * mobType;
                BladeRes *= 1.2 * mobType;
                MindRes *= 0.8 * mobType;
                SoundRes *= 1.2 * mobType;
                WindRes *= 1.2 * mobType;
                SymRes *= 0.8 * mobType;
                SpiritRes *= 1.2 * mobType;
                DuneRes *= 1.6 * mobType;
                AquaRes *= 1.6 * mobType;
            }

            // Updates mob attributes
            {
                setIfNonNull(mob, Attributes.ARMOR, Armor);
                setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Tough);
                setIfNonNull(mob, AttributeRegistry.SPELL_POWER, SpellPower);
                setIfNonNull(mob, AttributeRegistry.CAST_TIME_REDUCTION, CastReduction);
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
                    //var MIND_GOBLIN_RESIST = net.ender.endersequipment.registries.EEAttributeRegistry.MIND_SPELL_RESIST;
                    //setIfNonNull(mob, MIND_GOBLIN_RESIST, MindRes);
                }
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, AbyssRes);
                    //setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.TECHNOMANCY_MAGIC_RESIST, TechRes);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, SoundRes);
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, WindRes);
                }
                if (ModList.get().isLoaded("iss_magicfromtheeast")) {
                    setIfNonNull(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST, SymRes);
                    setIfNonNull(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST, SpiritRes);
                    setIfNonNull(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.DUNE_MAGIC_RESIST, DuneRes);
                }
                //if (ModList.get().isLoaded("traveloptics")) {}

                // Fixed Attributes
                setIfNonNull(mob, ALObjects.Attributes.ARMOR_PIERCE, ArmorPierce);
                setIfNonNull(mob, ALObjects.Attributes.ARMOR_SHRED, ArmorShred);
                setIfNonNull(mob, ALObjects.Attributes.PROT_PIERCE, ProtPierce);
                setIfNonNull(mob, ALObjects.Attributes.PROT_SHRED, ProtShred);
                setIfNonNull(mob, ALObjects.Attributes.CRIT_CHANCE, Crit);
                setIfNonNull(mob, ALObjects.Attributes.CRIT_DAMAGE, CritDmg);
            }

            // We reset this stuff so it doesn't make other mobs go crazy
            {
                SpellPower = 1;
                CastReduction = 1;
                Resist = 1;
                FireRes = 1;
                IceRes = 1;
                HolyRes = 1;
                NatRes = 1;
                EvokeRes = 1;
                BloodRes = 1;
                EndRes = 1;
                LigRes = 1;
                EldRes = 1;
                AbyssRes = 1;
                TechRes = 1;
                BladeRes = 1;
                MindRes = 1;
                SoundRes = 1;
                WindRes = 1;
                SymRes = 1;
                SpiritRes = 1;
                DuneRes = 1;
                AquaRes = 1;
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