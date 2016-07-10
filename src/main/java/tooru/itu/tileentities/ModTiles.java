package tooru.itu.tileentities;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tooru.itu.consts.TileConsts;

public class ModTiles {
    public static void init() {
        GameRegistry.registerTileEntity(TileEquipmentTuner.class, TileConsts.EQUIPMENTTUNER_NAME);
    }

}
