package net.tefyert.techatech.generators.block;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.block.cable.block.CableBlockEntity;
import net.tefyert.techatech.api.block.facade.FacadeBlockEntity;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorBlockEntity;
import net.tefyert.techatech.main.Techatech;


public class GeneratorBlockEntityRegistry {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Techatech.MODID);
public static final  RegistryObject<BlockEntityType<BasicGeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("basic_generator_be",
            ()-> BlockEntityType.Builder.of(BasicGeneratorBlockEntity::new,GeneratorBlockRegistry.BASIC_GENERATOR.get()).build(null));

}