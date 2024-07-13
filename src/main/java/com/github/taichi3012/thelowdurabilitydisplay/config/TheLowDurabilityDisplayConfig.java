package com.github.taichi3012.thelowdurabilitydisplay.config;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;

import com.github.taichi3012.thelowdurabilitydisplay.TheLowDurabilityDisplay;

public class TheLowDurabilityDisplayConfig {

    private static Configuration config;
    private static ConfigData configData;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        configData = getNewConfigData();
    }

    public static void sync() {
        configData = getNewConfigData();
        if (configData.enabled) {
            MinecraftForge.EVENT_BUS.register(TheLowDurabilityDisplay.ITEM_TOOLTIP_LISTENER);
        } else {
            MinecraftForge.EVENT_BUS.unregister(TheLowDurabilityDisplay.ITEM_TOOLTIP_LISTENER);
        }

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static ConfigData getConfigData() {
        return configData;
    }

    public static List<IConfigElement> getConfigElements() {
        return new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();
    }

    private static ConfigData getNewConfigData() {
        return new ConfigData(
            config.getBoolean(
                "enabled",
                Configuration.CATEGORY_GENERAL,
                true,
                "falseに設定するとModの機能が無効になります。",
                "thelowdurabilitydisplay.config.general.enabled"
            ),
            config.getBoolean(
                "onlyDisplayWhenEnabledAdvancedItemTooltips",
                Configuration.CATEGORY_GENERAL,
                false,
                "アイテムの詳細情報表示(F3+Hで切り替え)が有効なときのみ表示します。",
                "thelowdurabilitydisplay.config.general.onlyDisplayWhenEnabledAdvancedItemTooltips"
            )
        );
    }

}
