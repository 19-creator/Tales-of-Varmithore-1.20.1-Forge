package com.tov.tov.client.entity.model;

import com.tov.tov.TVModInfo;
import com.tov.tov.common.entity.wilkor.Wilkor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class WilkorModel extends GeoModel<Wilkor> {

    @Override
    public ResourceLocation getModelResource(Wilkor wilkor) {
        return (!wilkor.isBaby()) ? TVModInfo.createResource("geo/wilkor.geo.json") : TVModInfo.createResource("geo/baby_wilkor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Wilkor wilkor) {
        return switch (wilkor.getVariant()) {
            default -> TVModInfo.createResource("textures/entity/wilkor/wilkor.png");
            case 1 -> TVModInfo.createResource("textures/entity/wilkor/wilkor_brown.png");
            case 2 -> TVModInfo.createResource("textures/entity/wilkor/wilkor_dark.png");
            case 3 -> TVModInfo.createResource("textures/entity/wilkor/wilkor_grey.png");
            case 4 -> TVModInfo.createResource("textures/entity/wilkor/wilkor_redsand.png");
            case 5 -> TVModInfo.createResource("textures/entity/wilkor/wilkor_sand.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(Wilkor wilkor) {
        return TVModInfo.createResource("animations/wilkor.animation.json");
    }

    @Override
    public void setCustomAnimations(Wilkor wilkor, long instanceId, AnimationState<Wilkor> animationState) {
        CoreGeoBone root = getAnimationProcessor().getBone("Root");
        CoreGeoBone front = getAnimationProcessor().getBone("Front");
        CoreGeoBone head = getAnimationProcessor().getBone("Head");
        CoreGeoBone neck = getAnimationProcessor().getBone("Neck");
    }
}
