package com.tov.tov.entity.tantanium;

import com.tov.tov.api.entity.BaseTovDragon;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.ARMOR;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.FLYING_SPEED;
import static net.minecraft.world.entity.ai.attributes.Attributes.FOLLOW_RANGE;
import static net.minecraft.world.entity.ai.attributes.Attributes.JUMP_STRENGTH;
import static net.minecraft.world.entity.ai.attributes.Attributes.STEP_HEIGHT;

public class TantaniumEntity extends BaseTovDragon {
    public TantaniumEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder bakeAttributes() {
        return Mob.createMobAttributes()
                .add(MAX_HEALTH, 100.0)
                .add(ARMOR_TOUGHNESS, 16.0)
                .add(MOVEMENT_SPEED, 0.10D)
                .add(FLYING_SPEED, 0.14)
                .add(ATTACK_DAMAGE, 8)
                .add(JUMP_STRENGTH, 12)
                .add(STEP_HEIGHT, 1)
                .add(FOLLOW_RANGE, Math.min(2048, 128))
                .add(ARMOR, 4);
    }
}
