package com.augustsextus.gunmod.items;

import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.GunModClient;
import com.augustsextus.gunmod.entity.BulletEntity;
import com.augustsextus.gunmod.registry.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Gun extends Item{

    //all the different variables we can give to the tick mixin
    private static float bulletRangeSpeed;
    private static float damage;
    private static double zoomLevel;
    private static int bulletStackDecrement;
    private static int attackCooldown;
    private static DamageSource damageSource;

    private static Boolean isScoping = false;
    private static int useIndex = 0;

    public Gun(FabricItemSettings settings) {
        super(settings);
    }

    //the function for shoot
    public static void shoot(World world, LivingEntity user) {

        if (user instanceof PlayerEntity) {

            ItemStack itemStack = new ItemStack(ItemRegistry.BULLET);

            PlayerEntity playerEntity = (PlayerEntity) user;

            //goes through the inventory and checks if there is a bullet stack
            for(int i = 0; i < playerEntity.inventory.size(); ++i) {
                ItemStack stack = playerEntity.inventory.getStack(i);
                if (stack.getItem() == ItemRegistry.BULLET) {
                    itemStack = stack;
                    break;
                }
            }

            boolean bl = playerEntity.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, itemStack) > 0;
            if (!itemStack.isEmpty() || bl) {

                boolean bl2 = bl && itemStack.getItem() == ItemRegistry.BULLET;
                if (!world.isClient) {

                    BulletEntity bulletEntity = new BulletEntity(world, user);

                    bulletEntity.setItem(itemStack);
                    bulletEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 0F);
                    world.spawnEntity(bulletEntity); // spawns entity

                }

                //plays explosion sound
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5F, 1F);
                itemStack.decrement(bulletStackDecrement);
                if (itemStack.isEmpty()) {
                    playerEntity.inventory.removeOne(itemStack);
                }
            }
        }
    }

    //we use the "use" to make the scope toggable instead of holding the right click down(We could do that but it causes a bug so you cant shoot while scoping)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            useIndex++;
            if (useIndex >= 2) {
                isScoping = false;
                useIndex = 0;
            }

            if (useIndex == 0) {
                isScoping = false;
            } else if (useIndex == 1) {
                isScoping = true;
            }

        }

        return super.use(world, user, hand);

    }

    //the methods to call and set the variables from earlier

    public static boolean getIsScoping() {
        return isScoping;
    }

    public static float getBulletRangeSpeed() {
        return bulletRangeSpeed;
    }

    public static float getDamage() {
        return damage;
    }

    public static double getZoomLevel() {
        return zoomLevel;
    }

    public static int getBulletStackDecrement() {
        return bulletStackDecrement;
    }

    public static int getAttackCooldown() {
        return attackCooldown;
    }

    public static DamageSource getDamageSource() {
        return damageSource;
    }

    //:::::::::::::::::::::::::::::::::::::::::::

    public static void setBulletRangeSpeed(float bulletRangeSpeed) {
        Gun.bulletRangeSpeed = bulletRangeSpeed;
    }

    public static void setDamage(float damage) {
        Gun.damage = damage;
    }

    public static void setZoomLevel(double zoomLevel) {
        Gun.zoomLevel = zoomLevel;
    }

    public static void setBulletStackDecrement(int bulletStackDecrement) {
        Gun.bulletStackDecrement = bulletStackDecrement;
    }

    public static void setAttackCooldown(int attackCooldown) {
        Gun.attackCooldown = attackCooldown;
    }

    public static void setDamageSource(DamageSource damageSource) {
        Gun.damageSource = damageSource;
    }

    public static void sendShootPacket(){
        ClientSidePacketRegistry.INSTANCE.sendToServer(GunMod.getShootPacket(), PacketByteBufs.empty());
    }

}


