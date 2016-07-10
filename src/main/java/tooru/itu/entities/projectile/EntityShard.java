package tooru.itu.entities.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import tooru.itu.ITU;
import tooru.itu.items.ModItems;
import tooru.itu.items.base.ModItem;
import tooru.itu.utils.ModUtils;

import java.util.Random;

public class EntityShard extends EntityThrowable {
    public EntityShard(World worldIn)
    {
        super(worldIn);
    }
    public EntityShard(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }
    public EntityShard(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }


    @Override
    protected void onImpact(RayTraceResult result) {
        for (int j = 0; j < 8; ++j)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();

            Random random = new Random();

            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    for (int k = -1; k <= 1; ++k) {
                        Block blockHit = worldObj.getBlockState(result.getBlockPos().add(i,j,k)).getBlock();
                        if (blockHit instanceof BlockLeaves) {
                            worldObj.destroyBlock(result.getBlockPos().add(i,j,k), true);
                            if (random.nextInt(10) + ModUtils.getShardImpactValue(i, j, k) - 9 <= 0) {
                                ModUtils.spawnItemInWorld(worldObj, result.getBlockPos().add(i,j,k), new ItemStack(ModItems.itemChargedStick));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        //ITU.logger.info("FLYING!");
    }

    @Override
    protected float getGravityVelocity() {
        return 0.03F;
    }


}
