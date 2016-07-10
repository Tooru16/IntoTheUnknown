package tooru.itu.items.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tooru.itu.ITU;
import tooru.itu.particles.ParticleCapacitor;
import tooru.itu.utils.TooltipUtils;

import java.util.List;

public class ParticlesCoreItem extends ComponentItem {
    private ParticleCapacitor capacitor;

    public ParticlesCoreItem(String itemName, int maxStackSizeIn, int tickRateIn, boolean isTickableIn, float capacity, float chargeRate, String particleTypeIn) {
        super(itemName, maxStackSizeIn, tickRateIn, isTickableIn);
        capacitor = new ParticleCapacitor(capacity, chargeRate, particleTypeIn);
    }

    // SET

    public void setCapacitor(ParticleCapacitor capacitorIn) {
        this.capacitor = capacitorIn;
    }

    // GET

    public ParticleCapacitor getCapacitor() {
        return capacitor;
    }

    // OTHER

    @Override
    public void doSimpleTickAction(int tick, ItemStack itemStack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (capacitor == null) {
            return;
        }

        capacitor.updateContent(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(itemStack, playerIn, tooltip, advanced);
        TooltipUtils.addParticleCapacitorInformation(itemStack, capacitor, tooltip, advanced);

    }
}
