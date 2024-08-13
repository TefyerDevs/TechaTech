package net.tefyert.techatech.generators.block;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tefyert.techatech.api.registration.BlockDeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorBlock;

public class GeneratorBlockRegistry {
    public static final BlockDeferredRegister BLOCK_DEFERRED_REGISTER = new BlockDeferredRegister();

    public static final RegistryObject<Block>  BASIC_GENERATOR =
            BLOCK_DEFERRED_REGISTER.register("basic_generator", BasicGeneratorBlock::new);


}
