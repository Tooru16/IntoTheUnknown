package tooru.itu.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tooru.itu.consts.ModConsts;

public final class ModNBT {

    public static boolean hasNBT(ItemStack itemStack) {
        return itemStack.hasTagCompound();
    }

    public static void createNBT(ItemStack itemStack) {
        if(!hasNBT(itemStack)) {
            setNBT(itemStack, new NBTTagCompound());
        }
    }

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        createNBT(itemStack);
        return itemStack.getTagCompound();
    }

    public static void setNBT(ItemStack itemStack, NBTTagCompound tagCompound) {
        itemStack.setTagCompound(tagCompound);
    }


    public static boolean hasTag(ItemStack itemStack, String tagName) {
        return getNBT(itemStack).hasKey(tagName) && itemStack != null;
    }

    // GET

    public static int getInt(ItemStack itemStack, String tagName, int defaultValue) {
        return hasTag(itemStack, tagName) ? getNBT(itemStack).getInteger(tagName) : defaultValue;
    }

    public static float getFloat(ItemStack itemStack, String tagName, float defaultValue) {
        return hasTag(itemStack, tagName) ? getNBT(itemStack).getFloat(tagName) : defaultValue;
    }

    public static NBTTagCompound getCompound(ItemStack itemStack, String tagName) {
        return hasTag(itemStack, tagName) ? getNBT(itemStack).getCompoundTag(tagName) : new NBTTagCompound();
    }

    // SET

    public static void setInt(ItemStack itemStack, String tagName, int setValue) {
        getNBT(itemStack).setInteger(tagName, setValue);
    }

    public static void setFloat(ItemStack itemStack, String tagName, float setValue) {
        getNBT(itemStack).setFloat(tagName, setValue);
    }

    public static void setCompound(ItemStack itemStack, String tagName, NBTTagCompound setValue) {
        getNBT(itemStack).setTag(tagName, setValue);
    }

    // HELPER METHODS
    public static ItemStack getCoreItem(ItemStack itemEquipment) {
        if (itemEquipment == null) {
            return null;
        }

        return ItemStack.loadItemStackFromNBT(ModNBT.getCompound(itemEquipment, ModConsts.TAG_COMPONENT_CORE));
    }

    public static void setCoreItem(ItemStack itemEquipment, ItemStack itemCore) {
        if (itemEquipment == null || itemCore == null) {
            return;
        }

        NBTTagCompound nbtCore = new NBTTagCompound();
        itemCore.writeToNBT(nbtCore);
        ModNBT.setCompound(itemEquipment, ModConsts.TAG_COMPONENT_CORE, nbtCore);
    }

}
