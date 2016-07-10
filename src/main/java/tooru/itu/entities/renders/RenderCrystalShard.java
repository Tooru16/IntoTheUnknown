package tooru.itu.entities.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import tooru.itu.ITU;
import tooru.itu.consts.EntityConsts;
import tooru.itu.entities.projectile.EntityShard;
import tooru.itu.items.ModItems;
import tooru.itu.items.base.ModItem;

@SideOnly(Side.CLIENT)
public class RenderCrystalShard extends Render<EntityShard> {
    private static final ResourceLocation texture = new ResourceLocation(EntityConsts.CRYSTALSHARD_RENDER);
    private static float scale;

    public RenderCrystalShard(RenderManager renderManager) {
        super(renderManager);
        scale = EntityConsts.CRYSTALSHARD_SCALE;
    }

    //@Override
    protected ResourceLocation getEntityTexture(EntityShard entity) {
        //ITU.logger.info("getting texture! " + texture.toString());
        return texture;
    }

    @Override
    public void doRender(EntityShard entity, double x, double y, double z, float entityYaw, float partialTicks) {
        //this.renderEntityName(entity, x, y, z, EntityConsts.CRYSTALSHARD_NAME, 0);
        //ITU.logger.info("entityYaw = " + partialTicks);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        //GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);

        //Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        //Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 64, 64, 64, 64);

        this.bindEntityTexture(entity);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();

        float f1 = 0.0F;
        float f2 = 0.5F;
        float f3 = 0.125F;
        float f4 = 0.25F;
        float f5 = 1.0F;

        float x1 = 4.0F;
        float x2 = -x1;
        float y1 = 1.0F;
        float y2 = -y1;
        float z1 = 1.0F;
        float z2 = -z1;

        GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);

        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y1, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y2, z1).tex(f3, f3).endVertex();
        vertexbuffer.pos(x1, y2, z2).tex(f3, f4).endVertex();
        vertexbuffer.pos(x1, y1, z2).tex(f1, f4).endVertex();
        tessellator.draw();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-x1/8, 0, 0);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y1, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y2, z1).tex(f3, f3).endVertex();
        vertexbuffer.pos(x1, y2, z2).tex(f3, f4).endVertex();
        vertexbuffer.pos(x1, y1, z2).tex(f1, f4).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y2, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y1, z1).tex(f1, f1).endVertex();
        vertexbuffer.pos(x2, y1, z1).tex(f2, f1).endVertex();
        vertexbuffer.pos(x2, y2, z1).tex(f2, f3).endVertex();
        tessellator.draw();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y2, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y1, z1).tex(f1, f1).endVertex();
        vertexbuffer.pos(x2, y1, z1).tex(f2, f1).endVertex();
        vertexbuffer.pos(x2, y2, z1).tex(f2, f3).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y2, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y1, z1).tex(f1, f1).endVertex();
        vertexbuffer.pos(x2, y1, z1).tex(f2, f1).endVertex();
        vertexbuffer.pos(x2, y2, z1).tex(f2, f3).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(270.0F, 1.0F, 0.0F, 0.0F);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(x1, y2, z1).tex(f1, f3).endVertex();
        vertexbuffer.pos(x1, y1, z1).tex(f1, f1).endVertex();
        vertexbuffer.pos(x2, y1, z1).tex(f2, f1).endVertex();
        vertexbuffer.pos(x2, y2, z1).tex(f2, f3).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();



        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


}
