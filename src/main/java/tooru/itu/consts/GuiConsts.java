package tooru.itu.consts;

public class GuiConsts {

    public static final int GUI_ID_GLOVE = 0;
    public static final int GUI_ID_TUNER = 1;

    public static final int SLOT_OFFHAND = 40;

    private static final String GUICONTAINER_PATH = ModConsts.MOD_ID + ":textures/gui/container/";
    public static final String GUICONTAINER_EQUIPMENTTUNER = GUICONTAINER_PATH + "tileEquipmentTuner.png";

    // COMMON GUI CONSTS
    public static final int X_OFFSET = 18;
    public static final int Y_OFFSET = 18;

    public static final int X_HIDE = 8192;
    public static final int Y_HIDE = X_HIDE;

    // EQUIPMENT TUNNER
    public static final int ET_XSIZE = 198;
    public static final int ET_YSIZE = 240;

    public static final int ET_HOTBAR_X = 30;
    public static final int ET_HOTBAR_Y = 216;

    public static final int ET_INV_X = ET_HOTBAR_X;
    public static final int ET_INV_Y =  158;
    public static final int ET_INV_XSIZE = 9*X_OFFSET;

    public static final int ET_OFFHAND_X = 4;
    public static final int ET_OFFHAND_Y = 9;

    public static final int ET_EQUIPMENT_X = ET_HOTBAR_X + 4*X_OFFSET;
    public static final int ET_EQUIPMENT_Y = ET_OFFHAND_Y;

    public static final int ET_CORE_X = ET_EQUIPMENT_X;
    public static final int ET_CORE_Y = ET_EQUIPMENT_Y + 2*Y_OFFSET;

    public static final int ET_HIDDENSLOT_X = 202;
    public static final int ET_HIDDENSLOT_Y = 2;

    public static final int ET_SLOT_X = 202;
    public static final int ET_SLOT_Y = 20;

    public static final int indexOffhandSlot = 36;
    public static final int indexEquipmentSlot = 37;
    public static final int[] indexComponentSlots = {38};
    //public static final int ET_MAXSLOTS

    // COLORS
    public static final int clBlack = 0x000000;

}
