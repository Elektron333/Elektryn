package pl.elektryn;

import net.minecraft.client.Options;
import net.minecraft.client.ParticleStatus;

/** Wersje 1.14 - 1.18.2: ustawienia to zwykle pola. */
public final class OptionsApplier {
    private OptionsApplier() {}

    public static void apply(Options o) {
        if (ElektrynConfig.uncapFps) {
            o.framerateLimit = 260;
            o.enableVsync = false;
        }
        if (ElektrynConfig.disableEntityShadows) o.entityShadows = false;
        if (ElektrynConfig.reduceParticles) o.particles = ParticleStatus.DECREASED;
        if (ElektrynConfig.noMipmaps) o.mipmapLevels = 0;
    }
}
