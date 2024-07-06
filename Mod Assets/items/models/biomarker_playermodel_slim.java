// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class biomarker_playermodel_slim<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "biomarker_playermodel_slim"), "main");
	private final ModelPart global;

	public biomarker_playermodel_slim(ModelPart root) {
		this.global = root.getChild("global");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition global = partdefinition.addOrReplaceChild("global", CubeListBuilder.create().texOffs(13, 0).addBox(-2.125F, 6.8F, -1.25F, 0.125F, 1.2F, 3.1F, new CubeDeformation(0.0F))
		.texOffs(2, 0).addBox(-2.175F, 6.8F, -1.55F, 0.175F, 1.2F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-2.25F, 7.275F, -1.525F, 0.075F, 0.25F, 0.25F, new CubeDeformation(0.0F))
		.texOffs(1, 6).addBox(-2.25F, 7.2F, -1.65F, 0.25F, 0.4F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-2.25F, 7.2F, -2.25F, 0.25F, 0.4F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-2.15F, 7.2F, -2.15F, 0.15F, 0.4F, 0.5F, new CubeDeformation(0.0F))
		.texOffs(2, 2).addBox(-2.25F, 7.6F, -2.25F, 0.25F, 0.4F, 0.7F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(-2.25F, 6.8F, -2.25F, 0.25F, 0.4F, 0.7F, new CubeDeformation(0.0F))
		.texOffs(2, 6).addBox(-2.25F, 6.8F, 1.85F, 0.25F, 1.2F, 0.4F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-2.0F, 6.8F, -2.25F, 3.0F, 1.2F, 4.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r1 = global.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.1F, -0.1F, 0.0F, 0.2F, 0.2F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.175F, 7.4F, -2.15F, 0.0F, 0.0F, -0.7854F));

		PartDefinition RoundThing = global.addOrReplaceChild("RoundThing", CubeListBuilder.create(), PartPose.offset(-2.275F, 7.4F, -1.4F));

		PartDefinition cube_r2 = RoundThing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, -0.075F, -0.075F, 0.075F, 0.15F, 0.15F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition leds = global.addOrReplaceChild("leds", CubeListBuilder.create().texOffs(11, 31).addBox(-16.15F, -15.0F, 6.875F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(10, 31).addBox(-16.15F, -15.0F, 7.125F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(9, 31).addBox(-16.15F, -15.0F, 7.375F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(6, 31).addBox(-16.15F, -15.0F, 8.125F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(7, 31).addBox(-16.15F, -15.0F, 7.875F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(8, 31).addBox(-16.15F, -15.0F, 7.625F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(3, 31).addBox(-16.15F, -15.0F, 8.875F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(2, 31).addBox(-16.15F, -15.0F, 9.125F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(0, 31).addBox(-16.15F, -15.0F, 9.625F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(1, 31).addBox(-16.15F, -15.0F, 9.375F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(4, 31).addBox(-16.15F, -15.0F, 8.625F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F))
		.texOffs(5, 31).addBox(-16.15F, -15.0F, 8.375F, 0.1F, 0.8F, 0.1F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 22.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		global.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}