package net.dice7000.spawn_suppressor.mixin;

import net.dice7000.spawn_suppressor.ServerLevelMixinMethod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class ServerLevelMixin implements ServerLevelMixinMethod {
    @Unique private boolean spawn_suppressor$suppressSpawn = false;
    @Override public void spawn_suppressor$toggleSpawn() {spawn_suppressor$suppressSpawn = !spawn_suppressor$suppressSpawn;}
    @Override public boolean spawn_suppressor$getSuppressSpawn() {
        return spawn_suppressor$suppressSpawn;
    }

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    public void addEntityInject(Entity p_8873_, CallbackInfoReturnable<Boolean> cir) {
        if (!(p_8873_ instanceof Player) && spawn_suppressor$suppressSpawn) cir.cancel();
    }
}

