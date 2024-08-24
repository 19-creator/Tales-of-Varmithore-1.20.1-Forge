package com.tov.tov;

import net.minecraft.resources.ResourceLocation;

public final class TVModInfo {


    public static final String MOD_ID = "tov";

    public static ResourceLocation createResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private TVModInfo(){}
}
