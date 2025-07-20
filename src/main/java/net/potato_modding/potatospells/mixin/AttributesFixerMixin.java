package net.potato_modding.potatospells.mixin;

import io.redspace.ironsspellbooks.player.ServerPlayerEvents;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEvents.class)
public class AttributesFixerMixin {

    @Overwrite
    public static void handleResistanceAttributesOnSpawn(FinalizeSpawnEvent event) {
    }
}
