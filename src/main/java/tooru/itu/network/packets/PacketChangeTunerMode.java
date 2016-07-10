package tooru.itu.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tooru.itu.ITU;
import tooru.itu.consts.ModConsts;
import tooru.itu.consts.TileConsts;
import tooru.itu.tileentities.TileEquipmentTuner;
import tooru.itu.utils.ModNBT;

public class PacketChangeTunerMode implements IMessage {

    private TileEquipmentTuner tile;
    private int tunerMode;
    private BlockPos blockPos;

    public PacketChangeTunerMode() {}

    public PacketChangeTunerMode(BlockPos blockPos, int tunerMode) {
        this.setBlockPos(blockPos);
        this.setTunerMode(tunerMode);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        setBlockPos(BlockPos.fromLong(buf.readLong()));
        setTunerMode(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(getBlockPos().toLong());
        buf.writeInt(getTunerMode());
    }

    // GET

    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    public int getTunerMode() {
        return tunerMode;
    }

    // SET

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }
    public void setTunerMode(int tunerMode) {
        this.tunerMode = tunerMode;
    }

    public static class Handler implements IMessageHandler<PacketChangeTunerMode, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeTunerMode message, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleMessage(message, ctx));
            return null;
        }

        private void handleMessage(PacketChangeTunerMode message, MessageContext ctx) {
            TileEquipmentTuner tile = (TileEquipmentTuner) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.getBlockPos());
            ItemStack itemEquipment;
            ItemStack itemCore;
            NBTTagCompound nbtCore;

            if (tile.isTuningMode()) {
                //ITU.logger.info("Save components to equipment!");

                itemCore = tile.getStackInSlot(TileConsts.INDEX_CORE);
                itemEquipment = tile.getStackInSlot(TileConsts.INDEX_EQUIPMENT);
                if (itemEquipment == null) {
                    ITU.logger.error("Somehow we don't have equipment in EquipmentSlot when closing");
                    return;
                }

                nbtCore = new NBTTagCompound();
                if (itemCore != null) {
                    itemCore.copy().writeToNBT(nbtCore);
                }
                ModNBT.setCompound(itemEquipment, ModConsts.TAG_COMPONENT_CORE, nbtCore);

                tile.setInventorySlotContents(TileConsts.INDEX_CORE, null);
            }

            if (tile.isClosedMode()) {
                //ITU.logger.info("Load components from Equipment!");

                itemEquipment = tile.getStackInSlot(TileConsts.INDEX_EQUIPMENT);
                nbtCore = ModNBT.getCompound(itemEquipment, ModConsts.TAG_COMPONENT_CORE);
                itemCore = ItemStack.loadItemStackFromNBT(nbtCore);

                tile.setInventorySlotContents(TileConsts.INDEX_CORE, itemCore);
            }

            tile.setTunerMode(message.getTunerMode());
        }
    }
}
