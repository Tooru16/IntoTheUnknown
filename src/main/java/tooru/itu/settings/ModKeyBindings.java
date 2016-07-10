package tooru.itu.settings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tooru.itu.consts.ModConsts;
import tooru.itu.utils.enums.InputKey;

@SideOnly(Side.CLIENT)
public class ModKeyBindings {
    public static KeyBinding keyOpen = new KeyBinding(ModConsts.KEY_OPEN, Keyboard.KEY_G, ModConsts.KEY_CATEGORY_MAIN);

    public static void preInit() {
        ClientRegistry.registerKeyBinding(keyOpen);
    }

    public static InputKey keyPressed() {
        if(isKeyOpenPressed()) {
            return InputKey.KEY_OPEN;
        } else {
            return InputKey.KEY_UNKNOWN;
        }

    }

    public static boolean isKeyOpenPressed() {
        return keyOpen.isPressed();
    }

}
