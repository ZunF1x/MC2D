package fr.zunf1x.mc2d.game.level.inventory.items;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.ISpecialRender;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import fr.zunf1x.mc2d.rendering.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class ItemBlock extends Item {

    public Block block;

    public ItemBlock(Block block) {
        super(block.getTexture());

        this.block = block;
    }

    @Override
    public void render(Vector2d loc) {
        if (this.block instanceof ISpecialRender) {
            Texture.ITEMS.bind();

            Renderer.directTexturedCube(loc.getX(), loc.getY(), 16, 16, new Color4f(1, 1, 1), ((ISpecialRender) block).specialRender());

            Texture.ITEMS.unbind();
        } else {
            int xo = getTexture() % 16;
            int yo = getTexture() / 16;

            float x = (float) loc.getX() + 8;
            float y = (float) loc.getY() + 7;

            float w = 3.5F;
            float h = 3.5F;

            float v = y + h * 4 - h / 2f - h;

            Texture.BLOCKS.bind();

            glBegin(GL_QUADS);
            glColor3f(1, 1, 1);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x + w * 2, y - h);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x, y - h * 2);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x - w * 2, y - h);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x, y);

            glColor3f(0.69F, 0.69F, 0.69F);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x, y);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x - w * 2, y - h);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x - w * 2, y + h * 2 - h / 2f);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x, v);

            glColor3f(0.43F, 0.43F, 0.43F);
            glTexCoord2f(xo / 16F, yo / 16F);
            glVertex2f(x, y);
            glTexCoord2f((xo + 1) / 16F, yo / 16F);
            glVertex2f(x + w * 2, y - h);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F);
            glVertex2f(x + w * 2, y + h * 2 - h / 2f);
            glTexCoord2f(xo / 16F, (yo + 1) / 16F);
            glVertex2f(x, v);
            glEnd();

            Texture.BLOCKS.unbind();
        }
    }
}
