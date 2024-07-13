package com.github.taichi3012.thelowdurabilitydisplay;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.github.taichi3012.thelowdurabilitydisplay.config.TheLowDurabilityDisplayConfig;
import com.github.taichi3012.thelowdurabilitydisplay.listener.ItemTooltipListener;

@Mod(
    modid = TheLowDurabilityDisplay.MOD_ID,
    name = TheLowDurabilityDisplay.MOD_NAME,
    useMetadata = true,
    guiFactory = "com.github.taichi3012.thelowdurabilitydisplay.GuiFactoryTheLowDurabilityDisplay",
    clientSideOnly = true
)
public class TheLowDurabilityDisplay {

    public static final String MOD_ID = "thelowdurabilitydisplay";
    public static final String MOD_NAME = "TheLowDurabilityDisplay";

    public static final ItemTooltipListener ITEM_TOOLTIP_LISTENER = new ItemTooltipListener();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TheLowDurabilityDisplayConfig.init(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        if (TheLowDurabilityDisplayConfig.getConfigData().enabled) {
            MinecraftForge.EVENT_BUS.register(ITEM_TOOLTIP_LISTENER);
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(TheLowDurabilityDisplay.MOD_ID)) {
            TheLowDurabilityDisplayConfig.sync();
        }
    }

}
