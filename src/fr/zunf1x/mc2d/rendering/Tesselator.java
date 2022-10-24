package fr.zunf1x.mc2d.rendering;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;


public class Tesselator {

    private int vbo, cbo, tbo;

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;
    private final FloatBuffer textureBuffer;

    private int vertices;

    public Tesselator(int size) {
        this.vertexBuffer = BufferUtils.createFloatBuffer(size * 2);
        this.colorBuffer = BufferUtils.createFloatBuffer(size * 4);
        this.textureBuffer = BufferUtils.createFloatBuffer(size * 2);
    }

    public void vertex(float x, float y) {
        this.putInBuffer(this.vertexBuffer, x, y);
        vertices += 2;

        updateTesselator(false);
    }

    public void color(float r, float g, float b) {
        this.color(r, g, b, 1);
    }

    public void color(float r, float g, float b, float a) {
        this.putInBuffer(this.colorBuffer, r, g, b, a);
        vertices += 4;

        updateTesselator(false);
    }

    public void texture(float x, float y) {
        this.putInBuffer(this.textureBuffer, x, y);
        vertices += 2;

        updateTesselator(false);
    }

    public void draw() {

    }

    public void putInBuffer(FloatBuffer buffer, float... v) {
        for (Float f : v) {
            buffer.put(f);
        }
    }

    public void updateTesselator(boolean generate) {
        if (generate) {
            this.vbo = glGenBuffers();
            this.cbo = glGenBuffers();
            this.tbo = glGenBuffers();
        }

        glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
        glBufferData(GL_ARRAY_BUFFER, this.vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, this.cbo);
        glBufferData(GL_ARRAY_BUFFER, this.colorBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, this.tbo);
        glBufferData(GL_ARRAY_BUFFER, this.textureBuffer, GL_STATIC_DRAW);
    }
}
