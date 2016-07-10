package tooru.itu.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import tooru.itu.consts.ItemConsts;
import tooru.itu.entities.projectile.EntityShard;
import tooru.itu.items.base.ModItem;

public class ItemCrystalShard extends ModItem {
    public ItemCrystalShard() {
        super(
                ItemConsts.CRYSTALSHARD_NAME
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!playerIn.capabilities.isCreativeMode)
        {
            --itemStackIn.stackSize;
        }

        if (!worldIn.isRemote)
        {
            EntityShard entityShard = new EntityShard(worldIn, playerIn);
            entityShard.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.2F, 1.0F);
            worldIn.spawnEntityInWorld(entityShard);
        }

        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }


}
