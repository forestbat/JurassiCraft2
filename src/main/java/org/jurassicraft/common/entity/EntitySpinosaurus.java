package org.jurassicraft.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.client.AnimID;

import org.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.common.entity.base.EntityDinosaurAggressive;

import java.util.Random;

public class EntitySpinosaurus extends EntityDinosaurAggressive // implements IEntityAICreature, IOmnivore
{
    private static final String[] hurtSounds = new String[] { "spinosaurus_hurt_1" };
    private static final String[] livingSounds = new String[] { "spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4" };
    private static final String[] deathSounds = new String[] { "spinosaurus_death_1", "spinosaurus_death_2" };

    private static final Class[] targets = { EntityTyrannosaurus.class, EntityMob.class, EntityAnimal.class, EntityIndominus.class, EntityCompsognathus.class, EntityAnkylosaurus.class, EntityPlayer.class, EntityDilophosaurus.class, EntityDimorphodon.class, EntityDodo.class, EntityLeaellynasaura.class, EntityLudodactylus.class, EntityHypsilophodon.class, EntityGallimimus.class, EntitySegisaurus.class, EntityProtoceratops.class, EntityParasaurolophus.class, EntityOthnielia.class, EntityMicroceratus.class, EntityTriceratops.class, EntityStegosaurus.class, EntityBrachiosaurus.class, EntityApatosaurus.class, EntityRugops.class, EntityHerrerasaurus.class, EntityVelociraptor.class, EntityAchillobator.class, EntityCarnotaurus.class };

    private int stepCount = 0;

    public EntitySpinosaurus(World world)
    {
        super(world);

        for (int i = 0; i < targets.length; i++)
        {
            this.addAIForAttackTargets(targets[i], new Random().nextInt(3) + 1);
        }
        
        this.defendFromAttacker(EntityLivingBase.class, 0);

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, AnimID.INJURED, 750, "jurassicraft:spinosaurus_hurt_1", 1.5F));
    }
    
    public float getNewHealth()
    {
        return 250.0F;
    }
    
    public void updateCreatureData()
    {
        double prevHealth = getMaxHealth();
        double newHealth = getNewHealth();

        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(newHealth);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(transitionFromAge(dinosaur.getBabySpeed(), dinosaur.getAdultSpeed()));
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(transitionFromAge(dinosaur.getBabyKnockback(), dinosaur.getAdultKnockback()));

        // adjustHitbox();

        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30.0F);

        if (prevHealth != newHealth)
        {
            heal((float) (newHealth - lastDamage));
        }
    }

    @Override
    public int getTailBoxCount()
    {
        return 7;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}
