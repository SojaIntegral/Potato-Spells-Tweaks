package net.potato_modding.potatospells.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.potato_modding.potatoessentials.datagen.MobElementLoader;
import net.potato_modding.potatoessentials.datagen.MobRaceLoader;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.potato_modding.potatoessentials.utils.RebalanceHandler;
import net.potato_modding.potatospells.items.*;
import net.potato_modding.potatospells.registry.PotatoRegistry;
import net.potato_modding.potatospells.utils.CurioUtil;
import net.potato_modding.potatospells.utils.IHatePackets;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static net.potato_modding.potatoessentials.PotatoEssentials.MOD_ID;
import static net.potato_modding.potatoessentials.utils.ConfigFormulas.*;

@SuppressWarnings({"unused", "deprecation"})
public class MobInteractionScreen extends Screen {

    private long lastCleanupTime = 0L;

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
    private final double atkSpeed;

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

    private static double firstIV;
    private static double secondIV;
    private static double thirdIV;
    private static double fourthIV;
    private static double fifthIV;
    private static double sixthIV;
    private static double seventhIV;
    private static double eighthIV;

    private final double firePow;
    private final double icePow;
    private final double lightningPow;
    private final double naturePow;
    private final double enderPow;
    private final double holyPow;
    private final double eldritchPow;
    private final double evokerPow;
    private final double abyssPow;
    private final double bladePow;
    private final double songPow;
    private final double windPow;
    private final double symmetryPow;
    private final double dunePow;
    private final double spiritPow;

    private final double fireRes;
    private final double iceRes;
    private final double lightningRes;
    private final double natureRes;
    private final double enderRes;
    private final double holyRes;
    private final double eldritchRes;
    private final double evokerRes;
    private final double abyssRes;
    private final double bladeRes;
    private final double songRes;
    private final double windRes;
    private final double symmetryRes;
    private final double duneRes;
    private final double spiritRes;

    private final double rgb;

    private boolean firstRender = true;

    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/background.png");
    private static final ResourceLocation DEFAULT_ICONS =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/default_icons.png");
    private static final ResourceLocation COLORS_BUTTON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/colors_button.png");
    private static final ResourceLocation SHINY_ICON =
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/shiny_icon.png");

