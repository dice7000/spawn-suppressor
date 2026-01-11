package net.dice7000.spawn_suppressor;

public interface SpawnSuppressorMethod {
    default void spawn_suppressor$toggleSpawn() {
        SSStaticHolder.suppressSpawn = !SSStaticHolder.suppressSpawn;
    }
    default boolean spawn_suppressor$getSuppressSpawn() {
        return SSStaticHolder.suppressSpawn;
    }
}
