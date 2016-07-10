package tooru.itu.gui.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tooru.itu.utils.ModUtils;

public class EquipmentSlot extends Slot {
    private boolean canTakeStack;

    public EquipmentSlot(IInventory inv, int index, int posX, int posY) {
        super(inv, index, posX, posY);
        setCanTakeStack(true);
    }

    public void setCanTakeStack(boolean canTakeStack) {
        this.canTakeStack = canTakeStack;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return ModUtils.isGloveItem(itemStack);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        //ITU.logger.info("Can Take Stack");
        return this.canTakeStack;
    }

}
