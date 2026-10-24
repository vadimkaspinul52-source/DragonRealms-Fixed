package com.neura.dragonrealms.registry;

import com.neura.dragonrealms.DragonRealms;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DragonRealms.MOD_ID);

    // In Minecraft 1.21.11 SpawnEggItem receives the entity through Item.Properties.
    // Resolve the entity inside the registration lambda to avoid early registry access.
    public static final DeferredItem<SpawnEggItem> FIRE_DRAKE_SPAWN_EGG = ITEMS.registerItem(
            "fire_drake_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.FIRE_DRAKE.get()))
    );

    public static final DeferredItem<Item> DRAGON_SCALE = ITEMS.registerSimpleItem("dragon_scale");

    private ModItems() {}
}
