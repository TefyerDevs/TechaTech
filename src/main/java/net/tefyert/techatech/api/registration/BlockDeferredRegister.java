package net.tefyert.techatech.api.registration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.main.Techatech;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlockDeferredRegister {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Techatech.MODID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Techatech.MODID);


    public static final List<RegistryObject<Item>> ITEM_LIST = new ArrayList<>();

    public BlockDeferredRegister() {

    }


    public void register(IEventBus modeventbus){
        ITEM_DEFERRED_REGISTER.register(modeventbus);
        BLOCK_DEFERRED_REGISTER.register(modeventbus);
    }

    public <T extends Block>  RegistryObject<T> register(String id, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCK_DEFERRED_REGISTER.register(id,block);
        ITEM_LIST.add(registerBlockItem(id, toReturn));
        return toReturn;
    }

    public <T extends Block>  RegistryObject<T> registerWithOutItem(String id, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCK_DEFERRED_REGISTER.register(id,block);
        return toReturn;
    }
    public static <T extends Item> RegistryObject<T> registerItem(String id, Supplier<T> item){
        return ITEM_DEFERRED_REGISTER.register(id, item);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEM_DEFERRED_REGISTER.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public List<RegistryObject<Item>> get_item_list(){
        return ITEM_LIST;
    }


}
