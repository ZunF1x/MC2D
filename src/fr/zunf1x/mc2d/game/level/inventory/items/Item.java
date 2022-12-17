package fr.zunf1x.mc2d.game.level.inventory.items;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

public class Item {

    private final int texture;

    public Item(int texture) {
        this.texture = texture;
    }

    public int getTexture() {
        return texture;
    }

    public static Item getItemFromBlock(Block block) {
        return Blocks.getItemBlock(block);
    }

    public void render(Vector2d loc) {
        Texture.ITEMS.bind();

        Renderer.directTexturedCube(loc.getX(), loc.getY(), 16, 16, new Color4f(1, 1, 1), this.getTexture());

        Texture.ITEMS.unbind();
    }
}
