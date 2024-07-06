package net.gookieg.dying_world.client.renderer;

import net.gookieg.dying_world.DyingWorldMod;
import net.gookieg.dying_world.common.entity.airdropCrate_entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class airdropCrate_renderer extends GeoEntityRenderer<airdropCrate_entity> {

    public airdropCrate_renderer(EntityRendererProvider.Context context) {
        super(context, new DefaultedEntityGeoModel<>(new ResourceLocation(DyingWorldMod.MOD_ID, "airdrop_crate")));
    }
}

