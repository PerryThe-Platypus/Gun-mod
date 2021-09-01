package com.augustsextus.gunmod.mixin;

import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.registry.ItemRegistry;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(MinecraftClient.class)
public class GunMixin {

    @Shadow ClientPlayerEntity player;
    @Shadow int itemUseCooldown;

    //creates the attack cooldown, sends the shoot packet and cancels the arm movement on left click if the gun is in the players hand
    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void doAttack(CallbackInfo ci) {
        if (this.itemUseCooldown <= 0) {
            if (!this.player.isRiding()) {
                    if (GunMod.getGuns().contains(player.getEquippedStack(EquipmentSlot.MAINHAND).getItem())) {
                        this.itemUseCooldown = Gun.getAttackCooldown();
                        ci.cancel();
                        Gun.sendShootPacket();
                    }
            }

        }
        ci.cancel();
    }
}


