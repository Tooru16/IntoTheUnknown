package tooru.itu.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import tooru.itu.ITU;
import tooru.itu.consts.TileConsts;
import tooru.itu.tileentities.base.InventoryTileEntity;
import tooru.itu.utils.ModUtils;
import tooru.itu.utils.enums.EquipmentTunerMode;

public class TileEquipmentTuner extends InventoryTileEntity implements ITickable {
    private int tunerMode;

    public TileEquipmentTuner() {
        super(TileConsts.EQUIPMENTTUNER_CUSTOMNAME, TileConsts.EQUIPMENTTUNER_INVENTORYSIZE);
        this.setTunerMode(TileConsts.EQUIPMENTTUNER_CLOSEDMODE);
        ITU.logger.info("TileEquipmentTuner Constructor");
    }

    // SET

    public void setTunerMode(int tunerMode) {
        this.tunerMode = tunerMode;
    }

    //GET

    public int getTunerMode() {
        return tunerMode;
    }

    public boolean isTuningMode() {
        return getTunerMode() == TileConsts.EQUIPMENTTUNER_TUNINGMODE;
    }

    public boolean isClosedMode() {
        return getTunerMode() == TileConsts.EQUIPMENTTUNER_CLOSEDMODE;
    }

    @Override
    public void update() {
        /*if (!this.worldObj.isRemote) {
            ITU.logger.info("TileEquipmentTuner ticking! SERVER: TunerMode = "+this.getTunerMode());
        } else {
            ITU.logger.info("TileEquipmentTuner ticking! CLIENT: TunerMode = "+this.getTunerMode());
        }*/
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack) {
        return ModUtils.isGloveItem(itemStack) && isClosedMode();
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return super.removeStackFromSlot(index);
    }

    // NBT

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.hasKey(TileConsts.TAG_TUNERMODE, 3)) {
            this.setTunerMode(nbt.getInteger(TileConsts.TAG_TUNERMODE));
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtIn) {
        NBTTagCompound nbt = super.writeToNBT(nbtIn);

        nbt.setInteger(TileConsts.TAG_TUNERMODE, this.getTunerMode());

        return nbt;
    }
}
