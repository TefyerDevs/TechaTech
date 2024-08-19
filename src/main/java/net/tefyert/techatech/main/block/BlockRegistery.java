package net.tefyert.techatech.main.block;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.block.cable.block.CableBlock;
import net.tefyert.techatech.api.block.facade.FacadeBlock;
import net.tefyert.techatech.api.block.facade.FacadeBlockItem;
import net.tefyert.techatech.api.registration.BlockDeferredRegister;
import net.tefyert.techatech.api.utils.Value;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorBlock;
import net.tefyert.techatech.main.block.machines.MechanicalPressBlock;


public class BlockRegistery {
    public static final BlockDeferredRegister BLOCK_DEFERRED_REGISTER = new BlockDeferredRegister();

    public static final RegistryObject<Block> ENERGY_CABLE =
            BLOCK_DEFERRED_REGISTER.register("cable", CableBlock::new);

    public static final RegistryObject<Block> FACADE_BLOCK =
            BLOCK_DEFERRED_REGISTER.registerWithOutItem("facade", FacadeBlock::new);

    public static final RegistryObject<FacadeBlockItem> FACADE_ITEM =
            BlockDeferredRegister.registerItem("facade_item",()-> new FacadeBlockItem(FACADE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block>  BASIC_GENERATOR =
            BLOCK_DEFERRED_REGISTER.register("basic_generator", BasicGeneratorBlock::new);

    public static final RegistryObject<Block>  MECHANICAL_PRESS =
            BLOCK_DEFERRED_REGISTER.register("mechanical_press", MechanicalPressBlock::new);




    public static void register() {
    }

}