    public MobInteractionScreen(String entityName, LivingEntity entity,
                                float health, double armor, double attack, double mana,
                                double resist, double power, double cast, double cooldown,
                                double crit, double critChance, double armorPierce, double armorShred,
                                double protPierce, double protShred, double atkSpeed,
                                double firstIV, double secondIV, double thirdIV, double fourthIV,
                                double fifthIV, double sixthIV, double seventhIV, double eighthIV,
                                double firePow, double icePow, double lightningPow,
                                double naturePow, double enderPow, double holyPow,
                                double eldritchPow, double evokerPow, double abyssPow,
                                double bladePow, double songPow, double windPow,
                                double symmetryPow, double dunePow, double spiritPow,
                                double fireRes, double iceRes, double lightningRes,
                                double natureRes, double enderRes, double holyRes,
                                double eldritchRes, double evokerRes, double abyssRes,
                                double bladeRes, double songRes, double windRes,
                                double symmetryRes, double duneRes, double spiritRes,
                                double rgb) {

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


        this.atkSpeed = atkSpeed;
        this.firstIV = firstIV;
        this.secondIV = secondIV;
        this.thirdIV = thirdIV;
        this.fourthIV = fourthIV;
        this.fifthIV = fifthIV;
        this.sixthIV = sixthIV;
        this.seventhIV = seventhIV;
        this.eighthIV = eighthIV;

        this.firePow = firePow;
        this.icePow = icePow;
        this.lightningPow = lightningPow;
        this.naturePow = naturePow;
        this.enderPow = enderPow;
        this.holyPow = holyPow;
        this.eldritchPow = eldritchPow;
        this.evokerPow = evokerPow;
        this.abyssPow = abyssPow;
        this.bladePow = bladePow;
        this.songPow = songPow;
        this.windPow = windPow;
        this.symmetryPow = symmetryPow;
        this.dunePow = dunePow;
        this.spiritPow = spiritPow;

        this.fireRes = fireRes;
        this.iceRes = iceRes;
        this.lightningRes = lightningRes;
        this.natureRes = natureRes;
        this.enderRes = enderRes;
        this.holyRes = holyRes;
        this.eldritchRes = eldritchRes;
        this.evokerRes = evokerRes;
        this.abyssRes = abyssRes;
        this.bladeRes = bladeRes;
        this.songRes = songRes;
        this.windRes = windRes;
        this.symmetryRes = symmetryRes;
        this.duneRes = duneRes;
        this.spiritRes = spiritRes;

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

        entity.setYRot(originalYRot);
        entity.setXRot(originalXRot);
        entity.yHeadRot = originalYHeadRot;
        entity.yBodyRot = originalYBodyRot;


        var coloredGUI = new Object() {
            ResourceLocation overlayTexture = Analyzer.OVERLAY_TEXTURE;
        };

        int size = rgb == 8 ? 8 : 0;
        int overlayIndex = IHatePackets.loadGUI();
        coloredGUI.overlayTexture = AnalyzerPrismatic.OVERLAY_TEXTURE.get(overlayIndex);

        this.addRenderableWidget(Button.builder(
                Component.literal(""),
                btn -> {
                    showIVs = !showIVs;
                    btn.setMessage(Component.literal(""));
                }
        ).bounds(guiLeft + 152, guiTop + 110, 8, 8).build());

        this.addRenderableWidget(Button.builder(Component.empty(), btn -> {
            int currentGUI = IHatePackets.loadGUI();
            int nextGUI = (currentGUI + 1) % AnalyzerPrismatic.OVERLAY_TEXTURE.size();
            IHatePackets.saveGUI(nextGUI);
        }).bounds(guiLeft + 140, guiTop + 110, size, size).build());


        if (rgb == 1) coloredGUI.overlayTexture = AnalyzerRed.OVERLAY_TEXTURE;
        if (rgb == 2) coloredGUI.overlayTexture = AnalyzerOrange.OVERLAY_TEXTURE;
        if (rgb == 3) coloredGUI.overlayTexture = AnalyzerYellow.OVERLAY_TEXTURE;
        if (rgb == 4) coloredGUI.overlayTexture = AnalyzerGreen.OVERLAY_TEXTURE;
        if (rgb == 5) coloredGUI.overlayTexture = AnalyzerBlue.OVERLAY_TEXTURE;
        if (rgb == 6) coloredGUI.overlayTexture = AnalyzerPink.OVERLAY_TEXTURE;
        if (rgb == 7) coloredGUI.overlayTexture = AnalyzerPurple.OVERLAY_TEXTURE;
        if (rgb == 8) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                ItemStack stack = CurioUtil.getCurioPersistent(player, PotatoRegistry.PRISMATIC_ANALYZER.get());
                if (!stack.isEmpty()) {
                    coloredGUI.overlayTexture = AnalyzerPrismatic.OVERLAY_TEXTURE.get(overlayIndex);
                }
            }
        }


        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        graphics.setColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, coloredGUI.overlayTexture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        Minecraft.getInstance().getTextureManager().getTexture(coloredGUI.overlayTexture).setFilter(false, false);

        graphics.blit(coloredGUI.overlayTexture, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        graphics.blit(DEFAULT_ICONS, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        if (rgb == 8) graphics.blit(COLORS_BUTTON, guiLeft, guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        RenderSystem.disableBlend();

        graphics.pose().pushPose();
        float textScale = 0.85f;
        graphics.pose().scale(textScale, textScale, 1.0f);

        int drawX = (int) (guiLeft / textScale);
        int drawY = (int) (guiTop / textScale);
        float scaledMouseX = mouseX / textScale;
        float scaledMouseY = mouseY / textScale;

        double castParse = RebalanceHandler.rebalanceCastFormula(cast);
        double cooldownParse = RebalanceHandler.rebalanceCooldownFormula(cooldown);
        double resistParse = RebalanceHandler.rebalanceResistFormula(resist);

        double critParse = crit * 100;
        double critChanceParse = critChance * 100;
        double armorShredParse = armorShred * 100;
        double protShredParse = protShred * 100;

        //Colored natures text stuff
        var nat1 = TextColor.parseColor("#ffffff").getOrThrow();
        var nat2 = TextColor.parseColor("#ffffff").getOrThrow();
        var nat3 = TextColor.parseColor("#ffffff").getOrThrow();
        var nat4 = TextColor.parseColor("#ffffff").getOrThrow();
        var nat5 = TextColor.parseColor("#ffffff").getOrThrow();

        for (AttributeInstance attr : entity.getAttributes().getSyncableAttributes()) {
            for (AttributeModifier modifier : attr.getModifiers()) {
                ResourceLocation id = modifier.id();

                //CRIT
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "timid")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "hasty")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "jolly")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naive"))
                ) nat1 = TextColor.parseColor("#2aaed3").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "sassy")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "quiet")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "relaxed")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "brave"))
                ) nat1 = TextColor.parseColor("#990909").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "serious"))
                ) nat1 = TextColor.parseColor("#e88030").getOrThrow();

                //ARMOR
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "bold")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "impish")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lax")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "relaxed"))
                ) nat2 = TextColor.parseColor("#2aaed3").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "mild")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "gentle")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "hasty")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lonely"))
                ) nat2 = TextColor.parseColor("#990909").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "docile"))
                ) nat2 = TextColor.parseColor("#e88030").getOrThrow();

                //SPELL
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "modest")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "mild")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "rash")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "quiet"))
                ) nat3 = TextColor.parseColor("#2aaed3").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "adamant")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "impish")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "careful")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "jolly"))
                ) nat3 = TextColor.parseColor("#990909").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "bashful"))
                ) nat3 = TextColor.parseColor("#e88030").getOrThrow();

                //RESIST
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "calm")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "gentle")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "careful")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "sassy"))
                ) nat4 = TextColor.parseColor("#2aaed3").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naughty")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lax")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "rash")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naive"))
                ) nat4 = TextColor.parseColor("#990909").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "quirky"))
                ) nat4 = TextColor.parseColor("#e88030").getOrThrow();

                //CRIT DMG
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lonely")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "adamant")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naughty")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "brave"))
                ) nat5 = TextColor.parseColor("#2aaed3").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "bold")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "modest")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "calm")) ||
                        id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "timid"))
                ) nat5 = TextColor.parseColor("#990909").getOrThrow();
                if (id.equals(ResourceLocation.fromNamespaceAndPath("potatoessentials", "hardy"))
                ) nat5 = TextColor.parseColor("#e88030").getOrThrow();
            }
        }


        //Races and display stuff
        final String[] race = {"none"};
        final String[] element = {"none"};

        final double[] parseIV = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        double mobMod = 0;
        parseIV[0] = firstIV;
        parseIV[1] = secondIV;
        parseIV[2] = thirdIV;
        parseIV[3] = fourthIV;
        parseIV[4] = fifthIV;
        parseIV[5] = sixthIV;
        parseIV[6] = seventhIV;
        parseIV[7] = 0.05 + eighthIV;
        parseIV[8] = 0;

        entity.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
            if (tagKey.registry().equals(Registries.ENTITY_TYPE) &&
                    tagKey.location().getNamespace().equals(MOD_ID) &&
                    tagKey.location().getPath().startsWith("mob_races/")) {

                String raceName = tagKey.location().getPath().substring("mob_races/".length());
                ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, raceName);
                var data = MobRaceLoader.get(dataId);

                if (entity.getType().is(PotatoTags.BOSS)) {
                    mobType = boss_mod;
                    ArmorMod = boss_mod * (3 * (1 + randMax));
                    ToughMod = boss_mod * (2 * (1 + randMax));
                    AttackMod = boss_mod * (2 * (1 + randMax));
                } else if (entity.getType().is(PotatoTags.MINIBOSS)) {
                    mobType = mini_mod;
                    ArmorMod = mini_mod * (1.5 * (1 + randMax));
                    ToughMod = mini_mod * (1.5 * (1 + randMax));
                    AttackMod = mini_mod * (1 * (1 + randMax));
                } else if (entity.getType().is(PotatoTags.NORMAL)) {
                    mobType = mob_mod;
                    ArmorMod = mob_mod * (0.75 * (1 + randMax));
                    ToughMod = mob_mod * (0.5 * (1 + randMax));
                    AttackMod = mob_mod * (0.65 * (1 + randMax));
                } else if (entity.getType().is(PotatoTags.SUMMON)) {
                    mobType = summon_mod;
                    ArmorMod = summon_mod * (1 * (1 + randMax));
                    ToughMod = summon_mod * (0.5 * (1 + randMax));
                    AttackMod = summon_mod * (0.5 * (1 + randMax));
                } else {
                    mobType = 1;
                    ArmorMod = 1;
                    ToughMod = 1;
                    AttackMod = 1;
                }

                parseIV[0] /= data.attack() == 0 ? 1 : (data.attack() * AttackMod);
                parseIV[1] /= data.armor() == 0 ? 1 : (data.armor() * ArmorMod);
                parseIV[2] /= (data.spellPower() + randMax) - 1;
                parseIV[3] /= (data.castReduction() + randMax) - 1;
                parseIV[8] = (data.resist() + randMax);
                parseIV[5] /= (data.armorPierce() * (1 + randMax));
                parseIV[6] /= (data.protPierce() * (1 + randMax));
                parseIV[7] /= 0.05 + (data.crit() + randMax);

                race[0] = data.race();
            }
        });

        entity.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
            if (tagKey.registry().equals(Registries.ENTITY_TYPE)
                    && tagKey.location().getNamespace().equals(MOD_ID)
                    && tagKey.location().getPath().startsWith("mob_elements/")) {

                String elementName = tagKey.location().getPath().substring("mob_elements/".length());
                ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, elementName);
                var data = MobElementLoader.get(dataId);

                parseIV[4] /= (parseIV[8] * (data.resist() * mobType)) - 1;

                firstRender = false;

                element[0] = data.element();
            }
        });

        parseIV[0] = BigDecimal.valueOf(parseIV[0] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[1] = BigDecimal.valueOf(parseIV[1] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[2] = BigDecimal.valueOf(parseIV[2] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[3] = BigDecimal.valueOf(parseIV[3] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[4] = BigDecimal.valueOf(parseIV[4] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[5] = BigDecimal.valueOf(parseIV[5] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[6] = BigDecimal.valueOf(parseIV[6] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();
        parseIV[7] = BigDecimal.valueOf(parseIV[7] * 31).setScale(0, RoundingMode.HALF_UP).doubleValue();

        Component mobName = entity.getDisplayName();
        if (scaledMouseX >= (guiLeft + 16) / textScale && scaledMouseX <= (guiLeft + 61) / textScale
                && scaledMouseY >= (guiTop + 37) / textScale && scaledMouseY <= (guiTop + 81) / textScale) {
            graphics.renderTooltip(font, mobName, (int) scaledMouseX, (int) scaledMouseY);
        }

        var buttonTooltipColor = TextColor.parseColor("#bbe0f9").getOrThrow();
        if (scaledMouseX >= ((guiLeft + 152) / textScale) && scaledMouseX <= ((guiLeft + 160) / textScale)
                && scaledMouseY >= ((guiTop + 110) / textScale) && scaledMouseY <= ((guiTop + 118) / textScale)) {
            graphics.renderTooltip(font, Component.literal("Toggle IVs").withStyle(Style.EMPTY.withColor(buttonTooltipColor)), (int) scaledMouseX, (int) scaledMouseY);
        }

        if (rgb == 8) {
            if (scaledMouseX >= ((guiLeft + 140) / textScale) && scaledMouseX <= ((guiLeft + 148) / textScale)
                    && scaledMouseY >= ((guiTop + 110) / textScale) && scaledMouseY <= ((guiTop + 118) / textScale)) {

                String baseText = "Change Colors"; // tooltip text
                MutableComponent rainbowTooltip = Component.literal("");

                Minecraft mc = Minecraft.getInstance();
                long ticks = (mc.level != null) ? mc.level.getGameTime() : 0;

                for (int i = 0; i < baseText.length(); i++) {
                    // Same hue calculation as your item name
                    float hue = ((i * 40) + (ticks * 2.25f)) % 360 / 360f;
                    int rgbColor = java.awt.Color.HSBtoRGB(hue, 1f, 1f);
                    String hex = String.format("#%06X", (0xFFFFFF & rgbColor));
                    TextColor color = TextColor.parseColor(hex).getOrThrow();

                    rainbowTooltip = rainbowTooltip.append(
                            Component.literal(String.valueOf(baseText.charAt(i)))
                                    .withStyle(style -> style.withColor(color))
                    );
                }

                graphics.renderTooltip(font, rainbowTooltip, (int) scaledMouseX, (int) scaledMouseY);
            }
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
            graphics.drawString(this.font, Component.literal(String.format("%.0f", armor)).withStyle(Style.EMPTY.withColor(nat2)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor").withStyle(Style.EMPTY.withColor(nat2)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", attack)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Attack Damage"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", critChanceParse) + "%").withStyle(Style.EMPTY.withColor(nat5)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical Chance").withStyle(Style.EMPTY.withColor(nat5)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", critParse) + "%").withStyle(Style.EMPTY.withColor(nat1)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical Damage").withStyle(Style.EMPTY.withColor(nat1)), (int) scaledMouseX, (int) scaledMouseY);
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
            graphics.drawString(this.font, Component.literal(String.format("%.0f", (resistParse - 1) * 100) + "%").withStyle(Style.EMPTY.withColor(nat4)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell Resistance").withStyle(Style.EMPTY.withColor(nat4)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", ((power - 1) * 100)) + "%").withStyle(Style.EMPTY.withColor(nat3)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell Power").withStyle(Style.EMPTY.withColor(nat3)), (int) scaledMouseX, (int) scaledMouseY);
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
            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[1])).withStyle(Style.EMPTY.withColor(nat2)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 20) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 18) / textScale) && scaledMouseY <= ((guiTop + 28) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor IV").withStyle(Style.EMPTY.withColor(nat2)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[0])), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Attack IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[7])).withStyle(Style.EMPTY.withColor(nat5)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical IV").withStyle(Style.EMPTY.withColor(nat5)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[7])).withStyle(Style.EMPTY.withColor(nat1)), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Critical IV").withStyle(Style.EMPTY.withColor(nat1)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[5])), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor Piercing IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[5])), (int) ((guiLeft + 96) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 84) / textScale) && scaledMouseX <= ((guiLeft + 94) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Armor Shred IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            // RIGHT SIDE
            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[4])).withStyle(Style.EMPTY.withColor(nat4)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 62) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 60) / textScale) && scaledMouseY <= ((guiTop + 70) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Resistance IV").withStyle(Style.EMPTY.withColor(nat4)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[2])).withStyle(Style.EMPTY.withColor(nat3)), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 34) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 32) / textScale) && scaledMouseY <= ((guiTop + 42) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Spell IV").withStyle(Style.EMPTY.withColor(nat3)), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[3])), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 48) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 46) / textScale) && scaledMouseY <= ((guiTop + 56) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Cast IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[6])), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 76) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 74) / textScale) && scaledMouseY <= ((guiTop + 84) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Protection Pierce IV"), (int) scaledMouseX, (int) scaledMouseY);
            }

            graphics.drawString(this.font, Component.literal(String.format("%.0f", parseIV[6])), (int) ((guiLeft + 135) / textScale), (int) ((guiTop + 90) / textScale), 0xffffff);
            if (scaledMouseX >= ((guiLeft + 123) / textScale) && scaledMouseX <= ((guiLeft + 131) / textScale)
                    && scaledMouseY >= ((guiTop + 88) / textScale) && scaledMouseY <= ((guiTop + 98) / textScale)) {
                graphics.renderTooltip(font, Component.literal("Protection Shred IV"), (int) scaledMouseX, (int) scaledMouseY);
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
        RenderSystem.setShaderTexture(0, coloredGUI.overlayTexture);

        // Rendering icons and tooltips
        ResourceLocation schoolTexture = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/elements/" + element[0] + "_icon.png");
        Component schoolTooltip = Component.literal(element[0].substring(0, 1).toUpperCase() + element[0].substring(1));
        graphics.blit(schoolTexture, guiLeft + 30, guiTop + 8, 0, 0, 16, 16, 16, 16);
        if (typePosition) {
            graphics.renderTooltip(font, schoolTooltip, mouseX, mouseY);
        }
        ResourceLocation raceTexture = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "textures/gui/races/" + race[0] + "_icon.png");
        Component raceTooltip = Component.literal(race[0].substring(0, 1).toUpperCase() + race[0].substring(1));
        graphics.blit(raceTexture, guiLeft + 9, guiTop + 8, 0, 0, 16, 16, 16, 16);
        if (racePosition) {
            graphics.renderTooltip(font, raceTooltip, mouseX, mouseY);
        }
        var rankVisualTooltip = getRankVisuals(entity);
        ResourceLocation rankTextures = rankVisualTooltip.getKey();
        Component rankTooltips = rankVisualTooltip.getValue();
        graphics.blit(rankTextures, guiLeft + 51, guiTop + 8, 0, 0, 16, 16, 16, 16);
        if (rankPosition) {
            graphics.renderTooltip(font, rankTooltips, mouseX, mouseY);
        }

        for (AttributeInstance attr : entity.getAttributes().getSyncableAttributes()) {
            for (AttributeModifier modifier : attr.getModifiers()) {
                ResourceLocation id = modifier.id();

                if (NATURES.containsKey(id)) {
                    Component name = NATURES.get(id);
                    graphics.drawString(font, name, guiLeft + 6, guiTop + 93, 0xFFFFFF);
                }

                boolean thiShitIsShiny = (parseIV[0] == 31 && parseIV[1] == 31 && parseIV[2] == 31 && parseIV[3] == 31
                        && parseIV[4] == 31 && parseIV[5] == 31 && parseIV[6] == 31 && parseIV[7] == 31);

                if (thiShitIsShiny) {
                    graphics.blit(SHINY_ICON, guiLeft + 3, guiTop + 23, 0, 0, 16, 16, 16, 16);
                    if (mouseX >= (guiLeft + 3) && mouseX <= (guiLeft + 18)
                            && mouseY >= (guiTop + 25) && mouseY <= (guiTop + 35)) {
                        graphics.renderTooltip(font, Component.literal("Shiny"), mouseX, mouseY);
                    }
                }
            }
        }
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.disableBlend();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCleanupTime >= 5000L) {
            System.gc();
            lastCleanupTime = currentTime;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_E || keyCode == GLFW.GLFW_KEY_G || keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private static final Map<ResourceLocation, Component> NATURES = Map.ofEntries(
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "hardy"), Component.literal("Hardy")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lonely"), Component.literal("Lonely")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "adamant"), Component.literal("Adamant")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naughty"), Component.literal("Naughty")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "brave"), Component.literal("Brave")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "bold"), Component.literal("Bold")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "docile"), Component.literal("Docile")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "impish"), Component.literal("Impish")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "lax"), Component.literal("Lax")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "relaxed"), Component.literal("Relaxed")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "modest"), Component.literal("Modest")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "mild"), Component.literal("Mild")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "bashful"), Component.literal("Bashful")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "rash"), Component.literal("Rash")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "quiet"), Component.literal("Quiet")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "calm"), Component.literal("Calm")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "gentle"), Component.literal("Gentle")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "careful"), Component.literal("Careful")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "quirky"), Component.literal("Quirky")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "sassy"), Component.literal("Sassy")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "timid"), Component.literal("Timid")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "hasty"), Component.literal("Hasty")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "jolly"), Component.literal("Jolly")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "naive"), Component.literal("Naive")),
            Map.entry(ResourceLocation.fromNamespaceAndPath("potatoessentials", "serious"), Component.literal("Serious"))
    );

    private static final ResourceLocation DEFAULT_ICON =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/item/potato.png");

    private static final Component DEFAULT_TOOLTIP = Component.literal("None");

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