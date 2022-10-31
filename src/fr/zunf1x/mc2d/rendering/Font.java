package fr.zunf1x.mc2d.rendering;

import fr.zunf1x.mc2d.math.Mathf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

public class Font {

    private Texture fontTexture;

    private int[] fontSizeArray;

    public Font() {
        this.fontTexture = Texture.loadTexture("font");

        this.fontSizeArray = new int[256];

        for (int i = 0; i < 256; i++) {
            this.fontSizeArray[i] = getSizeForChar((char) i);
        }
    }

    public void drawCenteredStringWithShadow(String msg, float x, float y, float size, float offset) {
        this.drawStringWithShadow(msg, x - (getStringWidth(msg) / 2F), y - 4, size, offset);
    }

    public void drawCenteredStringWithShadow(String msg, float x, float y, float size, float offset, Color4f color) {
        this.drawStringWithShadow(msg, x - (getStringWidth(msg) / 2F), y - 4, size, offset, color);
    }

    public void drawCenteredStringWithShadow(String msg, float x, float y, float size, float offset, int color) {
        Color4f c = new Color4f((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF);

        this.drawStringWithShadow(msg, x - (getStringWidth(msg) / 2F), y - 4, size, offset, c);
    }

    public void drawCenteredString(String msg, float x, float y, float size, float offset) {
        this.drawString(msg, x - (getStringWidth(msg) / 2F), y - 4, size, offset);
    }

    public void drawCenteredString(String msg, float x, float y, float size, float offset, Color4f color) {
        this.drawString(msg, x - (getStringWidth(msg) / 2F), y - 4, size, offset, color);
    }

    public int getStringWidth(String v) {
        char[] cs = v.toCharArray();
        int i = 0;

        for (char aChar : cs) {
            i += this.fontSizeArray[aChar];
        }

        return i;
    }

    public void drawStringWithShadow(String msg, float x, float y, float size, float offset) {
        this.drawStringWithShadow(msg, x, y, size, offset, new Color4f(1, 1, 1));
    }

    public void drawStringWithShadow(String msg, float x, float y, float size, float offset, int color) {
        Color4f c = new Color4f((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF);

        this.drawStringWithShadow(msg, x, y, size, offset, c);
    }

    public void drawStringWithShadow(String msg, float x, float y, float size, float offset, Color4f color) {
        this.drawString(msg, x + size, y + size, size, offset, new Color4f(0.2F, 0.2F, 0.2F));
        this.drawString(msg, x, y, size, offset, color);
    }

    public void drawString(String msg, float x, float y, float size, float offset) {
        this.drawString(msg, x, y, size, offset, new Color4f(1, 1, 1, 1));
    }

    public void drawString(String msg, float x, float y, float size, float offset, Color4f color) {
        char[] chars = msg.toCharArray();

        float xo = x * (1F / size);
        float yo = y * (1F / size);

        float s = (float) Mathf.clamp(size, 0.5F, 8.0F);

        for (char c : chars) {
            glPushMatrix();
            glScalef(s, s, s);
            drawChar(xo, yo, c, color);
            glPopMatrix();

            xo += fontSizeArray[c] + offset;
        }
    }

    public void drawChar(float x, float y, char c) {
        this.drawChar(x, y, c, new Color4f(1, 1, 1, 1));
    }

    public void drawChar(float x, float y, char c, Color4f color) {
        int xo = (c % 16);
        int yo = (c / 16);

        this.fontTexture.bind();

        glBegin(GL_QUADS);

        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glTexCoord2f(xo / 16F, yo / 16F); glVertex2f(x, y);
        glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2f(x + 8, y);
        glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2f(x + 8, y + 8);
        glTexCoord2f(xo / 16F, (yo + 1) / 16F); glVertex2f(x, y + 8);

        glEnd();

        this.fontTexture.unbind();
    }

    public int getSizeForChar(char c) {
        int charWidth = 0;

        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(Font.class.getResource("/font.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int xo = (c % 16) * 8;
        int yo = (c / 16) * 8;

        int width = 8;
        int height = 8;
        int[] pixels = new int[width * height];
        image.getRGB(xo, yo, width, height, pixels, 0, width);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = pixels[x + y * width];
                if (i == 0xFFffffff) {
                    charWidth = x;
                    break;
                }
            }
        }

        return c == ' ' ? 3 : charWidth + 1;
    }
}
