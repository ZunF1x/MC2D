package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.game.level.BlockPlacer;
import fr.zunf1x.mc2d.game.level.blocks.*;
import fr.zunf1x.mc2d.game.level.world.features.Tree;
import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chunk {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 256;

    private BlockPlacer[][] blocks;

    private int x;

    private Noise noise;

    private World world;

    public Color4f foliageColor;

    private List<Integer> trees;

    private int vbo, cbo;
    private int vertices;
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;

    public Chunk(int x, World world) {
        this.blocks = new BlockPlacer[WIDTH][HEIGHT];

        this.trees = new ArrayList<>();

        this.x = x;

        this.world = world;
        this.noise = this.world.getWorldProvider().getNoise();

        Random rand = this.world.getWorldProvider().getWorldSeededRandom();
        if (x - 1 >= 0 && this.world.getChunk(x - 1) != null) {
            Color4f c = this.world.getChunk(x - 1).foliageColor;

            boolean flag = rand.nextInt(100) < 75;

            float g = flag ? c.getG() : (float) Mathf.clamp(rand.nextFloat(), c.getG() - 0.15F, c.getG() + 0.15F);
            float b = flag ? c.getB() : (float) Mathf.clamp(rand.nextFloat(), 0.0F, 0.15F);

            this.foliageColor = new Color4f(0, g, b);
        } else {
            this.foliageColor = new Color4f(0, (float) Mathf.clamp(rand.nextFloat(), 0.25F, 0.75F), (float) Mathf.clamp(rand.nextFloat(), 0.0F, 0.15F));
        }
    }

    public Chunk generateChunk() {
        Random random = this.world.getWorldProvider().getWorldSeededRandom();

        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                int xx = this.x * 16 + x;

                if (this.noise.getNoise(xx, y - 192) < y - 192) {
                    this.setBlock(x, y, Blocks.STONE);
                }

                if (grounded(x, y)) {
                    this.setBlock(x, y, Blocks.GRASS);
                }
            }
        }

        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                if (x - 2 < 0 || x + 2 >= Chunk.WIDTH) continue;

                if (grounded(x, y) && getBlock(x, y).getBlock() instanceof BlockGrass && !trees.contains(x - 1) && !trees.contains(x) && !trees.contains(x + 1)) {
                    if (random.nextInt(100) < 15) {
                        Tree.addTree(this, x, y);
                        trees.add(x);
                    }
                }
            }
        }

        return this;
    }

    public void putInBuffer(FloatBuffer buffer, float... values) {
        buffer.put(values);
    }

    public void generateGrid() {
        this.vertices = WIDTH * HEIGHT * 8 + 8;

        this.vertexBuffer = BufferUtils.createFloatBuffer(vertices * 2);
        this.colorBuffer = BufferUtils.createFloatBuffer(vertices * 4);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                putInBuffer(colorBuffer, 1, 0, 0, 1);
                putInBuffer(vertexBuffer, this.x * 16 + x, y);

                putInBuffer(colorBuffer, 1, 0, 0, 1);
                putInBuffer(vertexBuffer, this.x * 16 + x + 1, y);

                putInBuffer(colorBuffer, 1, 0, 0, 1);
                putInBuffer(vertexBuffer, this.x * 16 + x + 1, y + 1);

                putInBuffer(colorBuffer, 1, 0, 0, 1);
                putInBuffer(vertexBuffer, this.x * 16 + x, y + 1);
            }
        }

        putInBuffer(colorBuffer, 0, 1, 0, 1);
        putInBuffer(vertexBuffer, this.x * 16, 0);

        putInBuffer(colorBuffer, 0, 1, 0, 1);
        putInBuffer(vertexBuffer, this.x * 16 + 16, 0);

        putInBuffer(colorBuffer, 0, 1, 0, 1);
        putInBuffer(vertexBuffer, this.x * 16 + 16, 256);

        putInBuffer(colorBuffer, 0, 1, 0, 1);
        putInBuffer(vertexBuffer, this.x * 16, 256);

        vertexBuffer.flip();
        colorBuffer.flip();

        this.vbo = glGenBuffers();
        this.cbo = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER, this.vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, this.cbo);
        glBufferData(GL_ARRAY_BUFFER, this.colorBuffer, GL_STATIC_DRAW);

        vertexBuffer.clear();
        colorBuffer.clear();
    }

    public boolean grounded(int x, int y) {
        return getBlock(x, y) != null && getBlock(x, y - 1) == null;
    }

    public void update() {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                if (b != null) b.update();
            }
        }

        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                boolean flag = getBlock(x, y - 1) == null || !(getBlock(x, y - 1).getBlock() instanceof BlockDoor);
                boolean flag1 = getBlock(x, y + 1) == null || !(getBlock(x, y + 1).getBlock() instanceof BlockDoor);

                if (b != null && b.getBlock() instanceof BlockDoor && flag) {
                    if (getBlock(x, y - 1) == null || !(getBlock(x, y - 1).getBlock() instanceof BlockNull)) {
                        setBlock(x, y - 1, new BlockNull(), getBlock(x, y).half);
                    }
                }

                if (b != null && b.getBlock() instanceof BlockNull && flag1) {
                    blocks[x][y] = null;
                }

                if (b != null && b.getBlock() instanceof BlockNull) {
                    if (getBlock(x, y + 1) != null && getBlock(x, y + 1).getBlock() instanceof BlockDoor) {
                        if (getBlock(x, y + 1).isCollide() != getBlock(x, y).isCollide()) {
                            getBlock(x, y).setCollide(getBlock(x, y + 1).isCollide());
                        }
                    }
                }
            }
        }
    }

    public void render(boolean debug, World world) {
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int y = 0; y < Chunk.HEIGHT; y++) {
                BlockPlacer b = getBlock(x, y);

                if (b != null) b.render(new Color4f(foliageColor.getR(), foliageColor.getG(), foliageColor.getB()), world);
            }
        }

        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glLineWidth(1);

        if (debug) {
            glEnableClientState(GL_VERTEX_ARRAY);
            glEnableClientState(GL_COLOR_ARRAY);

            glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
            glVertexPointer(2, GL_FLOAT, 0, 0L);

            glBindBuffer(GL_ARRAY_BUFFER, this.cbo);
            glColorPointer(4, GL_FLOAT, 0, 0L);

            glDrawArrays(GL_QUADS, 0, vertices);

            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);
        }

        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }

    public void removeBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = null;
    }

    public void addBlock(int x, int y, Block b, boolean half) {
        if (getBlock(x, y) == null) this.setBlock(x, y, b, half);
    }

    public void setBlock(int x, int y, Block b, boolean half) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = new BlockPlacer(new Vector2d(xx, y), half, b, this.world, this.world.game);
    }

    public void setBlock(int x, int y, Block b) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        int xx = this.x * 16 + x;

        this.blocks[x][y] = new BlockPlacer(new Vector2d(xx, y), false, b, this.world, this.world.game);
    }

    public BlockPlacer getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return null;

        return this.blocks[x][y];
    }
}
