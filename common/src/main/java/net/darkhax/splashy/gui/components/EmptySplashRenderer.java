package net.darkhax.splashy.gui.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;

public class EmptySplashRenderer extends SplashRenderer {

    public static final SplashRenderer EMPTY = new EmptySplashRenderer();

    private EmptySplashRenderer() {
        super("");
    }

    @Override
    public void render(GuiGraphics graphics, int screenWidth, Font font, int color) {
    }
}
