package net.gookieg.dying_world;

import com.mojang.logging.LogUtils;
import net.gookieg.dying_world.client.renderer.airdropCrate_renderer;
import net.gookieg.dying_world.client.renderer.biomarker_renderer;
import net.gookieg.dying_world.common.registry.DW_CreativeTabs;
import net.gookieg.dying_world.common.registry.DW_Entities;
import net.gookieg.dying_world.common.registry.DW_Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod(DyingWorldMod.MOD_ID)
public class DyingWorldMod {
    public static final String MOD_ID = "dying_world";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DyingWorldMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        DW_Items.init(modEventBus);
        DW_CreativeTabs.init(modEventBus);
        DW_Entities.init(modEventBus);

        modEventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(FMLClientSetupEvent event) {
        LOGGER.info("HELLO from common setup");

    }

    public void clientSetup(FMLClientSetupEvent event) {
        if(FMLEnvironment.dist == Dist.CLIENT) {
            // Register the curios renderers
            CuriosRendererRegistry.register(DW_Items.BIOMARKER.get(), biomarker_renderer::new);
            EntityRenderers.register(DW_Entities.AIRDROP_CRATE.get(), airdropCrate_renderer::new);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
