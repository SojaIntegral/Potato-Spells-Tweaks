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
public class Amethyst_Crab {

    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

        double m = CompatFormulas.attrFormula;

        double crabArmor = 0;
        double crabTough = 0;
        double crabResist = 0;
        double crabFireRes = 0;
        double crabNatRes = 0;
        double crabEndRes = 0;
        double crabBldRes = 0;
        double crabIceRes = 0;
        double crabLigRes = 0;
        double crabEldRes = 0;
        double crabHolyRes = 0;
        double crabBladeRes = 0;
        double crabAbyssRes = 0;
        double crabSoundRes = 0;
        double crabWindRes = 0;

        if (ServerConfigs.CAT_SWITCH.get()) {
            // Amethyst Crab attributes
            crabArmor += 30;
            crabTough += 10;
            crabResist += Math.pow(1.35, m);
            // -= means negative resistance
            crabFireRes -= Math.pow(0.85, m);
            crabNatRes += Math.pow(4.0, m);
            crabEndRes += Math.pow(1.15, m);
            crabBldRes += Math.pow(0.35, m);
            crabIceRes += Math.pow(0.1, m);
            crabLigRes += Math.pow(1.8, m);
            crabEldRes -= Math.pow(0.2, m);
            crabHolyRes += Math.pow(1.5, m);
            crabBladeRes += Math.pow(2.15, m);
            crabAbyssRes += Math.pow(0.25, m);
            crabSoundRes += Math.pow(1.6, m);
            crabWindRes += Math.pow(1.2, m);
        }

        else {
            crabArmor = ServerConfigs.DEAD_ARMOR.get();
            crabResist = ServerConfigs.DEAD_ARMOR.get();
            crabFireRes = ServerConfigs.DEAD_ARMOR.get();
            crabNatRes = ServerConfigs.DEAD_ARMOR.get();
            crabEndRes = ServerConfigs.DEAD_ARMOR.get();
            crabBldRes = ServerConfigs.DEAD_ARMOR.get();
            crabIceRes = ServerConfigs.DEAD_ARMOR.get();
            crabLigRes = ServerConfigs.DEAD_ARMOR.get();
            crabEldRes = ServerConfigs.DEAD_ARMOR.get();
            crabHolyRes = ServerConfigs.DEAD_ARMOR.get();
            crabBladeRes = ServerConfigs.DEAD_ARMOR.get();
            crabAbyssRes = ServerConfigs.DEAD_ARMOR.get();
            crabSoundRes = ServerConfigs.DEAD_ARMOR.get();
            crabWindRes = ServerConfigs.DEAD_ARMOR.get();
        }

        if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.AMETHYST_CRAB)) {
            setIfNonNull(mob, Attributes.ARMOR, crabArmor);
            setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, crabTough);
            setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, crabResist);
            setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, crabFireRes);
            setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, crabNatRes);
            setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, crabEndRes);
            setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, crabBldRes);
            setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, crabIceRes);
            setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, crabLigRes);
            setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, crabEldRes);
            setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, crabHolyRes);
            // This needs to be conditional or the game shits itself if the mod is not present
            if (ModList.get().isLoaded("endersequipment")) {
                setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, crabBladeRes);
            }
            if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, crabAbyssRes);
            }
            if (ModList.get().isLoaded("alshanex_familiars")) {
                setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, crabSoundRes);
            }
            if (ModList.get().isLoaded("aero_additions")) {
                setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, crabWindRes);
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
