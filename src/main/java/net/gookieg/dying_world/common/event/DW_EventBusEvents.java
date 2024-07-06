package net.gookieg.dying_world.common.event;

import net.gookieg.dying_world.DyingWorldMod;
import net.gookieg.dying_world.common.entity.airdropCrate_entity;
import net.gookieg.dying_world.common.registry.DW_Entities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DyingWorldMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DW_EventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        // Register the attributes of the mod entities.
        event.put(DW_Entities.AIRDROP_CRATE.get(), airdropCrate_entity.createAttributes().build());
    }
}
