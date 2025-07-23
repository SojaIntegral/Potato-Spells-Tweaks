package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.acetheeldritchking.discerning_the_eldritch.DiscerningTheEldritch;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;

@EventBusSubscriber
public class PotatoAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, PotatoSpells.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> CAN_IDENTIFY = registerIdentifyAttribute("identify_attributes");
    public static final DeferredHolder<Attribute, Attribute> SHINY = registerIdentifyAttribute("shiny_attribute");

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

    // ;_;
    private static DeferredHolder<Attribute, Attribute> registerIdentifyAttribute(String id) {
        return ATTRIBUTES.register(id, () ->
                (new MagicRangedAttribute("attribute.potatospellbookstweaks." + id,
                        0, 0, 1).setSyncable(true)));
    }
}
