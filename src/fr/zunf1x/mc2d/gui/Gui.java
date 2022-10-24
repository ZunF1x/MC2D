package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

public abstract class Gui {

    List<GuiButton> buttonList;

    public Gui() {
        this.buttonList = new ArrayList<>();

        addButtons();
    }

    public void addButtons() {

    }

    int i = 0;

    public void update() {
        for (int i = 0; i < buttonList.size(); i++) {
            GuiButton button = buttonList.get(i);
            button.update();
        }

        this.buttonList.clear();
        this.addButtons();

        while (Mouse.next()) {
            if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                for (int i = 0; i < this.buttonList.size(); ++i) {
                    GuiButton button = this.buttonList.get(i);

                    if (button.mousePressed(MC2D.instance.game.getMouseX(false), MC2D.instance.game.getMouseY(false))) {
                        this.actionPerformed(button);
                    }
                }
            }
        }
    }

    public void actionPerformed(GuiButton button) {

    }

    public void render() {
        for (int i = 0; i < buttonList.size(); i++) {
            GuiButton button = buttonList.get(i);
            button.render(MC2D.instance.game.getMouseX(false), MC2D.instance.game.getMouseY(false));
        }
    }

    public void close() {
        MC2D.instance.guiManager.guis.remove(this);
    }
}
