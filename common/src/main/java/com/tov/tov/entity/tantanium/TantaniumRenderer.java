package com.tov.tov.entity.tantanium;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TantaniumRenderer extends GeoEntityRenderer<TantaniumEntity> {
    public TantaniumRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TantaniumModel());
    }
}
