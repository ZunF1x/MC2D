package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.math.vectors.Vector2f;

public class Chunk {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;

    private BlockPlacer[][] blocks;

    private int x;

    private Noise noise;

    public Chunk(int x, Noise noise) {
        this.blocks = new BlockPlacer[WIDTH][HEIGHT];

        this.x = x;

        this.noise = new Noise(noise.seed, 20, 10);

        this.generateChunk();
    }

    public void init(Game game) {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                if (b != null) b.init(game);
            }
        }
    }

    public void generateChunk() {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                int xx = this.x * 16 + x;

                if (this.noise.getNoise(xx, y - 192) < y - 192) {
                    this.setBlock(x, y, Blocks.GRASS);
                }
            }
        }
    }

    public void render() {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                if (b != null) b.render();
            }
        }
    }

    public void removeBlock(int x, int y) {
        if (getBlock(x, y) != null) this.setBlock(x, y, null);
    }

    public void addBlock(int x, int y, Block b) {
        if (getBlock(x, y) == null) this.setBlock(x, y, b);
    }

    public void setBlock(int x, int y, Block b) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = new BlockPlacer(new Vector2f(xx, y), b);
    }

    public BlockPlacer getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return null;

        return this.blocks[x][y];
    }
}
