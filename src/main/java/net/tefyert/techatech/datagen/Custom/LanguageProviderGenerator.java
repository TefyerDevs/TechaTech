package net.tefyert.techatech.datagen.Custom;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.block.BlockRegistery;

public class LanguageProviderGenerator extends LanguageProvider {
    public LanguageProviderGenerator(PackOutput output, String locale) {
        super(output, Techatech.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(BlockRegistery.ENERGY_CABLE.get(), "Energy Cable");
        add(BlockRegistery.FACADE_BLOCK.get(), "Energy Facade");

        add(BlockRegistery.BASIC_GENERATOR.get(), "Basic Generator");


        add(Techatech.MODID+".screen.generator", "Basic Generator");
        add("block."+Techatech.MODID+".mechanical_pressing", "Mechanical Press");
    }
}
