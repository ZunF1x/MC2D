package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.utils.GameState;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiManager {

    public final List<Gui> guis;

    public GuiManager() {
        this.guis = new ArrayList<>();
    }

    public void displayGuiScreen(Gui gui) {
        this.guis.add(gui);
        MC2D.instance.setGameState(GameState.INMENU);
        GL11.glTranslatef(0, 0, 0);
    }

    public void render() {
        for (int i = 0; i < guis.size(); i++) {
            Gui gui = guis.get(i);
            gui.render();
        }
    }

    public void update() {
        if (!guis.isEmpty()) {
            Gui gui = guis.get(guis.size() - 1);
            gui.update();
        }
    }
}
