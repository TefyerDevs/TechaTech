package net.tefyer.terratech.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.tefyer.terratech.api.ModdedEnergyStorage;
import net.tefyer.terratech.register.BlockEntityRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerBrickBlockEntity extends BlockEntity {
    public PowerBrickBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.POWER_BRICK_BE.get(), pos, state);
    }
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    private final ModdedEnergyStorage ENERGY_STORAGE = new ModdedEnergyStorage(60000,256){

        @Override
        public void onEnergyChange() {
            setChanged();
        }
    };

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY){
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler = LazyOptional.of(()->ENERGY_STORAGE);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("powerbrick.energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ENERGY_STORAGE.setEnergy(nbt.getInt("powerbrick.energy"));
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, BlockEntity blockEntity) {

    }
}
