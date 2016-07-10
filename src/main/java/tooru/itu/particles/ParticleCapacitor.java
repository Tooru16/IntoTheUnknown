package tooru.itu.particles;

import net.minecraft.item.ItemStack;
import tooru.itu.utils.ModNBT;

public class ParticleCapacitor {
    private float capacity;
    private float rechargeRate;
    private String type;

    public ParticleCapacitor() {
        setCapacity(0.0F);
        setRechargeRate(0.0F);
    }

    public ParticleCapacitor(float capacityIn, float chargeRateIn, String type) {
        setCapacity(capacityIn);
        setRechargeRate(chargeRateIn);
        setType(type);
    }

    // SET

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }
    public void setRechargeRate(float rechargeRate) {
        this.rechargeRate = rechargeRate;
    }
    public void setType(String type) {
        this.type = type;
    }

    public static void setContent(ItemStack itemStack, String typeIn, float setValue) {
        ModNBT.setFloat(itemStack, typeIn, setValue);
    }

    // GET

    public float getCapacity() {
        return capacity;
    }
    public float getRechargeRate() {
        return rechargeRate;
    }
    public String getType() {
        return type;
    }
    public static float getContent(ItemStack itemStack, String typeIn) {
        return ModNBT.getFloat(itemStack, typeIn, 0);
    }

    // OTHER

    public float updateContent(ItemStack itemStack, float modifierValue) {
        float currentContent = ParticleCapacitor.getContent(itemStack, getType());
        float surplusContent = 0.0F;

        currentContent += getRechargeRate()*modifierValue;
        if (currentContent > getCapacity()) {
            surplusContent = currentContent - getCapacity();
            currentContent = getCapacity();
        }

        ParticleCapacitor.setContent(itemStack, getType(), currentContent);
        return surplusContent;
    }

    public float updateContent(ItemStack itemStack) {
        return updateContent(itemStack, 1.0F);
    }


}
