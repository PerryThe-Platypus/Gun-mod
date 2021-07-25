package com.augustsextus.gunmod.mixin;

import com.augustsextus.gunmod.entity.BulletEntity;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BulletEntity.class)
public interface BulletEntityInvoker {
    @Invoker("spawnSmoke")
    public void invokeSpawnSmoke();
}