package com.augustsextus.gunmod.deathmessages;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

public class GunDamageSource extends EntityDamageSource {

    private final Entity attacker;

    public GunDamageSource(String name/*, Entity projectile*/, @Nullable Entity attacker) {
        super(name, attacker);
        this.attacker = attacker;
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
        Text text = this.attacker == null ? this.source.getDisplayName() : this.attacker.getDisplayName();
        ItemStack itemStack = this.attacker instanceof LivingEntity ? ((LivingEntity)this.attacker).getMainHandStack() : ItemStack.EMPTY;
        String string = "death.attack." + this.name;
        String string2 = string + ".item";
        return !itemStack.isEmpty() && itemStack.hasCustomName() ? new TranslatableText(string2, new Object[]{entity.getDisplayName(), text, itemStack.toHoverableText()}) : new TranslatableText(string, new Object[]{entity.getDisplayName(), text});
    }
}
