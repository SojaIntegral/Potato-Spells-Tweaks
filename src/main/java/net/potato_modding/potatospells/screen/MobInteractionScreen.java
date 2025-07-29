package net.potato_modding.potatospells.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatospells.items.*;
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;
import net.potato_modding.potatospells.utils.ConfigFormulas;
import net.potato_modding.potatospells.utils.RebalanceHandler;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Map;
import java.util.Objects;

import static net.potato_modding.potatospells.items.Analyzer.OVERLAY_TEXTURE;

public class MobInteractionScreen extends Screen {
    // Buttons on the GUI
    private Button button1;
    private Button button2;
    private boolean showIVs = false;
    // Size of GUI
    private static final int GUI_WIDTH = 164;
    private static final int GUI_HEIGHT = 124;
    // Renders the selected mob
    private final String entityName;
    private final LivingEntity entity;
    private final float health;
    private final double armor;
    private final double attack;
    private final double mana;
    private final double resist;
    private final double power;
    private final double cast;
    private final double cooldown;
    private final double crit;
    private final double critChance;
    private final double armorPierce;
    private final double armorShred;
    private final double protPierce;
    private final double protShred;
    private final double firstIV;
    private final double secondIV;
    private final double thirdIV;
    private final double fourthIV;
    private final double fifthIV;
    private final double sixthIV;
    private final double seventhIV;
    private final double eighthIV;
    private final double attackSpeed;
    private final double firePower;
    private final double icePower; private final double lightningPower; private final double naturePower; private final double enderPower;
    private final double holyPower; private final double eldritchPower; private final double evokerPower; private final double abyssPower;
    private final double bladePower; private final double songPower; private final double windPower; private final double symmetryPower;
    private final double dunePower; private final double spiritPower; private final double fireRes; private final double iceRes; private final double lightningRes;
    private final double natureRes; private final double enderRes; private final double holyRes; private final double eldritchRes; private final double evokerRes;
    private final double abyssRes; private final double bladeRes; private final double songRes; private final double windRes; private final double symmetryRes;
    private final double duneRes; private final double spiritRes;

    private final double rgb;

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/background.png");
    private static final ResourceLocation DEFAULT_ICONS =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/default_icons.png");
    private static final ResourceLocation SHINY_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/shiny_icon.png");

