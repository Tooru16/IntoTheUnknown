package tooru.itu.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tooru.itu.ITU;
import tooru.itu.network.ModPacket;

public class PacketMessage implements IMessage {
    private String msg;

    public PacketMessage() { }

    public PacketMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        setMsg(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, getMsg());
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Handler implements IMessageHandler<PacketMessage, IMessage> {
        @Override
        public IMessage onMessage(PacketMessage message, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleMessage(message, ctx));
            return null;
        }

        private void handleMessage(PacketMessage message, MessageContext ctx) {
            ITU.logger.info("Packet recived! Msg = " + message.getMsg());
        }
    }

}
