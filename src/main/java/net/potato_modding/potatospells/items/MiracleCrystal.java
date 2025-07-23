package net.potato_modding.potatospells.items;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.resistances.core.PotatoNaturesHandler;
import net.potato_modding.potatospells.tags.PotatoTags;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.potato_modding.potatospells.utils.ConfigFormulas.*;

public class MiracleCrystal extends Item {

    public MiracleCrystal(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        return applyIVCrystal(stack, player, target).getResult(); // Use on self
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player.isShiftKeyDown()) {
            return applyIVCrystal(player.getItemInHand(hand), player, player); // Use on self
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    // Add modifier (base)
    private static void addModifierIfValid(LivingEntity target, Holder<Attribute> attribute, double value, String idName) {
        var instance = target.getAttributes().getInstance(attribute);
        if (instance == null) return;
        System.out.println("add base");
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", idName);
        instance.removeModifier(id);
        instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_VALUE));
    }

    // Add modifier (multiplied base)
    private static void multiplyModifierIfValid(LivingEntity target, Holder<Attribute> attribute, double value, String idName) {
        var instance = target.getAttributes().getInstance(attribute);
        if (instance == null) return;
        System.out.println("multiply base");
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", idName);
        instance.removeModifier(id);
        instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    private static boolean shinyAttribute() {
        return ThreadLocalRandom.current().nextInt(ServerConfigs.SHINY_CHANCE.get()) == 0;
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

    private InteractionResultHolder<ItemStack> applyIVCrystal(ItemStack stack, Player player, LivingEntity target) {
        if (!player.level().isClientSide) {

            if (!player.getAbilities().instabuild) { // Not in creative
                if (stack.getCount() != 0) {
                    System.out.println("removed stack");
                    stack.shrink(1);
                }
            }

            // Grabs an attribute and checks if the mob has a modifier
            var shinyChecker = target.getAttributes().getInstance(PotatoAttributes.SHINY);
            ResourceLocation modCheck = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "normal");
            ResourceLocation shinyCheck = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "shiny");
            // If the mob is shiny, it won't have this modifier
            assert shinyChecker != null;
            boolean alreadyShiny = shinyChecker.getModifiers().stream().anyMatch(mod -> mod.id().equals(shinyCheck));
            // If the mob is shiny, it won't have this modifier
            boolean canReroll = shinyChecker.getModifiers().stream().noneMatch(mod -> mod.id().equals(shinyCheck));

            // IVs variation setup
            boolean isShiny = false;
            double[] attrVar = new double[10];
            System.out.println("done logic check");
            // Chance for shiny & prevents shinies from losing perfect IVs
            if (target != player) {
                if ((ServerConfigs.SHINY.get() && shinyAttribute()) || alreadyShiny) {
                    Arrays.fill(attrVar, 1 * randMax);
                    System.out.println("is shiny");
                    isShiny = true;
                }
                // Adds + 0~15% to Familiars' attributes & can be rerolled
                // I should be able to copy this code over and make so non-shinies are rerolled
                else if ((ServerConfigs.SHINY.get() && !shinyAttribute()) || canReroll) {
                    for (int i = 0; i < attrVar.length; i++) {
                        attrVar[i] = Math.random() * randMax;
                        System.out.println("is not shiny");
                    }
                }
            }
            else if (!ServerConfigs.SHINY.get() || target.getType().is(PotatoTags.PLAYER)) {
                Arrays.fill(attrVar, 0);
                System.out.println("is player");
            }

            // Checks if the mob has a valid modifier from here
            // If not, it gives the mob modifiers
            if (target.getType().is(PotatoTags.MOB_ENABLED) && canReroll && !alreadyShiny) {
                System.out.println("shit is happening");

                // Class modifier
                if (target.getType().is(PotatoTags.BOSS)) {
                    mobType = boss_mod;
                    ArmorMod = boss_mod * (3 * (1 + attrVar[0]));
                    ToughMod = boss_mod * (2 * (1 + attrVar[1]));
                    AttackMod = boss_mod * (2 * (1 + attrVar[1]));
                }
                if (target.getType().is(PotatoTags.MINIBOSS)) {
                    mobType = mini_mod;
                    ArmorMod = mini_mod * (1.5 * (1 + attrVar[0]));
                    ToughMod = mini_mod * (1.5 * (1 + attrVar[1]));
                    AttackMod = mini_mod * (1 * (1 + attrVar[1]));
                }
                if (target.getType().is(PotatoTags.NORMAL)) {
                    mobType = mob_mod;
                    ArmorMod = mob_mod * (0.75 * (1 + attrVar[0]));
                    ToughMod = mob_mod * (0.5 * (1 + attrVar[1]));
                    AttackMod = mob_mod * (0.65 * (1 + attrVar[1]));
                }
                if (target.getType().is(PotatoTags.SUMMON)) {
                    System.out.println("attribute calc");
                    mobType = summon_mod;
                    ArmorMod = summon_mod * (1 * (1 + attrVar[0]));
                    ToughMod = summon_mod * (0.5 * (1 + attrVar[1]));
                    AttackMod = summon_mod * (0.5 * (1 + attrVar[1]));
                }
                if (target.getType().is(PotatoTags.PLAYER)) {
                    mobType = 1;
                    ArmorMod = 1;
                    ToughMod = 1;
                    AttackMod = 1;
                }

                if (target.getType().is(PotatoTags.RACE_PLAYER)) {
                    System.out.println("player");
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
                if (target.getType().is(PotatoTags.RACE_HUMAN)) {
                    System.out.println("human");
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
                if (target.getType().is(PotatoTags.RACE_UNDEAD)) {
                    System.out.println("undead");
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
                if (target.getType().is(PotatoTags.RACE_HUMANOID)) {
                    System.out.println("humanoid");
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
                if (target.getType().is(PotatoTags.RACE_BRUTE)) {
                    System.out.println("brute");
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
                    ProtShred = 1.5 * (1 + attrVar[6]);
                    CritDmg = attrVar[7] - 0.15;
                    Crit = attrVar[7] + 0.05;
                }
                if (target.getType().is(PotatoTags.RACE_INSECT)) {
                    System.out.println("insect");
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
                if (target.getType().is(PotatoTags.RACE_FLYING)) {
                    System.out.println("flying");
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
                if (target.getType().is(PotatoTags.RACE_GOLEM)) {
                    System.out.println("golem");
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
                if (target.getType().is(PotatoTags.RACE_CONSTRUCT)) {
                    System.out.println("construct");
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
                if (target.getType().is(PotatoTags.RACE_FISH)) {
                    System.out.println("fish");
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
                if (target.getType().is(PotatoTags.RACE_SPIRIT)) {
                    System.out.println("spirit");
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
                if (target.getType().is(PotatoTags.RACE_AMORPH)) {
                    System.out.println("amorph");
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
                if (target.getType().is(PotatoTags.RACE_DRAGONBORN)) {
                    System.out.println("dragonborn");
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
                if (target.getType().is(PotatoTags.RACE_DRAGON)) {
                    System.out.println("dragon");
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
                if (target.getType().is(PotatoTags.TYPE_BLOOD)) {
                    System.out.println("blood");
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
                if (target.getType().is(PotatoTags.TYPE_ELDRITCH)) {
                    System.out.println("eldritch");
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
                if (target.getType().is(PotatoTags.TYPE_ENDER)) {
                    System.out.println("ender");
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
                if (target.getType().is(PotatoTags.TYPE_EVOKATION)) {
                    System.out.println("evoke");
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
                if (target.getType().is(PotatoTags.TYPE_FIRE)) {
                    System.out.println("fire");
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
                if (target.getType().is(PotatoTags.TYPE_HOLY)) {
                    System.out.println("holy");
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
                if (target.getType().is(PotatoTags.TYPE_ICE)) {
                    System.out.println("ice");
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
                if (target.getType().is(PotatoTags.TYPE_LIGHTNING)) {
                    System.out.println("lightning");
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
                if (target.getType().is(PotatoTags.TYPE_NATURE)) {
                    System.out.println("nature");
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
                if (target.getType().is(PotatoTags.TYPE_WIND)) {
                    System.out.println("wind");
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
                if (target.getType().is(PotatoTags.TYPE_ABYSS)) {
                    System.out.println("abyss");
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
                if (target.getType().is(PotatoTags.TYPE_TECHNOMANCY)) {
                    System.out.println("tech");
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
                if (target.getType().is(PotatoTags.TYPE_BLADE)) {
                    System.out.println("blade");
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
                if (target.getType().is(PotatoTags.TYPE_MIND)) {
                    System.out.println("mind");
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
                if (target.getType().is(PotatoTags.TYPE_SOUND)) {
                    System.out.println("sound");
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
                if (target.getType().is(PotatoTags.TYPE_DUNE)) {
                    System.out.println("dune");
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
                if (target.getType().is(PotatoTags.TYPE_SOUL)) {
                    System.out.println("soul");
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
                if (target.getType().is(PotatoTags.TYPE_SYM)) {
                    System.out.println("sym");
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
                if (target.getType().is(PotatoTags.TYPE_AQUA)) {
                    System.out.println("aqua");
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
                if (target.getType().is(PotatoTags.TYPE_NEUTRAL)) {
                    System.out.println("neutral");
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

                System.out.println("applying attributes");
                // Vanilla Attributes
                if (isShiny) {
                    addModifierIfValid(target, PotatoAttributes.SHINY, 0, "shiny");
                } else {
                    addModifierIfValid(target, PotatoAttributes.SHINY, 0, "normal");
                }
                addModifierIfValid(target, Attributes.ATTACK_DAMAGE, BigDecimal.valueOf(Attack).setScale(2, RoundingMode.HALF_UP).doubleValue(), "attack");
                addModifierIfValid(target, Attributes.ARMOR, BigDecimal.valueOf(Armor).setScale(2, RoundingMode.HALF_UP).doubleValue(), "armor");
                addModifierIfValid(target, Attributes.ARMOR_TOUGHNESS, BigDecimal.valueOf(Tough).setScale(2, RoundingMode.HALF_UP).doubleValue(), "toughness");

                // Magic Attributes
                multiplyModifierIfValid(target, AttributeRegistry.SPELL_POWER, (BigDecimal.valueOf(SpellPower).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spell_power");
                multiplyModifierIfValid(target, AttributeRegistry.CAST_TIME_REDUCTION, (BigDecimal.valueOf(CastReduction).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "cast");
                multiplyModifierIfValid(target, AttributeRegistry.SPELL_RESIST, (BigDecimal.valueOf(Resist).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "resist");
                multiplyModifierIfValid(target, AttributeRegistry.FIRE_MAGIC_RESIST, (BigDecimal.valueOf(FireRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "fire_resist");
                multiplyModifierIfValid(target, AttributeRegistry.NATURE_MAGIC_RESIST, (BigDecimal.valueOf(NatRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "nature_resist");
                multiplyModifierIfValid(target, AttributeRegistry.ENDER_MAGIC_RESIST, (BigDecimal.valueOf(EndRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "end_resist");
                multiplyModifierIfValid(target, AttributeRegistry.EVOCATION_MAGIC_RESIST, (BigDecimal.valueOf(EvokeRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "evoke_resist");
                multiplyModifierIfValid(target, AttributeRegistry.BLOOD_MAGIC_RESIST, (BigDecimal.valueOf(BloodRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blood_resist");
                multiplyModifierIfValid(target, AttributeRegistry.ICE_MAGIC_RESIST, (BigDecimal.valueOf(IceRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "ice_resist");
                multiplyModifierIfValid(target, AttributeRegistry.LIGHTNING_MAGIC_RESIST, (BigDecimal.valueOf(LigRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "lightning_resist");
                multiplyModifierIfValid(target, AttributeRegistry.ELDRITCH_MAGIC_RESIST, (BigDecimal.valueOf(EldRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "eldritch_resist");
                multiplyModifierIfValid(target, AttributeRegistry.HOLY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "holy_resist");
                // This needs to be conditional or the game shits itself if the mod is not present
                if (ModList.get().isLoaded("endersequipment")) {
                    multiplyModifierIfValid(target, net.ender.endersequipment.registries.EEAttributeRegistry.BLADE_MAGIC_RESIST, (BigDecimal.valueOf(BladeRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blade_resist");
                    //var MIND_GOBLIN_RESIST = net.ender.endersequipment.registries.EEAttributeRegistry.MIND_SPELL_RESIST;
                    //multiplyModifierIfValid(mob, MIND_GOBLIN_RESIST, (BigDecimal.valueOf(MindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "mind_resist");
                }
                if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                    multiplyModifierIfValid(target, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, (BigDecimal.valueOf(AbyssRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "abyssal_resist");
                    //multiplyModifierIfValid(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.TECHNOMANCY_MAGIC_RESIST, (BigDecimal.valueOf(TechRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "technomancy_resist");
                }
                if (ModList.get().isLoaded("alshanex_familiars")) {
                    //multiplyModifierIfValid(mob, net.alshanex.alshanex_familiars.registry.AttributeRegistry.SOUND_MAGIC_RESIST, (BigDecimal.valueOf(SoundRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "sound_resist");
                }
                if (ModList.get().isLoaded("aero_additions")) {
                    multiplyModifierIfValid(target, com.snackpirate.aeromancy.spells.AASpells.Attributes.WIND_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "wind_resist");
                }
                if (ModList.get().isLoaded("iss_magicfromtheeast")) {
                    multiplyModifierIfValid(target, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST, (BigDecimal.valueOf(SymRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "symmetry_resist");
                    multiplyModifierIfValid(target, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST, (BigDecimal.valueOf(SpiritRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spirit_resist");
                    multiplyModifierIfValid(target, net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries.DUNE_MAGIC_RESIST, (BigDecimal.valueOf(DuneRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "dune_resist");
                }
                //if (ModList.get().isLoaded("traveloptics")) {}

                // Apothic Attributes
                addModifierIfValid(target, ALObjects.Attributes.ARMOR_PIERCE, BigDecimal.valueOf(ArmorPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_pierce");
                addModifierIfValid(target, ALObjects.Attributes.ARMOR_SHRED, BigDecimal.valueOf(ArmorShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_shred");
                addModifierIfValid(target, ALObjects.Attributes.PROT_PIERCE, BigDecimal.valueOf(ProtPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_pierce");
                addModifierIfValid(target, ALObjects.Attributes.PROT_SHRED, BigDecimal.valueOf(ProtShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_shred");
                addModifierIfValid(target, ALObjects.Attributes.CRIT_CHANCE, BigDecimal.valueOf(Crit).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_chance");
                addModifierIfValid(target, ALObjects.Attributes.CRIT_DAMAGE, BigDecimal.valueOf(CritDmg).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_damage");
                System.out.println("finished attributes");
            }
        }
        System.out.println("success");
        return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide);
    }
}
