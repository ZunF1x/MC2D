package fr.zunf1x.mc2d.game.level.world.features;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;

import java.util.Random;

public class WorldGenMinable {

    private Block ore;
    private Block toReplace;
    private int veinSize;

    public WorldGenMinable(Block ore, Block toReplace, int veinSize) {
        this.ore = ore;
        this.toReplace = toReplace;
        this.veinSize = veinSize;
    }

    public void generate(World world, Random rand, Vector2d loc) {
        int x = (int) loc.getX();
        int y = (int) loc.getY();

        float f = rand.nextFloat() * (float)Math.PI;
        double d0 = ((float)x + Math.sin(f) * (float)this.veinSize / 8.0F);
        double d1 = (float)x - Math.sin(f) * (float)this.veinSize / 8.0F;
        double d4 = (y + rand.nextInt(3) - 2);
        double d5 = (y + rand.nextInt(3) - 2);

        for (int i = 0; i < this.veinSize; ++i) {
            float f1 = (float)i / (float)this.veinSize;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d9 = rand.nextDouble() * (double)this.veinSize / 16.0D;
            double d10 = (Math.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (Math.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = (int) Math.floor(d6 - d10 / 2.0D);
            int k = (int) Math.floor(d7 - d11 / 2.0D);
            int i1 = (int) Math.floor(d6 + d10 / 2.0D);
            int j1 = (int) Math.floor(d7 + d11 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1) {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            world.setBlock(l1, i2, this.ore, this.toReplace);
                        }
                    }
                }
            }
        }
    }
}
