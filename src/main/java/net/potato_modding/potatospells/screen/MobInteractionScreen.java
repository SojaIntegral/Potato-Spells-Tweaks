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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.potato_modding.potatospells.config.ServerConfigs;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static net.potato_modding.potatospells.utils.RebalanceHandler.reFormula;

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
    private final double crit;
    private final double armorPierce;
    private final double protPierce;
    private final boolean schoolFire; private final boolean schoolIce; private final boolean schoolHoly; private final boolean schoolNature; private final boolean schoolEvoke;
    private final boolean schoolBlood; private final boolean schoolEnder; private final boolean schoolLightning; private final boolean schoolEldritch;
    private final boolean schoolAbyss; private final boolean schoolTechno; private final boolean schoolBlade; private final boolean schoolMind;
    private final boolean schoolSound; private final boolean schoolWind; private final boolean schoolSym; private final boolean schoolSoul; private final boolean schoolDune; private final boolean schoolAqua;

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/background.png");
    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui.png");

    private static final ResourceLocation FIRE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/fire_icon.png");
    private static final ResourceLocation ICE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/ice_icon.png");
    private static final ResourceLocation HOLY_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/holy_icon.png");
    private static final ResourceLocation NATURE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/nature_icon.png");
    private static final ResourceLocation EVOKE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/evokation_icon.png");
    private static final ResourceLocation BLOOD_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/blood_icon.png");
    private static final ResourceLocation ENDER_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/ender_icon.png");
    private static final ResourceLocation LIGHTNING_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/lightning_icon.png");
    private static final ResourceLocation ELDRITCH_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/eldritch_icon.png");
    private static final ResourceLocation ABYSS_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/abyss_icon.png");
    private static final ResourceLocation TECHNO_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/technomancy_icon.png");
    private static final ResourceLocation BLADE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/blade_icon.png");
    private static final ResourceLocation MIND_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/mind_icon.png");
    private static final ResourceLocation SOUND_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/sound_icon.png");
    private static final ResourceLocation WIND_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/wind_icon.png");
    private static final ResourceLocation SYMMETRY_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/symmetry_icon.png");
    private static final ResourceLocation SOUL_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/soul_icon.png");
    private static final ResourceLocation DUNE_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/dune_icon.png");
    private static final ResourceLocation AQUA_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/aqua_icon.png");

    public MobInteractionScreen(String entityName, LivingEntity entity,
                                float health, double armor, double attack,
                                double mana, double resist, double power, double cast,
                                double crit, double armorPierce, double protPierce, boolean schoolFire, boolean schoolIce,
                                boolean schoolHoly, boolean schoolNature, boolean schoolEvoke,
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
        this.crit = crit;
        this.armorPierce = armorPierce;
        this.protPierce = protPierce;

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
        int entityY = guiTop + 80;

        float entityHeight = this.entity.getBbHeight();
        float entityWidth = this.entity.getBbWidth();

        float frameHeight = 48f;
        float frameWidth = 26f;
        float marginFactor = 0.9f;

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
        RenderSystem.setShaderTexture(0, FIRE_ICON);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        Minecraft.getInstance().getTextureManager().getTexture(OVERLAY_TEXTURE).setFilter(false, false);

        // Redraw over the background
        graphics.blit(OVERLAY_TEXTURE, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        RenderSystem.disableBlend();

        graphics.pose().pushPose();
        float textScale = 0.85f;
        graphics.pose().scale(textScale, textScale, 1.0f);

        int drawX = (int)((guiLeft + 22) / textScale);
        int drawY = (int)((guiTop + 94) / textScale);
        double resistParse = 0;
        double x = resist;

        if(ServerConfigs.FORMULA_REBALANCE.get() == 0) {
            if(resist <= 1.75) {
                resistParse = resist;
            }
            else {
                resistParse = 1 / (-16 * (x - 1.5)) + 2;
            }
        }
        if(ServerConfigs.FORMULA_REBALANCE.get() == 1) {
            if(resist <= 3.62699) {
                resistParse = 2*(Math.sin(0.4*(x+0.3)))+0.00624;
            }
            else {
                resistParse = 2;
            }
        }
        if(ServerConfigs.FORMULA_REBALANCE.get() == 2) {
            if(resist <= 4.80999) {
                resistParse = 2*(Math.sin(0.28*(x+0.8)))+0.034136;
            }
            else {
                resistParse = 2;
            }
        }
        if(ServerConfigs.FORMULA_REBALANCE.get() == 3) {
            if(resist <= 8.01198) {
                resistParse = 2*(Math.sin(0.15*(x+2.46)))+0.001736;
            }
            else {
                resistParse = 2;
            }
        }
        if(ServerConfigs.FORMULA_REBALANCE.get() == 4) {
            if(resist >= 0) {
                resistParse = 1.966667 - (30 / (29+x));
            }
            else {
                resistParse = 2 - ((20 - x) / 20);
            }
        }

        if(health < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", health)), drawX, drawY, 0xFFAAAA);
        }
        else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (health / 1000))+"k"), drawX, drawY, 0xFFAAAA);
        }
        graphics.drawString(this.font, Component.literal(String.format("%.0f", armor)), drawX, drawY + 16, 0xFFAAAA);
        graphics.drawString(this.font, Component.literal(String.format("%.0f", attack)), drawX, drawY + 32, 0xFFAAAA);
        if(mana < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", mana)), drawX + 38, drawY, 0xFFAAAA);
        }
        else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (mana / 1000))+"k"), drawX + 38, drawY, 0xFFAAAA);
        }
        graphics.drawString(this.font, Component.literal(String.format("%.0f", (resistParse - 1) * 100)+"%"), drawX + 38, drawY + 16, 0xFFAAAA);
        graphics.drawString(this.font, Component.literal(String.format("%.0f", ((power - 1) * 100))+"%"), drawX + 38, drawY + 32, 0xFFAAAA);
        graphics.pose().popPose();

        float scaledMouseX = mouseX / textScale;
        float scaledMouseY = mouseY / textScale;

        if (scaledMouseX >= drawX - 17 && scaledMouseX <= drawX - 2 && scaledMouseY >= drawY - 3 && scaledMouseY <= drawY + 10) {
            graphics.renderTooltip(font, Component.literal("Maximum Health"), mouseX, mouseY);
        }

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, OVERLAY_TEXTURE);
        RenderSystem.setShaderTexture(0, FIRE_ICON);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        if(schoolFire) {
            graphics.blit(FIRE_ICON, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
            if (mouseX >= guiLeft + 9 && mouseX <= guiLeft + 25 && mouseY >= guiTop + 8 && mouseY <= guiTop + 24) {
                graphics.renderTooltip(font, Component.literal("Fire Type"), mouseX, mouseY);
            }
        }
        RenderSystem.disableBlend();
    }
}