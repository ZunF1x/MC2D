package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2f;
import fr.zunf1x.mc2d.rendering.Color4f;

import java.util.Random;

public class Chunk {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;

    private BlockPlacer[][] blocks;

    private int x;

    private Noise noise;

    private World world;

    public Color4f foliageColor;

    public Chunk(int x, World world) {
        this.blocks = new BlockPlacer[WIDTH][HEIGHT];

        this.x = x;

        this.world = world;
        this.noise = this.world.getWorldProvider().getNoise();

        Random rand = this.world.getWorldProvider().getWorldSeededRandom();
        if (x - 1 >= 0) {
            Color4f c = this.world.getChunk(x - 1).foliageColor;

            boolean flag = rand.nextInt(100) < 75;

            float g = flag ? c.getG() : (float) Mathf.clamp(rand.nextFloat(), c.getG() - 0.15F, c.getG() + 0.15F);
            float b = flag ? c.getB() : (float) Mathf.clamp(rand.nextFloat(), 0.0F, 0.15F);

            c.print();

            this.foliageColor = new Color4f(0, g, b);
        } else {
            this.foliageColor = new Color4f(0, (float) Mathf.clamp(rand.nextFloat(), 0.25F, 0.75F), (float) Mathf.clamp(rand.nextFloat(), 0.0F, 0.15F));
        }

        this.generateChunk();
    }

    public void generateChunk() {
        Random random = this.world.getWorldProvider().getWorldSeededRandom();

        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                int xx = this.x * 16 + x;

                if (this.noise.getNoise(xx, y - 192) < y - 192) {
                    this.setBlock(x, y, Blocks.LEAVES);
                }
            }
        }
    }

    public void render() {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                if (b != null) b.render(new Color4f(foliageColor.getR(), foliageColor.getG(), foliageColor.getB()));
            }
        }
    }

    public void removeBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = null;
    }

    public void addBlock(int x, int y, Block b) {
        if (getBlock(x, y) == null) this.setBlock(x, y, b);
    }

    public void setBlock(int x, int y, Block b) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = new BlockPlacer(new Vector2f(xx, y), b, this.world);
    }

    public BlockPlacer getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return null;

        return this.blocks[x][y];
    }
}
