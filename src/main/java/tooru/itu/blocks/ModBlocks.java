package tooru.itu.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.blocks.BlockChargedBedrock;
import tooru.itu.blocks.BlockFlowOrigin;

public class ModBlocks {

    public static BlockFlowOrigin blockFlowOrigin;
    public static BlockChargedBedrock blockChargedBedrock;
    public static BlockEquipmentTuner blockEquipmentTuner;

    public static void init() {
        blockFlowOrigin = new BlockFlowOrigin();
        blockChargedBedrock = new BlockChargedBedrock();
        blockEquipmentTuner = new BlockEquipmentTuner();
    }

    @SideOnly(Side.CLIENT)
    public static void preInit() {
        blockFlowOrigin.initModel();
        blockChargedBedrock.initModel();
        blockEquipmentTuner.initModel();
    }

}
