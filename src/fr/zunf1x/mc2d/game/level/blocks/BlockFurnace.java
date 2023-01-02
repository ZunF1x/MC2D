package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.level.entities.particles.Particle;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;

public class BlockFurnace extends Block {

    private boolean burning;

    public BlockFurnace(boolean burning) {
        if (burning) setTexture(27);
        else setTexture(26);

        this.burning = burning;
    }

    @Override
    public void tick(Vector2d loc, World world, int chunkX, boolean halfSide, boolean halfTop) {
        if (burning) {
            Particle p = new Particle(Color4f.WHITE, 48, new Vector2d(1, 1), 0.25F, 25, false);

            world.addParticleSystem(((loc.getX() - 0.5) * 64) + 52, loc.getY() * 64 + 40, 1, p, 1);
        }
    }
}
