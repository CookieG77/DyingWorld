package net.gookieg.dying_world.common.registry;

import net.gookieg.dying_world.DyingWorldMod;
import net.gookieg.dying_world.common.entity.airdropCrate_entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DW_Entities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DyingWorldMod.MOD_ID);

    public static final RegistryObject<EntityType<airdropCrate_entity>> AIRDROP_CRATE = ENTITIES.register("airdrop_crate",
            () -> EntityType.Builder.of(airdropCrate_entity::new, MobCategory.MISC)
                    .sized(1.2F, 1.0F)
                    .build(DyingWorldMod.MOD_ID + ":airdrop_crate"));


    public static void init(IEventBus modEventBus) {
        ENTITIES.register(modEventBus);
    }
}
