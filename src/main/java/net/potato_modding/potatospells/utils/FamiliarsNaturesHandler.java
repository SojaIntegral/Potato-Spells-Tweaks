package net.potato_modding.potatospells.utils;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.*;

import static net.potato_modding.potatospells.registries.PotatoTags.HAS_NATURE;

@SuppressWarnings("unused")
public class FamiliarsNaturesHandler {

    private static final Map<String, List<AttributeModifierData>> NATURES;

    static {
        Map<String, List<AttributeModifierData>> natures = new HashMap<>();

        // +ATK natures
        natures.put("hardy", List.of(
                new AttributeModifierData("hardy", ALObjects.Attributes.CRIT_CHANCE, 0.1, UUID.fromString("3d45bb0e-e259-43d9-946c-38d15686df22")),
                new AttributeModifierData("hardy_minus", ALObjects.Attributes.CRIT_CHANCE, -0.1, UUID.fromString("045427de-2c58-475b-a6f4-a954a445b6e8"))
        ));

        natures.put("lonely", List.of(
                new AttributeModifierData("lonely", ALObjects.Attributes.CRIT_CHANCE, 0.1, UUID.fromString("897c783d-7db5-49ba-852e-9c11356a96ec")),
                new AttributeModifierData("lonely_minus", Attributes.ARMOR, -0.1, UUID.fromString("5ae0ce19-c268-4fa1-9e40-b6ee86ffc184"))
        ));

        natures.put("adamant", List.of(
                new AttributeModifierData("adamant", ALObjects.Attributes.CRIT_CHANCE, 0.1, UUID.fromString("4d942938-0169-43e5-b1fc-f70d2a7d0047")),
                new AttributeModifierData("adamant_minus", AttributeRegistry.SPELL_POWER, -0.1, UUID.fromString("211b39f4-71b4-43a3-a841-adde5b369b08"))
        ));

        natures.put("naughty", List.of(
                new AttributeModifierData("naughty", ALObjects.Attributes.CRIT_CHANCE, 0.1, UUID.fromString("df4f60e7-d2c1-48c3-97a1-30fe5077382a")),
                new AttributeModifierData("naughty_minus", AttributeRegistry.SPELL_RESIST, -0.1, UUID.fromString("eb0b941b-7d4a-42c1-93da-a24c24da8872"))
        ));

        natures.put("brave", List.of(
                new AttributeModifierData("brave", ALObjects.Attributes.CRIT_CHANCE, 0.1, UUID.fromString("6522a764-43b5-40aa-b1f0-58f44f8ad0b2")),
                new AttributeModifierData("brave_minus", AttributeRegistry.CAST_TIME_REDUCTION, -0.1, UUID.fromString("1d54a1af-e96f-4185-8f1b-061f92ad48df"))
        ));

        // +DEF natures
        natures.put("bold", List.of(
                new AttributeModifierData("bold", Attributes.ARMOR, 0.1, UUID.fromString("2479de28-b800-4380-b0dc-ab69add39910")),
                new AttributeModifierData("bold_minus", ALObjects.Attributes.CRIT_CHANCE, -0.1, UUID.fromString("357bb0b0-716a-45ec-b36c-ac3e42503935"))
        ));

        natures.put("docile", List.of(
                new AttributeModifierData("docile", Attributes.ARMOR, 0.1, UUID.fromString("7cee6fd1-5be3-4920-88d7-63d171bfd399")),
                new AttributeModifierData("docile_minus", Attributes.ARMOR, -0.1, UUID.fromString("1812f6d1-ac2a-427d-a5ae-a834600545fe"))
        ));

        natures.put("impish", List.of(
                new AttributeModifierData("impish", Attributes.ARMOR, 0.1, UUID.fromString("e5b19372-f710-4c63-b0f5-5bff3835c8ad")),
                new AttributeModifierData("impish_minus", AttributeRegistry.SPELL_POWER, -0.1, UUID.fromString("0366c8cf-20ec-43e4-910a-8759e3b4dcbf"))
        ));

        natures.put("lax", List.of(
                new AttributeModifierData("lax", Attributes.ARMOR, 0.1, UUID.fromString("e92a5958-0fc5-43c8-acb1-e72fb41ffeef")),
                new AttributeModifierData("lax_minus", AttributeRegistry.SPELL_RESIST, -0.1, UUID.fromString("dd875fe7-1b10-4f3e-b4d4-6bbb6f14df41"))
        ));

        natures.put("relaxed", List.of(
                new AttributeModifierData("relaxed", Attributes.ARMOR, 0.1, UUID.fromString("66a77ccd-1543-42f4-b213-4fa1300399fd")),
                new AttributeModifierData("relaxed_minus", AttributeRegistry.CAST_TIME_REDUCTION, -0.1, UUID.fromString("1d94cae3-c403-4771-981b-3c30136be14a"))
        ));

        // +SPATK natures
        natures.put("modest", List.of(
                new AttributeModifierData("modest", AttributeRegistry.SPELL_POWER, 0.1, UUID.fromString("e94dba66-a789-4c43-8dec-0985ed28b681")),
                new AttributeModifierData("modest_minus", ALObjects.Attributes.CRIT_CHANCE, -0.1, UUID.fromString("dd37d0e5-8429-4a90-b5b2-5d0ecfcc9d8c"))
        ));

        natures.put("mild", List.of(
                new AttributeModifierData("mild", AttributeRegistry.SPELL_POWER, 0.1, UUID.fromString("01600624-92a5-43a5-9744-32f5f62adcbd")),
                new AttributeModifierData("mild_minus", Attributes.ARMOR, -0.1, UUID.fromString("2f38920c-f138-49f5-9db8-7565813887be"))
        ));

        natures.put("bashful", List.of(
                new AttributeModifierData("bashful", AttributeRegistry.SPELL_POWER, 0.1, UUID.fromString("7f51b5ae-e6b5-4153-a8bf-29d0bd84b66b")),
                new AttributeModifierData("bashful_minus", AttributeRegistry.SPELL_POWER, -0.1, UUID.fromString("8f460362-9ebc-4565-9662-94f254266526"))
        ));

        natures.put("rash", List.of(
                new AttributeModifierData("rash", AttributeRegistry.SPELL_POWER, 0.1, UUID.fromString("486a6753-4612-4556-a8ad-97dde3a2529c")),
                new AttributeModifierData("rash_minus", AttributeRegistry.SPELL_RESIST, -0.1, UUID.fromString("158b6232-140e-43ea-af1a-4d03fde70aeb"))
        ));

        natures.put("quiet", List.of(
                new AttributeModifierData("quiet", AttributeRegistry.SPELL_POWER, 0.1, UUID.fromString("69cfc968-df49-4456-a8e4-06a69cf90759")),
                new AttributeModifierData("quiet_minus", AttributeRegistry.CAST_TIME_REDUCTION, -0.1, UUID.fromString("5c861870-5c86-447f-bef7-945e80f5d07d"))
        ));

        // +SPDEF natures
        natures.put("calm", List.of(
                new AttributeModifierData("calm", AttributeRegistry.SPELL_RESIST, 0.1, UUID.fromString("4e1595b8-7d2d-45bd-8ce5-4b0bc31a1ae0")),
                new AttributeModifierData("calm_minus", ALObjects.Attributes.CRIT_CHANCE, -0.1, UUID.fromString("40fc1fbc-1656-4ddb-a45d-225fe202ba5e"))
        ));

        natures.put("gentle", List.of(
                new AttributeModifierData("gentle", AttributeRegistry.SPELL_RESIST, 0.1, UUID.fromString("7e6a8546-6d05-4c6f-a227-87f66866cb79")),
                new AttributeModifierData("gentle_minus", Attributes.ARMOR, -0.1, UUID.fromString("9536eb01-e2e7-4002-a8d5-a3d8a5560513"))
        ));

        natures.put("careful", List.of(
                new AttributeModifierData("careful", AttributeRegistry.SPELL_RESIST, 0.1, UUID.fromString("ff3802fe-910e-4c4e-887b-b89abbb4feaf")),
                new AttributeModifierData("careful_minus", AttributeRegistry.SPELL_POWER, -0.1, UUID.fromString("b8545132-bafa-44f3-a0f4-3643002ff88a"))
        ));

        natures.put("quirky", List.of(
                new AttributeModifierData("quirky", AttributeRegistry.SPELL_RESIST, 0.1, UUID.fromString("bd67e59d-eb06-477f-b5cc-e438cd8b5cf3")),
                new AttributeModifierData("quirky_minus", AttributeRegistry.SPELL_RESIST, -0.1, UUID.fromString("14456b35-e04a-41fe-aee7-f1fc0feae4a8"))
        ));

        natures.put("sassy", List.of(
                new AttributeModifierData("sassy", AttributeRegistry.SPELL_RESIST, 0.1, UUID.fromString("f25c3d23-ea0d-4730-b705-3ac536e20e24")),
                new AttributeModifierData("sassy_minus", AttributeRegistry.CAST_TIME_REDUCTION, -0.1, UUID.fromString("62ea7f15-0e1a-423c-9d04-8c9804f58597"))
        ));

        // +SPEED natures
        natures.put("timid", List.of(
                new AttributeModifierData("timid", AttributeRegistry.CAST_TIME_REDUCTION, 0.1, UUID.fromString("d9fe77b6-90d6-4512-8869-4a2c9ffcc9b6")),
                new AttributeModifierData("timid_minus", ALObjects.Attributes.CRIT_CHANCE, -0.1, UUID.fromString("d2cc39c3-c815-4cf2-95c8-3fa5de0eaf05"))
        ));

        natures.put("hasty", List.of(
                new AttributeModifierData("hasty", AttributeRegistry.CAST_TIME_REDUCTION, 0.1, UUID.fromString("809ec0a5-a99a-4ab7-b142-3c3ccc28dd7f")),
                new AttributeModifierData("hasty_minus", Attributes.ARMOR, -0.1, UUID.fromString("71b70ca9-1718-46d2-9302-435f2115096d"))
        ));

        natures.put("jolly", List.of(
                new AttributeModifierData("jolly", AttributeRegistry.CAST_TIME_REDUCTION, 0.1, UUID.fromString("c90a1e93-9c79-4cf1-acc0-dc4657e5828d")),
                new AttributeModifierData("jolly_minus", AttributeRegistry.SPELL_POWER, -0.1, UUID.fromString("1bc076f6-0c19-4b2f-a5ca-4ad2959520bd"))
        ));

        natures.put("naive", List.of(
                new AttributeModifierData("naive", AttributeRegistry.CAST_TIME_REDUCTION, 0.1, UUID.fromString("8e680e75-19cc-46b9-bdec-64f9fe5abefc")),
                new AttributeModifierData("naive_minus", AttributeRegistry.SPELL_RESIST, -0.1, UUID.fromString("4a78ed74-9395-40c0-a4cf-cbcc93d10953"))
        ));

        natures.put("serious", List.of(
                new AttributeModifierData("serious", AttributeRegistry.CAST_TIME_REDUCTION, 0.1, UUID.fromString("90571154-a525-4699-9b53-5e5fff01edf3")),
                new AttributeModifierData("serious_minus", AttributeRegistry.CAST_TIME_REDUCTION, -0.1, UUID.fromString("744b2e72-424b-460d-a126-46185016deb5"))
        ));

        //noinspection Java9CollectionFactory
        NATURES = Collections.unmodifiableMap(natures);
    }

