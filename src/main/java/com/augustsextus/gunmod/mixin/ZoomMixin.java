package com.augustsextus.gunmod.mixin;

import com.augustsextus.gunmod.GunModClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class ZoomMixin {

    //makes the gun zoom the zoom level providen in "Gun"
    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("HEAD"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if(GunModClient.isZooming()) {
            callbackInfo.setReturnValue(GunModClient.zoomLevel);
        }

        GunModClient.manageSmoothCamera();
    }
}