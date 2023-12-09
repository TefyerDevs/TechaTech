package net.tefyer.terratech.register;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyer.terratech.TerraTechMod;
import net.tefyer.terratech.blockentity.PowerBrickBlockEntity;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TerraTechMod.MODID);

    public static final RegistryObject<BlockEntityType<PowerBrickBlockEntity>> POWER_BRICK_BE = BLOCK_ENTITY.register("power_brick_be",
            () -> BlockEntityType.Builder.of(PowerBrickBlockEntity::new,BlockRegister.POWER_BRICK_BLOCK.get()).build(null));

    public static void register(IEventBus modEventBus){
        BLOCK_ENTITY.register(modEventBus);
    }
}
