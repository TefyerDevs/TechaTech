package net.tefyert.techatech.api.energy;


import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class TechaEnergyStorage extends EnergyStorage {
    public TechaEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }
}