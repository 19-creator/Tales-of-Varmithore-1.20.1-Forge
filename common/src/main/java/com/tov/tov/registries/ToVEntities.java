package com.tov.tov.registries;

import com.tov.tov.entity.tantanium.TantaniumEntity;
import com.tov.tov.entity.tantanium.TantaniumRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static com.tov.tov.CommonClass.REGISTRY;

public class ToVEntities {
    public static void init() {}
    public static final Supplier<EntityType<TantaniumEntity>> TANTANIUM = REGISTRY.entity("tantanium", TantaniumEntity::new, MobCategory.CREATURE)
            .attributes(TantaniumEntity::bakeAttributes)
            .sized(1.9F, 2.0F)
            .renderer(TantaniumRenderer::new)
            .loadVariants()
            .spawnEgg(0X0000, 0X0000)
            .register();
}
