package com.neura.dragonrealms.client;

import com.neura.dragonrealms.DragonRealms;
import com.neura.dragonrealms.client.model.FireDrakeModel;
import com.neura.dragonrealms.client.renderer.FireDrakeRenderer;
import com.neura.dragonrealms.registry.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = DragonRealms.MOD_ID, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.FIRE_DRAKE.get(), FireDrakeRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FireDrakeModel.LAYER, FireDrakeModel::createBodyLayer);
    }
}
