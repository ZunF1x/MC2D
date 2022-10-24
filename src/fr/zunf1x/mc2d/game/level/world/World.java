package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.game.EntityManager;
import fr.zunf1x.mc2d.game.entities.EntityItem;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.blocks.placing.BlockBackgroundPlacer;
import fr.zunf1x.mc2d.game.level.blocks.placing.BlockPlacer;
import fr.zunf1x.mc2d.utils.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.lwjgl.input.Mouse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private final Noise noise;

    public int width, height;

    List<BlockPlacer> sBlocks;
    List<BlockPlacer> bBlocks;

    BlockPlacer[][] solidBlocks;
    BlockPlacer[][] bgBlocks;

    List<EntityItem> iDroped;
    EntityItem[][] itemsDroped;

    public World(int width, int height) {
        // seed . octave 10 . amplitude 3

        noise = new Noise(new Random().nextLong(), 10, 8);

        this.width = width;
        this.height = height;

        sBlocks = new ArrayList<>();
        bBlocks = new ArrayList<>();

        solidBlocks = new BlockPlacer[width][height];
        bgBlocks = new BlockPlacer[width][height];

        iDroped = new ArrayList<>();
        itemsDroped = new EntityItem[width][height];

        File world = new File("world/world.json");

        if (!world.exists()) {
            generate();
        } else {
            load();
        }
    }

    public void load() {
        JSONArray jsonArray = JSONUtils.readFromJSON("world/world.json");

        for (Object object : (JSONArray) jsonArray.get(0)) {
            JSONObject jsonObject = (JSONObject) object;
            int x = ((Double) jsonObject.get("x")).intValue();
            int y = ((Double) jsonObject.get("y")).intValue();
            int index = ((Long) jsonObject.get("index")).intValue();

            solidBlocks[x][y] = new BlockPlacer(x, y, Blocks.getBlock(index));
            sBlocks.add(solidBlocks[x][y]);
        }

        for (Object object : (JSONArray) jsonArray.get(1)) {
            JSONObject jsonObject = (JSONObject) object;
            int x = ((Double) jsonObject.get("x")).intValue();
            int y = ((Double) jsonObject.get("y")).intValue();
            int index = ((Long) jsonObject.get("index")).intValue();

            bgBlocks[x][y] = new BlockBackgroundPlacer(x, y, Blocks.getBlock(index));
            bBlocks.add(bgBlocks[x][y]);
        }
    }

    public void generate() {
        for (int x = 0; x < width; x++) {
            for (int y = 192; y < height - 1; y++) {
                if (noise.getNoise(x, y) < y) {
                    //stone
                    solidBlocks[x][y] = new BlockPlacer(x, y, Blocks.stone);
                    sBlocks.add(solidBlocks[x][y]);

                    if (getSolidBlock(x, y) != null) {
                        bgBlocks[x][y] = new BlockBackgroundPlacer(x, y, getSolidBlock(x, y).block);
                        bBlocks.add(bgBlocks[x][y]);
                    }
                }

                addGrass(x, y);
            }
        }

        addBedrock();

        Random rand = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (getSolidBlock(x, y + 1) != null) {
                    if (getSolidBlock(x, y + 1).block == Blocks.grass) {
                        if (rand.nextInt(100) < 10) {
                            if (x > 4 && x < width - 4 && y > 10 && y < height - 10) {
                                addTree(x, y);
                            }
                        }
                    }
                }
            }
        }
    }

    public List<BlockPlacer> getBlocks() {
        List<BlockPlacer> blocks = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (getSolidBlock(x, y) != null) {
                    blocks.add(getSolidBlock(x, y));
                }
            }
        }

        return blocks;
    }

    public List<BlockPlacer> getBgBlocks() {
        List<BlockPlacer> blocks = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (getBgBlock(x, y) != null) {
                    blocks.add(getBgBlock(x, y));
                }
            }
        }

        return blocks;
    }

    public void addGrass(int x, int y) {
        if (getSolidBlock(x, y) != null && getSolidBlock(x, y).block == Blocks.stone && getSolidBlock(x, y - 1) == null) {
            for (int i = 1; i < 4; i++) {
                solidBlocks[x][y - i] = new BlockPlacer(x, y - i, Blocks.dirt);
                sBlocks.add(solidBlocks[x][y - i]);

                bgBlocks[x][y - i] = new BlockBackgroundPlacer(x, y - i, Blocks.dirt);
                bBlocks.add(bgBlocks[x][y - i]);
            }

            solidBlocks[x][y - 4] = new BlockPlacer(x, y - 4, Blocks.grass);
            sBlocks.add(solidBlocks[x][y - 4]);

            bgBlocks[x][y - 4] = new BlockBackgroundPlacer(x, y - 4, Blocks.grass);
            bBlocks.add(bgBlocks[x][y - 4]);
        }
    }

    public void addBedrock() {
        for (int x = 0; x < width; x++) {
            solidBlocks[x][255] = new BlockPlacer(x, 255, Blocks.bedrock);
            sBlocks.add(solidBlocks[x][255]);

            bgBlocks[x][255] = new BlockBackgroundPlacer(x, 255, Blocks.bedrock);
            bBlocks.add(bgBlocks[x][255]);
        }
    }

    public void removeBlock(int x, int y) {
        BlockPlacer block = getSolidBlock(x, y);

        if (block != null) {
            solidBlocks[x][y] = null;
            sBlocks.remove(block);
        }
    }

    public BlockPlacer getSolidBlock(int x, int y) {
        if (x < 0 ||y < 0 || x >= width || y >= height) return null;
        return solidBlocks[x][y];
    }

    public EntityItem getEntityItem(int x, int y) {
        if (x < 0 ||y < 0 || x >= width || y >= height) return null;
        return itemsDroped[x][y];
    }

    /*public void addEntityItem(int x, int y, EntityItem item) {
        this.itemsDroped[x][y] = item;
        this.iDroped.add(itemsDroped[x][y]);
    }*/

    public void removeEntityItem(int x, int y) {
        EntityItem item = getEntityItem(x, y);

        if (item != null) {
            itemsDroped[x][y] = null;
            iDroped.remove(item);
        }
    }

    public void addBlock(int x, int y, BlockPlacer b) {
        if (getSolidBlock(x, y) == null) {
            this.solidBlocks[x][y] = b;
            this.sBlocks.add(solidBlocks[x][y]);
        }
    }

    public BlockPlacer getBgBlock(int x, int y) {
        if (x < 0 ||y < 0 || x >= width || y >= height) return null;
        return bgBlocks[x][y];
    }

    public void update(EntityManager entityManager) {
        for (int i = 0; i < bBlocks.size(); i++) {
            BlockPlacer b = bBlocks.get(i);
            b.update();
        }

        entityManager.update();

        for (int i = 0; i < sBlocks.size(); i++) {
            BlockPlacer b = sBlocks.get(i);
            if (b != null) {
                b.update();
                b.updateBreaking(Mouse.isButtonDown(0));
            }
        }

        for (int i = 0; i < iDroped.size(); i++) {
            EntityItem b = iDroped.get(i);
            b.update();
        }
    }

    public void addTree(int x, int y) {
        for (int yy = 0; yy < 2; yy++) {
            addBlock(x, y - yy, new BlockPlacer(x, y - yy, Blocks.log));
        }

        for (int xx = 0; xx < 3; xx++) {
            addBlock(x - (xx - 1), y - 4, new BlockPlacer(x - (xx - 1), y - 4, Blocks.leaves));
        }

        for (int xx = 0; xx < 5; xx++) {
            addBlock(x - (xx - 2), y - 3, new BlockPlacer(x - (xx - 2), y - 3, Blocks.leaves));
            addBlock(x - (xx - 2), y - 2, new BlockPlacer(x - (xx - 2), y - 2, Blocks.leaves));
        }
    }

    public void render(EntityManager entityManager) {
        for (int i = 0; i < bBlocks.size(); i++) {
            BlockPlacer b = bBlocks.get(i);
            if (getSolidBlock(b.x, b.y) == null) {
                b.render();
            } else if (getSolidBlock(b.x, b.y).block == Blocks.leaves) {
                b.render();
            }
        }

        entityManager.render();

        for (int i = 0; i < sBlocks.size(); i++) {
            BlockPlacer b = sBlocks.get(i);
            b.render();
        }

        for (int i = 0; i < iDroped.size(); i++) {
            EntityItem b = iDroped.get(i);
            b.render();
        }
    }
}
