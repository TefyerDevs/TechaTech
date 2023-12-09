package net.tefyer.terratech.register;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tefyer.terratech.TerraTechMod;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TerraTechMod.MODID);



    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
