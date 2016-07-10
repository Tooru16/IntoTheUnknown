package tooru.itu.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import tooru.itu.ITU;
import tooru.itu.consts.GuiConsts;
import tooru.itu.items.base.GloveItem;
import tooru.itu.utils.ModUtils;

public class ModGUIs {

    public static void openGloveGUI(EntityPlayer player, World world, int x, int y, int z) {
        player.openGui(ITU.instance, GuiConsts.GUI_ID_GLOVE, world, x, y, z);
    }

    public static void openGloveGUI() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        World world = Minecraft.getMinecraft().theWorld;
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        int z = player.getPosition().getZ();


        if(ModUtils.getOffHandClass() != null && ModUtils.getOffHandClass().getSuperclass().equals(GloveItem.class)) {
            player.openGui(ITU.instance, GuiConsts.GUI_ID_GLOVE, world, x, y, z);
        }

    }

}
