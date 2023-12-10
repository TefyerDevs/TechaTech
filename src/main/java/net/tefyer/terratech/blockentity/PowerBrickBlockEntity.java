package net.tefyer.terratech.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.tefyer.terratech.api.ModdedEnergyStorage;
import net.tefyer.terratech.client.menu.PowerBrickMenu;
import net.tefyer.terratech.register.BlockEntityRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerBrickBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;

    public PowerBrickBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.POWER_BRICK_BE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                ;
            }

            @Override
            public int getCount() {
                return 0;
            }
        };
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


    @Override
    public Component getDisplayName() {
        return Component.literal("");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory pPlayerInventory, Player pPlayer) {
        return new PowerBrickMenu(id,pPlayerInventory,this,this.data);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, BlockEntity blockEntity) {
        if(level.isClientSide()){
            return;
        }

    }

    public int getEnergy() {
        return this.ENERGY_STORAGE.getEnergyStored();
    }
}
