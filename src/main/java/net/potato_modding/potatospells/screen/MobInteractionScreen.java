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
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;
import net.potato_modding.potatospells.utils.ConfigFormulas;
import net.potato_modding.potatospells.utils.RebalanceHandler;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Map;
import java.util.Objects;

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

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/background.png");
    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui.png");
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
                                double sixthIV, double seventhIV, double eighthIV) {

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

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, OVERLAY_TEXTURE);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        Minecraft.getInstance().getTextureManager().getTexture(OVERLAY_TEXTURE).setFilter(false, false);

        graphics.blit(OVERLAY_TEXTURE, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
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

        double attacklevel = attack * 3;
        double healthLevel = health;
        double armorLevel = armor * 2;
        double manaLevel = Math.sqrt(mana);
        double resistLevel = 10 * Math.pow(resistParse, 2);
        double spellLevel = 10 * Math.pow((1 + power), 2);
        double castLevel = 5 * Math.pow(castParse, 2);
        double cooldownLevel = 7 * Math.pow(cooldownParse, 2);
        double criticalLevel = 1 + Math.clamp(critChance, 0, 1);
        double critLevel = attacklevel * Math.pow(crit, criticalLevel);
        double pierceLevel = 1 + (armorPierce / 2) + protPierce;
        double shredLevel = Math.clamp(armorShred, 0, 1) + Math.clamp(protShred, 0, 1);
        double bypassLevel = 25 * (pierceLevel * (1 + shredLevel));
        double ivLevel = firstIV + secondIV + thirdIV + fourthIV + fifthIV + sixthIV + seventhIV + eighthIV;
        double defensePower = (healthLevel + armorLevel + resistLevel);
        double offensePower = (spellLevel + castLevel + cooldownLevel + attacklevel + critLevel + bypassLevel);
        double powerLevel = 10 * (manaLevel + defensePower + offensePower) * (1 + ivLevel);
        if(minecraft.player != null && entity != minecraft.player) powerLevel /= 5;

        double IVGrab = ConfigFormulas.randMax;

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
        RenderSystem.setShaderTexture(0, OVERLAY_TEXTURE);

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
            Map.entry(PotatoTags.TYPE_ICE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/ice_icon.png")),
            Map.entry(PotatoTags.TYPE_HOLY, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/holy_icon.png")),
            Map.entry(PotatoTags.TYPE_NATURE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/nature_icon.png")),
            Map.entry(PotatoTags.TYPE_EVOKATION, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/evoke_icon.png")),
            Map.entry(PotatoTags.TYPE_BLOOD, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/blood_icon.png")),
            Map.entry(PotatoTags.TYPE_ENDER, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/ender_icon.png")),
            Map.entry(PotatoTags.TYPE_LIGHTNING, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/lightning_icon.png")),
            Map.entry(PotatoTags.TYPE_ELDRITCH, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/eldritch_icon.png")),
            Map.entry(PotatoTags.TYPE_ABYSS, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/abyss_icon.png")),
            Map.entry(PotatoTags.TYPE_TECHNOMANCY, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/techno_icon.png")),
            Map.entry(PotatoTags.TYPE_BLADE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/blade_icon.png")),
            Map.entry(PotatoTags.TYPE_MIND, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/mind_icon.png")),
            Map.entry(PotatoTags.TYPE_SOUND, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/sound_icon.png")),
            Map.entry(PotatoTags.TYPE_WIND, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/wind_icon.png")),
            Map.entry(PotatoTags.TYPE_SYM, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/sym_icon.png")),
            Map.entry(PotatoTags.TYPE_SOUL, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/soul_icon.png")),
            Map.entry(PotatoTags.TYPE_DUNE, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/dune_icon.png")),
            Map.entry(PotatoTags.TYPE_AQUA, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/aqua_icon.png")),
            Map.entry(PotatoTags.TYPE_NEUTRAL, ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/type/neutral_icon.png"))
    );

    private static final Map<TagKey<EntityType<?>>, Component> schoolTooltips = Map.ofEntries(
            Map.entry(PotatoTags.TYPE_FIRE, Component.literal("Fire")),
            Map.entry(PotatoTags.TYPE_ICE, Component.literal("Ice")),
            Map.entry(PotatoTags.TYPE_HOLY, Component.literal("Holy")),
            Map.entry(PotatoTags.TYPE_NATURE, Component.literal("Nature")),
            Map.entry(PotatoTags.TYPE_EVOKATION, Component.literal("Evocation")),
            Map.entry(PotatoTags.TYPE_BLOOD, Component.literal("Blood")),
            Map.entry(PotatoTags.TYPE_ENDER, Component.literal("Ender")),
            Map.entry(PotatoTags.TYPE_LIGHTNING, Component.literal("Lightning")),
            Map.entry(PotatoTags.TYPE_ELDRITCH, Component.literal("Eldritch")),
            Map.entry(PotatoTags.TYPE_ABYSS, Component.literal("Abyss")),
            Map.entry(PotatoTags.TYPE_TECHNOMANCY, Component.literal("Technomancy")),
            Map.entry(PotatoTags.TYPE_BLADE, Component.literal("Blade")),
            Map.entry(PotatoTags.TYPE_MIND, Component.literal("Mind")),
            Map.entry(PotatoTags.TYPE_SOUND, Component.literal("Sound")),
            Map.entry(PotatoTags.TYPE_WIND, Component.literal("Wind")),
            Map.entry(PotatoTags.TYPE_SYM, Component.literal("Symphony")),
            Map.entry(PotatoTags.TYPE_SOUL, Component.literal("Soul")),
            Map.entry(PotatoTags.TYPE_DUNE, Component.literal("Dune")),
            Map.entry(PotatoTags.TYPE_AQUA, Component.literal("Aqua")),
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