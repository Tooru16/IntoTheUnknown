package tooru.itu.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

import tooru.itu.consts.ModConsts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ModConfigGui.class; //tells Forge which class represents our main GUI screen
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ModConfigGui extends GuiConfig {
        public ModConfigGui(GuiScreen parentScreen)
        {
            super(parentScreen, getConfigElements(), ModConsts.MOD_ID,
                    false, false, I18n.format(ModConsts.CONFIG_GUITITLE));
        }

        private static List<IConfigElement> getConfigElements()
        {
            //ModLog.DebugMsg("Executing List<IConfigElement> in GUIFactory");
            List<IConfigElement> list = new ArrayList<IConfigElement>();
            //Add the two buttons that will go to each config category edit screen
            list.add(new DummyCategoryElement("mainCfg", ModConsts.CONFIG_GUIMAIN, CategoryEntryMain.class));
            //list.add(new DummyCategoryElement("miscCfg", "gui.mbe70_configuration.ctgy.other", CategoryEntryOther.class));
            return list;
        }

        //the next two classes are the edit screens for each configuration category
        public static class CategoryEntryMain extends CategoryEntry
        {
            public CategoryEntryMain(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
            {
                super(owningScreen, owningEntryList, prop);

                //ModLog.DebugMsg("Executing CategoryEntryMain in GUIFactory");
            }

            @Override
            protected GuiScreen buildChildScreen()
            {
                //The following GuiConfig object specifies the configID of the object and thus will force-save
                // when closed.
                //Parent GuiConfig object's entryList will also be refreshed to reflect the changes.
                // --see GuiFactory of Forge for more info
                //Additionally, Forge best practices say to put the path to the config file for the category as
                // the title for the category config screen

                //ModLog.DebugMsg("Executing buildChildScreen in GUIFactory");

                Configuration configuration = ModConfig.getConfig();
                ConfigElement cat_general = new ConfigElement(configuration.getCategory(ModConsts.CONFIG_CATEGORY_MAIN));
                List<IConfigElement> propertiesOnThisScreen = cat_general.getChildElements();
                String windowTitle = configuration.toString();

                //ModLog.DebugMsg("Category name "+ cat_general.getName());
                //ModLog.DebugMsg("Amount of elements in the category "+ cat_general.getChildElements().size());


                return new GuiConfig(this.owningScreen, propertiesOnThisScreen,
                        this.owningScreen.modID,
                        ModConsts.CONFIG_CATEGORY_MAIN,
                        this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                        this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                        GuiConfig.getAbridgedConfigPath(windowTitle));
                //this is a complicated object that specifies the category's gui screen, to better understand
                // how it works, look into the definitions of the called functions and objects
            }
        }
    }
}
