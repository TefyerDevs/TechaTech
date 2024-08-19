package net.tefyert.techatech.main;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tefyert.techatech.api.recipe.RecipeRegistry;
import net.tefyert.techatech.main.block.BlockEntityRegistry;
import net.tefyert.techatech.main.block.BlockRegistery;
import net.tefyert.techatech.main.client.screen.container.ContainerRegistry;
import net.tefyert.techatech.main.item.ItemRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Techatech.MODID)
public class Techatech {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "techatech";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static Logger Logger = LoggerFactory.getLogger(MODID);


    public Techatech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        // Register the Deferred Register to the mod event bus so blocks get registered

        ItemRegistry.register(modEventBus);
        BlockRegistery.BLOCK_DEFERRED_REGISTER.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);



        ContainerRegistry.MENU_TYPES.register(modEventBus);


        RecipeRegistry.SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


    }




    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
