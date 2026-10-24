package com.neura.dragonrealms.client.renderer;

import com.neura.dragonrealms.DragonRealms;
import com.neura.dragonrealms.client.model.FireDrakeModel;
import com.neura.dragonrealms.client.model.FireDrakeRenderState;
import com.neura.dragonrealms.entity.FireDrakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public final class FireDrakeRenderer extends MobRenderer<FireDrakeEntity, FireDrakeRenderState, FireDrakeModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            DragonRealms.MOD_ID, "textures/entity/fire_drake.png");

    public FireDrakeRenderer(EntityRendererProvider.Context context) {
        super(context, new FireDrakeModel(context.bakeLayer(FireDrakeModel.LAYER)), 0.85F);
    }

    @Override
    public FireDrakeRenderState createRenderState() {
        return new FireDrakeRenderState();
    }

    @Override
    public Identifier getTextureLocation(FireDrakeRenderState state) {
        return TEXTURE;
    }
}
