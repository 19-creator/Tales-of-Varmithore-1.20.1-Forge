package com.tov.tov;

import net.fabricmc.api.ModInitializer;

public class ToVFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();
    }
}
