package tooru.itu.items;

import tooru.itu.consts.ItemConsts;
import tooru.itu.consts.ModConsts;
import tooru.itu.consts.ParticleConsts;
import tooru.itu.items.base.ParticlesCoreItem;

public class ItemPrimitiveCore extends ParticlesCoreItem {

    public ItemPrimitiveCore() {
        super(
                ItemConsts.PRIMITIVECORE_NAME,
                1,
                ItemConsts.PRIMITIVECORE_TICKRATE,
                true,
                ParticleConsts.CAPACITY_PRIMALCORE,
                ParticleConsts.CHARGERATE_PRIMITIVECORE,
                ModConsts.TAG_PARTICLES_RAW
        );
    }
}
