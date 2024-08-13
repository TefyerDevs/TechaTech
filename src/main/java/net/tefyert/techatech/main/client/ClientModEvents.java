package net.tefyert.techatech.main.client;

import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.api.chemical.Chemical;
import net.tefyert.techatech.api.colour.ItemColour;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.item.ChemicalItem;
import net.tefyert.techatech.main.item.ItemRegistry;

@Mod.EventBusSubscriber(modid = Techatech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void itemColourEvent(RegisterColorHandlersEvent.Item event) {
        for(RegistryObject<Item> Item : ItemRegistry.ITEMS_LIST){
            if(Item.get() instanceof ChemicalItem chemicalItem){

                Chemical chemical = chemicalItem.getChemical();

                event.register(new ItemColour(chemical.get_colour_id()),Item.get());
            }
        }
    }
}