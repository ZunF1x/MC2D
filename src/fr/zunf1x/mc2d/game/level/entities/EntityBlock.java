package fr.zunf1x.mc2d.game.level.entities;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.math.vectors.Vector2d;

public class EntityBlock extends Entity {

    private final Block block;

    public EntityBlock(Vector2d location, Block block) {
        super(location);
        this.block = block;
    }

    float xa, ya;

    @Override
    public void update() {
        ya += 1.8F * 0.24F / 64F;

        if (this.blockOnGround()) {
            this.game.getWorld().addBlock((int) Math.ceil(getLocation().getX()), (int) Math.floor(getLocation().getY()), this.block, false);
            this.game.entityManager.removeEntity(this);
        }

        int xStep = (int) Math.abs(xa * 1000);
        for (int i = 0; i < xStep; i++) {
            if (collideBlock(xa / xStep, 0)) {
                xa = 0;
            } else {
                getLocation().addX(xa / xStep);
            }
        }

        int yStep = (int) Math.abs(ya * 1000);
        for (int i = 0; i < yStep; i++) {
            if (collideBlock(0, ya / yStep)) {
                ya = 0;
            } else {
                getLocation().addY(ya / yStep);
            }
        }

        xa *= 0.95F;
        ya *= 0.95F;
    }

    @Override
    public void render() {
        this.block.render(this.getLocation(), this.game.getWorld(), this.game.getWorld().getChunk((int) (this.getLocation().getX() / 16D)).foliageColor, false);
    }
}
