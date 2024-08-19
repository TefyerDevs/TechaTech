package net.tefyert.techatech.api.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class Utils {

    public static boolean isAir(Level level, BlockPos pos){
        return level.getBlockEntity(pos).getBlockState().isAir();
    }
}
