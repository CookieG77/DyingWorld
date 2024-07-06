package net.gookieg.dying_world.common.item;

import net.gookieg.dying_world.client.renderer.biomarker_renderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class biomarker_item extends Item implements GeoItem, ICurioItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final class biomarkerAnimations {
        // Animations for Steve model
        private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.BioMarker.idle");
        private static final RawAnimation INFECTION_LVL_1 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel1");
        private static final RawAnimation INFECTION_LVL_2 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel2");
        private static final RawAnimation INFECTION_LVL_3 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel3");
        private static final RawAnimation INFECTION_LVL_4 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel4");
        private static final RawAnimation INFECTION_LVL_5 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel5");
        private static final RawAnimation INFECTION_LVL_6 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel6");
        private static final RawAnimation INFECTION_LVL_7 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel7");
        private static final RawAnimation INFECTION_LVL_8 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel8");
        private static final RawAnimation INFECTION_LVL_9 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel9");
        private static final RawAnimation INFECTION_LVL_10 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel10");
        private static final RawAnimation INFECTION_LVL_11 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel11");
        private static final RawAnimation INFECTION_LVL_12 = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel12");

        // Animations for Alex model
        private static final RawAnimation IDLE_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.idle_slim");
        private static final RawAnimation INFECTION_LVL_1_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim1");
        private static final RawAnimation INFECTION_LVL_2_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim");
        private static final RawAnimation INFECTION_LVL_3_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim3");
        private static final RawAnimation INFECTION_LVL_4_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim4");
        private static final RawAnimation INFECTION_LVL_5_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim5");
        private static final RawAnimation INFECTION_LVL_6_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim6");
        private static final RawAnimation INFECTION_LVL_7_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim7");
        private static final RawAnimation INFECTION_LVL_8_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim8");
        private static final RawAnimation INFECTION_LVL_9_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim9");
        private static final RawAnimation INFECTION_LVL_10_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim10");
        private static final RawAnimation INFECTION_LVL_11_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim11");
        private static final RawAnimation INFECTION_LVL_12_SLIM = RawAnimation.begin().thenPlay("animation.BioMarker.infectionlevel_slim12");
    };

    public biomarker_item() {
        super(new Item.Properties().stacksTo(1).durability(0));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private biomarker_renderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(@NotNull LivingEntity livingEntity, @NotNull ItemStack itemStack, @NotNull EquipmentSlot equipmentSlot, @NotNull HumanoidModel<?> original) {
                if (this.renderer == null) this.renderer = new biomarker_renderer() {
                };
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            Entity entity = state.getData(DataTickets.ENTITY);

            if (entity instanceof AbstractClientPlayer player) {
                boolean isSlim = player.getModelName().equals("slim");
                state.setAnimation(isSlim ? biomarkerAnimations.IDLE_SLIM : biomarkerAnimations.IDLE);
                return PlayState.CONTINUE;
            }

            state.setAnimation(biomarkerAnimations.IDLE);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
