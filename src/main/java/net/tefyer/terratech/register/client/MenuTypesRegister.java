package net.tefyer.terratech.register.client;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tefyer.terratech.TerraTechMod;
import net.tefyer.terratech.client.menu.PowerBrickMenu;

public class MenuTypesRegister {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TerraTechMod.MODID);

    public static final RegistryObject<MenuType<PowerBrickMenu>> POWER_BRICK_MENU = registerMenuType(PowerBrickMenu::new,"power_brick");
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus modEventBus){
        MENUS.register(modEventBus);
    }
}
