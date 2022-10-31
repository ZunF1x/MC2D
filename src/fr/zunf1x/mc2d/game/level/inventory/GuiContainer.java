package fr.zunf1x.mc2d.game.level.inventory;

public class GuiContainer extends Gui {

    public Container container;

    public GuiContainer(Container container) {
        this.container = container;
    }

    public void update() {
        this.container.update();
    }

    public void updateSlots() {
        this.container.updateSlots();
    }

    public void render() {
        this.container.render();
    }
}
