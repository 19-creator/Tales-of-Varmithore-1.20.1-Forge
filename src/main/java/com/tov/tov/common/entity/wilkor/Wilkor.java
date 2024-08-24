package com.tov.tov.common.entity.wilkor;

import com.tov.tov.api.common.TamableMob;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.UUID;

public class Wilkor extends TamableMob implements GeoEntity, SmartBrainOwner<Wilkor> {

    private static final EntityDataAccessor<Boolean> DATA_IS_SPRINTING = SynchedEntityData.defineId(Wilkor.class, EntityDataSerializers.BOOLEAN);

    private static final UUID WILKOR_SPEED_MODIFIER_SPRINTING_UUID = UUID.fromString("469b11ea-5670-4126-8e3f-aa13c7d2da31");
    private static final AttributeModifier WILKOR_SPEED_MODIFIER_SPRINTING = new AttributeModifier(WILKOR_SPEED_MODIFIER_SPRINTING_UUID, "Sprinting speed boost", (double)0.45F, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public Wilkor(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_IS_SPRINTING, false);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide) setSprinting(this.getTarget() != null && !this.getTarget().isDeadOrDying());
        if(!this.level().isClientSide && this.level().isDay()) startSleeping(this.blockPosition());
    }

    @Override
    public void setSprinting(boolean pSprinting) {
        if(!this.level().isClientSide) {
            this.setSharedFlag(3, pSprinting);

            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            if (attributeinstance.getModifier(WILKOR_SPEED_MODIFIER_SPRINTING_UUID) != null) {
                attributeinstance.removeModifier(WILKOR_SPEED_MODIFIER_SPRINTING);
            }

            if (pSprinting) {
                attributeinstance.addTransientModifier(WILKOR_SPEED_MODIFIER_SPRINTING);
            }

            this.getEntityData().set(DATA_IS_SPRINTING, pSprinting);
        }
    }

    @Override
    public List<ExtendedSensor<Wilkor>> getSensors() {
        return ObjectArrayList.of(
                new NearbyPlayersSensor<>(),
                new NearbyLivingEntitySensor<Wilkor>()
                        .setPredicate(
                                (target, entity) -> target instanceof Player || target instanceof Chicken
                                        || target instanceof Cow || target instanceof Sheep
                                        || target instanceof Pig || target instanceof Hoglin
                                        || target instanceof Rabbit || target instanceof Monster
                        ),
                new HurtBySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends Wilkor> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends Wilkor> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<Wilkor>(      // Run only one of the below behaviours, trying each one in order. Include the generic type because JavaC is silly
                        new TargetOrRetaliate<>()            // Set the attack target and walk target based on nearby entities
                ),
                new OneRandomBehaviour<>(                 // Run a random task from the below options
                        new SetRandomWalkTarget<>(),          // Set a random walk target to a nearby position
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))) // Do nothing for 1.5->3 seconds
        );
    }

    @Override
    public BrainActivityGroup<? extends Wilkor> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(5)
        );
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected Brain.@NotNull Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    private PlayState predicate(AnimationState<Wilkor> event) {
        if(this.isSleeping()) return event.setAndContinue(RawAnimation.begin().thenLoop("direwolf.sleep"));
        if (event.isMoving()) {
            if (this.isVehicle() || this.isSprinting()) return event.setAndContinue(RawAnimation.begin().thenLoop("direwolf.run"));

            return event.setAndContinue(RawAnimation.begin().thenLoop("direwolf.walk"));
        }

        return event.setAndContinue(RawAnimation.begin().thenLoop("direwolf.idle"));
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        if(!this.isTame() && itemstack.is(Items.BONE)) {
            if (pPlayer.isCreative()) tame(pPlayer);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void setPose(Pose pPose) {
        if(pPose != Pose.SLEEPING) super.setPose(pPose);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "main", 5, this::predicate));
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.ATTACK_DAMAGE, 13.0f)
                .add(Attributes.ATTACK_SPEED, 5.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.JUMP_STRENGTH, 2)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .build();
    }

    public int getVariant(){
        return 0;
    }

    //Gecko Lib Stuff

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
