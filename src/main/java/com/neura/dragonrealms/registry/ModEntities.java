package com.neura.dragonrealms.registry;

import com.neura.dragonrealms.DragonRealms;
import com.neura.dragonrealms.entity.FireDrakeEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(DragonRealms.MOD_ID);

    public static final Supplier<EntityType<FireDrakeEntity>> FIRE_DRAKE = ENTITY_TYPES.registerEntityType(
            "fire_drake",
            FireDrakeEntity::new,
            MobCategory.MONSTER,
            builder -> builder.sized(1.8F, 1.65F).clientTrackingRange(8).updateInterval(3)
    );

    private ModEntities() {}

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier.Builder attributes = Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 90.0D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.27D)
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
        event.put(FIRE_DRAKE.get(), attributes.build());
    }
}
