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

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/background.png");
    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/identify_gui.png");

    public MobInteractionScreen(String entityName, LivingEntity entity,
                                float health, double armor, double attack,
                                double mana, double resist, double power, double cast,
                                double crit, double armorPierce, double protPierce) {
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

        // Render entity
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

        // Redraw over the background + entity
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
        graphics.drawString(this.font, Component.literal(String.format("%.1f", armor)), drawX, drawY + 16, 0xFFAAAA);
        graphics.drawString(this.font, Component.literal(String.format("%.1f", attack)), drawX, drawY + 32, 0xFFAAAA);
        if(mana < 1000) {
            graphics.drawString(this.font, Component.literal(String.format("%.0f", mana)), drawX + 38, drawY, 0xFFAAAA);
        }
        else {
            graphics.drawString(this.font, Component.literal(String.format("%.1f", (mana / 1000))+"k"), drawX + 38, drawY, 0xFFAAAA);
        }
        graphics.drawString(this.font, Component.literal(String.format("%.0f", (resistParse - 1) * 100)+"%"), drawX + 38, drawY + 16, 0xFFAAAA);
        graphics.drawString(this.font, Component.literal(String.format("%.1f", power)), drawX + 38, drawY + 32, 0xFFAAAA);
        graphics.pose().popPose();

        float scaledMouseX = mouseX / textScale;
        float scaledMouseY = mouseY / textScale;

        if (scaledMouseX >= drawX - 17 && scaledMouseX <= drawX - 2 && scaledMouseY >= drawY - 3 && scaledMouseY <= drawY + 10) {
            graphics.renderTooltip(font, Component.literal("Maximum Health"), mouseX, mouseY);
        }
    }
}