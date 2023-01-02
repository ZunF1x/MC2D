package fr.zunf1x.mc2d.game.level.world.environment;

import static org.lwjgl.opengl.GL11.glClearColor;

public class Skybox {

    private float r, g, b;

    private int tickTime;

    public Skybox(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.tickTime = 0;
    }

    public void updateSkybox() {
        this.tickTime++;

        // 36000 ticks = 10minutes -> one cycle day : 20 minutes = jour / nuit
    }

    public void renderSkybox() {
        glClearColor(r, g, b, 1F);
    }
}
