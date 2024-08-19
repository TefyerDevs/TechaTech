package net.tefyert.techatech.main.client.screen.container;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyert.techatech.generators.block.basic.BasicGeneratorContainer;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.client.screen.container.custom.MechanicalPressContainer;

public class ContainerRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Techatech.MODID);

    public static final RegistryObject<MenuType<BasicGeneratorContainer>> GENERATOR_CONTAINER = MENU_TYPES.register("generator_block_container",
            () -> IForgeMenuType.create((windowId, inv, data) -> new BasicGeneratorContainer(windowId, inv.player, data.readBlockPos())));

    public static final RegistryObject<MenuType<MechanicalPressContainer>> MECHANICAL_PRESS = registerMenuType("mechanical_press_container",  MechanicalPressContainer::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }
}

