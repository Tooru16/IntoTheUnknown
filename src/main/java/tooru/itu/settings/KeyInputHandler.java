package tooru.itu.settings;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.ITU;
import tooru.itu.gui.ModGUIs;

public class KeyInputHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void OnKeyInput(InputEvent.KeyInputEvent event) {
        switch(ModKeyBindings.keyPressed()) {
            case KEY_OPEN:
                ModGUIs.openGloveGUI();
                break;
            case KEY_UNKNOWN:
            default:
                //ITU.logger.info("Other key pressed!");
                break;
        }
    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

}
