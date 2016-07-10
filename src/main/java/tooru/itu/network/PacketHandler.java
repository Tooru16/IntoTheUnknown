package tooru.itu.network;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tooru.itu.consts.ModConsts;
import tooru.itu.consts.NetworkConsts;
import tooru.itu.network.packets.PacketChangeTunerMode;
import tooru.itu.network.packets.PacketMessage;

public class PacketHandler {
    private static int id = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public static int nextID() {
        return id++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModConsts.MOD_ID);
        INSTANCE.registerMessage(PacketMessage.Handler.class, PacketMessage.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketChangeTunerMode.Handler.class, PacketChangeTunerMode.class, nextID(), Side.SERVER);
    }

    public static void sendMessage(String msg) {
        PacketHandler.INSTANCE.sendToServer(new PacketMessage(msg));
    }

    public static void sendChangeTunerMode(BlockPos blockPos, int tunerMode) {
        PacketHandler.INSTANCE.sendToServer(new PacketChangeTunerMode(blockPos, tunerMode));
    }

}
