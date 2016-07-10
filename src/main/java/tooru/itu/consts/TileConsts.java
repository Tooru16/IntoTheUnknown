package tooru.itu.consts;

public class TileConsts {

    public static final String CUSTOMNAME_ROOT = "container.";
    public static final String CUSTOMNAME_DEFAULT = CUSTOMNAME_ROOT + "tileInventory";

    // EQUIPMENT TUNER
    public static final String EQUIPMENTTUNER_NAME = "tileEquipmentTuner";
    public static final String EQUIPMENTTUNER_CUSTOMNAME = CUSTOMNAME_ROOT + EQUIPMENTTUNER_NAME;
    public static final int EQUIPMENTTUNER_INVENTORYSIZE = 2;
    public static final int EQUIPMENTTUNER_CLOSEDMODE = 0;
    public static final int EQUIPMENTTUNER_TUNINGMODE = 1;

    public static final int INDEX_EQUIPMENT = 0;
    public static final int INDEX_CORE = 1;

    // NBT TAGS
    public static final String TAG_INVENTORY = "inventory";
    public static final String TAG_SLOT = "slot";
    public static final String TAG_SIZEINVENTORY = "sizeInventory";
    public static final String TAG_CUSTOMNAME = "customName";
    public static final String TAG_TUNERMODE = "tunerMode";

    // OTHER
    public static final int VAR_INVENTORYSIZE_MAX = 36;
    public static final int VAR_INVENTORYSIZE_DEFAULT = 9;
    public static final int VAR_INVENTORYSTACK_MAX = 64;

}
