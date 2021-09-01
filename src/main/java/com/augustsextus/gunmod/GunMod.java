package com.augustsextus.gunmod;

import com.augustsextus.gunmod.entity.BulletEntity;
import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.registry.EntityRegistry;
import com.augustsextus.gunmod.registry.ItemRegistry;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Set;

public class GunMod implements ModInitializer{

    public static final String MOD_ID = "gun";

    //creating a set list for each gun and weapon category since it will make my life a lot easier for future updates

    private static final Set<Item> GUNS = ImmutableSet.of(ItemRegistry.RIFLE_1, ItemRegistry.RIFLE_2, ItemRegistry.RIFLE_3, ItemRegistry.HANDGUN_1, ItemRegistry.HANDGUN_2, ItemRegistry.HANDGUN_3);
    private static final Set<Item> RIFLES = ImmutableSet.of(ItemRegistry.RIFLE_1, ItemRegistry.RIFLE_2, ItemRegistry.RIFLE_3);

    private static final Identifier SHOOT_PACKET = new Identifier("shoot_packet");//Identifier for the shoot packet sending

    public static Identifier getShootPacket() {
        return SHOOT_PACKET;
    }

    @Override
    public void onInitialize() {
        ItemRegistry.registerItems();
        EntityRegistry.registerEntities();

        //shoot packet receiever
        ServerPlayNetworking.registerGlobalReceiver(SHOOT_PACKET, (server, user, handler, buf, responseSender) -> server.execute(() -> {
            Gun.shoot(user.world, user);
        }));

    }

    public static Set<Item> getGuns() {
        return GUNS;
    }
}
