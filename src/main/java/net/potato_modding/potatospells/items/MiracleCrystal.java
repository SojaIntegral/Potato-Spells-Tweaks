package net.potato_modding.potatospells.items;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.alshanex.familiarslib.entity.AbstractSpellCastingPet;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.potato_modding.potatoessentials.config.ServerConfigs;
import net.potato_modding.potatoessentials.datagen.MobElementLoader;
import net.potato_modding.potatoessentials.datagen.MobRaceLoader;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.potato_modding.potatoessentials.PotatoEssentials.MOD_ID;
import static net.potato_modding.potatoessentials.utils.ConfigFormulas.*;

public class MiracleCrystal extends Item {

    public MiracleCrystal(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {

        if(player.getCooldowns().isOnCooldown(stack.getItem())) {
            player.displayClientMessage(
                    Component.literal("Item on Cooldown").withStyle(ChatFormatting.DARK_RED), true
            );
            return InteractionResult.FAIL;
        }

        if(target.getAttributeValue(PotatoEssentialsAttributes.SHINY) == 1) {
            player.displayClientMessage(
                    Component.literal("Already has perfect IVs").withStyle(ChatFormatting.DARK_RED), true
            );
            return InteractionResult.FAIL;
        }

        if(!player.level().isClientSide && target instanceof AbstractSpellCastingPet familiar) {
            if (familiar.getSummoner() != null && familiar.getSummoner().is(player)) {

                return applyIVCrystal(stack, player, target).getResult();
            }
        }
        if(!player.getAbilities().instabuild) {
            player.getCooldowns().addCooldown(stack.getItem(), 20);
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Use on a familiar to reroll IVs")
                .withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("Hold shift to use on yourself")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
        tooltip.add(Component.literal(""));
        tooltip.add(Component.literal("WARNING: Does not work on players by default!")
                .withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
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

        CompoundTag nbtdata = target.getPersistentData();
        CompoundTag potatoData = nbtdata.getCompound("PotatoData");
        var shinyCheck = false;
        if(!target.level().isClientSide()) shinyCheck = potatoData.getBoolean("shiny");

        if(!shinyCheck) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", idName);
            instance.removeModifier(id);
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    // Add modifier (multiplied base)
    private static void multiplyModifierIfValid(LivingEntity target, Holder<Attribute> attribute, double value, String idName) {
        var instance = target.getAttributes().getInstance(attribute);
        if (instance == null) return;

        CompoundTag nbtdata = target.getPersistentData();
        CompoundTag potatoData = nbtdata.getCompound("PotatoData");
        var shinyCheck = false;
        if(!target.level().isClientSide()) shinyCheck = potatoData.getBoolean("shiny");

        if(!shinyCheck) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", idName);
            instance.removeModifier(id);
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
    }

    private static boolean shinyAttribute() {
        return ThreadLocalRandom.current().nextInt(shinyChanceModifier) == 0;
    }

    private static double SpellPower = 0;
    private static double CastReduction = 0;
    private static double Resist = 0;
    private static double NeutralRes = 0;
    private static double WaterRes = 0;
    private static double EarthRes = 0;
    private static double FireRes = 0;
    private static double WindRes = 0;
    private static double EldritchRes = 0;
    private static double HolyRes = 0;
    private static double BloodRes = 0;
    private static double SoulRes = 0;
    private static double EnderRes = 0;
    private static double Armor = 0;
    private static double Tough = 0;
    private static double Attack = 0;
    private static double ArmorPierce = 0;
    private static double ArmorShred = 0;
    private static double ProtPierce = 0;
    private static double ProtShred = 0;
    private static double CritDmg = 0;
    private static double Crit = 0;

    private static boolean isShiny = false;

    public InteractionResultHolder<ItemStack> applyIVCrystal(ItemStack stack, Player player, LivingEntity target) {
        if (!player.level().isClientSide) {

            if(!ServerConfigs.IV_SYSTEM.get()) {
                player.displayClientMessage(
                        Component.literal("This item has been disabled").withStyle(ChatFormatting.DARK_RED), true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (target.getAttributeValue(PotatoEssentialsAttributes.SHINY) == 1) isShiny = true;

            if (player.getCooldowns().isOnCooldown(stack.getItem())) {
                player.displayClientMessage(
                        Component.literal("Item on Cooldown").withStyle(ChatFormatting.DARK_RED), true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (isShiny) {
                player.displayClientMessage(
                        Component.literal("Target already has perfect IVs").withStyle(ChatFormatting.GOLD), true
                );
                return InteractionResultHolder.fail(stack);
            }

            if (!target.getType().is(PotatoTags.MOB_ENABLED) || target.getType().is(PotatoTags.PLAYER)) {
                if(!player.getAbilities().instabuild) {
                    player.getCooldowns().addCooldown(stack.getItem(), 20);
                }
                player.displayClientMessage(
                        Component.literal("Target does not have IVs").withStyle(ChatFormatting.DARK_RED), true
                );
                return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide);
            }

            // IVs variation setup
            double[] attrVar = new double[10];
            // Chance for shiny & prevents shinies from losing perfect IVs
            if (ServerConfigs.IV_SYSTEM.get() && shinyAttribute()) {
                Arrays.fill(attrVar, 1 * randMax);
            }
            // Adds + 0~15% to Familiars' attributes & can be rerolled
            // I should be able to copy this code over and make so non-shinies are rerolled
            else if (ServerConfigs.IV_SYSTEM.get() && !shinyAttribute()) {
                for (int i = 0; i < attrVar.length; i++) {
                    attrVar[i] = Math.random() * randMax;
                }
            } else if (!ServerConfigs.IV_SYSTEM.get() || target.getType().is(PotatoTags.PLAYER)) {
                Arrays.fill(attrVar, 0);
            }

            // Checks if the mob has a valid modifier from here
            // If not, it gives the mob modifiers
            if (target.getType().is(PotatoTags.MOB_ENABLED)) {

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

                target.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
                    if (tagKey.registry().equals(Registries.ENTITY_TYPE) &&
                            tagKey.location().getNamespace().equals("potatoessentials") &&
                            tagKey.location().getPath().startsWith("mob_races/")) {

                        String raceName = tagKey.location().getPath().substring("mob_races/".length());
                        ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, raceName);
                        var data = MobRaceLoader.get(dataId);

                        Attack = data.attack() * AttackMod;
                        Armor = data.armor() * ArmorMod;
                        Tough = data.tough() * ToughMod;
                        SpellPower = data.spellPower() + attrVar[2];
                        CastReduction = data.castReduction() + attrVar[3];
                        Resist = data.resist() + attrVar[4];
                        NeutralRes = data.neutralRes() + attrVar[4];
                        WaterRes = data.waterRes() + attrVar[4];
                        EarthRes = data.earthRes() + attrVar[4];
                        FireRes = data.fireRes() + attrVar[4];
                        WindRes = data.windRes() + attrVar[4];
                        BloodRes = data.bloodRes() + attrVar[4];
                        HolyRes = data.holyRes() + attrVar[4];
                        EldritchRes = data.eldritchRes() + attrVar[4];
                        SoulRes = data.soulRes() + attrVar[4];
                        EnderRes = data.enderRes() + attrVar[4];
                        ArmorPierce = data.armorPierce() * (1 + attrVar[5]);
                        ArmorShred = data.armorShred() * (1 + attrVar[5]);
                        ProtPierce = data.protPierce() * (1 + attrVar[6]);
                        ProtShred = data.protShred() * (1 + attrVar[6]);
                        CritDmg = data.critDmg() + attrVar[7];
                        Crit = data.crit() + attrVar[7];
                    }
                });

                target.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
                    if (tagKey.registry().equals(Registries.ENTITY_TYPE)
                            && tagKey.location().getNamespace().equals("potatoessentials")
                            && tagKey.location().getPath().startsWith("mob_elements/")) {

                        String elementName = tagKey.location().getPath().substring("mob_elements/".length());
                        ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, elementName);
                        var data = MobElementLoader.get(dataId);

                        Resist *= data.resist() * mobType;
                        NeutralRes *= data.neutralRes() * mobType;
                        WaterRes *= data.waterRes() * mobType;
                        EarthRes *= data.earthRes() * mobType;
                        FireRes *= data.fireRes() * mobType;
                        WindRes *= data.windRes() * mobType;
                        BloodRes *= data.bloodRes() * mobType;
                        HolyRes *= data.holyRes() * mobType;
                        EldritchRes *= data.eldritchRes() * mobType;
                        SoulRes *= data.soulRes() * mobType;
                        EnderRes *= data.enderRes() * mobType;
                    }
                });

                // Updates mob attributes after rounding it up to 2 decimals
                {
                    if ((Armor == 0 || attrVar[0] == 1) && (Attack == 0 || attrVar[1] == 1) &&
                            (attrVar[2] == 1) && (attrVar[3] == 1) && (attrVar[4] == 1) &&
                            (ArmorPierce == 0 || attrVar[5] == 1) && (ProtPierce == 0 || attrVar[6] == 1) && (attrVar[7] == 1)) {
                        isShiny = true;
                    }

                    // Vanilla Attributes
                    if (isShiny) {
                        if(ServerConfigs.IV_SYSTEM.get()) {
                            setIfNonNull(target, PotatoEssentialsAttributes.SHINY, 1);
                        }
                    }

                    // Vanilla Attributes
                    {
                        addModifierIfValid(target, Attributes.ATTACK_DAMAGE, BigDecimal.valueOf(Attack).setScale(2, RoundingMode.HALF_UP).doubleValue(), "attack");
                        addModifierIfValid(target, Attributes.ARMOR, BigDecimal.valueOf(Armor).setScale(2, RoundingMode.HALF_UP).doubleValue(), "armor");
                        addModifierIfValid(target, Attributes.ARMOR_TOUGHNESS, BigDecimal.valueOf(Tough).setScale(2, RoundingMode.HALF_UP).doubleValue(), "toughness");
                    }

                    // Magic Attributes
                    if (ModList.get().isLoaded("irons_spellbooks")) {
                        multiplyModifierIfValid(target, AttributeRegistry.SPELL_POWER, (BigDecimal.valueOf(SpellPower).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spell_power");
                        multiplyModifierIfValid(target, AttributeRegistry.CAST_TIME_REDUCTION, (BigDecimal.valueOf(CastReduction).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "cast");
                        multiplyModifierIfValid(target, AttributeRegistry.SPELL_RESIST, (BigDecimal.valueOf(Resist).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "resist");
                        multiplyModifierIfValid(target, AttributeRegistry.FIRE_MAGIC_RESIST, (BigDecimal.valueOf(FireRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "fire_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.NATURE_MAGIC_RESIST, (BigDecimal.valueOf(EarthRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "nature_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.ENDER_MAGIC_RESIST, (BigDecimal.valueOf(EnderRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "end_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.EVOCATION_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "evoke_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.BLOOD_MAGIC_RESIST, (BigDecimal.valueOf(BloodRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blood_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.ICE_MAGIC_RESIST, (BigDecimal.valueOf(WaterRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "ice_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.LIGHTNING_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "lightning_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.ELDRITCH_MAGIC_RESIST, (BigDecimal.valueOf(EldritchRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "eldritch_resist");
                        multiplyModifierIfValid(target, AttributeRegistry.HOLY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "holy_resist");
                    }
                    // This needs to be conditional or the game shits itself if the mod is not present
                    if (ModList.get().isLoaded("endersequipment")) {
                        multiplyModifierIfValid(target, EEAttributeRegistry.BLADE_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blade_resist");
                        //var MIND_GOBLIN_RESIST = net.ender.endersequipment.registries.EEAttributeRegistry.MIND_SPELL_RESIST;
                        //multiplyModifierIfValid(mob, MIND_GOBLIN_RESIST, (BigDecimal.valueOf(SoulRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "mind_resist");
                    }
                    if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                        multiplyModifierIfValid(target, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, (BigDecimal.valueOf(WaterRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "abyssal_resist");
                        //multiplyModifierIfValid(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.TECHNOMANCY_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "technomancy_resist");
                    }
                    if (ModList.get().isLoaded("alshanex_familiars")) {
                        multiplyModifierIfValid(target, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "sound_resist");
                    }
                    if (ModList.get().isLoaded("aero_additions")) {
                        multiplyModifierIfValid(target, AASpells.Attributes.WIND_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "wind_resist");
                    }
                    if (ModList.get().isLoaded("iss_magicfromtheeast")) {
                        multiplyModifierIfValid(target, MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "symmetry_resist");
                        multiplyModifierIfValid(target, MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST, (BigDecimal.valueOf(SoulRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spirit_resist");
                        multiplyModifierIfValid(target, MFTEAttributeRegistries.DUNE_MAGIC_RESIST, (BigDecimal.valueOf(EarthRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "dune_resist");
                    }
                    //if (ModList.get().isLoaded("traveloptics")) {}

                    // Apothic Attributes
                    {
                        addModifierIfValid(target, ALObjects.Attributes.ARMOR_PIERCE, BigDecimal.valueOf(ArmorPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_pierce");
                        addModifierIfValid(target, ALObjects.Attributes.ARMOR_SHRED, BigDecimal.valueOf(ArmorShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_shred");
                        addModifierIfValid(target, ALObjects.Attributes.PROT_PIERCE, BigDecimal.valueOf(ProtPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_pierce");
                        addModifierIfValid(target, ALObjects.Attributes.PROT_SHRED, BigDecimal.valueOf(ProtShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_shred");
                        addModifierIfValid(target, ALObjects.Attributes.CRIT_CHANCE, BigDecimal.valueOf(Crit).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_chance");
                        addModifierIfValid(target, ALObjects.Attributes.CRIT_DAMAGE, BigDecimal.valueOf(CritDmg).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_damage");
                    }
                }
            }

            if (!player.getAbilities().instabuild) { // Not in creative
                if (stack.getCount() != 0) {
                    stack.shrink(1);
                    player.getCooldowns().addCooldown(stack.getItem(), 10);
                }
            }
        }
        //System.out.println("success");
        return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide);
    }

    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }
}
