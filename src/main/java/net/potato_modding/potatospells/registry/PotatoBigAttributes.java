package net.potato_modding.potatospells.registry;

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
public class PotatoBigAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, PotatoSpells.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST_PIERCE = registerIdentifyAttribute("spell_res_pierce");

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
                (new RangedAttribute("attribute.potatospellbookstweaks." + id,
                        0, 0, 10000).setSyncable(true)));
    }
}
