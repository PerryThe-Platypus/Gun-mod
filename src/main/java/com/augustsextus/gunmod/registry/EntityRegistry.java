package com.augustsextus.gunmod.registry;

import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.entity.BulletEntity;
//import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
//import com.augustsextus.gunmod.entity.PackedSnowballEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    //creates and builds the bullet
    public static final EntityType<BulletEntity> BulletEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(GunMod.MOD_ID, "bullet"),
            FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build());

    public static void registerEntities() {

    }

}
