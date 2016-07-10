package tooru.itu.gui.guiscreens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tooru.itu.ITU;
import tooru.itu.network.PacketHandler;

import java.io.IOException;

public class GuiMainScreen extends GuiScreen {

    GuiButton a;
    GuiButton b;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void initGui() {
        this.buttonList.add(this.a = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 24, "This is button a"));
        this.buttonList.add(this.b = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 4, "This is button b"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button == this.a) {
            ITU.logger.info("Button a pressed");
            PacketHandler.sendMessage("");
        }
        if(button == this.b) {
            ITU.logger.info("Button b pressed");
            PacketHandler.sendMessage("Button b sent Packet on server and it was recieved!");
        }
    }
}
