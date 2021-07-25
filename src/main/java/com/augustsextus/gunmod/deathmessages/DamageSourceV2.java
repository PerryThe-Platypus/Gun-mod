package com.augustsextus.gunmod.deathmessages;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

public class DamageSourceV2 extends DamageSource {

    protected DamageSourceV2(String name) {
        super(name);
    }

    public static DamageSource rifle_1(String name, @Nullable Entity attacker) {
        return (new GunDamageSource("rifle_1", attacker));
    }

    public Text getDeathMessage(LivingEntity entity) {
        LivingEntity livingEntity = entity.getPrimeAdversary();
        String string = "death.attack." + this.name;
        String string2 = string + ".player";
        return livingEntity != null ? new TranslatableText(string2, new Object[]{entity.getDisplayName(), livingEntity.getDisplayName()}) : new TranslatableText(string, new Object[]{entity.getDisplayName()});
    }

}
