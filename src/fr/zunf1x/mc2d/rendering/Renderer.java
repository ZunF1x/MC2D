package fr.zunf1x.mc2d.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void directCube(double x, double y, double width, double height, Color4f color) {
        glBegin(GL_QUADS);
        glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
        glVertex2d(x, y);
        glVertex2d(x + width, y);
        glVertex2d(x + width, y + height);
        glVertex2d(x, y + height);
        glColor4f(1, 1, 1, 1);
        glEnd();
    }

    public static void directTexturedCube(double x, double y, Color4f color, int texture) {
        directTexturedCube(x, y, 1, 1, color, texture);
    }

    public static void directTexturedCube(double x, double y, double width, double height, Color4f color, int texture) {
        int xo = texture % 16;
        int yo = texture / 16;

        if (texture == 404) {
            Texture.BLOCKS.unbind();
            directCube(x, y, 0.5F, 0.5F, new Color4f(1, 0, 1));
            directCube(x + 0.5F, y + 0.5F, 0.5F, 0.5F, new Color4f(1, 0, 1));
            directCube(x + 0.5F, y, 0.5F, 0.5F, new Color4f(0, 0, 0));
            directCube(x, y + 0.5F, 0.5F, 0.5F, new Color4f(0, 0, 0));
        } else {
            glBegin(GL_QUADS);
            glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
            glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x, y);
            glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x + width, y);
            glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x + width, y + height);
            glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x, y + height);
            glColor4f(1, 1, 1, 1);
            glEnd();
        }
    }

    public static void directTexturedCube(double x, double y, Color4f color, int texture, boolean reversedSide, boolean reversedTop) {
        directTexturedCube(x, y, 1, 1, color, texture, reversedSide, reversedTop);
    }

    public static void directTexturedCube(double x, double y, double width, double height, Color4f color, int texture, boolean reversedSide, boolean reversedTop) {
        int xo = texture % 16;
        int yo = texture / 16;

        if (texture == 404) {
            Texture.BLOCKS.unbind();
            directCube(x, y, 0.5F, 0.5F, new Color4f(1, 0, 1));
            directCube(x + 0.5F, y + 0.5F, 0.5F, 0.5F, new Color4f(1, 0, 1));
            directCube(x + 0.5F, y, 0.5F, 0.5F, new Color4f(0, 0, 0));
            directCube(x, y + 0.5F, 0.5F, 0.5F, new Color4f(0, 0, 0));
        } else {
            if (!reversedSide) {
                if (!reversedTop) {
                    glBegin(GL_QUADS);
                    glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
                    glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x, y);
                    glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x + width, y);
                    glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x + width, y + height);
                    glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x, y + height);
                    glColor4f(1, 1, 1, 1);
                    glEnd();
                } else {
                    glBegin(GL_QUADS);
                    glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
                    glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x, y);
                    glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x + width, y);
                    glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x + width, y + height);
                    glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x, y + height);
                    glColor4f(1, 1, 1, 1);
                    glEnd();
                }
            } else {
                if (!reversedTop) {
                    glBegin(GL_QUADS);
                    glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
                    glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x, y);
                    glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x + width, y);
                    glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x + width, y + height);
                    glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x, y + height);
                    glColor4f(1, 1, 1, 1);
                    glEnd();
                } else {
                    glBegin(GL_QUADS);
                    glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
                    glTexCoord2f((xo + 1) / 16F, (yo + 1) / 16F); glVertex2d(x, y);
                    glTexCoord2f(xo / 16F, (yo + 1) / 16f); glVertex2d(x + width, y);
                    glTexCoord2f(xo / 16F, yo / 16F); glVertex2d(x + width, y + height);
                    glTexCoord2f((xo + 1) / 16F, yo / 16F); glVertex2d(x, y + height);
                    glColor4f(1, 1, 1, 1);
                    glEnd();
                }
            }
        }
    }

    public static void drawEntity(double x, double y, int texture) {
        float width = 1;
        float height = 2;

        int xo = texture % 16;
        int yo = texture / 8;

        glBegin(GL_QUADS);
        glColor4f(1, 1, 1, 1);
        glTexCoord2f(xo / 16f, yo / 8f); glVertex2d(x, y);
        glTexCoord2f((xo + 1) / 16f, yo / 8f); glVertex2d(x + width, y);
        glTexCoord2f((xo + 1) / 16f, (yo + 1) / 8f); glVertex2d(x + width, y + height);
        glTexCoord2f(xo / 16f, (yo + 1) / 8f); glVertex2d(x, y + height);
        glEnd();
    }

    public static void drawGuiInventory(double x, double y, double u, double v, double width, double height) {
        glBegin(GL_QUADS);
        glColor4f(1, 1, 1, 1);
        glTexCoord2d(u / 256F, v / 256F); glVertex2d(x, y);
        glTexCoord2d((u + width) / 256F, v / 256F); glVertex2d(x + width, y);
        glTexCoord2d((u + width) / 256F, (v + height) / 256F); glVertex2d(x + width, y + height);
        glTexCoord2d(u / 256F, (v + height) / 256F); glVertex2d(x, y + height);
        glEnd();
    }
}
