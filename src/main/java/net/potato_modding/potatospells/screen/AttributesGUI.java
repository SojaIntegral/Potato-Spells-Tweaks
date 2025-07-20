package net.potato_modding.potatospells.screen;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.potato_modding.potatospells.tags.PotatoTags;

import java.util.Objects;

public class AttributesGUI extends Item {

    public AttributesGUI(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if(player.isShiftKeyDown()) {
            target = player;
        }
        if (player.level().isClientSide) {
            float targetHealth = target.getMaxHealth();
            double targetArmor = 0;
            double targetAttack = 0;
            double targetMana = 0;
            double targetResist = 0;
            double targetSpell = 0;
            double targetCast = 0;
            double targetCrit = 0;
            double targetPierce1 = 0;
            double targetPierce2 = 0;

            var armorAttr = target.getAttribute(Attributes.ARMOR);
            if (armorAttr != null) targetArmor = armorAttr.getValue();

            var attackAttr = target.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attackAttr != null) targetAttack = attackAttr.getValue();

            var manaAttr = target.getAttribute(AttributeRegistry.MAX_MANA);
            if (manaAttr != null) targetMana = manaAttr.getValue();

            var resAttr = target.getAttribute(AttributeRegistry.SPELL_RESIST);
            if (resAttr != null) targetResist = resAttr.getValue();

            var spellAttr = target.getAttribute(AttributeRegistry.SPELL_POWER);
            if (spellAttr != null) targetSpell = spellAttr.getValue();

            var schoolFire = target.getType().is(PotatoTags.TYPE_FIRE);
            var schoolIce = target.getType().is(PotatoTags.TYPE_ICE);
            var schoolHoly = target.getType().is(PotatoTags.TYPE_HOLY);
            var schoolNature = target.getType().is(PotatoTags.TYPE_NATURE);
            var schoolEvoke = target.getType().is(PotatoTags.TYPE_EVOKATION);
            var schoolBlood = target.getType().is(PotatoTags.TYPE_BLOOD);
            var schoolEnder = target.getType().is(PotatoTags.TYPE_ENDER);
            var schoolLightning = target.getType().is(PotatoTags.TYPE_LIGHTNING);
            var schoolEldritch = target.getType().is(PotatoTags.TYPE_ELDRITCH);
            var schoolAbyss = target.getType().is(PotatoTags.TYPE_ABYSS);
            var schoolTechno = target.getType().is(PotatoTags.TYPE_TECHNOMANCY);
            var schoolBlade = target.getType().is(PotatoTags.TYPE_BLADE);
            var schoolMind = target.getType().is(PotatoTags.TYPE_MIND);
            var schoolSound = target.getType().is(PotatoTags.TYPE_SOUND);
            var schoolWind = target.getType().is(PotatoTags.TYPE_WIND);
            var schoolSym = target.getType().is(PotatoTags.TYPE_SYM);
            var schoolSoul = target.getType().is(PotatoTags.TYPE_SOUL);
            var schoolDune = target.getType().is(PotatoTags.TYPE_DUNE);
            var schoolAqua = target.getType().is(PotatoTags.TYPE_AQUA);

            Minecraft.getInstance().setScreen(new MobInteractionScreen(target.getName().getString(), target,
                    targetHealth, targetArmor, targetAttack, targetMana, targetResist,
                    targetSpell, targetCast, targetCrit, targetPierce1, targetPierce2,
                    schoolFire, schoolIce, schoolHoly, schoolNature, schoolEvoke,
                    schoolBlood, schoolEnder, schoolLightning, schoolEldritch,
                    schoolAbyss, schoolTechno, schoolBlade, schoolMind,
                    schoolSound, schoolWind, schoolSym, schoolSoul, schoolDune, schoolAqua));
        }
        return InteractionResult.SUCCESS;
    }
}