    private static final Random RANDOM = new Random();

    public static void applySpawnModifiers(LivingEntity entity) {
        if (!entity.getType().is(HAS_NATURE)) return;

        // Pick one nature randomly
        var natureKeys = NATURES.keySet().toArray(new String[0]);
        String selectedNature = natureKeys[RANDOM.nextInt(natureKeys.length)];

        // Apply all modifiers for the chosen nature
        for (AttributeModifierData modData : NATURES.get(selectedNature)) {
            var loc = BuiltInRegistries.ATTRIBUTE.getKey(modData.attribute.value());
            if (loc == null) continue;

            ResourceLocation modifierId = ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", modData.id);

            applyModifier(entity, modData.attribute, modifierId, modData.amount);
        }
    }

    private static void applyModifier(LivingEntity entity, Holder<Attribute> attribute, ResourceLocation id, double amount) {
        var instance = entity.getAttribute(attribute);
        if (instance != null && instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id))) {
            instance.addPermanentModifier(new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
    }

    private record AttributeModifierData(String id, Holder<Attribute> attribute, double amount, UUID uuid) {
        AttributeModifierData(String id, Attribute attribute, double amount, UUID uuid) {
            this(id, BuiltInRegistries.ATTRIBUTE.wrapAsHolder(attribute), amount, uuid);
        }
    }

    private static final Set<ResourceLocation> NATURE_IDS = Set.of(
            // +CRIT DMG
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "hardy"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "lonely"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "adamant"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "naughty"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "brave"),
            // +ARMOR
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "bold"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "docile"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "impish"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "lax"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "relaxed"),
            // +SPELL
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "modest"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "mild"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "bashful"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "rash"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "quiet"),
            // +RESIST
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "calm"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "gentle"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "careful"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "quirky"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "sassy"),
            // +CRIT
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "timid"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "hasty"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "jolly"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "naive"),
            ResourceLocation.fromNamespaceAndPath("potatospellbookstweaks", "serious")
    );

    public static void checkMobNature(LivingEntity entity) {
        for (Attribute attribute : BuiltInRegistries.ATTRIBUTE) {
            Holder<Attribute> holder = BuiltInRegistries.ATTRIBUTE.wrapAsHolder(attribute);
            var instance = entity.getAttribute(holder);
            if (instance == null) continue;

            for (AttributeModifier modifier : instance.getModifiers()) {
                ResourceLocation id = modifier.id();
                if (NATURE_IDS.contains(id)) {
                    switch (id.getPath()) {
                        case "hardy" -> triggerHardy(entity);
                        case "lonely" -> triggerLonely(entity);
                        case "adamant" -> triggerAdamant(entity);
                        case "naughty" -> triggerNaughty(entity);
                        case "brave" -> triggerBrave(entity);
                            // +ARMOR
                        case "bold" -> triggerBold(entity);
                        case "docile" -> triggerDocile(entity);
                        case "impish" -> triggerImpish(entity);
                        case "lax" -> triggerLax(entity);
                        case "relaxed" -> triggerRelaxed(entity);
                        // +SPELL
                        case "modest" -> triggerModest(entity);
                        case "mild" -> triggerMild(entity);
                        case "bashful" -> triggerBashful(entity);
                        case "rash" -> triggerRash(entity);
                        case "quiet" -> triggerQuiet(entity);
                        // +RESIST
                        case "calm" -> triggerCalm(entity);
                        case "gentle" -> triggerGentle(entity);
                        case "careful" -> triggerCareful(entity);
                        case "quirky" -> triggerQuirky(entity);
                        case "sassy" -> triggerSassy(entity);
                        // +CRIT
                        case "timid" -> triggerTimid(entity);
                        case "hasty" -> triggerHasty(entity);
                        case "jolly" -> triggerJolly(entity);
                        case "naive" -> triggerNaive(entity);
                        case "serious" -> triggerSerious(entity);
                    }
                    return;
                }
            }
        }
    }

    // Debug code
    private static void triggerHardy(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "Neutral ATK");
    }

    private static void triggerLonely(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+CRITdmg -Armor");
    }

    private static void triggerAdamant(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+CRITdmg -Spell");
    }

    private static void triggerNaughty(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+CRITdmg -Resist");
    }

    private static void triggerBrave(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+CRITdmg -Crit");
    }

    private static void triggerBold(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Armor -CRITdmg");
    }

    private static void triggerDocile(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "Neutral Armor");
    }

    private static void triggerImpish(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Armor -Spell");
    }

    private static void triggerLax(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Armor -Resist");
    }

    private static void triggerRelaxed(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Armor -Crit");
    }

    private static void triggerModest(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Spell -CRITdmg");
    }

    private static void triggerMild(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Spell -Armor");
    }

    private static void triggerBashful(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "Neutral Spell");
    }

    private static void triggerRash(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Spell -Resist");
    }

    private static void triggerQuiet(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Spell -Crit");
    }

    private static void triggerCalm(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Resist -CRITdmg");
    }

    private static void triggerGentle(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Resist -Armor");
    }

    private static void triggerCareful(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Resist -Spell");
    }

    private static void triggerQuirky(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "Neutral Resist");
    }

    private static void triggerSassy(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Resist -Crit");
    }

    private static void triggerTimid(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Crit -CRITdmg");
    }

    private static void triggerHasty(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Crit -Armor");
    }

    private static void triggerJolly(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Crit -Spell");
    }

    private static void triggerNaive(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "+Crit -Resist");
    }

    private static void triggerSerious(LivingEntity entity) {
        System.out.println(entity.getName().getString() + "Neutral Crit");
    }

}
