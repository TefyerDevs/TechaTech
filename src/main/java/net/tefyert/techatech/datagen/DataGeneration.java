package net.tefyert.techatech.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tefyert.techatech.datagen.Custom.BlockStateGen;
import net.tefyert.techatech.datagen.Custom.ItemModelGenerator;
import net.tefyert.techatech.datagen.Custom.LanguageProviderGenerator;
import net.tefyert.techatech.main.Techatech;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Techatech.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generators = event.getGenerator();
        PackOutput packOutput = generators.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generators.addProvider(event.includeClient(), new BlockStateGen(packOutput, event.getExistingFileHelper()));

        generators.addProvider(event.includeClient(), new ItemModelGenerator(packOutput,existingFileHelper));
        generators.addProvider(event.includeClient(),new LanguageProviderGenerator(packOutput,"en_us"));
    }
}
