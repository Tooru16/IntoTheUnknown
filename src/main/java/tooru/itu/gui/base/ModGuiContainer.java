package tooru.itu.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import tooru.itu.consts.GuiConsts;
import tooru.itu.tileentities.base.InventoryTileEntity;

public class ModGuiContainer<T extends InventoryTileEntity> extends GuiContainer {

    private IInventory inventory;
    private T tile;

    private int hiddenSlotX;
    private int hiddenSlotY;

    private int slotX;
    private int slotY;

    public <U extends Container> ModGuiContainer(U container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // NO-OP
    }

    // GET
    public IInventory getInventory() {
        return this.inventory;
    }
    public T getTile() {
        return this.tile;
    }

    public int getHiddenSlotX() {
        return this.hiddenSlotX;
    }
    public int getHiddenSlotY() {
        return this.hiddenSlotY;
    }

    public int getSlotX() {
        return this.slotX;
    }
    public int getSlotY() {
        return this.slotY;
    }

    // SET
    public void setInventory(IInventory inventory) {
        this.inventory = inventory;
    }

    public void setTile(T tile) {
        this.tile = tile;
    }

    public void setHiddenSlotXY(int x, int y) {
        this.hiddenSlotX = x;
        this.hiddenSlotY = y;
    }

    public void setSlotXY(int x, int y) {
        this.slotX = x;
        this.slotY = y;
    }

    public void setSizeXY(int x, int y) {
        this.xSize = x;
        this.ySize = y;
    }

    // OTHER

    public void hideSlot(int index) {
        if (inventorySlots.inventorySlots.size() > index) {
            if (inventorySlots.getSlot(index).xDisplayPosition >= 0) {
                inventorySlots.getSlot(index).xDisplayPosition = inventorySlots.getSlot(index).xDisplayPosition - GuiConsts.X_HIDE;
            }
        }
    }

    public void showSlot(int index) {
        if (inventorySlots.inventorySlots.size() > index) {
            if (inventorySlots.getSlot(index).xDisplayPosition < 0) {
                inventorySlots.getSlot(index).xDisplayPosition = inventorySlots.getSlot(index).xDisplayPosition + GuiConsts.X_HIDE;
            }
        }
    }
/*
    public void drawHiddenSlot(int index) {
        if (inventorySlots.inventorySlots.size() > index) {
            int x = inventorySlots.getSlot(index).xDisplayPosition;
            int y = inventorySlots.getSlot(index).yDisplayPosition;
            if (x < 0) {
                this.drawTexturedModalRect(
                        this.guiLeft + x - 1 + (x >= 0 ? -GuiConsts.X_HIDE : GuiConsts.X_HIDE),
                        this.guiTop + y - 1,
                        this.getHiddenSlotX(), this.getHiddenSlotY(),
                        GuiConsts.X_OFFSET, GuiConsts.Y_OFFSET);
            }
        }
    }
*/
    public void drawBGSlot(int index) {
        if (inventorySlots.inventorySlots.size() > index) {
            if (inventorySlots.getSlot(index).xDisplayPosition >= 0) {
                this.drawTexturedModalRect(
                        this.guiLeft + inventorySlots.getSlot(index).xDisplayPosition - 1,
                        this.guiTop + inventorySlots.getSlot(index).yDisplayPosition - 1,
                        this.getSlotX(), this.getSlotY(),
                        GuiConsts.X_OFFSET, GuiConsts.Y_OFFSET);
            }
        }
    }

    public void drawFGSlot(int index, int x, int y) {
        if (inventorySlots.inventorySlots.size() > index) {
            if (inventorySlots.getSlot(index).xDisplayPosition >= 0) {
                this.drawTexturedModalRect(
                        inventorySlots.getSlot(index).xDisplayPosition - 1,
                        inventorySlots.getSlot(index).yDisplayPosition - 1,
                        x, y, GuiConsts.X_OFFSET, GuiConsts.Y_OFFSET);
            }
        }
    }

}
