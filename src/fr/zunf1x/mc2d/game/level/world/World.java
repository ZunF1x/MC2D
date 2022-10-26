package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.Block;

public abstract class World {

    private final int size;
    private final Chunk[] chunks;

    public final Game game;
    private final WorldProvider worldProvider;

    public World(int size, Game game, WorldProvider worldProvider) {
        this.worldProvider = worldProvider;
        this.size = size;
        this.chunks = new Chunk[size];

        this.game = game;
    }

    public void update() {
        Game game = Start.getInstance().getGame();
        double playerX = game.player.getLocation().getX();
        int playerChunkX = (int) (playerX / 16F);
        final int factor = 16;

        for (int i = 0; i <= factor; i++) {
            int f = i - factor / 2;
            int chunkToGenerate = playerChunkX + f;

            if (chunkToGenerate < 0 || chunkToGenerate >= size) continue;

            if (this.chunks[chunkToGenerate] == null) {
                Chunk c = new Chunk(chunkToGenerate, this).generateChunk();
                c.generateGrid();
                this.chunks[chunkToGenerate] = c;
            }
        }

        for (int x = 0; x < this.size; x++) {
            double xScroll = -game.getXScroll();

            int xx0 = x * 16 + 16;
            int xx1 = x * 16;

            if (xScroll > xx0 || xScroll + game.getWidth() / 64F < xx1) continue;

            if (getChunk(x) != null) getChunk(x).update();
        }
    }

    public WorldProvider getWorldProvider() {
        return worldProvider;
    }

    public void render(boolean debug) {
        Game game = Start.getInstance().getGame();

        for (int x = 0; x < this.size; x++) {
            double xScroll = -game.getXScroll();

            int xx0 = x * 16 + 16;
            int xx1 = x * 16;

            if (xScroll > xx0 || xScroll + game.getWidth() / 64F < xx1) continue;

            if (getChunk(x) != null) getChunk(x).render(debug);
        }
    }

    public boolean grounded(int x, int y) {
        return getBlock(x, y) != null && getBlock(x, y - 1) == null;
    }

    public Chunk getChunk(int x) {
        if (x < 0 || x >= this.size) return null;

        return this.chunks[x];
    }

    public void removeBlock(int x, int y) {
        int xx = x / 16;

        this.getChunk(xx).removeBlock(x % 16, y);
    }

    public void addBlock(int x, int y, Block block) {
        int xx = x / 16;

        this.getChunk(xx).addBlock(x % 16, y, block);
    }

    public void setBlock(int x, int y, Block block) {
        int xx = x / 16;

        this.getChunk(xx).setBlock(x % 16, y, block);
    }

    public BlockPlacer getBlock(int x, int y) {
        int xx = x / 16;

        if (this.getChunk(xx) == null) return null;

        return this.getChunk(xx).getBlock(x % 16, y);
    }

    public int getSize() {
        return size;
    }
}
