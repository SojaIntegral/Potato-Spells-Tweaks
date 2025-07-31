package net.potato_modding.potatospells.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
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

    public static final DeferredHolder<Attribute, Attribute> SHINY = registerIdentifyAttribute("shiny_attribute");
    public static final DeferredHolder<Attribute, Attribute> ATTACK_IV = registerIdentifyAttribute("attack_iv");
    public static final DeferredHolder<Attribute, Attribute> ARMOR_IV = registerIdentifyAttribute("armor_iv");
    public static final DeferredHolder<Attribute, Attribute> POWER_IV = registerIdentifyAttribute("power_iv");
    public static final DeferredHolder<Attribute, Attribute> RESIST_IV = registerIdentifyAttribute("resist_iv");
    public static final DeferredHolder<Attribute, Attribute> CAST_IV = registerIdentifyAttribute("cast_iv");
    public static final DeferredHolder<Attribute, Attribute> ARMOR_PEN_IV = registerIdentifyAttribute("armor_pen_iv");
    public static final DeferredHolder<Attribute, Attribute> PROT_PEN_IV = registerIdentifyAttribute("prot_pen_iv");
    public static final DeferredHolder<Attribute, Attribute> CRIT_IV = registerIdentifyAttribute("crit_iv");

    public static final DeferredHolder<Attribute, Attribute> MANA_SHIELD = registerIdentifyAttribute("mana_shield");
    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST_SHRED = registerIdentifyAttribute("spell_res_shred");

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

    private static DeferredHolder<Attribute, Attribute> registerIdentifyAttribute(String id) {
        return ATTRIBUTES.register(id, () ->
                (new PercentageAttribute("attribute.potatospellbookstweaks." + id,
                        0, 0, 1).setSyncable(true)));
    }
}
