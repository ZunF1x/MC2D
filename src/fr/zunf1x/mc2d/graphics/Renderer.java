package fr.zunf1x.mc2d.graphics;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.items.items.DescriptionElement;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private static void coloredQuadData(float x, float y, float width, float height, Color color) {
        glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
    }

    @Deprecated
    private static void texturedQuadData(float x, float y, float width, float height) {
        MC2D.instance.logger.err("texturedQuadData() function is deprecated and not implemented yet !");
    }

    public static void drawEntity(float x, float y, float width, float height, int index, boolean up) {
        int xo;
        int yo;

        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);

        if (up) {
            xo = index % 16;
            yo = index / 8;

            glTexCoord2f(xo / 16.0F, yo / 8.0F); glVertex2f(x, y);
            glTexCoord2f((1 + xo) / 16.0F, yo / 8.0F); glVertex2f(x + width, y);
            glTexCoord2f((1 + xo) / 16.0F, (1 + yo) / 8.0F); glVertex2f(x + width, y + height);
            glTexCoord2f(xo / 16.0F, (1 + yo) / 8.0F); glVertex2f(x, y + height);
        } else {
            xo = index % 8;
            yo = index / 16;

            glTexCoord2f(xo / 8.0F, yo / 16.0F); glVertex2f(x, y);
            glTexCoord2f((1 + xo) / 8.0F, yo / 16.0F); glVertex2f(x + width, y);
            glTexCoord2f((1 + xo) / 8.0F, (1 + yo) / 16.0F); glVertex2f(x + width, y + height);
            glTexCoord2f(xo / 8.0F, (1 + yo) / 16.0F); glVertex2f(x, y + height);
        }

        glEnd();
    }

    public static void drawHoveringBoxText(float x, float y, List<DescriptionElement> descriptionElementList) {
        float width;
        float height;

        String[] texts = new String[descriptionElementList.size()];
        Color[] colors = new Color[descriptionElementList.size()];

        for (int i = 0; i < descriptionElementList.size(); i++) {
            DescriptionElement de = descriptionElementList.get(i);
            texts[i] = de.textElement;
            colors[i] = de.color;
        }

        List<String> txtList = new ArrayList<>();

        for (int i = 0; i < descriptionElementList.size(); i++) {
            DescriptionElement de = descriptionElementList.get(i);
            txtList.add(de.textElement);
        }

        String txt = max(txtList);

        width = ((txt.length() * 8) * 0.75F) + 8;

        height = texts.length * 10 + 4;

        for (int i = 0; i < height; i++) {
            Renderer.drawQuad(x, y + 1, 1, i, new Color(27, 12, 27, 255, true));
            Renderer.drawQuad(x + width, y + 1, 1, i, new Color(27, 12, 27, 255, true));
        }

        for (int i = 0; i < width; i++) {
            Renderer.drawQuad(x + 1, y, i, 1, new Color(27, 12, 27, 255, true));
            Renderer.drawQuad(x + 1, y + height, i, 1, new Color(27, 12, 27, 255, true));
        }

        for (int i = 0; i < height; i++) {
            Renderer.drawQuad(x + 1, y + 1, 1, i, new Color(37, 2, 92, 255, true));
            Renderer.drawQuad(x + width - 1, y + 1, 1, i, new Color(37, 2, 92, 255, true));
        }

        for (int i = 0; i < width; i++) {
            Renderer.drawQuad(x + 2, y + 1, i - 1, 1, new Color(37, 2, 92, 255, true));
            Renderer.drawQuad(x + 2, y + height - 1, i - 1, 1, new Color(37, 2, 92, 255, true));
        }

        Renderer.drawQuad(x + 2, y + 2, width - 3, height - 3, new Color(27, 12, 27, 255, true));

        for (int i = 0; i < texts.length; i++) {
            MC2D.instance.font.drawString(texts[i], (x + 4), (y + 4) + (i * 10), 8, colors[i]);
        }
    }

    public static void drawBlockOrItem(float x, float y, float size, int index) {
        int xo = index % 16;
        int yo = index / 16;

        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);
            glTexCoord2f(xo / 16.0F, yo / 16.0F); glVertex2f(x, y);
            glTexCoord2f((1 + xo) / 16.0F, yo / 16.0F); glVertex2f(x + size, y);
            glTexCoord2f((1 + xo) / 16.0F, (1 + yo) / 16.0F); glVertex2f(x + size, y + size);
            glTexCoord2f(xo / 16.0F, (1 + yo) / 16.0F); glVertex2f(x, y + size);
        glEnd();
    }

    public static void drawQuad(float x, float y, float width, float height, Color color) {
        glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + width, y);
            glVertex2f(x + width, y + height);
            glVertex2f(x, y + height);
        glEnd();
    }

    public static void drawTexturedModalRect(float x, float y, float textureX, float textureY, float width, float height) {
        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);
            glTexCoord2f((textureX * 0.00390625F), (textureY * 0.00390625F)); glVertex2f((x), (y));
            glTexCoord2f(((textureX + width) * 0.00390625F), (textureY * 0.00390625F)); glVertex2f((x + width), (y));
            glTexCoord2f(((textureX + width) * 0.00390625F), ((textureY + height) * 0.00390625F)); glVertex2f((x + width), (y + height));
            glTexCoord2f((textureX * 0.00390625F), ((textureY + height) * 0.00390625F)); glVertex2f((x), (y + height));
        glEnd();
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);
            float f = 1.0F / textureWidth;
            float f1 = 1.0F / textureHeight;
            glTexCoord2f((u * f), (v * f1)); glVertex2f(x, y);
            glTexCoord2f(((u + width) * f), (v * f1)); glVertex2f((x + width), y);
            glTexCoord2f(((u + width) * f), ((v + height) * f1)); glVertex2f((x + width), (y + height));
            glTexCoord2f((u * f), ((v + height) * f1)); glVertex2f(x, (y + height));
        glEnd();
    }

    public static void drawBigButton(float x, float y, int index) {
        if (index < 0 || index >= 3) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Texture bigButtons = Texture.loadTexture("/buttons.png");

        bigButtons.bind();

        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);
            glTexCoord2f(0, index / 3.0F); glVertex2f(x, y);
            glTexCoord2f(1, index / 3.0F); glVertex2f(x + 200, y);
            glTexCoord2f(1, (1 + index) / 3.0F); glVertex2f(x + 200, y + 20);
            glTexCoord2f(0, (1 + index) / 3.0F); glVertex2f(x, y + 20);
        glEnd();

        bigButtons.unbind();
    }

    public static void drawChar(float x, float y, float size, int index, Color color) {
        int xo = index % 16;
        int yo = index / 16;

        glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

        glBegin(GL_QUADS);
            glTexCoord2f(xo / 16.0F, yo / 16.0F); glVertex2f(x, y);
            glTexCoord2f((1 + xo) / 16.0F, yo / 16.0F); glVertex2f(x + size, y);
            glTexCoord2f((1 + xo) / 16.0F, (1 + yo) / 16.0F); glVertex2f(x + size, y + size);
            glTexCoord2f(xo / 16.0F, (1 + yo) / 16.0F); glVertex2f(x, y + size);
        glEnd();
    }

    public static void drawColoredRect(float x, float y, float width, float height, Color color) {
        glBegin(GL_QUADS);
            coloredQuadData(x, y, width, height, color);
        glEnd();
    }

    public static String max(List<String> list) {
        String result = "";

        int len = 0;

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);

            if (str.length() > len) {
                len = str.length();
                result = str;
            }
        }

        return result;
    }
}
