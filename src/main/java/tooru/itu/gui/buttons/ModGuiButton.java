package tooru.itu.gui.buttons;

import net.minecraft.client.gui.GuiButton;

public class ModGuiButton extends GuiButton {
    public ModGuiButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y,buttonText);
    }

    public ModGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn,buttonText);
    }

    public void setVisible(boolean isVisible) {
        this.visible = isVisible;
        this.enabled = isVisible;
    }

}
