package pl.elektryn.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.elektryn.ElektrynConfig;

/** Entity culling. @Coerce na frustum, bo typ klasy zmienia sie miedzy wersjami (Culler -> Frustum). */
@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void elektryn$cull(Entity entity, @Coerce Object frustum, double x, double y, double z,
                               CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Player) return;
        double max = ElektrynConfig.entityCullDistance;
        if (entity.distanceToSqr(x, y, z) > max * max) {
            cir.setReturnValue(false);
        }
    }
}
