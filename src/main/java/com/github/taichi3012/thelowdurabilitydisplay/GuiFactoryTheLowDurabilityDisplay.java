package com.github.taichi3012.thelowdurabilitydisplay;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import com.github.taichi3012.thelowdurabilitydisplay.config.TheLowDurabilityDisplayConfig;

public class GuiFactoryTheLowDurabilityDisplay implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return GuiConfigTheLowDurabilityDisplay.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class GuiConfigTheLowDurabilityDisplay extends GuiConfig {
        public GuiConfigTheLowDurabilityDisplay(GuiScreen parent) {
            super(
                parent,
                TheLowDurabilityDisplayConfig.getConfigElements(),
                TheLowDurabilityDisplay.MOD_ID,
                false,
                false,
                TheLowDurabilityDisplay.MOD_NAME
            );
        }
    }

}
