package fr.zunf1x.mc2d.game.level.blocks.placing;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.blocks.blocks.Block;
import fr.zunf1x.mc2d.game.level.items.items.Item;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public class BlockBackgroundPlacer extends BlockPlacer {

    public BlockBackgroundPlacer(int x, int y, Block block) {
        super(x, y, block);
    }

    @Override
    public void render() {
        super.render();

        float x0 = x + MC2D.instance.game.xScroll / size;
        float y0 = y + MC2D.instance.game.yScroll / size;

        float x1 = x + size + MC2D.instance.game.xScroll / size;
        float y1 = y + size + MC2D.instance.game.yScroll / size;

        if (x1 < 0 || y1 < 0 || x0 > (float) MC2D.instance.width / size || y0 > (float) MC2D.instance.height / size) return;
        Renderer.drawColoredRect(x * size, y * size, size, size, new Color(0, 0, 0, 0.5F, false));
    }
}
