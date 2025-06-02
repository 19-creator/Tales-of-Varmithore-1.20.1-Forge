package com.tov.tov;

import com.tov.tov.registries.ToVCreativeTabs;
import com.tov.tov.registries.ToVEntities;
import software.bluelib.api.registry.AbstractRegistryBuilder;
import software.bluelib.api.registry.BlueRegistryBuilder;

public class CommonClass {
    public static AbstractRegistryBuilder REGISTRY = new BlueRegistryBuilder(Constants.MOD_ID);
    public static void init() {
        doRegistrations();
    }

    public static void doRegistrations() {
        ToVCreativeTabs.init();
        ToVEntities.init();
    }
}