package tooru.itu.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tooru.itu.ITU;
import tooru.itu.consts.EntityConsts;
import tooru.itu.consts.ModConsts;
import tooru.itu.entities.projectile.EntityShard;
import tooru.itu.entities.renders.RenderCrystalShard;
import tooru.itu.items.ItemCrystalShard;
import tooru.itu.items.ModItems;

public class ModEntities {
    public static void init() {
        EntityRegistry.registerModEntity(EntityShard.class, EntityConsts.CRYSTALSHARD_NAME, EntityConsts.CRYSTALSHARD_ID, ITU.instance, 80, 3, true);
    }
    public static void initRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityShard.class, RenderCrystalShard::new);
    }
}
