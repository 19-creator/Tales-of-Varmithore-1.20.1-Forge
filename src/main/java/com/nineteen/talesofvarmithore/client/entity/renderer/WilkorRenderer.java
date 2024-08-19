package com.nineteen.talesofvarmithore.client.entity.renderer;

import com.nineteen.talesofvarmithore.client.entity.model.WilkorModel;
import com.nineteen.talesofvarmithore.common.entity.wilkor.Wilkor;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WilkorRenderer extends GeoEntityRenderer<Wilkor> {

    public WilkorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WilkorModel());
    }
}
