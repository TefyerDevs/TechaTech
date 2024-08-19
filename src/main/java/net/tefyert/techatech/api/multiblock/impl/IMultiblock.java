package net.tefyert.techatech.api.multiblock.impl;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;

public interface IMultiblock {


    boolean checkMultiblock();
    void addMultiBlock(IMultiblockPart part);
}
