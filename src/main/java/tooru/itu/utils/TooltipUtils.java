package tooru.itu.utils;

import net.minecraft.item.ItemStack;
import tooru.itu.consts.ModConsts;
import tooru.itu.particles.ParticleCapacitor;

import java.util.List;

public final class TooltipUtils {

    //HELPER METHODS
    private static String formatParticleCapacity(float content, float capacity, String type) {
        return String.format(getParticleName(type) + " Particles: %.0f/%.0f", content, capacity);
    }

    private static String getParticleName(String type) {
        String typeString = "Unknown";
        if (type.equals(ModConsts.TAG_PARTICLES_RAW))
        {
            typeString = "Raw";
        }
        return typeString;
    }

    // MAIN METHODS
    public static void addParticleCapacitorInformation(ItemStack itemStack, ParticleCapacitor capacitorIn, List<String> tooltip, boolean advanced) {
        if (capacitorIn == null || itemStack == null) {
            return;
        }

        tooltip.add(formatParticleCapacity(ParticleCapacitor.getContent(itemStack, capacitorIn.getType()), capacitorIn.getCapacity(), capacitorIn.getType()));
    }
}
