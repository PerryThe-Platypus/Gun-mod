package com.augustsextus.gunmod.entity;

import com.augustsextus.gunmod.EntitySpawnPacket;
import com.augustsextus.gunmod.GunModClient;
import com.augustsextus.gunmod.deathmessages.DamageSource;
//import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.registry.EntityRegistry;
import com.augustsextus.gunmod.registry.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletEntity extends ThrownItemEntity {

    public BulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BulletEntity(World world, LivingEntity owner) {
        super(EntityRegistry.BulletEntityType, owner, world);
    }

    public BulletEntity(World world, double x, double y, double z) {
        super(EntityRegistry.BulletEntityType, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.BULLET;
    }

    //particle
    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.CAMPFIRE_COSY_SMOKE : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    //spawn packet
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, GunModClient.PacketID);
    }

    //sets the speed of the bullet from the information from TickMixin
    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, Gun.getBulletRangeSpeed(), 0);
    }

    //what happens if it hits an entity
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
        ((LivingEntity) entity).damage(Gun.getDamageSource(), Gun.getDamage());
    }

    //on collision with a block
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) { // checks if the world is client
            this.world.sendEntityStatus(this, (byte)3); // particle?
            this.remove(); // kills the projectile
        }

    }
}


