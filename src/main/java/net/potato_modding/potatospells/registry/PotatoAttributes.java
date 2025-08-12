package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.PercentageAttribute;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;

@EventBusSubscriber
public class PotatoAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, PotatoSpells.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> SHINY = registerRegularAttributes("shiny_attribute",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ATTACK_IV = registerRegularAttributes("attack_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ARMOR_IV = registerRegularAttributes("armor_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> POWER_IV = registerRegularAttributes("power_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> RESIST_IV = registerRegularAttributes("resist_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> CAST_IV = registerRegularAttributes("cast_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ARMOR_PEN_IV = registerRegularAttributes("armor_pen_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> PROT_PEN_IV = registerRegularAttributes("prot_pen_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> CRIT_IV = registerRegularAttributes("crit_iv",0,0,1);

    //Slayer Attributes
    public static final DeferredHolder<Attribute, Attribute> BOSS_SLAYER =
            registerSpecialAttributes("boss_slayer", 0, -100, 100);
    public static final DeferredHolder<Attribute, Attribute> MONSTER_SLAYER =
            registerSpecialAttributes("monster_slayer", 0, -100, 100);
    public static final DeferredHolder<Attribute, Attribute> PLAYER_SLAYER =
            registerSpecialAttributes("player_slayer", 0, -100, 100);

    //Magic attributes
    public static final DeferredHolder<Attribute, Attribute> GENERIC_SPELL_POWER =
            registerMagicAttributes("mana_spell_power", 1, 1, 1);
    public static final DeferredHolder<Attribute, Attribute> GENERIC_MAGIC_RESIST =
            registerMagicAttributes("mana_magic_resist",1 ,1 ,1);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType ->
                ATTRIBUTES.getEntries().forEach(
                        attributeDeferredHolder -> event.add(entityType, attributeDeferredHolder
                        )));
    }

    private static DeferredHolder<Attribute, Attribute> registerSpecialAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new PercentageAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerMagicAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new MagicRangedAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerRegularAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new RangedAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }
}
