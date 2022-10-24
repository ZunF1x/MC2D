package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;
import fr.zunf1x.mc2d.utils.GameState;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GuiMainMenu extends Gui {

    private final float minceraftRoll;

    private static final Texture EDITION = Texture.loadTexture("/edition.png");

    private static final Random RANDOM = new Random();

    private String consoleStr;

    public GuiMainMenu() {
        this.minceraftRoll = RANDOM.nextFloat();
    }

    @Override
    public void addButtons() {
        int j = MC2D.instance.height / 4 + 48;

        if (MC2D.instance.console.isVisible()) {
            consoleStr = "Hide Console";
        } else {
            consoleStr = "Show Console";
        }

        this.buttonList.add(new GuiButton(0, (float) MC2D.instance.width / 2 - 100, j, " Singleplayer"));
        this.buttonList.add(new GuiButton(1, (float) MC2D.instance.width / 2 - 100, j + 24, "Multiplayer"));
        this.buttonList.add(new GuiButton(4, (float) MC2D.instance.width / 2 - 100, j + 48, consoleStr));

        this.buttonList.add(new GuiButton(2, (float) MC2D.instance.width / 2 - 100, j + 72 + 12, 98, 20, " Options"));
        this.buttonList.add(new GuiButton(3, (float) MC2D.instance.width / 2 + 2, j + 72 + 12, 98, 20, "Quit Game"));
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.close();
            MC2D.instance.guiManager.displayGuiScreen(new GuiChargingScreen(new String[] {"     Loading Terrain...", "     Charging Chunks..."}, 50) {
                @Override
                public void action() {
                    MC2D.instance.setGameState(GameState.INGAME);
                }
            });
        } else if (button.id == 4) {
            MC2D.instance.console.setVisible(!MC2D.instance.console.isVisible());
        } else if (button.id == 3) {
            MC2D.instance.stop();
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        for (int x = 0; x < (MC2D.instance.width + 28) / 28; x++) {
            for (int y = 0; y < (MC2D.instance.height + 28) / 28; y++) {
                Texture.blocksTextures.bind();
                Renderer.drawBlockOrItem(x * 28, y * 28, 28, 1);
                Texture.blocksTextures.unbind();
            }
        }

        Renderer.drawQuad(0, 0, MC2D.instance.width, MC2D.instance.height, new Color(0, 0, 0, 0.5F, false));

        int j = MC2D.instance.width / 2 - 137;

        Texture logo = Texture.loadTexture("/logo.png");

        logo.bind();

        if ((double)this.minceraftRoll < 1.0E-4D)
        {
            Renderer.drawTexturedModalRect(j, 30, 0, 0, 99, 44);
            Renderer.drawTexturedModalRect(j + 99, 30, 129, 0, 27, 44);
            Renderer.drawTexturedModalRect(j + 99 + 26, 30, 126, 0, 3, 44);
            Renderer.drawTexturedModalRect(j + 99 + 26 + 3, 30, 99, 0, 26, 44);
            Renderer.drawTexturedModalRect(j + 155, 30, 0, 45, 155, 44);
        }
        else
        {
            Renderer.drawTexturedModalRect(j, 30, 0, 0, 155, 44);
            Renderer.drawTexturedModalRect(j + 155, 30, 0, 45, 155, 44);
        }

        logo.unbind();

        EDITION.bind();

        Renderer.drawModalRectWithCustomSizedTexture(j + 99, 67, 0.0F, 0.0F, 76, 14, 128.0F, 16.0F);

        EDITION.unbind();

        super.render();
    }
}
