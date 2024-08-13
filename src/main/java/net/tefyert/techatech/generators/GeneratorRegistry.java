package net.tefyert.techatech.generators;

import net.minecraftforge.eventbus.api.IEventBus;
import net.tefyert.techatech.generators.block.GeneratorBlockRegistry;


public class GeneratorRegistry{
    public void register(IEventBus modEventBus){
        GeneratorBlockRegistry.BLOCK_DEFERRED_REGISTER.register(modEventBus);
    }
}