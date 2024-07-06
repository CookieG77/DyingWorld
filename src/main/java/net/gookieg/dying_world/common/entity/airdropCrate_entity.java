package net.gookieg.dying_world.common.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class airdropCrate_entity extends LivingEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Crate animations
    private class CrateAnimations {
        protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
        protected static final RawAnimation OPEN = RawAnimation.begin().thenPlayAndHold("open");
        protected static final RawAnimation SEARCHED = RawAnimation.begin().thenLoop("empty");

        protected static <T extends GeoAnimatable> AnimationController<T> closedCrateController(T animatable, airdropCrate_entity entity) {
            return new AnimationController<T>(animatable, "closed", state -> {
                if (entity.entityData.get(OPEN_STATE) == 0) {
                    return state.setAndContinue(IDLE);
                } else {
                    return PlayState.STOP;
                }
            });
        }

        protected static <T extends GeoAnimatable> AnimationController<T> openedCrateController(T animatable, airdropCrate_entity entity) {
            return new AnimationController<T>(animatable, "opened", state -> {
                if (entity.entityData.get(OPEN_STATE) == 1) {
                    state.setAnimation(OPEN);
                } else {
                    return PlayState.STOP;
                }
                return PlayState.CONTINUE;
            });
        }

        protected static <T extends GeoAnimatable> AnimationController<T> searchedCrateController(T animatable, airdropCrate_entity entity) {
            return new AnimationController<T>(animatable, "searched", state -> {
                if (entity.entityData.get(OPEN_STATE) == 2) {
                    return state.setAndContinue(SEARCHED);
                } else {
                    return PlayState.STOP;
                }
            });
        }
    }

    // OPEN_STATE : 0 -> closed, 1 -> opened, 2 -> searched
    private static final EntityDataAccessor<Byte> OPEN_STATE =  SynchedEntityData.defineId(airdropCrate_entity.class, EntityDataSerializers.BYTE);
    protected final SimpleContainer inventory = new SimpleContainer(8);


    public airdropCrate_entity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setMaxUpStep(0.0F);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OPEN_STATE, (byte) 0);
    }

    // #TODO: Make this work !!! The animations are completely fucked !!!
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(CrateAnimations.closedCrateController(this, this));
        controllers.add(CrateAnimations.openedCrateController(this, this));
        controllers.add(CrateAnimations.searchedCrateController(this, this));
        Level world = this.level();
        if (!world.isClientSide() && world.getServer() != null)
            world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(this.entityData.get(OPEN_STATE).toString()), false);


    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }

    // #TODO: Make this work !!! And add inventory and GUI correctly too
    @Override
    @NotNull
    public InteractionResult interact(Player player,InteractionHand hand) {
        InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());

        super.interact(player, hand);
        if (!this.level().isClientSide() && !player.isSpectator()) {
            player.sendSystemMessage(Component.literal("Interacted with airdrop crate"));
            switch (this.entityData.get(OPEN_STATE)) {
                case 0 -> this.entityData.set(OPEN_STATE, (byte) 1);
                case 1 -> this.entityData.set(OPEN_STATE, (byte) 2);
                case 2 -> {
                    this.kill();
                    this.entityData.set(OPEN_STATE, (byte) 3);
                }
            }
        }
        return retval;
    }

    /* #TODO: Make this work !!!
    public boolean hurt(DamageSource pSource, float pAmount) {
        return (pSource.getDirectEntity() instanceof Player player && player.isCreative()) || pSource.is(DamageTypeTags.IS_EXPLOSION) || pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }
    */
    @Override
    public void tickDeath() {
        this.remove(Entity.RemovalReason.KILLED);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public boolean ignoreExplosion() {
        // Ignore explosions so the crate doesn't get destroyed when landing
        return this.onGround();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.FOLLOW_RANGE, 16.0D)
            .add(Attributes.MOVEMENT_SPEED, 0D)
            .add(Attributes.MAX_HEALTH, 1D)
            .add(Attributes.ARMOR, 0D)
            .add(Attributes.ATTACK_DAMAGE, 0D)
            .add(Attributes.FLYING_SPEED, 0D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public boolean shouldShowName() {
        return false;
    }
}
