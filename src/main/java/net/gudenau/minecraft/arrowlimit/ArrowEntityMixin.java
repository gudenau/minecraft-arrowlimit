package net.gudenau.minecraft.arrowlimit;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends PersistentProjectileEntity {
    @SuppressWarnings("ConstantConditions")
    private ArrowEntityMixin() {
        super(null, null);
    }
    
    @Inject(
        method = "tick",
        at = @At("HEAD")
    )
    private void tick(CallbackInfo ci) {
        var velocity = getVelocity();
        if (velocity.lengthSquared() > 4 * 4) {
            setVelocity(velocity.normalize().multiply(4));
        }
    }
}
