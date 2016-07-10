package tooru.itu.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tooru.itu.items.ModItems;
import tooru.itu.items.base.GloveItem;
import tooru.itu.items.base.ParticlesCoreItem;

public final class ModUtils {

    public static boolean isValidDoubleValue(double value, double minValue, double maxValue) {
        if (value > maxValue || value < minValue) {
            return false;
        } else {
            return true;
        }
    }

    // Spawn Item in World
    public static void spawnItemInWorld(World worldIn, BlockPos blockPosIn, ItemStack itemStackIn) {
        if (!worldIn.isRemote) {
            double d = (double)(worldIn.rand.nextFloat() * 0.5F) + (double)(1.0F - 0.5F) * 0.5D;
            worldIn.spawnEntityInWorld(new EntityItem(worldIn,
                    (double)blockPosIn.getX() + d, (double)blockPosIn.getY() + d, (double)blockPosIn.getZ() + d,
                    itemStackIn));
        }
    }

    // MATH HELPERS
    public static int getShardImpactValue(int i, int j, int k) {
        return MathHelper.abs_int(i)*10 + MathHelper.abs_int(j)*10 + MathHelper.abs_int(k)*10;
    }

    public static Class getOffHandClass() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        return player.getHeldItemOffhand() == null ? null : player.getHeldItemOffhand().getItem().getClass();
    }

    // CHECKS
    // Check if ItemStack extends GloveItem
    public static boolean isGloveItem(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getItem() instanceof  GloveItem) {
            return true;
        }

        return false;
    }

    // Check if ItemStack extends ParticleCoreItem
    public static boolean isParticleCoreItem(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getItem() instanceof ParticlesCoreItem) {
            return true;
        }

        return false;
    }

    public static boolean isEquipmentItem(ItemStack itemStack) {
        if (isGloveItem(itemStack)) {
            return true;
        }

        return false;
    }

}
