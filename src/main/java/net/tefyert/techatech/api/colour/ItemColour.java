package net.tefyert.techatech.api.colour;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemColour implements ItemColor {
    int colour;

    public ItemColour(int colour) {
        this.colour = colour;
    }

    public ItemColour() {
        this.colour = 0x0;
    }


    @Override
    public int getColor(@NotNull ItemStack itemStack, int i) {
        return this.colour;
    }
}
