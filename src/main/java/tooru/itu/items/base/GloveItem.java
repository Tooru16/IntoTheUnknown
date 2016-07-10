package tooru.itu.items.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.ITU;
import tooru.itu.consts.ItemConsts;
import tooru.itu.consts.ModConsts;
import tooru.itu.particles.ParticleCapacitor;
import tooru.itu.utils.ModNBT;
import tooru.itu.utils.ModUtils;
import tooru.itu.utils.TooltipUtils;
import tooru.itu.utils.interfaces.ITickingItem;

import java.util.List;

public abstract class GloveItem extends ModItem implements ITickingItem {

    private int tickRate;
    private float chargeRateModifier;

    public GloveItem(String itemName, int tickRateIn, float modifierIn) {
        super(itemName);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setFull3D();
        this.setTickRate(tickRateIn);
        this.setChargeRateModifier(modifierIn);
    }

    // SET

    public void setTickRate(int tickRateIn) {
        if(tickRateIn <= ModConsts.TICKRATE_MAX && tickRateIn >= ModConsts.TICKRATE_MIN) {
            tickRate = tickRateIn;
        } else {
            tickRate = ItemConsts.CUSTOMGLOVE_TICKRATE_DEFAULT;
        }
    }

    public void setChargeRateModifier(float chargeRateModifier) {
        this.chargeRateModifier = chargeRateModifier;
    }

    // GET

    public int getTickRate() {
        return tickRate;
    }

    public float getChargeRateModifier() {
        return chargeRateModifier <= 0.0F ? 1.0F : chargeRateModifier;
    }

    // OTHER

    @Override
    public void doSimpleTickAction(int tick, ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        ItemStack itemCore = ModNBT.getCoreItem(itemStack);
        float surplusContent;

        if (ModUtils.isParticleCoreItem(itemCore)) {
            surplusContent = ((ParticlesCoreItem) itemCore.getItem()).getCapacitor().updateContent(itemCore, getChargeRateModifier());
        }

        ModNBT.setCoreItem(itemStack, itemCore);

    }

    @Override
    public void onCreated(ItemStack itemStack, World worldIn, EntityPlayer playerIn) {
        if(!worldIn.isRemote) {
            ITU.logger.info(itemStack.getMetadata());
        }
    }

    @Override
    public void onUpdate(ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote) {
            doSimpleTick(itemStack, worldIn, entityIn, itemSlot, isSelected, getTickRate());
            //EntityPlayer player = (EntityPlayer) entityIn;
        }
        super.onUpdate(itemStack, worldIn, entityIn, itemSlot, isSelected);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(hand == EnumHand.OFF_HAND) {

            if(!worldIn.isRemote) {
                ITU.logger.info("Right Clicked GloveItem!");
            }

            playerIn.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);

        } else {
            return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        ITU.logger.info("onUsingTick count = " + count);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return ItemConsts.GLOVEITEM_MAXUSEDURATION;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(itemStack, playerIn, tooltip, advanced);

        ItemStack itemCore = ModNBT.getCoreItem(itemStack);

        if (ModUtils.isParticleCoreItem(itemCore)) {
            TooltipUtils.addParticleCapacitorInformation(itemCore, ((ParticlesCoreItem) itemCore.getItem()).getCapacitor(), tooltip, advanced);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.areItemsEqual(oldStack, newStack);
    }
}
