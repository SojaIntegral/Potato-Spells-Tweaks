package net.potato_modding.potatospells.registry;

import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatospells.PotatoSpells;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SchoolRegistry.SCHOOL_REGISTRY_KEY;

public class PotatoSchool {
    private static final DeferredRegister<SchoolType> POTATO_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, PotatoSpells.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        POTATO_SCHOOLS.register(eventBus);
    }

    private static Supplier<SchoolType> registerSchool(SchoolType type)
    {
        return POTATO_SCHOOLS.register(type.getId().getPath(), () -> type);
    }

    public static final ResourceLocation GENERIC_RESOURCE = PotatoSpells.id("generic");

    public static final Supplier<SchoolType> GENERIC = registerSchool(new SchoolType
            (
                    GENERIC_RESOURCE,
                    PotatoFocuses.GENERIC_FOCUS,
                    Component.translatable("school.potatospellbookstweaks.generic").withStyle(Style.EMPTY.withColor(0xbaf7ff)),
                    PotatoAttributes.GENERIC_SPELL_POWER,
                    PotatoAttributes.GENERIC_MAGIC_RESIST,
                    SoundRegistry.EVOCATION_CAST,
                    PotatoDamageTypes.GENERIC_MAGIC,
                    false,
                    false
            ));
}
