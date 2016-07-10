package tooru.itu.network;

public class ModPacket {
    private String packetName;
    private String packetMsg;
    private int l;

    public ModPacket() {}

    public ModPacket(String name, String msg, int l) {
        setPacketName(name);
        setPacketMsg(msg);
        setL(l);
    }

    // SET

    public void setPacketName(String name) {
        this.packetName = name;
    }
    public void setPacketMsg(String msg) {
        this.packetMsg = msg;
    }
    public void setL(int l) {
        this.l = l;
    }

    // GET

    public String getPacketName() {
        return this.packetName;
    }
    public String getPacketMsg() {
        return this.packetMsg;
    }
    public int getL() {
        return this.l;
    }

}
