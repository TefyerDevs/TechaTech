package net.tefyert.techatech.main.block.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tefyert.techatech.api.block.machines.BE.BasicMachine;
import net.tefyert.techatech.api.energy.ModEnergyStorage;
import net.tefyert.techatech.api.energy.TechaEnergyStorage;
import net.tefyert.techatech.api.recipe.MechanicalPressRecipe;
import net.tefyert.techatech.main.block.BlockEntityRegistry;
import net.tefyert.techatech.main.client.screen.container.ContainerRegistry;
import net.tefyert.techatech.main.client.screen.container.custom.MechanicalPressContainer;
import net.tefyert.techatech.main.item.ItemRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

public class MechanicalPressBlockEntity extends BasicMachine {

    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    public static final String ENERGY_TAG = "Energy";

    public static final int MAXTRANSFER = 100;
    public static final int CAPACITY = 1000;

    private final ModEnergyStorage energy = createEnergyStorage();
    private LazyOptional<EnergyStorage> lazyEnergyHandler = LazyOptional.of(() -> new EnergyStorage(1000));
    private static final int ENERGY_REQ = 32;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    public MechanicalPressBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.MECHANICAL_PRESS.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> MechanicalPressBlockEntity.this.progress;
                    case 1 -> MechanicalPressBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> MechanicalPressBlockEntity.this.progress =pValue;
                    case 1 -> MechanicalPressBlockEntity.this.maxProgress =pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    public IEnergyStorage getEnergyStorage() {
        return energy;
    }

    public void getEnergy(int energy) {
        this.energy.extractEnergy(energy,false);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        } else if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> energy);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.techatech.mechanical_press");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new MechanicalPressContainer(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("mechanical_press.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("mechanical_press.progress");
        energy.setEnergy(pTag.getInt("gem_infusing_station.energy"));
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        assert level != null;
        MechanicalPressBlockEntity be = (MechanicalPressBlockEntity) level.getBlockEntity(pPos);
        if(hasRecipe() && hasEnoughEnergy(Objects.requireNonNull(be))) {
            increaseCraftingProgress();
            extractEnergy(be);
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }


    private static void extractEnergy(MechanicalPressBlockEntity pEntity) {
        pEntity.energy.extractEnergy(ENERGY_REQ, false);
    }

    private static boolean hasEnoughEnergy(MechanicalPressBlockEntity pEntity) {
        return pEntity.energy.getEnergyStored() >= ENERGY_REQ * pEntity.maxProgress;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<MechanicalPressRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Optional<MechanicalPressRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<MechanicalPressRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(MechanicalPressRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    @Nonnull
    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(CAPACITY, MAXTRANSFER) {
            @Override
            public void onEnergyChanged() {
                setChanged();
            }
        };
    }


}
