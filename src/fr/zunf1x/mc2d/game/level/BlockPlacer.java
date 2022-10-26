package fr.zunf1x.mc2d.game.level;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.IGravity;
import fr.zunf1x.mc2d.game.level.entities.EntityBlock;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.game.level.world.WorldGenerator;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;

import java.util.Random;

public class BlockPlacer {

    private final Vector2d loc;
    private final Block block;

    private Color4f color;

    private World world;
    private Game game;

    public BlockPlacer(Vector2d loc, Block block, World world, Game game) {
        this.loc = loc;
        this.block = block;

        this.game = game;
        this.world = world;

        Random rdm = world.getWorldProvider().getWorldSeededRandom();

        this.color = new Color4f(rdm.nextFloat() * 4, rdm.nextFloat() * 2, rdm.nextFloat() * 2);
    }

    public void update() {
        if (getBlock() instanceof IGravity) {
            if (this.world.getBlock((int) Math.ceil(getLocation().getX()), (int) Math.floor(getLocation().getY() + 1)) == null) {
                this.game.entityManager.addEntity(new EntityBlock(new Vector2d((int) Math.ceil(getLocation().getX()), (int) Math.floor(getLocation().getY())), this.block));
                this.world.removeBlock((int) Math.ceil(getLocation().getX()), (int) Math.floor(getLocation().getY()));
            }
        }
    }

    public void render(Color4f grassColor) {
        double x = this.getLocation().getX();
        double y = this.getLocation().getY();

        Game game = Start.getInstance().getGame();

        double x0 = x + game.getXScroll();
        double y0 = y + game.getYScroll();

        double x1 = x + 1 + game.getXScroll();
        double y1 = y + 1 + game.getYScroll();

        if (x1 < 0 || y1 < 0 || x0 > game.getWidth() / 64F || y0 > game.getHeight() / 64F) return;

        this.block.render(this.loc, grassColor);
    }

    public Vector2d getLocation() {
        return loc;
    }

    public Block getBlock() {
        return block;
    }
}
