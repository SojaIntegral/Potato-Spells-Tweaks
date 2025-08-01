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
public class PotatoMagicAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, PotatoSpells.MOD_ID);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    public static final DeferredHolder<Attribute, Attribute> GENERIC_SPELL_POWER = registerIdentifyAttribute("mana_spell_power");
    public static final DeferredHolder<Attribute, Attribute> GENERIC_MAGIC_RESIST = registerIdentifyAttribute("mana_magic_resist");

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
                        1, 1, 1).setSyncable(true)));
    }
}
