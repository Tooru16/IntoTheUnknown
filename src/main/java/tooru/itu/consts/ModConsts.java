package tooru.itu.consts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.items.ModItems;

public class ModConsts {
    public static final String MOD_ID = "itu";
    public static final String MOD_NAME = "Into The Unknown";
    public static final String MOD_VERSION = "0.0.1";
    public static final String MOD_DEPENDENCIES = "required-after:Forge@[12.17.0.1936]";

    public static final String PATH_ROOT = "tooru.itu";
    public static final String PATH_CLIENTPROXY = PATH_ROOT + ".proxy.ClientProxy";
    public static final String PATH_SERVERPROXY = PATH_ROOT + ".proxy.ServerProxy";
    public static final String PATH_GUIFACTORY = PATH_ROOT + ".config.GuiFactory";

    public static final String CONFIG_FILENAME = "itu.cfg";
    public static final String CONFIG_ROOT = "gui.config";
    public static final String CONFIG_CATEGORY_MAIN = "main";
    public static final String CONFIG_GUITITLE = CONFIG_ROOT + ".mainTitle";
    public static final String CONFIG_GUIMAIN = CONFIG_ROOT + ".ctgy." + CONFIG_CATEGORY_MAIN;

    public static final double CONFIG_DIFFICULTY_DEFAULT = 1.0;
    public static final double CONFIG_DIFFICULTY_MIN = 0.1;
    public static final double CONFIG_DIFFICULTY_MAX = 10.0;
    public static final String CONFIG_DIFFICULTY_NAME = "valueDifficulty";
    public static final String CONFIG_DIFFICULTY_DESCRIPTION = "Mod difficulty multiplier";
    public static final String CONFIG_DIFFICULTY_GUINAME = CONFIG_ROOT + "." + CONFIG_DIFFICULTY_NAME;

    public static final String KEY_ROOT = "key";
    public static final String KEY_CATEGORY_MAIN = KEY_ROOT + ".categories.main";
    public static final String KEY_OPEN = KEY_ROOT + ".open";

    public static final CreativeTabs tabMod = new CreativeTabs(MOD_ID) {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ModItems.itemChargedStick;
        }

    };

    public static final int TICK = 5;
    public static final int TICKRATE_MAX = 2400;
    public static final int TICKRATE_MIN = 1;
    public static final int TICK_RESET = TICK*TICKRATE_MAX; //232792560; //НОК(1,2,...,20)
    public static final int TICK_START = 0;

    public static final String TAG_TICK = "tick";
    public static final String TAG_TICKRATE = "tickrate";
    public static final String TAG_COMPONENT_CORE = "componentCore";
    public static final String TAG_PARTICLES_RAW = "particlesRaw";

}
