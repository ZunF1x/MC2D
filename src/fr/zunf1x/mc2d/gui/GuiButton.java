package fr.zunf1x.mc2d.gui;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.graphics.Color;
import fr.zunf1x.mc2d.graphics.Renderer;
import fr.zunf1x.mc2d.graphics.Texture;

public class GuiButton {

    protected static final Texture BUTTON_TEXTURES = Texture.loadTexture("/widgets.png");

    public int width;
    /** Button height in pixels */
    public int height;
    /** The x position of this control. */
    public float x;
    /** The y position of this control. */
    public float y;
    /** The string displayed on this control. */
    public String displayString;
    public int id;
    /** True if this control is enabled, false to disable. */
    public boolean enabled;
    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean hovered;
    public int packedFGColour; //FML

    public GuiButton(int buttonId, float x, float y, String buttonText) {
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiButton(int buttonId, float x, float y, int widthIn, int heightIn, String buttonText) {
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.x = x;
        this.y = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    public void update() {

    }

    public void update(String text) {
        this.displayString = text;
    }

    public void render(int mouseX, int mouseY)
    {
        if (this.visible)
        {
            BUTTON_TEXTURES.bind();
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            Renderer.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, (float) this.width / 2, this.height);
            Renderer.drawTexturedModalRect(this.x + (float) this.width / 2, this.y, 200 - (float) this.width / 2, 46 + i * 20, (float) this.width / 2, this.height);
            int j = 14737632;
            BUTTON_TEXTURES.unbind();

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            MC2D.instance.font.drawCenteredString(this.x + (float) this.width / 2, this.y + (float) this.height / 2, 8, j, this.displayString);
        }
    }

    public GuiButton setEnabled(boolean enabled) {
        this.enabled = enabled;

        return this;
    }

    public boolean mousePressed(int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }
}
