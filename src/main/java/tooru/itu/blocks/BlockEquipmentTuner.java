package tooru.itu.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tooru.itu.ITU;
import tooru.itu.blocks.base.TileModBlock;
import tooru.itu.consts.BlockConsts;
import tooru.itu.consts.GuiConsts;
import tooru.itu.tileentities.TileEquipmentTuner;

public class BlockEquipmentTuner extends TileModBlock implements ITileEntityProvider{
    public BlockEquipmentTuner() {
        super(BlockConsts.EQUIPMENTTUNER_NAME, BlockConsts.EQUIPMENTTUNER_MATERIAL);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEquipmentTuner();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEquipmentTuner tile = (TileEquipmentTuner) worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn, pos, tile);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
                                    ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(ITU.instance, GuiConsts.GUI_ID_TUNER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
