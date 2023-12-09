package net.tefyer.terratech.api;

import net.minecraftforge.energy.EnergyStorage;

public abstract class ModdedEnergyStorage extends EnergyStorage {

    public ModdedEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int extractEnergy = super.extractEnergy(maxReceive,simulate);
        if(extractEnergy != 0){
            onEnergyChange();
        }
        return extractEnergy;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int receiveEnergy = super.receiveEnergy(maxReceive,simulate);
        if(receiveEnergy != 0){
            onEnergyChange();
        }
        return receiveEnergy;
    }
    public int setEnergy(int energy){
        this.energy = energy;
        return energy;
    }
    public abstract void onEnergyChange();
}
