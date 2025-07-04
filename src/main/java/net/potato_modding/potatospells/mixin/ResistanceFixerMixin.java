package net.potato_modding.potatospells.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.redspace.ironsspellbooks.player.ServerPlayerEvents;
import jdk.jfr.Event;
import net.neoforged.neoforge.event.entity.living.*;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEvents.class)
public class ResistanceFixerMixin {

    // Overwrites ISS attribute weakness system
    @WrapMethod(method = "handleResistanceAttributesOnSpawn")
    private static void handleResistanceAttributesOnSpawn(FinalizeSpawnEvent event, Operation<Event> original) {
    }
}
