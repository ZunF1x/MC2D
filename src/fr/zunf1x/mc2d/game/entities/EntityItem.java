package fr.zunf1x.mc2d.game.entities;

import fr.zunf1x.mc2d.game.level.Level;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.graphics.ItemRendering;

public class EntityItem extends Entity {

    ItemStack stack;

    public EntityItem(Level level, int x, int y, ItemStack stack) {
        super(level, x, y);

        this.stack = stack;

        mass = 0.48F;
        drag = 0.95F;
    }

    float xa, ya;

    @Override
    public void update() {
        ya += level.gravity * mass;

        int xStep = (int) Math.abs(xa * 1000);
        for (int i = 0; i < xStep; i++) {
            if (!isSolidTileForItem(xa / xStep, 0)) {
                x += xa / xStep;
            } else {
                xa = 0;
            }
        }

        int yStep = (int) Math.abs(ya * 1000);
        for (int i = 0; i < yStep; i++) {
            if (!isSolidTileForItem(0, ya / yStep)) {
                y += ya / yStep;
            } else {
                ya = 0;
            }
        }

        xa *= drag;
        ya *= drag;
    }

    public boolean isSolidTileForItem(float xa, float ya) {
        int x0 = (int) (x + xa + 3) / 64;
        int x1 = (int) (x + xa + 15) / 64;
        int y0 = (int) (y + ya + 3) / 64;
        int y1 = (int) (y + ya + 15) / 64;

        if (level.world.getSolidBlock(x0, y0) != null) return true;
        if (level.world.getSolidBlock(x1, y0) != null) return true;
        if (level.world.getSolidBlock(x1, y1) != null) return true;
        return level.world.getSolidBlock(x0, y1) != null;
    }

    @Override
    public void render() {
        ItemRendering it = new ItemRendering(stack);
        it.render(x, y);
    }
}
