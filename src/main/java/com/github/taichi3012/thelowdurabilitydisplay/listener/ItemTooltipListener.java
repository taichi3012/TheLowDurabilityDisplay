package com.github.taichi3012.thelowdurabilitydisplay.listener;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.github.taichi3012.thelowdurabilitydisplay.config.TheLowDurabilityDisplayConfig;

public class ItemTooltipListener {

    private static final String THELOW_MAX_DURABILITY_TAG_KEY = "thelow_max_durability";
    private static final String THELOW_NOW_DURABILITY_TAG_KEY = "thelow_now_durability";

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (TheLowDurabilityDisplayConfig.getConfigData().onlyDisplayWhenEnabledAdvancedItemTooltips && !event.showAdvancedItemTooltips) {
            return;
        }

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
            I18n.format("thelowdurabilitydisplay.remainingAndMaxDurabilityValues", max - now, max)
        );
    }

}
