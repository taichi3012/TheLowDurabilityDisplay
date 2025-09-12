package com.github.taichi3012.thelowdurabilitydisplay.listener;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.github.taichi3012.thelowdurabilitydisplay.config.TheLowDurabilityDisplayConfig;

public class ItemTooltipListener {

    private static final String THELOW_MAX_DURABILITY_TAG_KEY = "thelow_max_durability";
    private static final String THELOW_DEFAULT_MAX_DURABILITY_TAG_KEY = "thelow_default_max_durability";
    private static final String THELOW_NOW_DURABILITY_TAG_KEY = "thelow_now_durability";
    private static final String THELOW_ITEM_STRENGTH_TAG_KEY = "thelow_item_strength_level";

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (TheLowDurabilityDisplayConfig.getConfigData().onlyDisplayWhenEnabledAdvancedItemTooltips && !event.showAdvancedItemTooltips) {
            return;
        }

        NBTTagCompound tags = event.itemStack.getTagCompound();
        if (tags == null || !tags.hasKey(THELOW_DEFAULT_MAX_DURABILITY_TAG_KEY, Constants.NBT.TAG_SHORT) || !tags.hasKey(THELOW_NOW_DURABILITY_TAG_KEY, Constants.NBT.TAG_SHORT)) {
            return;
        }

        short now = tags.getShort(THELOW_NOW_DURABILITY_TAG_KEY);
        if (now == 0) {
            return;
        }

        short max = getMaxDurability(tags);
        List<String> tooltip = event.toolTip;
        tooltip.add(
            Math.max(tooltip.size() + (event.showAdvancedItemTooltips ? -2 : 0), 0),
            I18n.format("thelowdurabilitydisplay.remainingAndMaxDurabilityValues", max - now, max)
        );
    }

    private static short getMaxDurability(NBTTagCompound tags) {
      short defaultDurability = tags.getShort(THELOW_DEFAULT_MAX_DURABILITY_TAG_KEY);
      if (!isArmor(tags)) {
        return defaultDurability;
      }

      long strength = tags.getLong(THELOW_ITEM_STRENGTH_TAG_KEY);
      return (short) (defaultDurability + defaultDurability / 13.0 * strength);
    }

    private static boolean isArmor(NBTTagCompound tags) {
      return tags.hasKey("thelow_item_add_max_health", Constants.NBT.TAG_BYTE)
        || tags.hasKey("thelow_item_normal_armor_point", Constants.NBT.TAG_BYTE)
        || tags.hasKey("thelow_item_boss_armor_point", Constants.NBT.TAG_BYTE);
    }

}
