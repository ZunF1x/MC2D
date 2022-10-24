package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import org.lwjgl.opengl.Display;

public class GuiLoadingScreen extends Gui {

    int i = 0;

    int a = 255;

    @Override
    public void update() {
        i++;

        if (i >= 50) {
            a -= 2;

            if (a <= 0) {
                this.close();
                Display.setResizable(true);
            }
        }
    }

    @Override
    public void render() {
        Renderer.drawColoredRect(0, 0, MC2D.instance.width, MC2D.instance.height, new Color(120, 120, 120, a, true));

        MC2D.instance.font.drawCenteredString("ZunF1x", (float) (MC2D.instance.width / 2), (float) (MC2D.instance.height / 2), 40, a);
    }
}
