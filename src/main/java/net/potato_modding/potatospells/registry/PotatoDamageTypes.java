package net.potato_modding.potatospells.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.potato_modding.potatospells.PotatoSpells;

public class PotatoDamageTypes {
    public static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, name));
    }

    public static final ResourceKey<DamageType> GENERIC_MAGIC = register("generic_magic.json");
    public static final ResourceKey<DamageType> ACHERON_DAMAGE = register("acheron.json");

    public static void bootstrap(BootstrapContext<DamageType> context)
    {
        context.register(GENERIC_MAGIC, new DamageType(GENERIC_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
        context.register(ACHERON_DAMAGE, new DamageType(ACHERON_DAMAGE.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0F));
    }
}
