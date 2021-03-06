package omtteam.openmodularturrets.entity.projectiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import omtteam.openmodularturrets.blocks.turretheads.BlockAbstractTurretHead;
import omtteam.openmodularturrets.entity.projectiles.damagesources.NormalDamageSource;
import omtteam.openmodularturrets.handler.ConfigHandler;
import omtteam.openmodularturrets.tileentity.TurretBase;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BlazingClayProjectile extends TurretProjectile {
    @SuppressWarnings("unused")
    public BlazingClayProjectile(World world) {
        super(world);
        this.gravity = 0.00F;
    }

    public BlazingClayProjectile(World world, ItemStack ammo, TurretBase turretBase) {
        super(world, ammo, turretBase);
        this.gravity = 0.00F;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onHitBlock(IBlockState hitBlock, BlockPos pos) {

        if (hitBlock.getBlock() instanceof BlockAbstractTurretHead) {
            return;
        }

        if (!hitBlock.getMaterial().isSolid()) {
            // Go through non solid block
            return;
        }


        if (!getEntityWorld().isRemote) {
            AxisAlignedBB axis = new AxisAlignedBB(this.posX - 5, this.posY - 5, this.posZ - 5,
                    this.posX + 5, this.posY + 5, this.posZ + 5);
            List<EntityLivingBase> targets = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, axis);

            int damage = ConfigHandler.getIncendiaryTurretSettings().getDamage();
            for (Entity mob : targets) {

                if (mob instanceof EntityPlayer) {
                    if (canDamagePlayer((EntityPlayer) mob)) {
                        mob.attackEntityFrom(new NormalDamageSource("bullet", fakeDrops, (WorldServer) this.getEntityWorld()), damage);
                        mob.hurtResistantTime = 0;
                        mob.setFire(5);
                        playSound();
                    }
                } else {
                    mob.attackEntityFrom(new NormalDamageSource("bullet", fakeDrops, (WorldServer) this.getEntityWorld()), damage);
                    mob.hurtResistantTime = 0;
                    mob.setFire(5);
                    playSound();
                }
            }
            this.setDead();
        }
    }

    @Override
    public void onHitEntity(Entity entity) {
        if (!getEntityWorld().isRemote && !(entity instanceof EntityPlayer && !canDamagePlayer((EntityPlayer) entity))
                && !(entity instanceof TurretProjectile)) {

            AxisAlignedBB axis = new AxisAlignedBB(this.posX - 5, this.posY - 5, this.posZ - 5,
                    this.posX + 5, this.posY + 5, this.posZ + 5);
            List<EntityLivingBase> targets = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, axis);

            int damage = ConfigHandler.getIncendiaryTurretSettings().getDamage();

            if (isAmped) {
                if (entity instanceof EntityLivingBase) {
                    EntityLivingBase elb = (EntityLivingBase) entity;
                    damage += ((int) elb.getHealth() * (getDamageAmpBonus() * amp_level));
                }
            }

            for (EntityLivingBase mob : targets) {
                setTagsForTurretHit(mob);

                if (mob instanceof EntityPlayer) {
                    if (canDamagePlayer((EntityPlayer) mob)) {
                        mob.attackEntityFrom(new NormalDamageSource("bullet", fakeDrops, (WorldServer) this.getEntityWorld()), damage);
                        mob.hurtResistantTime = 0;
                        mob.setFire(5);
                    }
                } else {
                    mob.attackEntityFrom(new NormalDamageSource("bullet", fakeDrops, (WorldServer) this.getEntityWorld()), damage);
                    mob.hurtResistantTime = 0;
                    mob.setFire(5);
                }
                setTagsForTurretHit(entity);
            }
            this.setDead();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
        this.posY = posY + 12F;
    }

    @Override
    protected float getGravityVelocity() {
        return this.gravity;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onImpact(RayTraceResult result) {
    }

    @Override
    public void playSound() {

    }

    @Override
    public double getDamageAmpBonus() {
        return ConfigHandler.getIncendiaryTurretSettings().getDamageAmp();
    }
}