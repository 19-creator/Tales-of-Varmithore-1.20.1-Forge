package com.tov.tov.setup.common.registries;

import com.tov.tov.TVModInfo;
import com.tov.tov.common.entity.sumi.Sumi;
import com.tov.tov.common.entity.wilkor.Wilkor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class TVEntities {

    static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TVModInfo.MOD_ID);

    public static final RegistryObject<EntityType<Wilkor>> WILKOR = entity(
            "wilkor",
            EntityType.Builder.of(Wilkor::new, MobCategory.MISC).sized(1.3f,2.4f)
    );

    public static final RegistryObject<EntityType<Sumi>> SUMI = entity(
            "sumi",
            EntityType.Builder.of(Sumi::new, MobCategory.MISC).sized(1.3f,2.4f)
    );


    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(TVEntities.WILKOR.get(), Wilkor.createAttributes());
        event.put(TVEntities.SUMI.get(), Sumi.createAttributes());
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> entity(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(TVModInfo.MOD_ID, name).toString()));
    }

    private TVEntities(){}
}
