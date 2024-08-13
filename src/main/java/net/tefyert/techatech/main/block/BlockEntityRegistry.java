package net.tefyert.techatech.main.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.block.cable.block.CableBlockEntity;
import net.tefyert.techatech.api.block.facade.FacadeBlockEntity;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorBlockEntity;
import net.tefyert.techatech.main.Techatech;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Techatech.MODID);

    public static final RegistryObject<BlockEntityType<CableBlockEntity>> ENERGY_CABLE_BE =
            BLOCK_ENTITIES.register("cable_be",()-> BlockEntityType.Builder.of(CableBlockEntity::new,BlockRegistery.ENERGY_CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<FacadeBlockEntity>> ENERGY_FACADE_BE =
            BLOCK_ENTITIES.register("facade_be",()-> BlockEntityType.Builder.of(FacadeBlockEntity::new,BlockRegistery.FACADE_BLOCK.get()).build(null));

    public static final  RegistryObject<BlockEntityType<BasicGeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("basic_generator_be",()-> BlockEntityType.Builder.of(BasicGeneratorBlockEntity::new,BlockRegistery.BASIC_GENERATOR.get()).build(null));
}
