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
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.potato_modding.potatospells.config.ServerConfigs;
import net.potato_modding.potatospells.tags.PotatoTags;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class MobInteractionScreen extends Screen {
    // Buttons on the GUI
    private Button button1;
    private Button button2;
    // Size of GUI
    private static final int GUI_WIDTH = 232;
    private static final int GUI_HEIGHT = 142;
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
    private final boolean schoolFire;
    private final boolean schoolIce;
    private final boolean schoolHoly;
    private final boolean schoolNature;
    private final boolean schoolEvoke;
    private final boolean schoolBlood;
    private final boolean schoolEnder;
    private final boolean schoolLightning;
    private final boolean schoolEldritch;
    private final boolean schoolAbyss;
    private final boolean schoolTechno;
    private final boolean schoolBlade;
    private final boolean schoolMind;
    private final boolean schoolSound;
    private final boolean schoolWind;
    private final boolean schoolSym;
    private final boolean schoolSoul;
    private final boolean schoolDune;
    private final boolean schoolAqua;

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
                                double cooldown, double crit, double critChance,
                                double armorPierce, double armorShred, double protPierce, double protShred,
                                boolean schoolFire, boolean schoolIce, boolean schoolHoly, boolean schoolNature, boolean schoolEvoke,
                                boolean schoolBlood, boolean schoolEnder, boolean schoolLightning, boolean schoolEldritch,
                                boolean schoolAbyss, boolean schoolTechno, boolean schoolBlade, boolean schoolMind,
                                boolean schoolSound, boolean schoolWind, boolean schoolSym, boolean schoolSoul,
                                boolean schoolDune, boolean schoolAqua) {

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

        this.schoolFire = schoolFire;
        this.schoolIce = schoolIce;
        this.schoolHoly = schoolHoly;
        this.schoolNature = schoolNature;
        this.schoolEvoke = schoolEvoke;
        this.schoolBlood = schoolBlood;
        this.schoolEnder = schoolEnder;
        this.schoolLightning = schoolLightning;
        this.schoolEldritch = schoolEldritch;
        this.schoolAbyss = schoolAbyss;
        this.schoolTechno = schoolTechno;
        this.schoolBlade = schoolBlade;
        this.schoolMind = schoolMind;
        this.schoolSound = schoolSound;
        this.schoolWind = schoolWind;
        this.schoolSym = schoolSym;
        this.schoolSoul = schoolSoul;
        this.schoolDune = schoolDune;
        this.schoolAqua = schoolAqua;
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

        double castParse = 0;
        double cooldownParse = 0;
        double resistParse = 0;
        double x = resist;
        double y = cooldown;
        double z = cast;

        if (ServerConfigs.FORMULA_REBALANCE.get() == 0) {
            if (resist <= 1.75) {
                resistParse = resist;
                cooldownParse = cooldown;
                castParse = cast;
            } else {
                resistParse = 1 / (-16 * (x - 1.5)) + 2;
                cooldownParse = 1 / (-16 * (y - 1.5)) + 2;
                castParse = 1 / (-16 * (z - 1.5)) + 2;
            }
        }
        if (ServerConfigs.FORMULA_REBALANCE.get() == 1) {
            if (resist <= 3.62699) {
                resistParse = 2 * (Math.sin(0.4 * (x + 0.3))) + 0.00624;
                cooldownParse = 2 * (Math.sin(0.4 * (y + 0.3))) + 0.00624;
                castParse = 2 * (Math.sin(0.4 * (z + 0.3))) + 0.00624;
            } else {
                resistParse = cooldownParse = castParse = 2;
            }
        }
        if (ServerConfigs.FORMULA_REBALANCE.get() == 2) {
            if (resist <= 4.80999) {
                resistParse = 2 * (Math.sin(0.28 * (x + 0.8))) + 0.034136;
                cooldownParse = 2 * (Math.sin(0.28 * (y + 0.8))) + 0.034136;
                castParse = 2 * (Math.sin(0.28 * (z + 0.8))) + 0.034136;
            } else {
                resistParse = cooldownParse = castParse = 2;
            }
        }
        if (ServerConfigs.FORMULA_REBALANCE.get() == 3) {
            if (resist <= 8.01198) {
                resistParse = 2 * (Math.sin(0.15 * (x + 2.46))) + 0.001736;
                cooldownParse = 2 * (Math.sin(0.15 * (y + 2.46))) + 0.001736;
                castParse = 2 * (Math.sin(0.15 * (z + 2.46))) + 0.001736;
            } else {
                resistParse = cooldownParse = castParse = 2;
            }
        }
        if (ServerConfigs.FORMULA_REBALANCE.get() == 4) {
            if (resist >= 0) {
                resistParse = 1.966667 - (30 / (29 + x));
                cooldownParse = 1.966667 - (30 / (29 + y));
                castParse = 1.966667 - (30 / (29 + z));
            } else {
                resistParse = 2 - ((20 - x) / 20);
                cooldownParse = 2 - ((20 - y) / 20);
                castParse = 2 - ((20 - z) / 20);
            }
        }

        double critParse = crit * 100;
        double critChanceParse = critChance * 100;
        double armorShredParse = armorShred * 100;
        double protShredParse = protShred * 100;

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
        if (mana < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", mana)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 6) / textScale), 0x57b6db);
        } else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (mana / 1000)) + "k"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 6) / textScale), 0x57b6db);
        }
        if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                && scaledMouseY >= ((guiTop + 4) / textScale) && scaledMouseY <= ((guiTop + 14) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Maximum Mana"), (int) scaledMouseX, (int) scaledMouseY);
        }

        graphics.drawString(this.font, Component.literal(String.format("%.0f", (resistParse - 1) * 100) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
        if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
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

        graphics.drawString(this.font, Component.literal(String.format("%.0f", (cooldownParse - 1) * 100) + "%"), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
        if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Cooldown Reduction"), (int) scaledMouseX, (int) scaledMouseY);
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

                if(SHINY.containsKey(id)) {
                    graphics.blit(SHINY_ICON, guiLeft + 3, guiTop + 23, 0, 0, 16, 16, 16 ,16);
                    if (mouseX >= (guiLeft + 3) && mouseX <= (guiLeft + 19)
                            && mouseY >= (guiTop + 23) && mouseY <= (guiTop + 39)) {
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

    private static final Map<ResourceLocation, Component> SHINY = Map.ofEntries(
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "shiny"), Component.literal("Shiny"))
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