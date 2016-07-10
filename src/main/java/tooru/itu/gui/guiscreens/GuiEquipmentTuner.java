package tooru.itu.gui.guiscreens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import tooru.itu.consts.GuiConsts;
import tooru.itu.consts.TileConsts;
import tooru.itu.gui.base.ModGuiContainer;
import tooru.itu.gui.buttons.ModGuiButton;
import tooru.itu.network.PacketHandler;
import tooru.itu.tileentities.TileEquipmentTuner;
import tooru.itu.gui.containers.ContainerEquipmentTuner;
import tooru.itu.utils.ModUtils;

import java.io.IOException;


public class GuiEquipmentTuner extends ModGuiContainer<TileEquipmentTuner> {

    private ModGuiButton buttonOpen;
    private ModGuiButton buttonClose;

    private int indexEquipmentSlot;
    private int[] indexComponentSlots;
    
    public GuiEquipmentTuner(IInventory playerInventory, TileEquipmentTuner tile) {
        super(new ContainerEquipmentTuner(playerInventory, tile));

        this.setInventory(playerInventory);
        this.setTile(tile);
        this.setSizeXY(GuiConsts.ET_XSIZE, GuiConsts.ET_YSIZE);
        this.setHiddenSlotXY(GuiConsts.ET_HIDDENSLOT_X, GuiConsts.ET_HIDDENSLOT_Y);
        this.setSlotXY(GuiConsts.ET_SLOT_X, GuiConsts.ET_SLOT_Y);

        this.indexEquipmentSlot = GuiConsts.indexEquipmentSlot;
        this.indexComponentSlots = GuiConsts.indexComponentSlots.clone();

        this.setTunerMode(tile.getTunerMode());
    }

    private void setTunerMode(int tunerMode) {
        this.getTile().setTunerMode(tunerMode);

        if (getTile().isTuningMode()) {
            this.showComponentSlots();
            //this.enableEquipmentSlot();
        } else
        if (getTile().isClosedMode()) {
            this.hideComponentSlots();
            //this.enableEquipmentSlot();
        }
    }

    private int getTunerMode() {
        return this.getTile().getTunerMode();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(GuiConsts.GUICONTAINER_EQUIPMENTTUNER));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        this.drawButtons();
        this.enableEquipmentSlot();
        this.drawComponentSlots();

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.getTile().getDisplayName().getUnformattedText();
        fontRendererObj.drawString(s, GuiConsts.ET_INV_X + GuiConsts.ET_INV_XSIZE / 2 - fontRendererObj.getStringWidth(s) / 2,
                GuiConsts.ET_INV_Y - 10, GuiConsts.clBlack);
        //ITU.logger.info(s);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(GuiConsts.GUICONTAINER_EQUIPMENTTUNER));
        if (getTile().isTuningMode()) {
            this.drawFGSlot(this.indexEquipmentSlot, this.getSlotX() + GuiConsts.X_OFFSET, this.getSlotY() - GuiConsts.Y_OFFSET);
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(this.buttonOpen = new ModGuiButton(0, this.guiLeft + GuiConsts.ET_EQUIPMENT_X,
                GuiConsts.ET_EQUIPMENT_Y + GuiConsts.Y_OFFSET + this.guiTop, 14, 14, "Open"));
        this.buttonList.add(this.buttonClose = new ModGuiButton(0, this.guiLeft + GuiConsts.ET_EQUIPMENT_X,
                GuiConsts.ET_EQUIPMENT_Y + GuiConsts.Y_OFFSET +  this.guiTop, 14, 14, "Close"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == buttonOpen) {
            // Check if possible
            if (ModUtils.isEquipmentItem(inventorySlots.getSlot(this.indexEquipmentSlot).getStack())) {
                this.setTunerMode(TileConsts.EQUIPMENTTUNER_TUNINGMODE);
                PacketHandler.sendChangeTunerMode(getTile().getPos(), getTunerMode());
            }
        }

        if (button == buttonClose) {
            // Check if possible

            this.setTunerMode(TileConsts.EQUIPMENTTUNER_CLOSEDMODE);
            PacketHandler.sendChangeTunerMode(getTile().getPos(), getTunerMode());
        }
    }

    public void hideComponentSlots() {
        for (int i = 0; i < indexComponentSlots.length; ++i) {
            this.hideSlot(indexComponentSlots[i]);
        }
    }

    public void showComponentSlots() {
        for (int i = 0; i < indexComponentSlots.length; ++i) {
            this.showSlot(indexComponentSlots[i]);
        }
    }

    private void drawComponentSlots() {
        for (int i = 0; i < indexComponentSlots.length; ++i) {
            this.drawBGSlot(indexComponentSlots[i]);
        }
    }

    private void drawButtons() {
        buttonOpen.setVisible(!getTile().isTuningMode());
        buttonClose.setVisible(!getTile().isClosedMode());
    }

    private void enableEquipmentSlot() {
        //((EquipmentSlot) inventorySlots.getSlot(indexEquipmentSlot)).setCanTakeStack(!getTile().isTuningMode());
    }
}
