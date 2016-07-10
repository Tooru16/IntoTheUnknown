package tooru.itu.gui.containers;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tooru.itu.ITU;
import tooru.itu.consts.GuiConsts;
import tooru.itu.consts.TileConsts;
import tooru.itu.gui.slots.EquipmentSlot;
import tooru.itu.tileentities.TileEquipmentTuner;

import javax.annotation.Nullable;

public class ContainerEquipmentTuner extends Container {

    private TileEquipmentTuner tile;
    private int indexEquipmentSlot;
    private int indexOffhandSlot;

    public ContainerEquipmentTuner(IInventory playerInv, TileEquipmentTuner tile) {
        this.tile = tile;

        // Player: Inventory, Slot 0-8, Slot IDs 0-8
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, GuiConsts.ET_HOTBAR_X + x * GuiConsts.X_OFFSET, GuiConsts.ET_HOTBAR_Y));
        }

        // Player: Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, GuiConsts.ET_INV_X + x * GuiConsts.X_OFFSET, GuiConsts.ET_INV_Y + y * GuiConsts.Y_OFFSET));
            }
        }

        // Player: Offhand, Slot 40, Slot ID 36
        this.addSlotToContainer(new Slot(playerInv, GuiConsts.SLOT_OFFHAND, GuiConsts.ET_OFFHAND_X , GuiConsts.ET_OFFHAND_Y));
        this.indexOffhandSlot = GuiConsts.indexOffhandSlot;

        // ET Tile: Equipment Slot, Slot 0, Slot ID 37
        this.addSlotToContainer(new EquipmentSlot(tile, TileConsts.INDEX_EQUIPMENT, GuiConsts.ET_EQUIPMENT_X, GuiConsts.ET_EQUIPMENT_Y));
        this.indexEquipmentSlot = GuiConsts.indexEquipmentSlot;

        // ET Tile: Component Slots, Slot 1-?, Slot IDs 38-?
        this.addSlotToContainer(new Slot(tile, TileConsts.INDEX_CORE, GuiConsts.ET_CORE_X, GuiConsts.ET_CORE_Y));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tile.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack previousStack = null;
        Slot currentSlot = (Slot) this.inventorySlots.get(index);

        if (currentSlot != null && currentSlot.getHasStack()) {

            ItemStack currentStack = currentSlot.getStack();
            previousStack = currentStack.copy();

            if (index >= 0 && index < this.indexOffhandSlot) {
                // From: Inventory/Hotbar Slots, To: Equipment Slot
                if (!this.mergeItemStack(currentStack, this.indexEquipmentSlot, this.indexEquipmentSlot + 1, false)) {
                    return null;
                }
            } else
            if (index == this.indexOffhandSlot) {
                // From: Offhand Slot To: Equipment Slot
                if (!this.mergeItemStack(currentStack,  this.indexEquipmentSlot, this.indexEquipmentSlot + 1, false)) {
                    return null;
                }
            } else
            if (index == this.indexEquipmentSlot) {
                // From: Equipment Slot To: Offhand-Inventory-Hotbar
                if (!this.mergeItemStack(currentStack, 0, this.indexEquipmentSlot, true)) {
                    return null;
                }
            }

            if (currentStack.stackSize == 0)
                currentSlot.putStack((ItemStack) null);
            else
                currentSlot.onSlotChanged();

            if (currentStack.stackSize == previousStack.stackSize)
                return null;
            currentSlot.onPickupFromSlot(playerIn, currentStack);
        }
        return previousStack;
    }

    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        // maybe something here

        return super.mergeItemStack(stack, startIndex, endIndex, reverseDirection);
    }

    @Nullable
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        if (slotId == indexEquipmentSlot && tile.isTuningMode()) {
            return null;
        }

        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
