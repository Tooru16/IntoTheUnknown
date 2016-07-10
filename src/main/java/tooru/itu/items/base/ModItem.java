package tooru.itu.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.consts.ModConsts;

public abstract class ModItem extends Item {
    public ModItem(String itemName) {
        super();
        this.setCreativeTab(ModConsts.tabMod);
        this.setRegistryName(itemName);
        this.setUnlocalizedName(itemName);
        GameRegistry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        for (int i = 0; i <= 2; ++i) {
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName(), "inventory"));
        }

    }
}
