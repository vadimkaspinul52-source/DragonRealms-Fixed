package com.neura.dragonrealms.client.model;

import com.neura.dragonrealms.DragonRealms;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public final class FireDrakeModel extends EntityModel<FireDrakeRenderState> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(DragonRealms.MOD_ID, "fire_drake"), "main");

    private final ModelPart head;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public FireDrakeModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-6.0F, -4.0F, -8.0F, 12.0F, 8.0F, 16.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        root.addOrReplaceChild("neck", CubeListBuilder.create()
                .texOffs(20, 32).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 7.0F),
                PartPose.offset(0.0F, 11.0F, -7.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 7.0F, 8.0F)
                .texOffs(32, 32).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 3.0F, 4.0F)
                .texOffs(44, 32).addBox(-4.0F, -6.0F, -2.0F, 2.0F, 3.0F, 2.0F)
                .addBox(2.0F, -6.0F, -2.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 10.0F, -11.0F));

        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 48).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 13.0F),
                PartPose.offset(0.0F, 13.0F, 8.0F));

        root.addOrReplaceChild("left_wing", CubeListBuilder.create()
                .texOffs(28, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 14.0F),
                PartPose.offset(6.0F, 10.0F, -1.0F));
        root.addOrReplaceChild("right_wing", CubeListBuilder.create()
                .texOffs(28, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 14.0F),
                PartPose.offset(-6.0F, 10.0F, -1.0F));

        root.addOrReplaceChild("front_left_leg", CubeListBuilder.create()
                .texOffs(44, 40).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F),
                PartPose.offset(4.0F, 16.0F, -4.0F));
        root.addOrReplaceChild("front_right_leg", CubeListBuilder.create()
                .texOffs(44, 40).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F),
                PartPose.offset(-4.0F, 16.0F, -4.0F));
        root.addOrReplaceChild("back_left_leg", CubeListBuilder.create()
                .texOffs(44, 40).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F),
                PartPose.offset(4.0F, 16.0F, 5.0F));
        root.addOrReplaceChild("back_right_leg", CubeListBuilder.create()
                .texOffs(44, 40).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F),
                PartPose.offset(-4.0F, 16.0F, 5.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(FireDrakeRenderState state) {
        super.setupAnim(state);
        float flap = Mth.sin(state.ageInTicks * 0.38F) * 0.10F;
        this.leftWing.zRot = flap;
        this.rightWing.zRot = -flap;
        this.head.xRot = Mth.sin(state.ageInTicks * 0.07F) * 0.025F;
    }
}
