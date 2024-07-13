package com.github.taichi3012.thelowdurabilitydisplay;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import com.github.taichi3012.thelowdurabilitydisplay.listener.ItemTooltipListener;

@Mod(modid = TheLowDurabilityDisplay.MOD_ID, useMetadata = true)
public class TheLowDurabilityDisplay {
    public static final String MOD_ID = "thelowdurabilitydisplay";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ItemTooltipListener());
    }
}
