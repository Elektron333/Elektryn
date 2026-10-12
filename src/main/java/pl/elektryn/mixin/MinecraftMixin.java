package pl.elektryn.mixin;

import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.elektryn.ElektrynConfig;
import pl.elektryn.OptionsApplier;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow @Final public Options options;

    /** Ustawienia stosujemy raz (przy pierwszym uruchomieniu), zeby nie nadpisywac wyborow gracza. */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void elektryn$init(CallbackInfo ci) {
        try {
            Path flag = FabricLoader.getInstance().getConfigDir().resolve("elektryn-applied.flag");
            if (Files.exists(flag)) return;
            OptionsApplier.apply(this.options);
            this.options.save();
            if (ElektrynConfig.uncapFps) {
                ((Minecraft) (Object) this).getWindow().updateVsync(false);
            }
            Files.write(flag, new byte[0]);
        } catch (Throwable t) {
            System.err.println("[Elektryn] Nie udalo sie zastosowac ustawien: " + t);
        }
    }
}
