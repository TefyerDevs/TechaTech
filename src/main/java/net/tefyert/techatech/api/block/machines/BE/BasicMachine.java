package net.tefyert.techatech.api.block.machines.BE;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.EnergyStorage;
import org.jetbrains.annotations.Nullable;

public class BasicMachine extends BlockEntity implements IBasicMachine, MenuProvider {


    int energyLevel;
    EnergyStorage energy;
    public BasicMachine(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }






    @Override
    public void doRecipe() {

    }

    @Override
    public boolean canDoRecipe() {
        return false;
    }

    @Override
    public boolean doeHaveMaxEnergy() {
        return this.energy.getEnergyStored() == this.energy.getMaxEnergyStored();
    }

    @Override
    public boolean doesHaveEnergy() {
        return this.energy.getEnergyStored() != 0;
    }

    @Override
    public EnergyStorage getEnergyStorage(int maxEnergy) {
        return new EnergyStorage(this.energyLevel,maxEnergy);
    }

    @Override
    public void useEnergy(int i) {
        this.energy.extractEnergy(i,false);
    }

    @Override
    public <T extends IBasicMachine> T setEnergyLevel(int EnergyLevel, int maxEnergy) {
        this.energyLevel = EnergyLevel;
        this.energy = getEnergyStorage(maxEnergy);
        return (T) this;
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }

}
