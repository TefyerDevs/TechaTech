package net.tefyert.techatech.main.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tefyert.techatech.api.block.facade.FacadeBlockColor;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorBlock;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorScreen;
import net.tefyert.techatech.main.block.BlockRegistery;
import net.tefyert.techatech.main.client.screen.container.ContainerRegistry;

import static net.tefyert.techatech.main.Techatech.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void modelInit(ModelEvent.RegisterGeometryLoaders event) {
        CableModelLoader.register(event);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register(new FacadeBlockColor(), BlockRegistery.FACADE_BLOCK.get());
    }
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegistry.GENERATOR_CONTAINER.get(), BasicGeneratorScreen::new);
        });
    }
}
