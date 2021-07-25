package com.augustsextus.gunmod.mixin;

import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.GunModClient;
import com.augustsextus.gunmod.entity.BulletEntity;
import com.augustsextus.gunmod.items.Bullet;
import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.registry.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class TickMixin {

    /*
        else if (main.getItem().equals(ItemRegistry.))

        Gun.setBulletRangeSpeed();
        Gun.setDamage();
        Gun.setZoomLevel();
        Gun.setBulletStackDecrement();
        Gun.setAttackCooldown();
     */

    @Shadow
    @Final
    private DefaultedList<ItemStack> equippedHand;

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        ItemStack main = equippedHand.get(0);
        ItemStack off = equippedHand.get(1);

        if (main.getItem().equals(ItemRegistry.RIFLE_1)) {
            Gun.setBulletRangeSpeed(10f);
            Gun.setDamage(10f);
            Gun.setZoomLevel(45d);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(60);
        } else if (main.getItem().equals(ItemRegistry.RIFLE_2)) {
            Gun.setBulletRangeSpeed(10f);
            Gun.setDamage(15f);
            Gun.setZoomLevel(45d);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(80);
        } else if (main.getItem().equals(ItemRegistry.RIFLE_3)) {
            Gun.setBulletRangeSpeed(10f);
            Gun.setDamage(3f);
            Gun.setZoomLevel(43d);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(10);
        } else if (main.getItem().equals(ItemRegistry.HANDGUN_1)) {
            Gun.setBulletRangeSpeed(5f);
            Gun.setDamage(10f);
            Gun.setZoomLevel(65);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(100);
        } else if (main.getItem().equals(ItemRegistry.HANDGUN_2)) {
            Gun.setBulletRangeSpeed(5f);
            Gun.setDamage(10f);
            Gun.setZoomLevel(60);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(100);
        } else if (main.getItem().equals(ItemRegistry.HANDGUN_3)) {
            Gun.setBulletRangeSpeed(5f);
            Gun.setDamage(10f);
            Gun.setZoomLevel(43);
            Gun.setBulletStackDecrement(1);
            Gun.setAttackCooldown(5);
        }

        GunModClient.zoomLevel = Gun.getZoomLevel();
    }

}
