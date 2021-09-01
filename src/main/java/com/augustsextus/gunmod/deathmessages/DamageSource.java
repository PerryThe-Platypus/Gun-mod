package com.augustsextus.gunmod.deathmessages;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

public class DamageSource extends EntityDamageSource {

    private final Entity attacker; //the attacker variable


    public DamageSource(String name, @Nullable Entity attacker) {
        super(name, attacker);
        this.attacker = attacker;
    }

    //the different damagesources
    public static net.minecraft.entity.damage.DamageSource rifle_1(String name, @Nullable Entity attacker) {
        return (new DamageSource("rifle_1", attacker));
    }

    public static net.minecraft.entity.damage.DamageSource rifle_2(String name, @Nullable Entity attacker) {
        return (new DamageSource("rifle_2", attacker));
    }

    public static net.minecraft.entity.damage.DamageSource rifle_3(String name, @Nullable Entity attacker) {
        return (new DamageSource("rifle_3", attacker));
    }

    public static net.minecraft.entity.damage.DamageSource handgun_1(String name, @Nullable Entity attacker) {
        return (new DamageSource("handgun_1", attacker));
    }

    public static net.minecraft.entity.damage.DamageSource handgun_2(String name, @Nullable Entity attacker) {
        return (new DamageSource("handgun_2", attacker));
    }

    public static net.minecraft.entity.damage.DamageSource handgun_3(String name, @Nullable Entity attacker) {
        return (new DamageSource("handgun_3", attacker));
    }


    @Nullable
    public Entity getSource() {
        return this.source;
    }

    @Nullable
    public Entity getAttacker() {
        return this.attacker;
    }

    public Text getDeathMessage(LivingEntity entity) {
        LivingEntity livingEntity = entity.getPrimeAdversary();
        String string = "death.attack." + this.name;
        String string2 = string + ".player";
        return livingEntity != null ? new TranslatableText(string2, new Object[]{entity.getDisplayName(), livingEntity.getDisplayName()}) : new TranslatableText(string, new Object[]{entity.getDisplayName()});
    }
}
