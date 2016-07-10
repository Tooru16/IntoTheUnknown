package tooru.itu.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tooru.itu.blocks.ModBlocks;
import tooru.itu.config.ModConfig;
import tooru.itu.crafting.ModCrafting;
import tooru.itu.entities.ModEntities;
import tooru.itu.gui.GuiHandler;
import tooru.itu.items.ModItems;
import tooru.itu.network.PacketHandler;
import tooru.itu.tileentities.ModTiles;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        ModConfig.preInit();

        ModBlocks.init();
        ModItems.init();
        ModTiles.init();
        ModEntities.init();

        PacketHandler.init();
    }

    public void init(FMLInitializationEvent e) {
        ModCrafting.init();
        GuiHandler.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
