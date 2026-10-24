package com.neura.dragonrealms.registry;

import com.neura.dragonrealms.DragonRealms;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = DragonRealms.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ModCreativeTabs {
    private ModCreativeTabs() {}

    @SubscribeEvent
    public static void addItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.FIRE_DRAKE_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.DRAGON_SCALE);
        }
    }
}
