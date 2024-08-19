package net.tefyert.techatech.api.block.machines.BE;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public interface IBasicMachine {

    void doRecipe();
    boolean canDoRecipe();


    // Implementations for Energy
    boolean doeHaveMaxEnergy();
    boolean  doesHaveEnergy();
    EnergyStorage getEnergyStorage(int maxEnergy);
    void useEnergy(int i);

    <T extends IBasicMachine> T setEnergyLevel(int EnergyLevel,int maxEnergy);

}
