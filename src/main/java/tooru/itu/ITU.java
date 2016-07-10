package tooru.itu;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import tooru.itu.consts.ModConsts;
import tooru.itu.proxy.CommonProxy;

@Mod(
        modid = ModConsts.MOD_ID,
        name = ModConsts.MOD_NAME,
        version = ModConsts.MOD_VERSION,
        dependencies = ModConsts.MOD_DEPENDENCIES,
        useMetadata = true,
        guiFactory = ModConsts.PATH_GUIFACTORY
)
public class ITU {

    @SidedProxy(clientSide = ModConsts.PATH_CLIENTPROXY, serverSide = ModConsts.PATH_SERVERPROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    public static ITU instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
