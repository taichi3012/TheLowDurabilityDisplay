package com.github.taichi3012.thelowdurabilitydisplay;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = TheLowDurabilityDisplay.MOD_ID, useMetadata = true)
public class TheLowDurabilityDisplay {
    public static final String MOD_ID = "thelowdurabilitydisplay";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final String THELOW_MAX_DURABILITY_TAG_KEY = "thelow_max_durability";
    private static final String THELOW_NOW_DURABILITY_TAG_KEY = "thelow_now_durability";
    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        NBTTagCompound tags = event.itemStack.getTagCompound();
        if (tags == null || !tags.hasKey(THELOW_MAX_DURABILITY_TAG_KEY) || !tags.hasKey(THELOW_NOW_DURABILITY_TAG_KEY)) {
            return;
        }

        short now = tags.getShort(THELOW_NOW_DURABILITY_TAG_KEY);
        if (now == 0) {
            return;
        }

        short max = tags.getShort(THELOW_MAX_DURABILITY_TAG_KEY);
        List<String> tooltip = event.toolTip;
        tooltip.add(
            Math.max(tooltip.size() + (event.showAdvancedItemTooltips ? -2 : 0), 0),
            String.format("§aTheLowDurability: %d / %d", max - now, max)
        );
    }
}
