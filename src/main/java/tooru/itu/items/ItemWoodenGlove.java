package tooru.itu.items;

import tooru.itu.consts.ItemConsts;
import tooru.itu.consts.ParticleConsts;
import tooru.itu.items.base.GloveItem;

public class ItemWoodenGlove extends GloveItem {
    public ItemWoodenGlove() {
        super(
                ItemConsts.WOODENGLOVE_NAME,
                ItemConsts.WOODENGLOVE_TICKRATE,
                ParticleConsts.CHARGERATEMODIFIER_WOODENGLOVE
        );
    }

}
