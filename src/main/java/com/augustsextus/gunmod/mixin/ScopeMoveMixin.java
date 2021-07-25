package com.augustsextus.gunmod.mixin;

import com.augustsextus.gunmod.GunMod;
import com.augustsextus.gunmod.items.Gun;
import com.augustsextus.gunmod.registry.ItemRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class ScopeMoveMixin {

    @Shadow private final MinecraftClient client;

    private ScopeMoveMixin(MinecraftClient client) {
        this.client = client;
    }

    private void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((double)((float)i * 0.56F), (double)(-0.52F + equipProgress * -0.6F), -0.7200000286102295D);
    }

    @Inject(at = @At("HEAD"), method = "renderFirstPersonItem")
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        boolean bl = hand == Hand.MAIN_HAND;
        Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
        matrices.push();
        boolean bl4;
        float v;
        float w;
        float x;
        float y;
        if (GunMod.getGuns().contains(item.getItem())) {
            boolean bl3 = arm == Arm.RIGHT;
            int i = bl3 ? 1 : -1;
            Gun gun = (Gun)item.getItem();
            if (Gun.getIsScoping()) {

                if (GunMod.getGuns().contains(item.getItem()))
                this.applyEquipOffset(matrices, arm, equipProgress);
                matrices.translate((double) ((float) i * -1d), 0.6d, 1d);
                matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) i * 56.5F));
                v = (float) item.getMaxUseTime() - ((float) this.client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
                w = v / (float) CrossbowItem.getPullTime(item);
                if (w > 1.0F) {
                    w = 1.0F;
                }

                if (w > 0.1F) {
                    x = MathHelper.sin((v - 0.1F) * 1.3F);
                    y = w - 0.1F;
                    float k = x * y;
                    matrices.translate((double) (k * 0.0F), (double) (k * 0.004F), (double) (k * 0.0F));
                }

                matrices.translate((double) (w * 0.0F), (double) (w * 0.0F), (double) (w * 0.04F));
                matrices.scale(1.0F, 1.0F, 1.0F + w * 0.2F);
                matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((float) i * 45.0F));
            }
        }

    }
}