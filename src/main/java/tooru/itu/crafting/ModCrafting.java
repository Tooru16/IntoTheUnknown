package tooru.itu.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tooru.itu.items.ModItems;

public class ModCrafting {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ModItems.itemWoodenGlove, 1, 0),
                new Object[] {
                        "SPS", "S S", "S S",
                        'S', Items.STICK,
                        'P', Blocks.PLANKS
                });
        GameRegistry.addRecipe(new ItemStack(ModItems.itemWoodenGlove, 1, 1),
                new Object[] {
                        "SPS", "S S", "S S",
                        'S', Items.STICK,
                        'P', Blocks.COBBLESTONE
                });
        GameRegistry.addRecipe(new ItemStack(ModItems.itemPrimitiveCore),
                new Object[] {
                        "SSS", "S S", "SSS",
                        'S', ModItems.itemChargedStick
                });
    }

}
