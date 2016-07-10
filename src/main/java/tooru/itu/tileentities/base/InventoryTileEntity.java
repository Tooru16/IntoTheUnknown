package tooru.itu.tileentities.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import tooru.itu.consts.TileConsts;

import java.util.Arrays;

public abstract class InventoryTileEntity extends ModTileEntity implements IInventory {

    private ItemStack[] inventory;
    private int sizeInventory;
    private String customName;

    public InventoryTileEntity(String name, int size) {
        super();
        this.setCustomName(name);
        this.setSizeInventory(size);
        this.inventory = new ItemStack[getSizeInventory()];
    }

    // SET

    public void setSizeInventory(int size) {
        sizeInventory = size >= 0 && size <= TileConsts.VAR_INVENTORYSIZE_MAX ? size : TileConsts.VAR_INVENTORYSIZE_DEFAULT;
    }

    public void setCustomName(String name) {
        customName = name;
    }

    // GET

    @Override
    public int getSizeInventory() {
        return sizeInventory;
    }

    public String getCustomName() {
        return customName;
    }

    // NAMES

    public boolean hasCustomName() {
        return getCustomName() != null && !getCustomName().equals("");
    }

    @Override
    public String getName() {
        return hasCustomName() ? getCustomName() : TileConsts.CUSTOMNAME_DEFAULT;
    }

    @Override
    public ITextComponent getDisplayName() {
        //return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
        return new TextComponentTranslation(getName());
    }

    // INVENTORY MANAGEMENT

    private boolean isValidIndex(int indexValue) {
        return indexValue >=0 && indexValue < this.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return isValidIndex(index) ? this.inventory[index] : null;
    }



    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(getStackInSlot(index) != null) {
            ItemStack itemStack;

            if(getStackInSlot(index).stackSize <= count) {
                itemStack = getStackInSlot(index);
                setInventorySlotContents(index, null);
            } else {
                itemStack = getStackInSlot(index).splitStack(count);
                saveInventory(index);
            }
            //saveInventory(index);

            return itemStack;
        }

        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = getStackInSlot(index);
        setInventorySlotContents(index, null);
        saveInventory(index);

        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack) {
        if(isValidIndex(index)) {
            inventory[index] = itemStack;
            saveInventory(index);
        }
    }

    private void saveInventory(int index) {
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return TileConsts.VAR_INVENTORYSTACK_MAX;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    // NBT

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtIn) {
        NBTTagCompound nbt = super.writeToNBT(nbtIn);

        NBTTagList list = new NBTTagList();
        for(int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte(TileConsts.TAG_SLOT, (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        nbt.setTag(TileConsts.TAG_INVENTORY, list);

        nbt.setInteger(TileConsts.TAG_SIZEINVENTORY, this.getSizeInventory());
        nbt.setString(TileConsts.TAG_CUSTOMNAME, this.getCustomName());

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList(TileConsts.TAG_INVENTORY, 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            byte slot = stackTag.getByte(TileConsts.TAG_SLOT);
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
        }

        if (nbt.hasKey(TileConsts.TAG_SIZEINVENTORY, 3)) {
            this.setSizeInventory(nbt.getInteger(TileConsts.TAG_SIZEINVENTORY));
        }

        if (nbt.hasKey(TileConsts.TAG_CUSTOMNAME, 8)) {
            this.setCustomName(nbt.getString(TileConsts.TAG_CUSTOMNAME));
        }
    }

    // OTHER

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        Arrays.fill(inventory, null);
    }
}
