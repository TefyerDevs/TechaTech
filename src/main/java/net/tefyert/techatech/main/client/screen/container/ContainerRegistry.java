package net.tefyert.techatech.main.client.screen.container;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorContainer;
import net.tefyert.techatech.main.Techatech;

public class ContainerRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Techatech.MODID);

    public static final RegistryObject<MenuType<BasicGeneratorContainer>> GENERATOR_CONTAINER = MENU_TYPES.register("generator_block",
            () -> IForgeMenuType.create((windowId, inv, data) -> new BasicGeneratorContainer(windowId, inv.player, data.readBlockPos())));

}

