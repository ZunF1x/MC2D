package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.world.features.WorldGenerator;
import fr.zunf1x.mc2d.game.level.world.features.WorldGeneratorOres;

public abstract class World {

    private boolean rain = false;

    private final int size;
    private final Chunk[] chunks;

    public final Game game;
    private final WorldProvider worldProvider;

    private final WorldGenerator gen;

    private int rainPhases;

    public World(int size, Game game, WorldProvider worldProvider) {
        this.worldProvider = worldProvider;
        this.size = size;
        this.chunks = new Chunk[size];

        gen = new WorldGeneratorOres();

        this.game = game;

        this.rainPhases = this.getWorldProvider().getWorldSeededRandom().nextInt(20);
    }

    private int i = 0;

    public void update() {
        Game game = Start.getInstance().getGame();
        double playerX = game.player.getLocation().getX();
        int playerChunkX = (int) (playerX / 16F);
        final int factor = 16;

        i++;

        if (i % 2500 == 0) {
            System.out.println(this.rainPhases);

            if (rainPhases <= 0) {
                this.rain = !this.rain;
                this.rainPhases = this.getWorldProvider().getWorldSeededRandom().nextInt(25);
            } else {
                this.rainPhases -= 1;
            }
        }

        for (int i = 0; i <= factor; i++) {
            int f = i - factor / 2;
            int chunkToGenerate = playerChunkX + f;

            if (chunkToGenerate < 0 || chunkToGenerate >= size) continue;

            if (this.chunks[chunkToGenerate] == null) {
                Chunk c = new Chunk(chunkToGenerate, game, this).generateChunk();
                c.generateGrid();
                this.chunks[chunkToGenerate] = c;
                gen.generate(this.getWorldProvider().getWorldSeededRandom(), chunkToGenerate, this);
            }
        }

        for (int x = 0; x < this.size; x++) {
            double xScroll = -game.getXScroll();

            int xx0 = x * 16 + 16 + (simulationDistance * 16);
            int xx1 = x * 16 - (simulationDistance * 16);

            if (xScroll > xx0 || xScroll + game.getWidth() / 64F < xx1) continue;

            if (getChunk(x) != null) getChunk(x).update(this.rain);
        }
    }

    private int simulationDistance = 4;
    private int renderDistance = 0;

    public WorldProvider getWorldProvider() {
        return worldProvider;
    }

    public void render(boolean debug) {
        Game game = Start.getInstance().getGame();

        for (int x = 0; x < this.size; x++) {
            double xScroll = -game.getXScroll();

            int xx0 = x * 16 + 16 + (renderDistance * 16);
            int xx1 = x * 16 - (renderDistance * 16);

            if (xScroll > xx0 || xScroll + game.getWidth() / 64F < xx1) continue;

            if (getChunk(x) != null) getChunk(x).render(debug, this);
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

    public void addBlock(int x, int y, Block block, boolean halfSide, boolean halfTop) {
        int xx = x / 16;

        this.getChunk(xx).addBlock(x % 16, y, block, halfSide, halfTop);
    }

    public void setBlock(int x, int y, Block block) {
        int xx = x / 16;

        if (getChunk(xx) == null) return;

        this.getChunk(xx).setBlock(x % 16, y, block);
    }

    public void setBlock(int x, int y, Block block, Block toReplace) {
        int xx = x / 16;

        if (getChunk(xx) == null) return;

        if (getBlock(x, y) != null && getBlock(x, y).getBlock() == toReplace) {
            this.getChunk(xx).setBlock(x % 16, y, block);
        }
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
