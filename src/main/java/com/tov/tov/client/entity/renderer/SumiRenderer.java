package com.tov.tov.client.entity.renderer;

import com.tov.tov.client.entity.model.SumiModel;
import com.tov.tov.common.entity.sumi.Sumi;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SumiRenderer extends GeoEntityRenderer<Sumi> {

    public SumiRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SumiModel());
    }
}
