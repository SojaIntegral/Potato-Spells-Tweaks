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
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.utils.PotatoTags;

@SuppressWarnings("unused")
@EventBusSubscriber
public class Amethyst_Crab {
    @SubscribeEvent
    public static void handleResistanceAttributeSpawn(FinalizeSpawnEvent event) {
        //System.out.println("Event");
        var mob = event.getEntity();

        if (ServerConfigs.AUTO_BALANCE.get()) {
            float a = Integer.parseInt(ServerConfigs.COOLDOWN_UNCAP.get());
            float b = Integer.parseInt(ServerConfigs.CAST_UNCAP.get());
            float c = Integer.parseInt(ServerConfigs.RESIST_UNCAP.get());

            float m = (2 * (a + b + c + 1) / 3);
            float n = 0;
            if (ServerConfigs.RESIST_UNCAP.get().equals("0")) n += 15;

            if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.AMETHYST_CRAB)) {
                setIfNonNull(mob, Attributes.ARMOR, Math.round(4.0 * m + n / 2));
                setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, Math.round(2 * m + n / 3));
                setIfNonNull(mob, Attributes.ATTACK_DAMAGE, Math.round(m) + 5 + n / 5);
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, 1 + (Math.pow(2, m) / 10) + n);
                setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, (n / 2) + (m / 4) - 0.8);
                setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, m + 0.5 + (n * 3));
                setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, (m / 5) + 1 + (n * 2));
                setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, (m / 3) + 1 + (n * 2.3));
                setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, n + 1 + (m / 3));
                setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, (n * 1.5) + 1 + (m / 2));
                setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, (n / 3) + (m / 3) - 1);
                setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, 1 + (m / 1.5) + (n * 3.5));
                // This needs to be conditional or the game shits itself if the mod is not present
                if (ModList.get().isLoaded("endersequipment")) {
                    setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, (n / 3.5) + (m / 4) - 1);
                }
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, (n / 3) + (m / 3) - 1);
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, 1 + (m / 1.5) + (n * 3.5));
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, (n * 1.5) + 1 + (m / 2));
                }
            }
        }
        else {
            if (ModList.get().isLoaded("cataclysm_spellbooks") && mob.getType().is(PotatoTags.AMETHYST_CRAB)) {
                setIfNonNull(mob, Attributes.ARMOR, );
                setIfNonNull(mob, Attributes.ARMOR_TOUGHNESS, );
                setIfNonNull(mob, Attributes.ATTACK_DAMAGE, );
                setIfNonNull(mob, AttributeRegistry.SPELL_RESIST, );
                setIfNonNull(mob, AttributeRegistry.FIRE_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.NATURE_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.ENDER_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.ICE_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, );
                setIfNonNull(mob, AttributeRegistry.HOLY_MAGIC_RESIST, );
                // This needs to be conditional or the game shits itself if the mod is not present
                if (ModList.get().isLoaded("endersequipment")) {
                    setIfNonNull(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, );
                }
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    setIfNonNull(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, );
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    setIfNonNull(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, );
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    setIfNonNull(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, );
                }
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
