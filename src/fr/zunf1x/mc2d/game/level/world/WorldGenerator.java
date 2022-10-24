package fr.zunf1x.mc2d.game.level.world;

public abstract class WorldGenerator {

    private final WorldProvider worldProvider;

    public WorldGenerator(WorldProvider worldProvider) {
        this.worldProvider = worldProvider;
    }

    public WorldProvider getWorldProvider() {
        return worldProvider;
    }
}
