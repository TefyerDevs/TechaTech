package net.tefyert.techatech.datagen.Custom;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.block.BlockRegistery;
import net.tefyert.techatech.main.item.ItemRegistry;


public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Techatech.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(BlockRegistery.ENERGY_CABLE.getId().getPath(), modLoc("block/cable"));
        withExistingParent(BlockRegistery.FACADE_BLOCK.getId().getPath(), modLoc("block/facade"));

        withExistingParent(BlockRegistery.BASIC_GENERATOR.getId().getPath(), modLoc("block/basic_generator_off"));

        for(RegistryObject<Item> Item: ItemRegistry.ITEMS_LIST) {

            if (Item.getId().getPath().contains("impure")) {
                simpleItem2(Item, "dust_impure", "dust_impure_overlay");
            }else if(Item.getId().getPath().contains("plate")){
                simpleItem(Item,"plate");
            }else if(Item.getId().getPath().contains("ingot")){
                simpleItem(Item,"ingot");
            }else if(Item.getId().getPath().contains("pellet")){
                simpleItem(Item,"pellet");
            }else{
                simpleItem(Item,"dust_pure");
            }


        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item,String item_type) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Techatech.MODID,"item/" + item_type));
    }
    private ItemModelBuilder simpleItem2(RegistryObject<Item> item,String item_type_1,String item_type_2) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Techatech.MODID,"item/" + item_type_1))
                .texture("layer1",new ResourceLocation(Techatech.MODID,"item/" + item_type_2));
    }
}
