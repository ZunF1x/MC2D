package fr.zunf1x.mc2d.rendering;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class Texture {

    public static final Texture BLOCKS = Texture.loadTexture("blocks");
    public static final Texture ENTITIES = Texture.loadTexture("entities");

    private int width, height;
    private int id;

    public Texture(int width, int height, int id) {
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public static Texture loadTexture(String texture) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(Texture.class.getResource("/" + texture + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = pixels[x + y * width];
                buffer.put((byte) ((i >> 16) & 0xFF));
                buffer.put((byte) ((i >> 8) & 0xFF));
                buffer.put((byte) (i & 0xFF));
                buffer.put((byte) ((i >> 24) & 0xFF));
            }
        }

        buffer.flip();

        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return new Texture(width, height, id);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
