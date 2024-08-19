package net.tefyert.techatech.main.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.chemical.Chemical;
import net.tefyert.techatech.api.chemical.ChemicalIdentifiers;
import net.tefyert.techatech.api.registration.RegistrationList;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.block.BlockRegistery;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {

    public static final DeferredRegister<Item> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Techatech.MODID);


    public static final List<RegistryObject<Item>> ITEMS_LIST = new ArrayList<>();

    public static final DeferredRegister<CreativeModeTab> CREATIVEMODETAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Techatech.MODID);

    public static final RegistryObject<CreativeModeTab> item_tab =
            CREATIVEMODETAB.register(Techatech.MODID+"_items",
                    ()-> CreativeModeTab.builder()
                            .title(Component.translatable(Techatech.MODID+".items_tab_name"))
                            .icon(ITEMS_LIST.get(0).get()::getDefaultInstance)
                            .displayItems((parameters, output) -> {
                                for(RegistryObject<Item> item : ITEMS_LIST){
                                    output.accept(item.get().getDefaultInstance());
                                }
                            })
                            .build());


    public static final RegistryObject<CreativeModeTab> block_tab =
            CREATIVEMODETAB.register(Techatech.MODID+"_blocks",
                    ()-> CreativeModeTab.builder()
                            .title(Component.translatable(Techatech.MODID+".blocks_tab_name"))
                            .icon(ITEMS_LIST.get(0).get()::getDefaultInstance)
                            .displayItems((parameters, output) -> {
                                for(RegistryObject<Item> item : BlockRegistery.BLOCK_DEFERRED_REGISTER.get_item_list()){
                                    output.accept(item.get().getDefaultInstance());
                                }
                            })
                            .build());




    private static void registerItem(){
        for(ChemicalIdentifiers identifier : ChemicalIdentifiers.values()){
            Chemical chemical = identifier.get_chemical();

            ITEMS_LIST.add(DEFERRED_REGISTER.register(chemical.get_id()+"_pure_dust",()->new ChemicalItem(new Item.Properties(),chemical)));
            ITEMS_LIST.add(DEFERRED_REGISTER.register(chemical.get_id()+"_impure_dust",()->new ChemicalItem(new Item.Properties(),chemical)));

            ITEMS_LIST.add(DEFERRED_REGISTER.register(chemical.get_id()+"_plate",()->new ChemicalItem(new Item.Properties(),chemical)));

            if(!chemical.is_pellet())
                ITEMS_LIST.add(DEFERRED_REGISTER.register(chemical.get_id()+"_ingot",()->new ChemicalItem(new Item.Properties(),chemical)));
            else
                ITEMS_LIST.add(DEFERRED_REGISTER.register(chemical.get_id()+"_pellet",()->new ChemicalItem(new Item.Properties(),chemical)));

        }
    }

    public static void register(IEventBus modeventbus){
        registerItem();
        DEFERRED_REGISTER.register(modeventbus);
        CREATIVEMODETAB.register(modeventbus);
    }

}