    public MobInteractionScreen(String entityName, LivingEntity entity,
                                float health, double armor, double attack,
                                double mana, double resist, double power, double cast,
                                double cooldown, double crit, double critChance, double armorPierce,
                                double armorShred, double protPierce, double protShred, double firstIV,
                                double secondIV, double thirdIV, double fourthIV, double fifthIV,
                                double sixthIV, double seventhIV, double eighthIV, double attackSpeed, double firePower,
                                double icePower, double lightningPower, double naturePower, double enderPower,
                                double holyPower, double eldritchPower, double evokerPower, double abyssPower,
                                double bladePower, double songPower, double windPower, double symmetryPower,
                                double dunePower, double spiritPower, double fireRes, double iceRes, double lightningRes,
                                double natureRes, double enderRes, double holyRes, double eldritchRes, double evokerRes,
                                double abyssRes, double bladeRes, double songRes, double windRes, double symmetryRes,
                                double duneRes, double spiritRes, double rgb) {

        super(Component.literal(""));
        this.entityName = entityName;
        this.entity = entity;
        this.health = health;
        this.armor = armor;
        this.attack = attack;
        this.mana = mana;
        this.resist = resist;
        this.power = power;
        this.cast = cast;
        this.cooldown = cooldown;
        this.crit = crit;
        this.critChance = critChance;
        this.armorPierce = armorPierce;
        this.armorShred = armorShred;
        this.protPierce = protPierce;
        this.protShred = protShred;

        this.firstIV = firstIV;
        this.secondIV = secondIV;
        this.thirdIV = thirdIV;
        this.fourthIV = fourthIV;
        this.fifthIV = fifthIV;
        this.sixthIV = sixthIV;
        this.seventhIV = seventhIV;
        this.eighthIV = eighthIV;
        this.attackSpeed = attackSpeed;

        this.firePower = firePower;
        this.icePower = icePower; this.lightningPower = lightningPower; this.naturePower = naturePower; this.enderPower = enderPower;
        this.holyPower = holyPower; this.eldritchPower = eldritchPower; this.evokerPower = evokerPower; this.abyssPower = abyssPower;
        this.bladePower = bladePower; this.songPower = songPower; this.windPower = windPower; this.symmetryPower = symmetryPower;
        this.dunePower = dunePower; this.spiritPower = spiritPower; this.fireRes = fireRes; this.iceRes = iceRes; this.lightningRes = lightningRes;
        this.natureRes = natureRes; this.enderRes = enderRes; this.holyRes = holyRes; this.eldritchRes = eldritchRes; this.evokerRes = evokerRes;
        this.abyssRes = abyssRes; this.bladeRes = bladeRes; this.songRes = songRes; this.windRes = windRes; this.symmetryRes = symmetryRes;
        this.duneRes = duneRes; this.spiritRes = spiritRes;

        this.rgb = rgb;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        int guiLeft = (this.width - GUI_WIDTH) / 2;
        int guiTop = (this.height - GUI_HEIGHT) / 2;

        Minecraft.getInstance().getTextureManager()
                .getTexture(BACKGROUND_TEXTURE)
                .setFilter(false, false); // Disable blurring

        // Draw background texture (none, but is there for resource packs makers)
        graphics.blit(BACKGROUND_TEXTURE, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);

        // Call super first to render buttons (no buttons atm)
        super.render(graphics, mouseX, mouseY, partialTicks);

        int entityX = guiLeft + 36;
        int entityY = guiTop + 84;

        float entityHeight = this.entity.getBbHeight();
        float entityWidth = this.entity.getBbWidth();

        float frameHeight = 48f;
        float frameWidth = 26f;
        float marginFactor = 0.8f;

        float scale = Math.min(frameHeight / entityHeight, frameWidth / entityWidth) * marginFactor;
        scale = Mth.clamp(scale, 10f, 80f);

        // Save original rotations
        float originalYRot = entity.getYRot();
        float originalXRot = entity.getXRot();
        float originalYHeadRot = entity.yHeadRot;
        float originalYBodyRot = entity.yBodyRot;

        entity.setYRot(0f);
        entity.setXRot(0f);
        entity.yHeadRot = 0f;
        entity.yBodyRot = 0f;

        // Renders the entity in the GUI
        InventoryScreen.renderEntityInInventory(
                graphics,
                entityX,
                entityY,
                scale,
                new Vector3f(0f, 0f, 0f),
                new Quaternionf().rotateX((float) Math.toRadians(180)),
                new Quaternionf(),
                this.entity
        );

        // Restore original rotations
        entity.setYRot(originalYRot);
        entity.setXRot(originalXRot);
        entity.yHeadRot = originalYHeadRot;
        entity.yBodyRot = originalYBodyRot;

        var overlayTexture = Analyzer.OVERLAY_TEXTURE;
        if(rgb == 1) overlayTexture = AnalyzerRed.OVERLAY_TEXTURE;
        if(rgb == 2) overlayTexture = AnalyzerGreen.OVERLAY_TEXTURE;
        if(rgb == 3) overlayTexture = AnalyzerBlue.OVERLAY_TEXTURE;
        if(rgb == 4) overlayTexture = AnalyzerYellow.OVERLAY_TEXTURE;
        if(rgb == 5) overlayTexture = AnalyzerPink.OVERLAY_TEXTURE;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, overlayTexture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        Minecraft.getInstance().getTextureManager().getTexture(overlayTexture).setFilter(false, false);

        graphics.blit(overlayTexture, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        graphics.blit(DEFAULT_ICONS, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        RenderSystem.disableBlend();

        graphics.pose().pushPose();
        float textScale = 0.85f;
        graphics.pose().scale(textScale, textScale, 1.0f);

        int drawX = (int) (guiLeft / textScale);
        int drawY = (int) (guiTop / textScale);
        float scaledMouseX = mouseX / textScale;
        float scaledMouseY = mouseY / textScale;

        double castParse = RebalanceHandler.rebalanceFormula(cast);
        double cooldownParse = RebalanceHandler.rebalanceFormula(cooldown);
        double resistParse = RebalanceHandler.rebalanceFormula(resist);

        double IVGrab = ConfigFormulas.randMax;

        double armorLevel;
        double manaLevel;
        double resistLevel;
        double spellLevel;
        double castLevel;
        double cooldownLevel;
        double criticalLevel;
        double critLevel;
        double critMagicLevel;
        double pierceLevel;
        double shredLevel;
        double bypassLevel;
        double ivLevel;
        double defensePower;
        double magicalPower;
        double attackPower;
        double attackLevel;
        double powerLevel;
        double elementalResist;

        // Defensive attributes
        armorLevel = armor;
        elementalResist = RebalanceHandler.rebalanceFormula(fireRes) * RebalanceHandler.rebalanceFormula(iceRes)
                * RebalanceHandler.rebalanceFormula(lightningRes) * RebalanceHandler.rebalanceFormula(natureRes)
                * RebalanceHandler.rebalanceFormula(enderRes) * RebalanceHandler.rebalanceFormula(holyRes)
                * RebalanceHandler.rebalanceFormula(eldritchRes) * RebalanceHandler.rebalanceFormula(evokerRes)
                * RebalanceHandler.rebalanceFormula(abyssRes) * RebalanceHandler.rebalanceFormula(bladeRes)
                * RebalanceHandler.rebalanceFormula(songRes) * RebalanceHandler.rebalanceFormula(windRes)
                * RebalanceHandler.rebalanceFormula(symmetryRes) * RebalanceHandler.rebalanceFormula(duneRes)
                * RebalanceHandler.rebalanceFormula(spiritRes);
        resistLevel = (resistParse * elementalResist);
        defensePower = ((health * 0.05) * resistLevel) + (armorLevel * resistLevel); // Using vanilla health as base (1/20)
        if(entity.getType().is(PotatoTags.PLAYER)) defensePower = defensePower * (1 + (mana / 3500));

        // Universal combat attributes
        criticalLevel = Math.clamp(critChance, 0, 1);
        critLevel = Math.max(Math.pow(crit, criticalLevel + 0.25), 1);
        pierceLevel = (armorPierce + (1.5 * protPierce)) * 0.05;
        shredLevel = (0.5 * Math.clamp(armorShred, 0, 1)) + Math.clamp(protShred, 0, 1);
        bypassLevel = 1 + (pierceLevel + shredLevel);

        double[] values = {firePower, icePower, lightningPower,
                naturePower, enderPower, holyPower, eldritchPower,
                evokerPower, abyssPower, bladePower, songPower,
                windPower, symmetryPower, dunePower, spiritPower};
        double elementalPower = Double.NEGATIVE_INFINITY;

        for (double val : values) {
            if (val > elementalPower) elementalPower = val;
        }

        // Magic attributes
        manaLevel = mana <= 100? mana : 100 + Math.sqrt(mana - 100);
        spellLevel = power * elementalPower;
        castLevel = (1 + castParse) == 0 ? -0.01 : (1 + castParse);
        cooldownLevel = (1 + cooldownParse) == 0 ? -0.01 : (1 + cooldownParse);
        critMagicLevel = Math.max(Math.pow(crit, criticalLevel), 1);
        magicalPower = manaLevel * spellLevel * castLevel * cooldownLevel * bypassLevel * critMagicLevel;

        // Attack
        attackLevel = 5 * attack * attackSpeed;
        attackPower = attackLevel * bypassLevel * critLevel; // Critical for attack has more value because of jump crit

        // IV bonus
        ivLevel = (firstIV + secondIV + thirdIV + fourthIV + fifthIV + sixthIV + seventhIV + eighthIV) * 0.125;

        // Power Level
        powerLevel = (defensePower + attackPower + magicalPower) * (1 + ivLevel);

        // Type modifiers
        if(entity.getType().is(PotatoTags.BOSS)) powerLevel *= ConfigFormulas.boss_mod * 0.75;
        if(entity.getType().is(PotatoTags.MINIBOSS)) powerLevel *= ConfigFormulas.mini_mod * 0.5;
        if(entity.getType().is(PotatoTags.NORMAL)) powerLevel *= ConfigFormulas.mob_mod * 0.3;
        if(entity.getType().is(PotatoTags.SUMMON)) powerLevel *= ConfigFormulas.summon_mod * 0.25;

        // Race modifiers
        if(entity.getType().is(PotatoTags.RACE_AMORPH)) powerLevel *= 1.15;
        if(entity.getType().is(PotatoTags.RACE_BRUTE)) powerLevel *= 1.05;
        if(entity.getType().is(PotatoTags.RACE_CONSTRUCT)) powerLevel *= 1.2;
        if(entity.getType().is(PotatoTags.RACE_DRAGON)) powerLevel *= 1.2;
        if(entity.getType().is(PotatoTags.RACE_DRAGONBORN)) powerLevel *= 1.15;
        if(entity.getType().is(PotatoTags.RACE_FISH)) powerLevel *= 1.05;
        if(entity.getType().is(PotatoTags.RACE_FLYING)) powerLevel *= 1;
        if(entity.getType().is(PotatoTags.RACE_GOLEM)) powerLevel *= 1.25;
        if(entity.getType().is(PotatoTags.RACE_HUMAN)) powerLevel *= 1.05;
        if(entity.getType().is(PotatoTags.RACE_HUMANOID)) powerLevel *= 1.025;
        if(entity.getType().is(PotatoTags.RACE_INSECT)) powerLevel *= 1.15;

        double critParse = crit * 100;
        double critChanceParse = critChance * 100;
        double armorShredParse = armorShred * 100;
        double protShredParse = protShred * 100;

        this.addRenderableWidget(Button.builder(
                Component.literal(""),
                btn -> {
                    showIVs = !showIVs;
                    btn.setMessage(Component.literal(""));
                }
        ).bounds(guiLeft + 153, guiTop + 96, 9, 9).build());


        Component mobName = entity.getDisplayName();
        if (scaledMouseX >= (guiLeft + 16) / textScale && scaledMouseX <= (guiLeft + 61) / textScale
                && scaledMouseY >= (guiTop + 37) / textScale && scaledMouseY <= (guiTop + 81) / textScale) {
            graphics.renderTooltip(font, mobName, (int) scaledMouseX, (int) scaledMouseY);
        }

        // LEFT SIDE
        if (health < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", health)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 6) / textScale), 0xe25c5c);
        } else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (health / 1000)) + "k"), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 6) / textScale), 0xe25c5c);
        }
        if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                && scaledMouseY >= ((guiTop + 4) / textScale) && scaledMouseY <= ((guiTop + 14) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Maximum Health"), (int) scaledMouseX, (int) scaledMouseY);
        }

        if (mana < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", mana)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 6) / textScale), 0x57b6db);
        } else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (mana / 1000)) + "k"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 6) / textScale), 0x57b6db);
        }
        if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                && scaledMouseY >= ((guiTop + 4) / textScale) && scaledMouseY <= ((guiTop + 14) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Maximum Mana"), (int) scaledMouseX, (int) scaledMouseY);
        }

        graphics.drawString(this.font, Component.literal(String.format("%.0f", (cooldownParse - 1) * 100) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
        if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Cooldown Reduction"), (int) scaledMouseX, (int) scaledMouseY);
        }

        if (!showIVs) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", armor)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", attack)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Attack Damage"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", critChanceParse) + "%"), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical Chance"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", critParse) + "%"), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical Damage"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", armorPierce)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor Piercing"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", armorShredParse) + "%"), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Ignored Armor"), (int) scaledMouseX, (int) scaledMouseY);
            }

            // RIGHT SIDE
            graphics.drawString(this.font, Component.literal(String.format("%.0f", (resistParse - 1) * 100) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell Resistance"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", ((power - 1) * 100)) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell Power"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", (castParse - 1) * 100) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Cast Reduction"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", protPierce)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Protection Pierce"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", protShredParse) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Ignored Protection"), (int) scaledMouseX, (int) scaledMouseY);
            }
        }
        if (showIVs) {

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (secondIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (firstIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Attack IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (eighthIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (eighthIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (sixthIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor Piercing IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (sixthIV / IVGrab))), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor Piercing IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            // RIGHT SIDE
            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (fourthIV / IVGrab))), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Resistance IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (thirdIV / IVGrab))), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (fifthIV / IVGrab))), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Cast IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (seventhIV / IVGrab))), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Protection Pierce IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", 31 * (seventhIV / IVGrab))), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Protection Pierce IV"), (int) scaledMouseX, (int) scaledMouseY);
            }
        }

        graphics.pose().popPose();

        boolean typePosition = mouseX >= guiLeft + 25 && mouseX <= guiLeft + 41 && mouseY >= guiTop + 8 && mouseY <= guiTop + 24;
        boolean racePosition = mouseX >= guiLeft + 9 && mouseX <= guiLeft + 25 && mouseY >= guiTop + 8 && mouseY <= guiTop + 24;
        boolean rankPosition = mouseX >= guiLeft + 46 && mouseX <= guiLeft + 62 && mouseY >= guiTop + 8 && mouseY <= guiTop + 24;

        // Rendering the actual UI background
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, overlayTexture);

        // Rendering icons and tooltips
        var schoolVisualTooltip = getSchoolVisuals(entity);
        ResourceLocation schoolTexture = schoolVisualTooltip.getKey();
        Component schoolTooltip = schoolVisualTooltip.getValue();
        graphics.blit(schoolTexture, guiLeft + 30, guiTop + 8, 0, 0, 16, 16, 16, 16);
        if (typePosition) {
            graphics.renderTooltip(font, schoolTooltip, mouseX, mouseY);
        }
        var raceVisualTooltip = getRaceVisuals(entity);
        ResourceLocation raceTexture = raceVisualTooltip.getKey();
        Component raceTooltip = raceVisualTooltip.getValue();
        graphics.blit(raceTexture, guiLeft + 9, guiTop + 8, 0, 0, 16, 16, 16 ,16);
        if(racePosition) {
            graphics.renderTooltip(font, raceTooltip, mouseX, mouseY);
        }
        var rankVisualTooltip = getRankVisuals(entity);
        ResourceLocation rankTextures = rankVisualTooltip.getKey();
        Component rankTooltips = rankVisualTooltip.getValue();
        graphics.blit(rankTextures, guiLeft + 51, guiTop + 8, 0, 0, 16, 16, 16 ,16);
        if(rankPosition) {
            graphics.renderTooltip(font, rankTooltips, mouseX, mouseY);
        }

        for (AttributeInstance attr : entity.getAttributes().getSyncableAttributes()) {
            for (AttributeModifier modifier : attr.getModifiers()) {
                ResourceLocation id = modifier.id();

                if (NATURES.containsKey(id)) {
                    Component name = NATURES.get(id);
                    graphics.drawString(font, name, guiLeft + 6, guiTop + 93, 0xFFFFFF);
                }

                graphics.drawString(font, Component.literal("Power: " + String.format("%.0f", powerLevel)), guiLeft + 6, guiTop + 111, 0xFFFFFF);

                if(Objects.requireNonNull(entity.getAttribute(PotatoAttributes.SHINY)).getValue() == 1) {
                    graphics.blit(SHINY_ICON, guiLeft + 3, guiTop + 23, 0, 0, 16, 16, 16 ,16);
                    if (mouseX >= (guiLeft + 3) && mouseX <= (guiLeft + 18)
                            && mouseY >= (guiTop + 25) && mouseY <= (guiTop + 35)) {
                        graphics.renderTooltip(font, Component.literal("Shiny"), mouseX, mouseY);
                    }
                }
            }
        }
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.disableBlend();
    }

    private static final Map<ResourceLocation, Component> NATURES = Map.ofEntries(
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks","hardy"), Component.literal("Hardy")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "lonely"), Component.literal("Lonely")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "adamant"), Component.literal("Adamant")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "naughty"), Component.literal("Naughty")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "brave"), Component.literal("Brave")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "bold"), Component.literal("Bold")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "docile"), Component.literal("Docile")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "impish"), Component.literal("Impish")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "lax"), Component.literal("Lax")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "relaxed"), Component.literal("Relaxed")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "modest"), Component.literal("Modest")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "mild"), Component.literal("Mild")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "bashful"), Component.literal("Bashful")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "rash"), Component.literal("Rash")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "quiet"), Component.literal("Quiet")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "calm"), Component.literal("Calm")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "gentle"), Component.literal("Gentle")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "careful"), Component.literal("Careful")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "quirky"), Component.literal("Quirky")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "sassy"), Component.literal("Sassy")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "timid"), Component.literal("Timid")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "hasty"), Component.literal("Hasty")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "jolly"), Component.literal("Jolly")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "naive"), Component.literal("Naive")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "serious"), Component.literal("Serious"))
    );

    private static final Map<TagKey<EntityType<?>>, ResourceLocation> schoolTextures = Map.ofEntries(
            Map.entry(PotatoTags.TYPE_FIRE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/fire_icon.png")),
            Map.entry(PotatoTags.TYPE_HOLY, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/holy_icon.png")),
            Map.entry(PotatoTags.TYPE_EARTH, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/nature_icon.png")),
            Map.entry(PotatoTags.TYPE_BLOOD, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/blood_icon.png")),
            Map.entry(PotatoTags.TYPE_ENDER, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/ender_icon.png")),
            Map.entry(PotatoTags.TYPE_ELDRITCH, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/eldritch_icon.png")),
            Map.entry(PotatoTags.TYPE_WIND, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/wind_icon.png")),
            Map.entry(PotatoTags.TYPE_SOUL, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/soul_icon.png")),
            Map.entry(PotatoTags.TYPE_WATER, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/aqua_icon.png")),
            Map.entry(PotatoTags.TYPE_NEUTRAL, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/neutral_icon.png"))
    );

    private static final Map<TagKey<EntityType<?>>, Component> schoolTooltips = Map.ofEntries(
            Map.entry(PotatoTags.TYPE_FIRE, Component.literal("Fire")),
            Map.entry(PotatoTags.TYPE_HOLY, Component.literal("Holy")),
            Map.entry(PotatoTags.TYPE_EARTH, Component.literal("Earth")),
            Map.entry(PotatoTags.TYPE_BLOOD, Component.literal("Blood")),
            Map.entry(PotatoTags.TYPE_ENDER, Component.literal("Ender")),
            Map.entry(PotatoTags.TYPE_ELDRITCH, Component.literal("Eldritch")),
            Map.entry(PotatoTags.TYPE_WIND, Component.literal("Wind")),
            Map.entry(PotatoTags.TYPE_SOUL, Component.literal("Soul")),
            Map.entry(PotatoTags.TYPE_WATER, Component.literal("Water")),
            Map.entry(PotatoTags.TYPE_NEUTRAL, Component.literal("Neutral"))
    );

    public static ResourceLocation getSchoolTexture(LivingEntity entity) {
        for (var entry : schoolTextures.entrySet()) {
            if (entity.getType().is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return DEFAULT_ICON;
    }

    public static Map.Entry<ResourceLocation, Component> getSchoolVisuals(LivingEntity entity) {
        for (TagKey<EntityType<?>> tag : schoolTextures.keySet()) {
            if (entity.getType().is(tag)) {
                return Map.entry(schoolTextures.get(tag), schoolTooltips.get(tag));
            }
        }
        return Map.entry(DEFAULT_ICON, DEFAULT_TOOLTIP);
    }

    private static final Map<TagKey<EntityType<?>>, ResourceLocation> raceTexture = Map.ofEntries(
            Map.entry(PotatoTags.RACE_AMORPH, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/amorph_icon.png")),
            Map.entry(PotatoTags.RACE_BRUTE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/brute_icon.png")),
            Map.entry(PotatoTags.RACE_CONSTRUCT, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/construct_icon.png")),
            Map.entry(PotatoTags.RACE_DRAGON, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/dragon_icon.png")),
            Map.entry(PotatoTags.RACE_DRAGONBORN, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/dragonborn_icon.png")),
            Map.entry(PotatoTags.RACE_FLYING, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/flying_icon.png")),
            Map.entry(PotatoTags.RACE_FISH, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/fish_icon.png")),
            Map.entry(PotatoTags.RACE_GOLEM, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/golem_icon.png")),
            Map.entry(PotatoTags.RACE_HUMAN, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/human_icon.png")),
            Map.entry(PotatoTags.RACE_HUMANOID, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/humanoid_icon.png")),
            Map.entry(PotatoTags.RACE_INSECT, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/insect_icon.png")),
            Map.entry(PotatoTags.RACE_SPIRIT, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/spirit_icon.png")),
            Map.entry(PotatoTags.RACE_UNDEAD, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/undead_icon.png")),
            Map.entry(PotatoTags.RACE_PLAYER, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/race/player_icon.png"))

    );

    private static final Map<TagKey<EntityType<?>>, Component> raceTooltips = Map.ofEntries(
            Map.entry(PotatoTags.RACE_AMORPH, Component.literal("Amorph")),
            Map.entry(PotatoTags.RACE_BRUTE, Component.literal("Brute")),
            Map.entry(PotatoTags.RACE_CONSTRUCT, Component.literal("Construct")),
            Map.entry(PotatoTags.RACE_DRAGON, Component.literal("Dragon")),
            Map.entry(PotatoTags.RACE_DRAGONBORN, Component.literal("Dragonborn")),
            Map.entry(PotatoTags.RACE_FLYING, Component.literal("Flying")),
            Map.entry(PotatoTags.RACE_FISH, Component.literal("Fish")),
            Map.entry(PotatoTags.RACE_GOLEM, Component.literal("Golem")),
            Map.entry(PotatoTags.RACE_HUMAN, Component.literal("Human")),
            Map.entry(PotatoTags.RACE_HUMANOID, Component.literal("Humanoid")),
            Map.entry(PotatoTags.RACE_INSECT, Component.literal("Insect")),
            Map.entry(PotatoTags.RACE_SPIRIT, Component.literal("Spirit")),
            Map.entry(PotatoTags.RACE_UNDEAD, Component.literal("Undead")),
            Map.entry(PotatoTags.RACE_PLAYER, Component.literal("Human"))
    );

    private static final ResourceLocation DEFAULT_ICON =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/item/potato.png");

    private static final Component DEFAULT_TOOLTIP = Component.literal("None");

    public static ResourceLocation getRaceIcons(LivingEntity entity) {
        for (var entry : raceTexture.entrySet()) {
            if (entity.getType().is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return DEFAULT_ICON;
    }

    public static Map.Entry<ResourceLocation, Component> getRaceVisuals(LivingEntity entity) {
        for (TagKey<EntityType<?>> tag : raceTexture.keySet()) {
            if (entity.getType().is(tag)) {
                return Map.entry(raceTexture.get(tag), raceTooltips.get(tag));
            }
        }
        return Map.entry(DEFAULT_ICON, DEFAULT_TOOLTIP);
    }

    private static final Map<TagKey<EntityType<?>>, ResourceLocation> rankTexture = Map.ofEntries(
            Map.entry(PotatoTags.BOSS, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/rank/rank_boss.png")),
            Map.entry(PotatoTags.MINIBOSS, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/rank/rank_miniboss.png")),
            Map.entry(PotatoTags.NORMAL, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/rank/rank_normal.png")),
            Map.entry(PotatoTags.PLAYER, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/rank/rank_player.png")),
            Map.entry(PotatoTags.SUMMON, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/rank/rank_summon.png"))
    );

    private static final Map<TagKey<EntityType<?>>, Component> rankTooltip = Map.ofEntries(
            Map.entry(PotatoTags.BOSS, Component.literal("Boss")),
            Map.entry(PotatoTags.MINIBOSS, Component.literal("Miniboss")),
            Map.entry(PotatoTags.NORMAL, Component.literal("Normal")),
            Map.entry(PotatoTags.PLAYER, Component.literal("Player")),
            Map.entry(PotatoTags.SUMMON, Component.literal("Summon"))
    );

    public static ResourceLocation getRankIcons(LivingEntity entity) {
        for (var entry : rankTexture.entrySet()) {
            if (entity.getType().is(entry.getKey())) {
                return entry.getValue();
            }
        }
        return DEFAULT_ICON;
    }

    public static Map.Entry<ResourceLocation, Component> getRankVisuals(LivingEntity entity) {
        for (TagKey<EntityType<?>> tag : rankTexture.keySet()) {
            if (entity.getType().is(tag)) {
                return Map.entry(rankTexture.get(tag), rankTooltip.get(tag));
            }
        }
        return Map.entry(DEFAULT_ICON, DEFAULT_TOOLTIP);
    }
}