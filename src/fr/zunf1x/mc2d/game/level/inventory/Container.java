package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.Start;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.math.vectors.Vector2d;
import fr.zunf1x.mc2d.rendering.Color4f;
import fr.zunf1x.mc2d.rendering.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

public abstract class Container {

    public List<Slot> containerSlots;
    public int guiTop;
    public int guiLeft;
    public int xSize;
    public int ySize;
    public Inventory inventory;

    public ItemStack currentStack;
    private EntityPlayer player;

    public Container(Inventory inventory) {
        this.containerSlots = new ArrayList<>();
        this.inventory = inventory;
        this.player = inventory.player;

        this.xSize = 176;
        this.ySize = 166;
        this.guiLeft = (Display.getWidth() / 2 / 2) - (xSize / 2);
        this.guiTop = (Display.getHeight() / 2 / 2) - (ySize / 2);

        this.currentStack = ItemStack.EMPTY;

        this.init();
    }

    public void init() {}

    public void addSlotToContainer(Slot s) {
        s.setX(guiLeft + s.getX());
        s.setY(guiTop + s.getY());

        this.containerSlots.add(s);
    }

    public void update() {
        if (this.inventory instanceof ITickable) {
            ((ITickable) this.inventory).update();
        }

        this.guiLeft = ((Display.getWidth() / Start.getInstance().getGame().getScale()) / 2) - (xSize / 2);
        this.guiTop = ((Display.getHeight() / Start.getInstance().getGame().getScale()) / 2) - (ySize / 2);

        if (Keyboard.isKeyDown(Keyboard.KEY_K)) this.inventory.setInventorySlotContents(0, new ItemStack(Blocks.getItemBlock(Blocks.CRAFTING_TABLE), 1));

        this.containerSlots.clear();
        this.init();
    }

    public void updateSlots() {
        for (Slot slot : containerSlots) {
            float x = slot.getX();
            float y = slot.getY();

            if (mouseHover(x, y)) {
                if (Mouse.getEventButton() == 0) {
                    if (!slot.getStack().isEmpty()) {
                        slot.onTake(this.player, slot.getStack());
                    }

                    if (currentStack.isEmpty()) {
                        ItemStack s = slot.getStack().copy();
                        if (slot.canTakeStack(this.player)) {
                            slot.putStack(ItemStack.EMPTY);
                        }
                        currentStack = s;
                    } else {
                        if (slot.getStack().getItem() != null) {
                            if (currentStack.getItem() == slot.getStack().getItem()) {
                                int n = slot.getStack().getCount() + currentStack.getCount();

                                if (this.inventory.isItemValidForSlot(slot.getIndex(), currentStack.copy())) {
                                    if (slot.canTakeStack(this.player)) {
                                        if (n <= 64) {
                                            slot.getStack().grow(currentStack.getCount());
                                            currentStack = ItemStack.EMPTY;
                                        } else {
                                            int min = Math.min(slot.getStack().getCount(), currentStack.getCount());
                                            int max = Math.max(slot.getStack().getCount(), currentStack.getCount());
                                            currentStack.shrink(max - min);
                                            slot.getStack().grow(n);
                                        }
                                    } else {
                                        currentStack.grow(slot.getStack().getCount());
                                    }
                                }
                            } else {
                                if (this.inventory.isItemValidForSlot(slot.getIndex(), currentStack.copy())) {
                                    if (slot.canTakeStack(this.player)) {
                                        ItemStack s = currentStack.copy();
                                        currentStack = slot.getStack().copy();
                                        slot.putStack(s);
                                    } else {
                                        currentStack = ItemStack.EMPTY;
                                    }
                                }
                            }
                        } else {
                            if (this.inventory.isItemValidForSlot(slot.getIndex(), currentStack.copy())) {
                                if (slot.canTakeStack(this.player)) {
                                    if (this.inventory.isItemValidForSlot(slot.getIndex(), currentStack)) {
                                        ItemStack s = currentStack.copy();
                                        currentStack = ItemStack.EMPTY;
                                        slot.putStack(s);
                                    }
                                } else {
                                    if (slot.deleteStack()) {
                                        currentStack = ItemStack.EMPTY;
                                    }
                                }
                            }
                        }
                    }
                }

                if (Mouse.getEventButton() == 1) {
                    if (!slot.getStack().isEmpty()) {
                        slot.onTake(this.player, slot.getStack());
                    }

                    if (currentStack.isEmpty()) {
                        if (slot.canTakeStack(this.player)) {
                            if (slot.getStack().getCount() > 1) {
                                float splitStack = slot.getStack().getCount() / 2F;
                                int currentStackCount = (int) Math.ceil(splitStack);
                                int slotStackCount = (int) Math.floor(splitStack);

                                slot.getStack().setCount(slotStackCount);
                                this.currentStack = slot.getStack().copy().setCount(currentStackCount);
                            } else {
                                this.currentStack = slot.getStack().copy();
                                slot.putStack(ItemStack.EMPTY);
                            }
                        } else {
                            currentStack = slot.getStack().copy();
                        }
                    } else {
                        if (this.inventory.isItemValidForSlot(slot.getIndex(), this.currentStack.copy())) {
                            if (slot.canTakeStack(this.player)) {
                                if (slot.getStack().getItem() != null) {
                                    if (currentStack.getItem() == slot.getStack().getItem()) {
                                        currentStack.shrink(1);
                                        slot.getStack().grow(1);
                                    } else {
                                        ItemStack s = currentStack.copy();
                                        currentStack = slot.getStack().copy();
                                        slot.putStack(s);
                                    }
                                } else {
                                    slot.putStack(currentStack.copy().setCount(1));
                                    currentStack.shrink(1);
                                }
                            } else {
                                if (slot.deleteStack()) {
                                    currentStack = ItemStack.EMPTY;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void render() {
        for (Slot s : containerSlots) {
            float x = s.getX();
            float y = s.getY();

            if (s.getHasStack()) s.getStack().getItem().render(new Vector2d(x, y));

            if (mouseHover(x, y)) Renderer.directCube(x, y, 16, 16, new Color4f(1, 1, 1, 0.5F));

            if (s.getHasStack() && s.getStack().getCount() > 1) Start.getInstance().getFont().drawCenteredStringWithShadow("" + s.getStack().getCount(), x + 10, y + 12, 1, 1);
        }

        float mouseX = Start.getInstance().getGame().getMouseX(false) - 8;
        float mouseY = Start.getInstance().getGame().getMouseY(false) - 8;

        if (!currentStack.isEmpty()) {
            currentStack.getItem().render(new Vector2d(mouseX, mouseY));
            if (currentStack.getCount() > 1) Start.getInstance().getFont().drawCenteredStringWithShadow("" + currentStack.getCount(), mouseX + 10, mouseY + 12, 1, 1);
        }
    }

    public boolean mouseHover(float slotX, float slotY) {
        float mouseX = Start.getInstance().getGame().getMouseX(false);
        float mouseY = Start.getInstance().getGame().getMouseY(false);

        return mouseX >= slotX && mouseX <= slotX + 16 && mouseY >= slotY && mouseY <= slotY + 16;
    }
}
