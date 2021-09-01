package com.augustsextus.gunmod.registry;


import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.items.Bullet;
//import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.items.Gun;
//import com.augustsextus.gunmod.items.PackedSnowballItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    //creates the items and the settings for the items
    public static final Gun RIFLE_1 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Bullet BULLET = new Bullet(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(64));
    public static final Gun  RIFLE_2 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Gun RIFLE_3 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Gun HANDGUN_1 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Gun HANDGUN_2 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Gun HANDGUN_3 = new Gun(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));

    public static void registerItems () {

        //register...
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "rifle_1"), RIFLE_1);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "bullet"), BULLET);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "rifle_2"), RIFLE_2);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "rifle_3"), RIFLE_3);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "handgun_1"), HANDGUN_1);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "handgun_2"), HANDGUN_2);
        Registry.register(Registry.ITEM, new Identifier(GunMod.MOD_ID, "handgun_3"), HANDGUN_3);

    }

}
