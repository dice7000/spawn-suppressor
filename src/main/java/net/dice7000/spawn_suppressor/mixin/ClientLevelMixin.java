package net.dice7000.spawn_suppressor.mixin;

import net.dice7000.spawn_suppressor.SpawnSuppressorMethod;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin implements SpawnSuppressorMethod {
    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    public void addEntityInject(int p_104740_, Entity p_104741_, CallbackInfo ci) {
        if (!(p_104741_ instanceof Player) && spawn_suppressor$getSuppressSpawn()) ci.cancel();
    }
}
