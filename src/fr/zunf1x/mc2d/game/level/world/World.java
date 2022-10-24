package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;

import java.util.Random;

public class World extends WorldGenerator {

    private final int size;
    private final Chunk[] chunks;

    public World(int size) {
        super(new WorldProvider(-3021263370980223720L, 20, 10));

        this.size = size;
        this.chunks = new Chunk[size];

        this.generate();
    }

    public void generate() {
        for (int x = 0; x < 4; x++) {
            this.chunks[x] = new Chunk(x, this.getWorldProvider().getNoise());
        }
    }

    public void init(Game game) {
        for (int x = 0; x < this.size; x++) {
            if (getChunk(x) != null) getChunk(x).init(game);
        }
    }

    public void update() {
        Game game = Start.getInstance().getGame();
        float xScroll = -(game.getXScroll() / 64) / 16F;
        int a = (int) xScroll;

        if (a + 1 >= this.size) return;

        if (chunks[a + 1] == null) {
            chunks[a + 1] = new Chunk(a + 1, this.getWorldProvider().getNoise());
            System.out.println(getWorldProvider().getWorldSeededRandom().nextInt(50) + getWorldProvider().getWorldSeededRandom().nextInt(25));
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
}
