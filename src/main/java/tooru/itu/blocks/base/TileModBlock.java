package tooru.itu.blocks.base;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileModBlock extends ModBlock implements ITileEntityProvider {
    public TileModBlock(String blockName, Material blockMaterial) {
        super(blockName, blockMaterial);
        this.isBlockContainer = true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.removeTileEntity(pos);
        super.breakBlock(worldIn, pos, state);
    }
/*
    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        return super.eventReceived(state, worldIn, pos, id, param);
    }

    @Override
    public boolean eventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tile = worldIn.getTileEntity(pos);
        return tile == null ? false : tile.receiveClientEvent(eventID, eventParam);
    }
*/
}
