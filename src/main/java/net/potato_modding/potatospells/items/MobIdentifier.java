package net.potato_modding.potatospells.items;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.fml.ModList;
import net.potato_modding.potatospells.client.ClientScreens;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;
import net.potato_modding.potatospells.utils.RebalanceHandler;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.util.List;

import static net.potato_modding.potatospells.utils.ConfigFormulas.mobType;

public class MobIdentifier extends CurioBaseItem {

    public MobIdentifier() {
        super(ItemPropertiesHelper.equipment().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Can identify attributes")
                .withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
        tooltip.add(Component.literal("Right click on a mob to identify")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
        tooltip.add(Component.literal("Hold shift to identify yourself")
                .withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (player.level().isClientSide) {
            if (player.isShiftKeyDown()) {
                target = player; // View self
            }

            float targetHealth = target.getMaxHealth();
            double targetArmor = getAttr(target, Attributes.ARMOR);
            double targetAttack = getAttr(target, Attributes.ATTACK_DAMAGE);
            double targetMana = getAttr(target, AttributeRegistry.MAX_MANA);
            double targetResist = getAttr(target, AttributeRegistry.SPELL_RESIST);
            double targetSpell = getAttr(target, AttributeRegistry.SPELL_POWER);
            double targetCast = getAttr(target, AttributeRegistry.CAST_TIME_REDUCTION);
            double targetCooldown = getAttr(target, AttributeRegistry.COOLDOWN_REDUCTION);
            double targetCrit = getAttr(target, ALObjects.Attributes.CRIT_DAMAGE);
            double targetCritChance = getAttr(target, ALObjects.Attributes.CRIT_CHANCE);
            double targetPierce1 = getAttr(target, ALObjects.Attributes.ARMOR_PIERCE);
            double targetShred1 = getAttr(target, ALObjects.Attributes.ARMOR_SHRED);
            double targetPierce2 = getAttr(target, ALObjects.Attributes.PROT_PIERCE);
            double targetShred2 = getAttr(target, ALObjects.Attributes.PROT_SHRED);

            double firstIV = getAttr(target, PotatoAttributes.ATTACK_IV);
            double secondIV = getAttr(target, PotatoAttributes.ARMOR_IV);
            double thirdIV = getAttr(target, PotatoAttributes.POWER_IV);
            double fourthIV = getAttr(target, PotatoAttributes.RESIST_IV);
            double fifthIV = getAttr(target, PotatoAttributes.CAST_IV);
            double sixthIV = getAttr(target, PotatoAttributes.ARMOR_PEN_IV);
            double seventhIV = getAttr(target, PotatoAttributes.PROT_PEN_IV);
            double eighthIV = getAttr(target, PotatoAttributes.CRIT_IV);

            double attackSpeed = getAttr(target, Attributes.ATTACK_SPEED);

            double firePower = getAttr(target, AttributeRegistry.FIRE_SPELL_POWER);
            double icePower = getAttr(target, AttributeRegistry.ICE_SPELL_POWER);
            double lightningPower = getAttr(target, AttributeRegistry.LIGHTNING_SPELL_POWER);
            double naturePower = getAttr(target, AttributeRegistry.NATURE_SPELL_POWER);
            double enderPower = getAttr(target, AttributeRegistry.ENDER_SPELL_POWER);
            double holyPower = getAttr(target, AttributeRegistry.HOLY_SPELL_POWER);
            double eldritchPower = getAttr(target, AttributeRegistry.ELDRITCH_SPELL_POWER);
            double evokerPower = getAttr(target, AttributeRegistry.EVOCATION_SPELL_POWER);
            double abyssPower = (ModList.get().isLoaded("cataclysm_spellbooks")) ? getAttr(target, CSAttributeRegistry.ABYSSAL_MAGIC_POWER) : 1;
            double bladePower = (ModList.get().isLoaded("endersequipment")) ? getAttr(target, EEAttributeRegistry.BLADE_SPELL_POWER) : 1;
            double songPower = (ModList.get().isLoaded("alshanex_familiars")) ? getAttr(target, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_SPELL_POWER) : 1;
            double windPower = (ModList.get().isLoaded("aero_additions")) ? getAttr(target, AASpells.Attributes.WIND_SPELL_POWER) : 1;
            double symmetryPower = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SYMMETRY_SPELL_POWER) : 1;
            double dunePower = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.DUNE_SPELL_POWER) : 1;
            double spiritPower = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SPIRIT_SPELL_POWER) : 1;

            double fireRes = getAttr(target, AttributeRegistry.FIRE_MAGIC_RESIST);
            double iceRes = getAttr(target, AttributeRegistry.ICE_MAGIC_RESIST);
            double lightningRes = getAttr(target, AttributeRegistry.LIGHTNING_MAGIC_RESIST);
            double natureRes = getAttr(target, AttributeRegistry.NATURE_MAGIC_RESIST);
            double enderRes = getAttr(target, AttributeRegistry.ENDER_MAGIC_RESIST);
            double holyRes = getAttr(target, AttributeRegistry.HOLY_MAGIC_RESIST);
            double eldritchRes = getAttr(target, AttributeRegistry.ELDRITCH_MAGIC_RESIST);
            double evokerRes = getAttr(target, AttributeRegistry.EVOCATION_MAGIC_RESIST);
            double abyssRes = (ModList.get().isLoaded("cataclysm_spellbooks")) ? getAttr(target, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST) : 1;
            double bladeRes = (ModList.get().isLoaded("endersequipment")) ? getAttr(target, EEAttributeRegistry.BLADE_MAGIC_RESIST) : 1;
            double songRes = (ModList.get().isLoaded("alshanex_familiars")) ? getAttr(target, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST) : 1;
            double windRes = (ModList.get().isLoaded("aero_additions")) ? getAttr(target, AASpells.Attributes.WIND_MAGIC_RESIST) : 1;
            double symmetryRes = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST) : 1;
            double duneRes = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.DUNE_MAGIC_RESIST) : 1;
            double spiritRes = (ModList.get().isLoaded("iss_magicfromtheeast")) ? getAttr(target, MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST) : 1;

            double rgb = 0;


            // Call the client screen opener
            ClientScreens.openMobInteractionScreen(
                    target.getName().getString(), target,
                    targetHealth, targetArmor, targetAttack, targetMana, targetResist,
                    targetSpell, targetCast, targetCooldown, targetCrit, targetCritChance, targetPierce1,
                    targetShred1, targetPierce2, targetShred2,
                    firstIV, secondIV, thirdIV, fourthIV, fifthIV,
                    sixthIV, seventhIV, eighthIV, attackSpeed,
                    firePower, icePower, lightningPower, naturePower,
                    enderPower, holyPower, eldritchPower, evokerPower,
                    abyssPower, bladePower, songPower, windPower,
                    symmetryPower, dunePower, spiritPower,
                    fireRes, iceRes, lightningRes, natureRes,
                    enderRes, holyRes, eldritchRes, evokerRes,
                    abyssRes, bladeRes, songRes, windRes,
                    symmetryRes, duneRes, spiritRes, rgb
            );
        }
        return InteractionResult.SUCCESS;
    }

    private double getAttr(LivingEntity target, Holder<Attribute> Holder) {
        var attrInstance = target.getAttribute(Holder);
        return attrInstance != null ? attrInstance.getValue() : 0;
    }
}
