package fr.zunf1x.mc2d.game.gui.inventory;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.level.items.utils.ItemStack;
import fr.zunf1x.mc2d.game.level.items.utils.Slot;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

public abstract class Inventory {

    private final List<ItemStack> stacks;
    private final List<Slot> slots;

    public ItemStack currentStack;

    public float gx, gy;
    public float guiWidth, guiHeight;
    public float guiX, guiY;
    public float mouseX, mouseY;

    public Inventory(int stacksNumber) {
        this.stacks = new ArrayList<>();
        this.slots = new ArrayList<>();

        this.currentStack = ItemStack.EMPTY;

        this.guiWidth = 176;
        this.guiHeight = 166;

        this.guiX = ((float) (Display.getWidth() / 2) / 2) - (guiWidth / 2);
        this.guiY = ((float) (Display.getHeight() / 2) / 2) - (guiHeight / 2);

        init();

        for (int i = 0; i < stacksNumber; i++) {
            this.stacks.add(ItemStack.EMPTY);
            this.slots.get(i).setStackInSlot(this.stacks.get(i));
        }
    }

    public void update() {
        this.gx = (int) -MC2D.instance.game.xScroll;
        this.gy = (int) -MC2D.instance.game.yScroll;

        this.guiX = gx + ((float) (Display.getWidth() / 2) / 2) - (guiWidth / 2);
        this.guiY = gy + ((float) (Display.getHeight() / 2) / 2) - (guiHeight / 2);

        this.mouseX = MC2D.instance.game.getMouseX(true);
        this.mouseY = MC2D.instance.game.getMouseY(true);

        this.slots.clear();
        this.init();

        for (int i = 0; i < slots.size(); i++) {
            this.slots.get(i).setStackInSlot(this.stacks.get(i));
        }

        for (int i = 0; i < 36; i++) {
            MC2D.instance.game.player.inventory.getStacks().set(i, stacks.get(i));
        }

        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    for (int i = 0; i < this.slots.size(); i++) {
                        ItemStack s = this.stacks.get(i);

                        if (this.slots.get(i).mouseHover(mouseX, mouseY)) {
                            if (s != ItemStack.EMPTY) {
                                if (currentStack != ItemStack.EMPTY) {
                                    if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                        if (s.getItem() == currentStack.getItem() && s.getStackSize() + currentStack.getStackSize() <= 64) {
                                            s.incrStackSize(currentStack.getStackSize());
                                            currentStack = ItemStack.EMPTY;
                                        } else if (s.getItem() == currentStack.getItem()) {
                                            int n = 64 - s.getStackSize();

                                            s.incrStackSize(n);
                                            currentStack.decrStackSize(n);
                                        } else {
                                            ItemStack sh = s.copy();
                                            this.stacks.set(i, currentStack.copy());
                                            currentStack = sh;
                                        }
                                    }
                                } else {
                                    if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                        currentStack = s.copy();
                                        this.stacks.set(i, ItemStack.EMPTY);
                                    }
                                }
                            } else {
                                if (currentStack != ItemStack.EMPTY) {
                                    if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                        this.stacks.set(i, currentStack.copy());
                                        currentStack = ItemStack.EMPTY;
                                    }
                                }
                            }
                        }
                    }
                }

                if (Mouse.getEventButton() == 1) {
                    for (int i = 0; i < this.slots.size(); i++) {
                        ItemStack s = this.stacks.get(i);

                        if (this.slots.get(i).mouseHover(mouseX, mouseY)) {
                            if (s != ItemStack.EMPTY) {
                                if (currentStack != ItemStack.EMPTY) {
                                    if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                        if (s.getItem() == currentStack.getItem() && s.getStackSize() < 64) {
                                            s.incrStackSize(1);

                                            if (currentStack.getStackSize() > 1) {
                                                currentStack.decrStackSize(1);
                                            } else {
                                                currentStack = ItemStack.EMPTY;
                                            }
                                        }
                                    } else {
                                        if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                            ItemStack sh = s.copy();
                                            this.stacks.set(i, currentStack.copy());
                                            currentStack = sh;
                                        }
                                    }
                                } else {
                                    if (s.getStackSize() % 2 == 0) {
                                        if (s.getStackSize() > 1) {
                                            currentStack = new ItemStack(s.getItem(), s.getStackSize() / 2);
                                            stacks.set(i, new ItemStack(s.getItem(), s.getStackSize() / 2));
                                        } else {
                                            ItemStack sh = s.copy();
                                            stacks.set(i, ItemStack.EMPTY);
                                            currentStack = sh;
                                        }
                                    } else {
                                        if (s.getStackSize() > 1) {
                                            currentStack = new ItemStack(s.getItem(), (s.getStackSize() / 2) + 1);
                                            stacks.set(i, new ItemStack(s.getItem(), s.getStackSize() / 2));
                                        } else {
                                            ItemStack sh = s.copy();
                                            stacks.set(i, ItemStack.EMPTY);
                                            currentStack = sh;
                                        }
                                    }

                                }
                            } else {
                                if (currentStack != ItemStack.EMPTY) {
                                    if (this.slots.get(i).isItemValidForSlot(currentStack)) {
                                        this.stacks.set(i, new ItemStack(currentStack.getItem(), 1));

                                        if (currentStack.getStackSize() > 1) {
                                            currentStack.decrStackSize(1);
                                        } else {
                                            currentStack = ItemStack.EMPTY;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (Mouse.getEventButton() == 2) {
                    for (int i = 0; i < this.slots.size(); i++) {
                        ItemStack s = this.stacks.get(i);

                        if (this.slots.get(i).mouseHover(mouseX, mouseY)) {
                            if (s != ItemStack.EMPTY) {
                                if (currentStack == ItemStack.EMPTY) {
                                    currentStack = new ItemStack(s.copy().getItem(), 64);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void init() {}

    public void addSlotInInventory(Slot slot) {
        this.slots.add(slot);
    }

    public ItemStack getStackInInventory(int index) {
        return this.stacks.get(index);
    }

    public void setStackInInventory(int index, ItemStack stack) {
        this.stacks.set(index, stack);
    }

    public List<ItemStack> getStacks() {
        return this.stacks;
    }

    public ItemStack getCurrentStack() {
        return this.currentStack;
    }

    public List<Slot> getSlots() {
        return this.slots;
    }
}
