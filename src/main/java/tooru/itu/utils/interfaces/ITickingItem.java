package tooru.itu.utils.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tooru.itu.consts.ModConsts;
import tooru.itu.utils.ModNBT;

public interface ITickingItem {

    public default void doSimpleTick(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected, int tickrateIn) {
        int tick = ModNBT.getInt(itemStack, ModConsts.TAG_TICK, ModConsts.TICK_START);

        tick++;

        if(tick % (tickrateIn * ModConsts.TICK) == 0) {
            doSimpleTickAction(tick, itemStack, worldIn, entityIn, itemSlot, isSelected);
        } else
        if(tick >= ModConsts.TICK_RESET) {
            tick = 0;
        }

        ModNBT.setInt(itemStack, ModConsts.TAG_TICK, tick);
    }

    public void doSimpleTickAction(int tick, ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected);
}
