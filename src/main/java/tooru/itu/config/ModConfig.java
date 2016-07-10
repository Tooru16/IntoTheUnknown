package tooru.itu.config;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tooru.itu.consts.ModConsts;
import tooru.itu.utils.ModUtils;
import tooru.itu.utils.enums.ConfigSyncType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModConfig
{

    private static Configuration config = null;

    public static double valueDifficulty;

    public static void preInit()
    {
        File configFile = new File(Loader.instance().getConfigDir(), ModConsts.CONFIG_FILENAME);
        config = new Configuration(configFile);

        syncConfig(ConfigSyncType.FROMFILE);
    }

    public static void clientPreInit() {
        MinecraftForge.EVENT_BUS.register(new ModConfig());
    }

    public static Configuration getConfig() {
        return config;
    }

    public static void syncConfig(ConfigSyncType type)
    {
        switch (type)
        {
            case FROMFILE:
                config.load();
                break;
            case FROMGUI:
                break;
            case FROMFIELDS:
                break;
        }

        Property propertyDifficulty = config.get(
                ModConsts.CONFIG_CATEGORY_MAIN,
                ModConsts.CONFIG_DIFFICULTY_NAME,
                ModConsts.CONFIG_DIFFICULTY_DEFAULT,
                ModConsts.CONFIG_DIFFICULTY_DESCRIPTION,
                ModConsts.CONFIG_DIFFICULTY_MIN, ModConsts.CONFIG_DIFFICULTY_MAX);
        propertyDifficulty.setLanguageKey(ModConsts.CONFIG_DIFFICULTY_GUINAME);

        List<String> propertyOrderMain = new ArrayList<String>();
        propertyOrderMain.add(propertyDifficulty.getName());
        config.setCategoryPropertyOrder(ModConsts.CONFIG_CATEGORY_MAIN, propertyOrderMain);

        valueDifficulty = propertyDifficulty.getDouble(ModConsts.CONFIG_DIFFICULTY_DEFAULT);
        if (!ModUtils.isValidDoubleValue(valueDifficulty, ModConsts.CONFIG_DIFFICULTY_MIN, ModConsts.CONFIG_DIFFICULTY_MAX)) {
            valueDifficulty = ModConsts.CONFIG_DIFFICULTY_DEFAULT;
        }

        propertyDifficulty.set(valueDifficulty);

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event)
    {
        //ModLog.DebugMsg("OnConfigChangedEvent");
        if (ModConsts.MOD_ID.equals(event.getModID()) && !event.isWorldRunning()) {
            if (ModConsts.CONFIG_CATEGORY_MAIN.equals(event.getConfigID())) {
                syncConfig(ConfigSyncType.FROMGUI);
            }
        }
    }
}