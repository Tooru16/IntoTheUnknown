package tooru.itu.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tooru.itu.blocks.ModBlocks;
import tooru.itu.config.ModConfig;
import tooru.itu.entities.ModEntities;
import tooru.itu.settings.KeyInputHandler;
import tooru.itu.items.ModItems;
import tooru.itu.settings.ModKeyBindings;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        ModConfig.clientPreInit();

        ModItems.preInit();
        ModBlocks.preInit();
        ModEntities.initRender();

        ModKeyBindings.preInit();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        KeyInputHandler.init();
    }

}
