package com.neura.dragonrealms;

import com.neura.dragonrealms.registry.ModEntities;
import com.neura.dragonrealms.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DragonRealms.MOD_ID)
public final class DragonRealms {
    public static final String MOD_ID = "dragonrealms";

    public DragonRealms(IEventBus modEventBus) {
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        modEventBus.addListener(ModEntities::registerAttributes);
    }
}
