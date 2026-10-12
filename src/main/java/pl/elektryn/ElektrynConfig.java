package pl.elektryn;

public final class ElektrynConfig {
    /** Encje dalej niz tyle blokow nie sa renderowane (gracze zawsze). */
    public static double entityCullDistance = 48.0;
    public static boolean disableEntityShadows = true;
    public static boolean reduceParticles = true;
    public static boolean noBiomeBlend = true;
    public static boolean noMipmaps = false;
    public static boolean uncapFps = true;

    private ElektrynConfig() {}
}
