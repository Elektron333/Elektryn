package pl.elektryn;

import net.minecraft.client.Options;
import net.minecraft.client.ParticleStatus;

/** Wersje 1.19 - 1.21.11: ustawienia to OptionInstance. */
public final class OptionsApplier {
    private OptionsApplier() {}

    public static void apply(Options o) {
        if (ElektrynConfig.uncapFps) {
            o.framerateLimit().set(260);
            o.enableVsync().set(false);
        }
        if (ElektrynConfig.disableEntityShadows) o.entityShadows().set(false);
        if (ElektrynConfig.reduceParticles) o.particles().set(ParticleStatus.DECREASED);
        if (ElektrynConfig.noBiomeBlend) o.biomeBlendRadius().set(0);
        if (ElektrynConfig.noMipmaps) o.mipmapLevels().set(0);
        o.entityDistanceScaling().set(0.75);
    }
}
