package com.neura.dragonrealms.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * First playable creature prototype. Expensive breath logic is throttled instead
 * of scanning nearby entities every game tick.
 */
public final class FireDrakeEntity extends Monster {
    private static final int BREATH_CHECK_INTERVAL = 10;
    private static final int BREATH_COOLDOWN_TICKS = 80;
    private static final double BREATH_RANGE_SQUARED = 100.0D;
    private int lastBreathTick = -BREATH_COOLDOWN_TICKS;

    public FireDrakeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 15;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.15D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.85D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!(this.level() instanceof ServerLevel serverLevel) || this.tickCount % BREATH_CHECK_INTERVAL != 0) {
            return;
        }
        if (this.tickCount - this.lastBreathTick < BREATH_COOLDOWN_TICKS) {
            return;
        }

        LivingEntity target = this.getTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        if (this.distanceToSqr(target) > BREATH_RANGE_SQUARED || !this.hasLineOfSight(target)) {
            return;
        }

        this.lastBreathTick = this.tickCount;
        target.setSecondsOnFire(3);
        target.hurtServer(serverLevel, this.damageSources().mobAttack(this), 5.0F);
        serverLevel.playSound(null, this, SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.0F, 0.75F);

        double mouthX = this.getX() + this.getLookAngle().x * 1.2D;
        double mouthY = this.getY() + this.getBbHeight() * 0.65D;
        double mouthZ = this.getZ() + this.getLookAngle().z * 1.2D;
        serverLevel.sendParticles(ParticleTypes.FLAME, mouthX, mouthY, mouthZ, 12, 0.25D, 0.2D, 0.25D, 0.03D);
        serverLevel.sendParticles(ParticleTypes.SMOKE, mouthX, mouthY, mouthZ, 4, 0.18D, 0.15D, 0.18D, 0.01D);
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource source) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }
}
