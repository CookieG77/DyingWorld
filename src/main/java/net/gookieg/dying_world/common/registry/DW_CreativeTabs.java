package net.gookieg.dying_world.common.registry;

import net.gookieg.dying_world.DyingWorldMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DW_CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DyingWorldMod.MOD_ID);

    // Register Creative Tabs
    public static final RegistryObject<CreativeModeTab> DYING_WORLD_TAB = CREATIVE_MODE_TABS.register("dying_world_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(DW_Items.MOD_ICON.get()))
                    .title(Component.translatable("creative_tab.%s.first".formatted(DyingWorldMod.MOD_ID)))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(DW_Items.BIOMARKER.get());
                    })
                    .withBackgroundLocation(new ResourceLocation(DyingWorldMod.MOD_ID, "textures/gui/container/creative_inventory/dying_world_tab_items.png"))
                    .withTabsImage(new ResourceLocation(DyingWorldMod.MOD_ID, "textures/gui/container/creative_inventory/dying_world_tabs.png"))
                    .build()
    );

    public static void init(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
