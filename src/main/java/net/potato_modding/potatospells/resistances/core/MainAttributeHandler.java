package net.potato_modding.potatospells.resistances.core;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

@SuppressWarnings("unused")
@EventBusSubscriber
public class MainAttributeHandler {

    public static boolean blockReroll = false;
    public static void checkForNoReroll(LivingEntity entity) {
        blockReroll = false;

        Set<ResourceLocation> reset = Set.of(
                ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "attack")
        );

        for (var instance : entity.getAttributes().getSyncableAttributes()) {
            boolean found = instance.getModifiers().stream().anyMatch(mod ->
                    mod.id().getNamespace().equals("potatospellbookstweaks") && reset.contains(mod.id()));

            if (found) {
                blockReroll = true;
                return;
            }
        }
    }


    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value)
    {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null)
        {
            instance.setBaseValue(value);
        }
    }

    // Add modifier (base)
    private static void addModifierIfValid(LivingEntity entity, Holder<Attribute> attribute, double value, String idName) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance == null) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", idName);

        // Avoid duplicates
        if (instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id))) {
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    // Add modifier (multiplied base)
    private static void multiplyModifierIfValid(LivingEntity entity, Holder<Attribute> attribute, double value, String idName) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance == null) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", idName);

        // Avoid duplicates
        if (instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id))) {
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
    }

    private static boolean shinyAttribute() {
        return ThreadLocalRandom.current().nextInt(shinyChanceModifier) == 0;
    }

    private static final Map<ResourceLocation, Component> SHINY = Map.ofEntries(
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "attack"), Component.literal("attack"))
    );

    @SubscribeEvent(priority = net.neoforged.bus.api.EventPriority.LOWEST)
    private static void handleResistanceAttribute(EntityJoinLevelEvent event) {

        var entity = event.getEntity();
        if (!(entity instanceof LivingEntity mob)) return;

        MainAttributeHandler.checkForNoReroll(mob);
        if (!blockReroll) {

            var shinyAttribute = mob.getAttributes().getValue(PotatoAttributes.SHINY);
            // If the mob is shiny, it won't have this modifier
            boolean alreadyShiny = shinyAttribute >= 1;
            // If the mob is shiny, it won't have this modifier
            boolean canReroll = shinyAttribute == 0;

            // IVs variation setup
            boolean isShiny = false;
            double[] attrVar = new double[10];
            // Chance for shiny & prevents shinies from losing perfect IVs
            if ((ServerConfigs.SHINY.get() && shinyAttribute()) || alreadyShiny) {
                Arrays.fill(attrVar, 1 * randMax);
                isShiny = true;
            }
            // Adds + 0~15% to Familiars' attributes & can be rerolled
            // I should be able to copy this code over and make so non-shinies are rerolled
            else if ((ServerConfigs.SHINY.get() && !shinyAttribute()) || canReroll) {
                for (int i = 0; i < attrVar.length; i++) {
                    attrVar[i] = Math.random() * randMax;
                }
            } else if (!ServerConfigs.SHINY.get() || mob.getType().is(PotatoTags.PLAYER)) {
                Arrays.fill(attrVar, 0);
            }

            // Checks if the mob has a valid modifier from here
            // If not, it gives the mob modifiers
            if (mob.getType().is(PotatoTags.MOB_ENABLED) && canReroll && !alreadyShiny) {

                if (ServerConfigs.FAMILIAR_NATURE.get() && mob.getType().is(PotatoTags.HAS_NATURE)) {
                    PotatoNaturesHandler.applySpawnModifiers(mob);
                }

                // Class modifier
                if (mob.getType().is(PotatoTags.BOSS)) {
                    mobType = boss_mod;
                    ArmorMod = boss_mod * (3 * (1 + attrVar[0]));
                    ToughMod = boss_mod * (2 * (1 + attrVar[0]));
                    AttackMod = boss_mod * (2 * (1 + attrVar[1]));
                }
                if (mob.getType().is(PotatoTags.MINIBOSS)) {
                    mobType = mini_mod;
                    ArmorMod = mini_mod * (1.5 * (1 + attrVar[0]));
                    ToughMod = mini_mod * (1.5 * (1 + attrVar[0]));
                    AttackMod = mini_mod * (1 * (1 + attrVar[1]));
                }
                if (mob.getType().is(PotatoTags.NORMAL)) {
                    mobType = mob_mod;
                    ArmorMod = mob_mod * (0.75 * (1 + attrVar[0]));
                    ToughMod = mob_mod * (0.5 * (1 + attrVar[0]));
                    AttackMod = mob_mod * (0.65 * (1 + attrVar[1]));
                }
                if (mob.getType().is(PotatoTags.SUMMON)) {
                    mobType = summon_mod;
                    ArmorMod = summon_mod * (1 * (1 + attrVar[0]));
                    ToughMod = summon_mod * (0.5 * (1 + attrVar[0]));
                    AttackMod = summon_mod * (0.5 * (1 + attrVar[1]));
                }
                if (mob.getType().is(PotatoTags.PLAYER)) {
                    mobType = 1;
                    ArmorMod = 1;
                    ToughMod = 1;
                    AttackMod = 1;
                }

                if (mob.getType().is(PotatoTags.RACE_PLAYER)) {
                    Attack = 0 * AttackMod;
                    Armor = 0 * ArmorMod;
                    Tough = 0 * ToughMod;
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
                    ArmorPierce = 0;
                    ArmorShred = 0;
                    ProtPierce = 0;
                    ProtShred = 0;
                    CritDmg = 0;
                    Crit = 0;
                }

                // Type Modifiers
                if (mob.getType().is(PotatoTags.RACE_HUMAN)) {
                    Attack = 0 * AttackMod;
                    Armor = 0 * ArmorMod;
                    Tough = 0 * ToughMod;
                    SpellPower = 1 + attrVar[2];
                    CastReduction = 1 + attrVar[3];
                    Resist = 1 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 1 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1 + attrVar[4];
                    EvokeRes = 1 + attrVar[4];
                    BloodRes = 1 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 1 + attrVar[4];
                    EldRes = 1 + attrVar[4];
                    AbyssRes = 1 + attrVar[4];
                    TechRes = 1 + attrVar[4];
                    BladeRes = 1 + attrVar[4];
                    MindRes = 1 + attrVar[4];
                    SoundRes = 1 + attrVar[4];
                    WindRes = 1 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1 + attrVar[4];
                    DuneRes = 1 + attrVar[4];
                    AquaRes = 1 + attrVar[4];
                    ArmorPierce = 0.5 * (1 + attrVar[5]);
                    ArmorShred = 0.05 * (1 + attrVar[5]);
                    ProtPierce = 0.5 * (1 + attrVar[6]);
                    ProtShred = 0.05 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.05;
                    Crit = attrVar[7] - 0.025;
                }
                if (mob.getType().is(PotatoTags.RACE_UNDEAD)) {
                    Attack = 1.5 * AttackMod;
                    Armor = 2.5 * ArmorMod;
                    Tough = 1.5 * ToughMod;
                    SpellPower = 1.2 + attrVar[2];
                    CastReduction = 0.9 + attrVar[3];
                    Resist = 1.15 + attrVar[4];
                    FireRes = 0.85 + attrVar[4];
                    IceRes = 1.15 + attrVar[4];
                    HolyRes = 0.85 + attrVar[4];
                    NatRes = 0.85 + attrVar[4];
                    EvokeRes = 1.15 + attrVar[4];
                    BloodRes = 1.15 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 1.15 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1.15 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 0.85 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 0.85 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 0.85 + attrVar[4];
                    SpiritRes = 1.15 + attrVar[4];
                    DuneRes = 1 + attrVar[4];
                    AquaRes = 1 + attrVar[4];
                    ArmorPierce = 2 * (1 + attrVar[5]);
                    ArmorShred = 0.15 * (1 + attrVar[5]);
                    ProtPierce = 2 * (1 + attrVar[6]);
                    ProtShred = 0.1 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.15;
                    Crit = attrVar[7];
                }
                if (mob.getType().is(PotatoTags.RACE_HUMANOID)) {
                    Attack = 2 * AttackMod;
                    Armor = 2 * ArmorMod;
                    Tough = 2 * ToughMod;
                    SpellPower = 1.15 + attrVar[2];
                    CastReduction = 1.15 + attrVar[3];
                    Resist = 1.2 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 0.85 + attrVar[4];
                    HolyRes = 1.15 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 1 + attrVar[4];
                    BloodRes = 0.85 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 0.85 + attrVar[4];
                    EldRes = 0.85 + attrVar[4];
                    AbyssRes = 0.85 + attrVar[4];
                    TechRes = 1.15 + attrVar[4];
                    BladeRes = 0.85 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 1.15 + attrVar[4];
                    WindRes = 1 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 0.85 + attrVar[4];
                    DuneRes = 1.15 + attrVar[4];
                    AquaRes = 1.15 + attrVar[4];
                    ArmorPierce = 3 * (1 + attrVar[5]);
                    ArmorShred = 0.2 * (1 + attrVar[5]);
                    ProtPierce = 1.5 * (1 + attrVar[6]);
                    ProtShred = 0.1 * (1 + attrVar[6]);
                    CritDmg = attrVar[7];
                    Crit = attrVar[7] - 0.05;
                }
                if (mob.getType().is(PotatoTags.RACE_BRUTE)) {
                    Attack = 3 * AttackMod;
                    Armor = 3 * ArmorMod;
                    Tough = 3 * ToughMod;
                    SpellPower = 1 + attrVar[2];
                    CastReduction = 0.9 + attrVar[3];
                    Resist = 1.2 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 0.85 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 1 + attrVar[4];
                    BloodRes = 0.85 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 0.85 + attrVar[4];
                    EldRes = 1 + attrVar[4];
                    AbyssRes = 0.85 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 1.15 + attrVar[4];
                    MindRes = 1 + attrVar[4];
                    SoundRes = 1 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1 + attrVar[4];
                    DuneRes = 1.15 + attrVar[4];
                    AquaRes = 1.15 + attrVar[4];
                    ArmorPierce = 5 * (1 + attrVar[5]);
                    ArmorShred = 0.1 * (1 + attrVar[5]);
                    ProtPierce = 1 * (1 + attrVar[6]);
                    ProtShred = 0.15 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.15;
                    Crit = attrVar[7] + 0.05;
                }
                if (mob.getType().is(PotatoTags.RACE_INSECT)) {
                    Attack = 1 * AttackMod;
                    Armor = 1 * ArmorMod;
                    Tough = 2 * ToughMod;
                    SpellPower = 0.9 + attrVar[2];
                    CastReduction = 1 + attrVar[3];
                    Resist = 1 + attrVar[4];
                    FireRes = 0.85 + attrVar[4];
                    IceRes = 0.85 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 1 + attrVar[4];
                    BloodRes = 1 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 1 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1.15 + attrVar[4];
                    TechRes = 1.15 + attrVar[4];
                    BladeRes = 1 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 1 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1 + attrVar[4];
                    DuneRes = 1.15 + attrVar[4];
                    AquaRes = 0.85 + attrVar[4];
                    ArmorPierce = 1 * (1 + attrVar[5]);
                    ArmorShred = 0.25 * (1 + attrVar[5]);
                    ProtPierce = 1 * (1 + attrVar[6]);
                    ProtShred = 0.15 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.35;
                    Crit = attrVar[7] + 0.25;
                }
                if (mob.getType().is(PotatoTags.RACE_FLYING)) {
                    Attack = 1.5 * AttackMod;
                    Armor = 1 * ArmorMod;
                    Tough = 0 * ToughMod;
                    SpellPower = 0.95 + attrVar[2];
                    CastReduction = 1.25 + attrVar[3];
                    Resist = 0.9 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 0.85 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1 + attrVar[4];
                    EvokeRes = 1 + attrVar[4];
                    BloodRes = 0.85 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 0.85 + attrVar[4];
                    EldRes = 1 + attrVar[4];
                    AbyssRes = 1 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 1.15 + attrVar[4];
                    MindRes = 1 + attrVar[4];
                    SoundRes = 0.85 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1 + attrVar[4];
                    DuneRes = 1 + attrVar[4];
                    AquaRes = 0.85 + attrVar[4];
                    ArmorPierce = 1 * (1 + attrVar[5]);
                    ArmorShred = 0.1 * (1 + attrVar[5]);
                    ProtPierce = 1 * (1 + attrVar[6]);
                    ProtShred = 0.1 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.25;
                    Crit = attrVar[7] + 0.35;
                }
                if (mob.getType().is(PotatoTags.RACE_GOLEM)) {
                    Attack = 3.5 * AttackMod;
                    Armor = 5 * ArmorMod;
                    Tough = 5 * ToughMod;
                    SpellPower = 1.15 + attrVar[2];
                    CastReduction = 0.8 + attrVar[3];
                    Resist = 1.3 + attrVar[4];
                    FireRes = 1.15 + attrVar[4];
                    IceRes = 1.15 + attrVar[4];
                    HolyRes = 0.85 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 1.15 + attrVar[4];
                    BloodRes = 1.15 + attrVar[4];
                    EndRes = 0.85 + attrVar[4];
                    LigRes = 1.15 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1.15 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 1.15 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 0.85 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 0.85 + attrVar[4];
                    SpiritRes = 1.15 + attrVar[4];
                    DuneRes = 0.85 + attrVar[4];
                    AquaRes = 1 + attrVar[4];
                    ArmorPierce = 3 * (1 + attrVar[5]);
                    ArmorShred = 0.15 * (1 + attrVar[5]);
                    ProtPierce = 1 * (1 + attrVar[6]);
                    ProtShred = 0.15 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] + 0.15;
                    Crit = attrVar[7] - 0.1;
                }
                if (mob.getType().is(PotatoTags.RACE_CONSTRUCT)) {
                    Attack = 4 * AttackMod;
                    Armor = 4 * ArmorMod;
                    Tough = 5 * ToughMod;
                    SpellPower = 0.8 + attrVar[2];
                    CastReduction = 1.5 + attrVar[3];
                    Resist = 1.25 + attrVar[4];
                    FireRes = 1.15 + attrVar[4];
                    IceRes = 1.15 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 1.15 + attrVar[4];
                    BloodRes = 1.15 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 0.85 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1 + attrVar[4];
                    TechRes = 1.15 + attrVar[4];
                    BladeRes = 1 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 1 + attrVar[4];
                    WindRes = 1.15 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1.15 + attrVar[4];
                    DuneRes = 0.85 + attrVar[4];
                    AquaRes = 0.85 + attrVar[4];
                    ArmorPierce = 2 * (1 + attrVar[5]);
                    ArmorShred = 0.2 * (1 + attrVar[5]);
                    ProtPierce = 2 * (1 + attrVar[6]);
                    ProtShred = 0.2 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.3;
                    Crit = attrVar[7] + 1;
                }
                if (mob.getType().is(PotatoTags.RACE_FISH)) {
                    Attack = 2 * AttackMod;
                    Armor = 3 * ArmorMod;
                    Tough = 1.5 * ToughMod;
                    SpellPower = 0.95 + attrVar[2];
                    CastReduction = 1.2 + attrVar[3];
                    Resist = 0.9 + attrVar[4];
                    FireRes = 1.15 + attrVar[4];
                    IceRes = 1.15 + attrVar[4];
                    HolyRes = 1 + attrVar[4];
                    NatRes = 1.15 + attrVar[4];
                    EvokeRes = 0.85 + attrVar[4];
                    BloodRes = 0.85 + attrVar[4];
                    EndRes = 1 + attrVar[4];
                    LigRes = 0.85 + attrVar[4];
                    EldRes = 1 + attrVar[4];
                    AbyssRes = 1.15 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 0.85 + attrVar[4];
                    MindRes = 1 + attrVar[4];
                    SoundRes = 0.85 + attrVar[4];
                    WindRes = 1 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1 + attrVar[4];
                    DuneRes = 0.85 + attrVar[4];
                    AquaRes = 1.15 + attrVar[4];
                    ArmorPierce = 1.5 * (1 + attrVar[5]);
                    ArmorShred = 0.15 * (1 + attrVar[5]);
                    ProtPierce = 1.5 * (1 + attrVar[6]);
                    ProtShred = 0.15 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.075;
                    Crit = attrVar[7] - 0.05;
                }
                if (mob.getType().is(PotatoTags.RACE_SPIRIT)) {
                    Attack = 1 * AttackMod;
                    Armor = 0 * ArmorMod;
                    Tough = 0 * ToughMod;
                    SpellPower = 1.25 + attrVar[2];
                    CastReduction = 1.25 + attrVar[3];
                    Resist = 1.15 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 1 + attrVar[4];
                    HolyRes = 0.85 + attrVar[4];
                    NatRes = 1 + attrVar[4];
                    EvokeRes = 1.15 + attrVar[4];
                    BloodRes = 0.85 + attrVar[4];
                    EndRes = 0.85 + attrVar[4];
                    LigRes = 1 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 1.15 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 1 + attrVar[4];
                    WindRes = 1 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 0.85 + attrVar[4];
                    DuneRes = 1 + attrVar[4];
                    AquaRes = 1 + attrVar[4];
                    ArmorPierce = 3 * (1 + attrVar[5]);
                    ArmorShred = 0.25 * (1 + attrVar[5]);
                    ProtPierce = 3 * (1 + attrVar[6]);
                    ProtShred = 0.25 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.4;
                    Crit = attrVar[7] - 0.1;
                }
                if (mob.getType().is(PotatoTags.RACE_AMORPH)) {
                    Attack = 2.5 * AttackMod;
                    Armor = 2.5 * ArmorMod;
                    Tough = 2.5 * ToughMod;
                    SpellPower = 1.05 + attrVar[2];
                    CastReduction = 1.05 + attrVar[3];
                    Resist = 1 + attrVar[4];
                    FireRes = 1 + attrVar[4];
                    IceRes = 1 + attrVar[4];
                    HolyRes = 0.85 + attrVar[4];
                    NatRes = 0.85 + attrVar[4];
                    EvokeRes = 1.15 + attrVar[4];
                    BloodRes = 1.15 + attrVar[4];
                    EndRes = 1.15 + attrVar[4];
                    LigRes = 1.15 + attrVar[4];
                    EldRes = 1.15 + attrVar[4];
                    AbyssRes = 1.15 + attrVar[4];
                    TechRes = 0.85 + attrVar[4];
                    BladeRes = 0.85 + attrVar[4];
                    MindRes = 1.15 + attrVar[4];
                    SoundRes = 0.85 + attrVar[4];
                    WindRes = 1 + attrVar[4];
                    SymRes = 1 + attrVar[4];
                    SpiritRes = 1.15 + attrVar[4];
                    DuneRes = 1 + attrVar[4];
                    AquaRes = 1 + attrVar[4];
                    ArmorPierce = 1 * (1 + attrVar[5]);
                    ArmorShred = 0.1 * (1 + attrVar[5]);
                    ProtPierce = 2 * (1 + attrVar[6]);
                    ProtShred = 0.15 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] + 0.05;
                    Crit = attrVar[7] + 0.1;
                }
                if (mob.getType().is(PotatoTags.RACE_DRAGONBORN)) {
                    Attack = 1 * AttackMod;
                    Armor = 2 * ArmorMod;
                    Tough = 1 * ToughMod;
                    SpellPower = 1.15 + attrVar[2];
                    CastReduction = 1.05 + attrVar[3];
                    Resist = 1.1 + attrVar[4];
                    FireRes = 1.05 + attrVar[4];
                    IceRes = 1.05 + attrVar[4];
                    HolyRes = 1.05 + attrVar[4];
                    NatRes = 1.05 + attrVar[4];
                    EvokeRes = 1.05 + attrVar[4];
                    BloodRes = 1.05 + attrVar[4];
                    EndRes = 1.05 + attrVar[4];
                    LigRes = 1.05 + attrVar[4];
                    EldRes = 1.05 + attrVar[4];
                    AbyssRes = 1.05 + attrVar[4];
                    TechRes = 1.05 + attrVar[4];
                    BladeRes = 1.05 + attrVar[4];
                    MindRes = 1.05 + attrVar[4];
                    SoundRes = 1.05 + attrVar[4];
                    WindRes = 1.05 + attrVar[4];
                    SymRes = 1.05 + attrVar[4];
                    SpiritRes = 1.05 + attrVar[4];
                    DuneRes = 1.05 + attrVar[4];
                    AquaRes = 1.05 + attrVar[4];
                    ArmorPierce = 0 * (1 + attrVar[5]);
                    ArmorShred = 0 * (1 + attrVar[5]);
                    ProtPierce = 0 * (1 + attrVar[6]);
                    ProtShred = 0 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.2;
                    Crit = attrVar[7] - 0.15;
                }
                if (mob.getType().is(PotatoTags.RACE_DRAGON)) {
                    Attack = 5 * AttackMod;
                    Armor = 5 * ArmorMod;
                    Tough = 5 * ToughMod;
                    SpellPower = 1.3 + attrVar[2];
                    CastReduction = 1.2 + attrVar[3];
                    Resist = 1.5 + attrVar[4];
                    FireRes = 1.1 + attrVar[4];
                    IceRes = 1.1 + attrVar[4];
                    HolyRes = 1.1 + attrVar[4];
                    NatRes = 1.1 + attrVar[4];
                    EvokeRes = 1.1 + attrVar[4];
                    BloodRes = 1.1 + attrVar[4];
                    EndRes = 1.1 + attrVar[4];
                    LigRes = 1.1 + attrVar[4];
                    EldRes = 1.1 + attrVar[4];
                    AbyssRes = 1.1 + attrVar[4];
                    TechRes = 1.1 + attrVar[4];
                    BladeRes = 1.1 + attrVar[4];
                    MindRes = 1.1 + attrVar[4];
                    SoundRes = 1.1 + attrVar[4];
                    WindRes = 1.1 + attrVar[4];
                    SymRes = 1.1 + attrVar[4];
                    SpiritRes = 1.1 + attrVar[4];
                    DuneRes = 1.1 + attrVar[4];
                    AquaRes = 1.1 + attrVar[4];
                    ArmorPierce = 0 * (1 + attrVar[5]);
                    ArmorShred = 0 * (1 + attrVar[5]);
                    ProtPierce = 2 * (1 + attrVar[6]);
                    ProtShred = 0.2 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.2;
                    Crit = attrVar[7] - 0.075;
                }

                // School Modifiers
                if (mob.getType().is(PotatoTags.TYPE_BLOOD)) {
                    FireRes *= 0.65 * mobType;
                    IceRes *= 1.155 * mobType;
                    HolyRes *= 0.65 * mobType;
                    NatRes *= 1.15 * mobType;
                    EvokeRes *= 1.45 * mobType;
                    BloodRes *= 0.85 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 1.15 * mobType;
                    AbyssRes *= 1.45 * mobType;
                    TechRes *= 1.15 * mobType;
                    BladeRes *= 0.65 * mobType;
                    MindRes *= 1.45 * mobType;
                    SoundRes *= 0.65 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 0.65 * mobType;
                    SpiritRes *= 1.45 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 1.45 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_ELDRITCH)) {
                    FireRes *= 0.85 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 0.65 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1.45 * mobType;
                    BloodRes *= 1 * mobType;
                    EndRes *= 1.15 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 1.45 * mobType;
                    AbyssRes *= 1 * mobType;
                    TechRes *= 0.65 * mobType;
                    BladeRes *= 0.65 * mobType;
                    MindRes *= 1.45 * mobType;
                    SoundRes *= 0.65 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 1.45 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_ENDER)) {
                    FireRes *= 1 * mobType;
                    IceRes *= 1 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 1 * mobType;
                    EvokeRes *= 1.45 * mobType;
                    BloodRes *= 1 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1 * mobType;
                    EldRes *= 0.85 * mobType;
                    AbyssRes *= 1.15 * mobType;
                    TechRes *= 1 * mobType;
                    BladeRes *= 1.15 * mobType;
                    MindRes *= 1.15 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1.15 * mobType;
                    SpiritRes *= 1 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 1 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_EVOKATION)) {
                    FireRes *= 0.85 * mobType;
                    IceRes *= 1 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 1 * mobType;
                    EvokeRes *= 1.45 * mobType;
                    BloodRes *= 0.65 * mobType;
                    EndRes *= 1.15 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 0.85 * mobType;
                    TechRes *= 0.85 * mobType;
                    BladeRes *= 0.65 * mobType;
                    MindRes *= 1.15 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 1.15 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 0.65 * mobType;
                    DuneRes *= 1.45 * mobType;
                    AquaRes *= 1 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_FIRE)) {
                    FireRes *= 1.45 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 1.15 * mobType;
                    AbyssRes *= 0.85 * mobType;
                    TechRes *= 1.15 * mobType;
                    BladeRes *= 1 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 0.85 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 0.85 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 0.65 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_HOLY)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 1.45 * mobType;
                    NatRes *= 1 * mobType;
                    EvokeRes *= 1.15 * mobType;
                    BloodRes *= 1.45 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 1.45 * mobType;
                    AbyssRes *= 1.15 * mobType;
                    TechRes *= 0.65 * mobType;
                    BladeRes *= 0.65 * mobType;
                    MindRes *= 0.65 * mobType;
                    SoundRes *= 1.45 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1 * mobType;
                    SpiritRes *= 1.45 * mobType;
                    DuneRes *= 0.85 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_ICE)) {
                    FireRes *= 0.85 * mobType;
                    IceRes *= 1.45 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 0.85 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1.15 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 0.85 * mobType;
                    AbyssRes *= 1 * mobType;
                    TechRes *= 0.85 * mobType;
                    BladeRes *= 0.85 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 0.85 * mobType;
                    WindRes *= 1.45 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 1.15 * mobType;
                    DuneRes *= 0.85 * mobType;
                    AquaRes *= 0.85 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_LIGHTNING)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.45 * mobType;
                    HolyRes *= 1 * mobType;
                    NatRes *= 1.15 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1.15 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.65 * mobType;
                    EldRes *= 0.85 * mobType;
                    AbyssRes *= 1.45 * mobType;
                    TechRes *= 1.45 * mobType;
                    BladeRes *= 1.45 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1 * mobType;
                    SpiritRes *= 1 * mobType;
                    DuneRes *= 0.65 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_NATURE)) {
                    FireRes *= 0.65 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 1 * mobType;
                    NatRes *= 1 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 0.65 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 1.15 * mobType;
                    TechRes *= 0.65 * mobType;
                    BladeRes *= 0.85 * mobType;
                    MindRes *= 1.15 * mobType;
                    SoundRes *= 1.15 * mobType;
                    WindRes *= 1.45 * mobType;
                    SymRes *= 1.15 * mobType;
                    SpiritRes *= 0.85 * mobType;
                    DuneRes *= 0.65 * mobType;
                    AquaRes *= 1.45 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_WIND)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 0.65 * mobType;
                    HolyRes *= 1 * mobType;
                    NatRes *= 0.65 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 0.85 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.65 * mobType;
                    EldRes *= 0.85 * mobType;
                    AbyssRes *= 1.45 * mobType;
                    TechRes *= 1.15 * mobType;
                    BladeRes *= 1.15 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 1.15 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1 * mobType;
                    SpiritRes *= 1 * mobType;
                    DuneRes *= 0.65 * mobType;
                    AquaRes *= 0.85 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_ABYSS)) {
                    FireRes *= 1.45 * mobType;
                    IceRes *= 1 * mobType;
                    HolyRes *= 1.15 * mobType;
                    NatRes *= 1.15 * mobType;
                    EvokeRes *= 1.15 * mobType;
                    BloodRes *= 1.15 * mobType;
                    EndRes *= 1.15 * mobType;
                    LigRes *= 0.65 * mobType;
                    EldRes *= 1.15 * mobType;
                    AbyssRes *= 0 * mobType;
                    TechRes *= 1.45 * mobType;
                    BladeRes *= 1.45 * mobType;
                    MindRes *= 1.45 * mobType;
                    SoundRes *= 0.85 * mobType;
                    WindRes *= 0.65 * mobType;
                    SymRes *= 0.65 * mobType;
                    SpiritRes *= 1.15 * mobType;
                    DuneRes *= 0.65 * mobType;
                    AquaRes *= 1.45 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_TECHNOMANCY)) {
                    FireRes *= 0.85 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 1.45 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1.15 * mobType;
                    BloodRes *= 0.85 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.65 * mobType;
                    EldRes *= 1.45 * mobType;
                    AbyssRes *= 0.65 * mobType;
                    TechRes *= 1 * mobType;
                    BladeRes *= 0.85 * mobType;
                    MindRes *= 1.15 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 0.85 * mobType;
                    SymRes *= 1.45 * mobType;
                    SpiritRes *= 0.85 * mobType;
                    DuneRes *= 0.85 * mobType;
                    AquaRes *= 0.65 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_BLADE)) {
                    FireRes *= 1 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 1.15 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 0.85 * mobType;
                    EndRes *= 0.85 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 0.85 * mobType;
                    AbyssRes *= 1.15 * mobType;
                    TechRes *= 1.15 * mobType;
                    BladeRes *= 1 * mobType;
                    MindRes *= 1 * mobType;
                    SoundRes *= 0.85 * mobType;
                    WindRes *= 0.85 * mobType;
                    SymRes *= 1.15 * mobType;
                    SpiritRes *= 0.65 * mobType;
                    DuneRes *= 1.45 * mobType;
                    AquaRes *= 0.65 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_MIND)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 1.45 * mobType;
                    NatRes *= 0.85 * mobType;
                    EvokeRes *= 0.85 * mobType;
                    BloodRes *= 0.65 * mobType;
                    EndRes *= 0.85 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 0.65 * mobType;
                    TechRes *= 0.85 * mobType;
                    BladeRes *= 1.45 * mobType;
                    MindRes *= 1 * mobType;
                    SoundRes *= 1.15 * mobType;
                    WindRes *= 1.15 * mobType;
                    SymRes *= 1.45 * mobType;
                    SpiritRes *= 0.65 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_SOUND)) {
                    FireRes *= 1 * mobType;
                    IceRes *= 0.85 * mobType;
                    HolyRes *= 1.45 * mobType;
                    NatRes *= 0.65 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1.45 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 0.65 * mobType;
                    TechRes *= 0.85 * mobType;
                    BladeRes *= 1.45 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 1.45 * mobType;
                    WindRes *= 0.85 * mobType;
                    SymRes *= 1 * mobType;
                    SpiritRes *= 1.15 * mobType;
                    DuneRes *= 1 * mobType;
                    AquaRes *= 0.65 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_DUNE)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.45 * mobType;
                    HolyRes *= 1.15 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1.15 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1.15 * mobType;
                    EldRes *= 1.15 * mobType;
                    AbyssRes *= 0.65 * mobType;
                    TechRes *= 1.15 * mobType;
                    BladeRes *= 0.85 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 0.85 * mobType;
                    WindRes *= 1.45 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 0.85 * mobType;
                    DuneRes *= 1 * mobType;
                    AquaRes *= 0.65 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_SOUL)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.45 * mobType;
                    HolyRes *= 0.65 * mobType;
                    NatRes *= 0.85 * mobType;
                    EvokeRes *= 1.15 * mobType;
                    BloodRes *= 0.65 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.85 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 0.85 * mobType;
                    TechRes *= 0.85 * mobType;
                    BladeRes *= 1.45 * mobType;
                    MindRes *= 1.15 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 0.65 * mobType;
                    SpiritRes *= 0 * mobType;
                    DuneRes *= 0.85 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_SYM)) {
                    FireRes *= 1.15 * mobType;
                    IceRes *= 1.15 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 1.15 * mobType;
                    EvokeRes *= 1.15 * mobType;
                    BloodRes *= 1 * mobType;
                    EndRes *= 0.85 * mobType;
                    LigRes *= 1 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 1.45 * mobType;
                    TechRes *= 0.65 * mobType;
                    BladeRes *= 0.85 * mobType;
                    MindRes *= 0.65 * mobType;
                    SoundRes *= 1.45 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1.15 * mobType;
                    SpiritRes *= 1.45 * mobType;
                    DuneRes *= 1.15 * mobType;
                    AquaRes *= 1.15 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_AQUA)) {
                    FireRes *= 1.45 * mobType;
                    IceRes *= 0.85 * mobType;
                    HolyRes *= 0.85 * mobType;
                    NatRes *= 1.45 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 0.85 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 0.65 * mobType;
                    EldRes *= 0.65 * mobType;
                    AbyssRes *= 1.45 * mobType;
                    TechRes *= 1.45 * mobType;
                    BladeRes *= 1.15 * mobType;
                    MindRes *= 0.85 * mobType;
                    SoundRes *= 1.15 * mobType;
                    WindRes *= 1.15 * mobType;
                    SymRes *= 0.85 * mobType;
                    SpiritRes *= 1.15 * mobType;
                    DuneRes *= 1.45 * mobType;
                    AquaRes *= 1.45 * mobType;
                }
                if (mob.getType().is(PotatoTags.TYPE_NEUTRAL)) {
                    FireRes *= 1 * mobType;
                    IceRes *= 1 * mobType;
                    HolyRes *= 1 * mobType;
                    NatRes *= 1 * mobType;
                    EvokeRes *= 1 * mobType;
                    BloodRes *= 1 * mobType;
                    EndRes *= 1 * mobType;
                    LigRes *= 1 * mobType;
                    EldRes *= 1 * mobType;
                    AbyssRes *= 1 * mobType;
                    TechRes *= 1 * mobType;
                    BladeRes *= 1 * mobType;
                    MindRes *= 1 * mobType;
                    SoundRes *= 1 * mobType;
                    WindRes *= 1 * mobType;
                    SymRes *= 1 * mobType;
                    SpiritRes *= 1 * mobType;
                    DuneRes *= 1 * mobType;
                    AquaRes *= 1 * mobType;
                }

                // Updates mob attributes after rounding it up to 2 decimals
                {
                    if(attrVar[0] == 1 && attrVar[1] == 1 && attrVar[2] == 1 && attrVar[3] == 1 &&
                            attrVar[4] == 1 && attrVar[5] == 1 && attrVar[6] == 1 && attrVar[7] == 1) {
                        isShiny = true;
                    }
                    // Vanilla Attributes
                    if (isShiny) {
                        setIfNonNull(mob, PotatoAttributes.SHINY, 1);
                    }
                    addModifierIfValid(mob, Attributes.ATTACK_DAMAGE, BigDecimal.valueOf(Attack).setScale(2, RoundingMode.HALF_UP).doubleValue(), "attack");
                    addModifierIfValid(mob, Attributes.ARMOR, BigDecimal.valueOf(Armor).setScale(2, RoundingMode.HALF_UP).doubleValue(), "armor");
                    addModifierIfValid(mob, Attributes.ARMOR_TOUGHNESS, BigDecimal.valueOf(Tough).setScale(2, RoundingMode.HALF_UP).doubleValue(), "toughness");

                    // Magic Attributes
                    multiplyModifierIfValid(mob, AttributeRegistry.SPELL_POWER, (BigDecimal.valueOf(SpellPower).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spell_power");
                    multiplyModifierIfValid(mob, AttributeRegistry.CAST_TIME_REDUCTION, (BigDecimal.valueOf(CastReduction).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "cast");
                    multiplyModifierIfValid(mob, AttributeRegistry.SPELL_RESIST, (BigDecimal.valueOf(Resist).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.FIRE_MAGIC_RESIST, (BigDecimal.valueOf(FireRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "fire_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.NATURE_MAGIC_RESIST, (BigDecimal.valueOf(NatRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "nature_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.ENDER_MAGIC_RESIST, (BigDecimal.valueOf(EndRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "end_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.EVOCATION_MAGIC_RESIST, (BigDecimal.valueOf(EvokeRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "evoke_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, (BigDecimal.valueOf(BloodRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blood_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.ICE_MAGIC_RESIST, (BigDecimal.valueOf(IceRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "ice_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, (BigDecimal.valueOf(LigRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "lightning_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, (BigDecimal.valueOf(EldRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "eldritch_resist");
                    multiplyModifierIfValid(mob, AttributeRegistry.HOLY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "holy_resist");
                    // This needs to be conditional or the game shits itself if the mod is not present
                    if (ModList.get().isLoaded("endersequipment")) {
                        multiplyModifierIfValid(mob, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, (BigDecimal.valueOf(BladeRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blade_resist");
                        //var MIND_GOBLIN_RESIST = net.ender.endersequipment.registries.EEAttributeRegistry.MIND_SPELL_RESIST;
                        //multiplyModifierIfValid(mob, MIND_GOBLIN_RESIST, (BigDecimal.valueOf(MindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "mind_resist");
                    }
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        multiplyModifierIfValid(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, (BigDecimal.valueOf(AbyssRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "abyssal_resist");
                        //multiplyModifierIfValid(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.TECHNOMANCY_MAGIC_RESIST, (BigDecimal.valueOf(TechRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "technomancy_resist");
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        //multiplyModifierIfValid(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, (BigDecimal.valueOf(SoundRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "sound_resist");
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        multiplyModifierIfValid(mob, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "wind_resist");
                    }
                    if (ModList.get().isLoaded("iss_magicfromtheeast")) {
                        multiplyModifierIfValid(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST, (BigDecimal.valueOf(SymRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "symmetry_resist");
                        multiplyModifierIfValid(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST, (BigDecimal.valueOf(SpiritRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spirit_resist");
                        multiplyModifierIfValid(mob, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.DUNE_MAGIC_RESIST, (BigDecimal.valueOf(DuneRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "dune_resist");
                    }
                    //if (ModList.get().isLoaded("traveloptics")) {}

                    // Apothic Attributes
                    addModifierIfValid(mob, ALObjects.Attributes.ARMOR_PIERCE, BigDecimal.valueOf(ArmorPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_pierce");
                    addModifierIfValid(mob, ALObjects.Attributes.ARMOR_SHRED, BigDecimal.valueOf(ArmorShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_shred");
                    addModifierIfValid(mob, ALObjects.Attributes.PROT_PIERCE, BigDecimal.valueOf(ProtPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_pierce");
                    addModifierIfValid(mob, ALObjects.Attributes.PROT_SHRED, BigDecimal.valueOf(ProtShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_shred");
                    addModifierIfValid(mob, ALObjects.Attributes.CRIT_CHANCE, BigDecimal.valueOf(Crit).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_chance");
                    addModifierIfValid(mob, ALObjects.Attributes.CRIT_DAMAGE, BigDecimal.valueOf(CritDmg).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_damage");

                    setIfNonNull(mob, PotatoAttributes.ATTACK_IV, attrVar[0]);
                    setIfNonNull(mob, PotatoAttributes.ARMOR_IV, attrVar[1]);
                    setIfNonNull(mob, PotatoAttributes.POWER_IV, attrVar[2]);
                    setIfNonNull(mob, PotatoAttributes.RESIST_IV, attrVar[3]);
                    setIfNonNull(mob, PotatoAttributes.CAST_IV, attrVar[4]);
                    setIfNonNull(mob, PotatoAttributes.ARMOR_PEN_IV, attrVar[5]);
                    setIfNonNull(mob, PotatoAttributes.PROT_PEN_IV, attrVar[6]);
                    setIfNonNull(mob, PotatoAttributes.CRIT_IV, attrVar[7]);
                }
            }
        }
    }

    private static double SpellPower = 0;
    private static double CastReduction = 0;
    private static double Resist = 0;
    private static double FireRes = 0;
    private static double IceRes = 0;
    private static double HolyRes = 0;
    private static double NatRes = 0;
    private static double EvokeRes = 0;
    private static double BloodRes = 0;
    private static double EndRes = 0;
    private static double LigRes = 0;
    private static double EldRes = 0;
    private static double AbyssRes = 0;
    private static double TechRes = 0;
    private static double BladeRes = 0;
    private static double MindRes = 0;
    private static double SoundRes = 0;
    private static double WindRes = 0;
    private static double SpiritRes = 0;
    private static double SymRes = 0;
    private static double DuneRes = 0;
    private static double AquaRes = 0;
    private static double Armor = 0;
    private static double Tough = 0;
    private static double Attack = 0;
    private static double ArmorPierce = 0;
    private static double ArmorShred = 0;
    private static double ProtPierce = 0;
    private static double ProtShred = 0;
    private static double CritDmg = 0;
    private static double Crit = 0;
}
