package tooru.itu.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tooru.itu.ITU;
import tooru.itu.consts.GuiConsts;
import tooru.itu.gui.guiscreens.GuiMainScreen;
import tooru.itu.gui.guiscreens.GuiEquipmentTuner;
import tooru.itu.tileentities.TileEquipmentTuner;
import tooru.itu.gui.containers.ContainerEquipmentTuner;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case GuiConsts.GUI_ID_GLOVE:
                ITU.logger.info("GUI_ID_GLOVE Oppened on server!");
                break;
            case GuiConsts.GUI_ID_TUNER:
                ITU.logger.info("GUI_ID_TUNER Openned on server!");
                return new ContainerEquipmentTuner(player.inventory, (TileEquipmentTuner) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID) {
            case GuiConsts.GUI_ID_GLOVE:
                ITU.logger.info("GUI_ID_GLOVE Oppened on client!");
                return new GuiMainScreen();
            case GuiConsts.GUI_ID_TUNER:
                ITU.logger.info("GUI_ID_TUNER Openned on client!");
                return new GuiEquipmentTuner(player.inventory, (TileEquipmentTuner) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                break;
        }
        return null;
    }

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ITU.instance, new GuiHandler());
    }
}
