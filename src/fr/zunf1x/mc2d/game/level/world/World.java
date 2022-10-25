package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import org.lwjgl.input.Mouse;

import java.util.Random;

public class World extends WorldGenerator {

    private final int size;
    private final Chunk[] chunks;

    public World(int size) {
        super(new WorldProvider(-4575487547547842124L, 20, 10));

        this.size = size;
        this.chunks = new Chunk[size];

        this.generate();
    }

    public void generate() {
        for (int x = 0; x < 4; x++) {
            this.chunks[x] = new Chunk(x, this);
        }
    }

    public void update() {
        Game game = Start.getInstance().getGame();
        float xScroll = -(game.getXScroll() / 64) / 16F;
        int a = (int) xScroll;

        if (a + 1 >= this.size) return;

        for (int i = 0; i < 16; i++) {
            int pPos = a + i / 2;
            int mPos = a - i / 2;

            if (pPos < 0 || mPos < 0) continue;

            if (chunks[pPos] == null) {
                chunks[pPos] = new Chunk(pPos, this);
            }

            if (chunks[mPos] == null) {
                chunks[mPos] = new Chunk(mPos, this);
            }
        }
    }

    public void render() {
        Game game = Start.getInstance().getGame();

        for (int x = 0; x < this.size; x++) {
            float xScroll = -(game.getXScroll() / 64);

            int xx0 = x * 16 + 16;
            int xx1 = x * 16;

            if (xScroll > xx0 || xScroll + game.getWidth() / 64F < xx1) continue;

            if (getChunk(x) != null) getChunk(x).render();
        }
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
}
