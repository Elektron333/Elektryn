package pl.elektryn;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;

/** Wersje 1.19 - 1.21.11: ustawienia to OptionInstance. */
public final class OptionsApplier {
    private OptionsApplier() {}

    /** Bez importu ParticleStatus, bo ta klasa zmienia pakiet miedzy wersjami. */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void reduceParticles(Options o) {
        OptionInstance opt = o.particles();
        Object current = opt.get();
        if (current instanceof Enum) {
            Object[] values = ((Enum<?>) current).getDeclaringClass().getEnumConstants();
            if (values.length > 1) opt.set(values[1]); // kolejnosc: ALL, DECREASED, MINIMAL
        }
    }

    public static void apply(Options o) {
        if (ElektrynConfig.uncapFps) {
            o.framerateLimit().set(260);
            o.enableVsync().set(false);
        }
        if (ElektrynConfig.disableEntityShadows) o.entityShadows().set(false);
        if (ElektrynConfig.reduceParticles) reduceParticles(o);
        if (ElektrynConfig.noBiomeBlend) o.biomeBlendRadius().set(0);
        if (ElektrynConfig.noMipmaps) o.mipmapLevels().set(0);
        o.entityDistanceScaling().set(0.75);
    }
}
