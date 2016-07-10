package tooru.itu.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    // Basic Items
    public static ItemChargedStick itemChargedStick;
    public static ItemCrystalShard itemCrystalShard;

    // Equipment
    public static ItemWoodenGlove itemWoodenGlove;

    // Components
    public static ItemPrimitiveCore itemPrimitiveCore;

    public static void init() {
        itemChargedStick = new ItemChargedStick();
        itemCrystalShard = new ItemCrystalShard();

        itemWoodenGlove = new ItemWoodenGlove();

        itemPrimitiveCore = new ItemPrimitiveCore();
    }

    @SideOnly(Side.CLIENT)
    public static void preInit() {
        itemChargedStick.initModel();
        itemCrystalShard.initModel();

        itemWoodenGlove.initModel();

        itemPrimitiveCore.initModel();
    }
}
