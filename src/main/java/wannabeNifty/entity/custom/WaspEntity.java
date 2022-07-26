package wannabeNifty.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WaspEntity extends FlyingMob implements IAnimatable, FlyingAnimal {


    private AnimationFactory factory = new AnimationFactory(this);

    public WaspEntity(EntityType<? extends FlyingMob> entity, Level level) {
        super(entity, level);
    }


    public static AttributeSupplier setAttributes() {
        return FlyingMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.1f)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1 , new FloatGoal(this));

    }





    //GEOCKOLIB

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wasp.fly", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.wasp.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this , "controller", 0 , this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    @Override
    public boolean isFlying() {
        return true;
    }
}
