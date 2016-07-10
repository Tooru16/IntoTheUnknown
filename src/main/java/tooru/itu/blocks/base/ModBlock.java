package tooru.itu.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.consts.ModConsts;

public abstract class ModBlock extends Block {
    public ModBlock(String blockName, Material blockMaterial) {
        super(blockMaterial);
        this.setCreativeTab(ModConsts.tabMod);
        setUnlocalizedName(blockName);
        setRegistryName(blockName);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
/*
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }
*/
}
