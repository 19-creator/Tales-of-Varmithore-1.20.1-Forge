package com.tov.tov.client.entity.model;

import com.tov.tov.TVModInfo;
import com.tov.tov.common.entity.sumi.Sumi;
import com.tov.tov.common.entity.wilkor.Wilkor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class SumiModel extends GeoModel<Sumi> {

    @Override
    public ResourceLocation getModelResource(Sumi sumi) {
        return (!sumi.isBaby()) ? TVModInfo.createResource("geo/sumi.geo.json") : TVModInfo.createResource("geo/baby_wilkor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Sumi sumi) {
        return switch (sumi.getVariant()) {
            default -> TVModInfo.createResource("textures/entity/sumi/sumibluebase.png");
            case 1 -> TVModInfo.createResource("textures/entity/sumi/sumiblackblue.png");
            case 2 -> TVModInfo.createResource("textures/entity/sumi/sumibluered.png");
            case 3 -> TVModInfo.createResource("textures/entity/sumi/sumiredblue.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(Sumi sumi) {
        return TVModInfo.createResource("animations/sumi.animation.json");
    }

    @Override
    public void setCustomAnimations(Sumi sumi, long instanceId, AnimationState<Sumi> animationState) {
        CoreGeoBone root = getAnimationProcessor().getBone("Root");
        CoreGeoBone front = getAnimationProcessor().getBone("Front");
        CoreGeoBone head = getAnimationProcessor().getBone("Head");
        CoreGeoBone neck = getAnimationProcessor().getBone("Neck");
    }
}
