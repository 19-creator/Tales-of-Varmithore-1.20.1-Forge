package com.tov.tov.api.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bluelib.api.entity.variant.IVariantEntity;
import software.bluelib.api.registry.builders.entity.EntityBuilder;
import software.bluelib.entity.variant.IVariantAccessor;

import java.util.List;

public abstract class BaseTovDragon  extends TamableAnimal implements GeoEntity, IVariantEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected BaseTovDragon(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public String getVariantName() {
        return ((IVariantAccessor) this).getEntityVariantName();
    }

    public void setVariantName(String variantName) {
        ((IVariantAccessor) this).setEntityVariantName(variantName);
    }

    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, @Nullable SpawnGroupData spawnDataIn) {
        this.setInSittingPose(false);
        if (getVariantName() == null || getVariantName().isEmpty() || handleSpawnTypes(type)) {
            this.setVariantName(getRandomVariant(getEntityVariants(returnDragonType()), returnDragonType()));
        }

        return super.finalizeSpawn(level, difficulty, type, spawnDataIn);
    }

    public static boolean handleSpawnTypes(@NotNull MobSpawnType type) {
        return type.equals(MobSpawnType.SPAWN_EGG)
                || type.equals(MobSpawnType.MOB_SUMMONED)
                || type.equals(MobSpawnType.SPAWNER)
                || type.equals(MobSpawnType.CHUNK_GENERATION)
                || type.equals(MobSpawnType.BREEDING);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public String returnDragonType() {
        String typeName = BuiltInRegistries.ENTITY_TYPE.getKey(this.getType()).getPath();
        List<String> dragonNames = EntityBuilder.getDragonNames();
        if (dragonNames.contains(typeName)) {
            return typeName;
        }
        throw new IllegalStateException("Entity type " + typeName + " is not a registered dragon. Registered dragons: " + dragonNames);
    }
}
