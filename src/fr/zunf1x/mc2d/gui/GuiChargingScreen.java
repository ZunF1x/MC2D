package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public abstract class GuiChargingScreen extends Gui {

    public int loadingTime;
    public int prevLoadingTime;
    public String[] strings;

    private String text = "";

    private final int maxBarValue;

    public GuiChargingScreen(String[] strings, int loadingTime) {
        this.strings = strings;
        this.loadingTime = loadingTime;
        this.prevLoadingTime = loadingTime;

        this.maxBarValue = loadingTime;
    }

    int i = 0;
    int barValue = 0;

    public abstract void action();

    @Override
    public void update() {
        this.barValue++;

        this.loadingTime--;

        if (loadingTime <= 0) {
            this.loadingTime = this.prevLoadingTime;
            this.barValue = 0;

            if (strings.length < i + 2) {
                this.close();
                action();
            } else {
                i++;
            }
        }

        text = strings[i];

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

        MC2D.instance.font.drawCenteredString(text, (float) MC2D.instance.width / 2, (float) MC2D.instance.height / 2, 8, 255);

        int barWidth = (int) (100f / maxBarValue * barValue);

        Renderer.drawQuad((float) MC2D.instance.width / 2 - 50, (float) MC2D.instance.height / 2 + 20, 100, 2.5F, new Color(80, 80, 80, 255, true));
        Renderer.drawQuad((float) MC2D.instance.width / 2 - 50, (float) MC2D.instance.height / 2 + 20, barWidth, 2.5F, new Color(0, 255, 0, 255, true));

        super.render();
    }
}
