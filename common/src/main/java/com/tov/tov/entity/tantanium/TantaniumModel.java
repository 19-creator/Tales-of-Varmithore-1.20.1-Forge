package com.tov.tov.entity.tantanium;

import com.tov.tov.Constants;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TantaniumModel extends GeoModel<TantaniumEntity> {
    @Override
    public ResourceLocation getModelResource(TantaniumEntity tantaniumEntity) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "geo/tantanium.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TantaniumEntity tantaniumEntity) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/" + tantaniumEntity.returnDragonType() + "/" + tantaniumEntity.getVariantName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TantaniumEntity tantaniumEntity) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "animations/tantanium.animation.json");
    }
}
