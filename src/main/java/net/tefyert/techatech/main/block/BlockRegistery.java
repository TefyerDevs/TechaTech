package net.tefyert.techatech.main.block;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.block.cable.block.CableBlock;
import net.tefyert.techatech.api.block.facade.FacadeBlock;
import net.tefyert.techatech.api.block.facade.FacadeBlockItem;
import net.tefyert.techatech.api.registration.BlockDeferredRegister;


import java.util.function.Supplier;

public class BlockRegistery {
    public static final BlockDeferredRegister BLOCK_DEFERRED_REGISTER = new BlockDeferredRegister();





    public static final RegistryObject<Block> ENERGY_CABLE =
            BLOCK_DEFERRED_REGISTER.register("cable", CableBlock::new);

    public static final RegistryObject<Block> FACADE_BLOCK =
            BLOCK_DEFERRED_REGISTER.registerWithOutItem("facade", FacadeBlock::new);

    public static final RegistryObject<FacadeBlockItem> FACADE_ITEM =
            BlockDeferredRegister.registerItem("facade",()-> new FacadeBlockItem(FACADE_BLOCK.get(), new Item.Properties()));



}
