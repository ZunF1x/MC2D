package fr.zunf1x.mc2d.graphics;

import fr.zunf1x.mc2d.MC2D;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Font {

    private int[] charWidths = new int[256];

    public Font() {

    }

    public void drawString(String str, float x, float y, float size, int a) {
        char[] chars = str.toCharArray();

        drawShadow(str, x + (size / 8), y + (size / 8), size, a);

        for (int i = 0; i < chars.length; i++) {
            Texture.font_textures.bind();
            Renderer.drawChar(x + (i * (size * 0.75F)), y, size, chars[i], new Color(255, 255, 255, a, true));
            Texture.font_textures.unbind();
        }
    }

    public void drawString(String str, float x, float y, float size, Color color) {
        char[] chars = str.toCharArray();

        if (color.high) {
            drawShadow(str, x + (size / 8), y + (size / 8), size, (int) color.getAlpha());
        } else {
            drawShadow(str, x + (size / 8), y + (size / 8), size, (int) color.getAlpha() * 255);
        }

        for (int i = 0; i < chars.length; i++) {
            Texture.font_textures.bind();
            Renderer.drawChar(x + (i * (size * 0.75F)), y, size, chars[i], color);
            Texture.font_textures.unbind();
        }
    }

    public void drawCenteredString(String str, float x, float y, float size, int a) {
        drawString(str, x - ((size * (float) (str.length() / 2)) - (size * 0.75F)), y - (size / 2), size, a);
    }

    public void drawCenteredString(float x, float y, float size, int color, String str) {
        drawString(str, x - ((size * (float) (str.length() / 2)) - (size * 0.75F)), y - (size / 2), size, color);
    }

    private void drawShadow(String str, float x, float y, float size, int a) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            Texture.font_textures.bind();
            Renderer.drawChar(x + (i * (size * 0.75F)), y, size, chars[i], new Color(50, 50, 50, a, true));
            Texture.font_textures.unbind();
        }
    }
}
