package net.tefyer.terratech.generators.datageneration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tefyer.terratech.TerraTechMod;



@Mod.EventBusSubscriber(modid = TerraTechMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ModBlockStateProvider(generator,existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator,existingFileHelper));

    }
}
