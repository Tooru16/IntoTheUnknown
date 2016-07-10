package tooru.itu.items.base;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tooru.itu.ITU;
import tooru.itu.utils.interfaces.ITickingItem;

public abstract class ComponentItem extends ModItem implements ITickingItem {

    private int tickRate;

    public ComponentItem(String itemName, int maxStackSizeIn, int tickRateIn, boolean isTickableIn) {
        super(itemName);
        this.setMaxStackSize(maxStackSizeIn);
        this.setMaxDamage(0);
        this.setTickRate(tickRateIn);
    }

    //SET

    public void setTickRate(int tickRateIn) {
        this.tickRate = tickRateIn;
    }

    // GET

    public int getTickRate() {
        return tickRate;
    }

    // OTHER


    @Override
    public void onUpdate(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote) {
            doSimpleTick(itemStack, worldIn, entityIn, itemSlot, isSelected, getTickRate());
            //EntityPlayer player = (EntityPlayer) entityIn;
        }
        super.onUpdate(itemStack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public void doSimpleTickAction(int tick, ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        ITU.logger.info("SimpleTickAction in Component");
        // NO OP
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {

        return !ItemStack.areItemsEqual(oldStack, newStack);
    }
}
