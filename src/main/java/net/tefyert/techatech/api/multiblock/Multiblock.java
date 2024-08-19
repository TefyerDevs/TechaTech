package net.tefyert.techatech.api.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.tefyert.techatech.api.multiblock.impl.IMultiblock;
import net.tefyert.techatech.api.multiblock.impl.IMultiblockPart;

import java.util.ArrayList;
import java.util.List;

public class Multiblock implements IMultiblock {
    List<IMultiblockPart> array_part = new ArrayList<>();
    BlockPos multiblock_controller;
    boolean is_valid = false;
    public static final DirectionProperty DIRECTION = BlockStateProperties.FACING;


    public Multiblock(BlockPos MultiBlock_Controller) {
        multiblock_controller = MultiBlock_Controller;
    }

    @Override
    public boolean checkMultiblock() {

        return is_valid;
    }

    @Override
    public void addMultiBlock(IMultiblockPart part) {
        array_part.add(part);
    }

    public List<IMultiblockPart> getArrayParts() {
        return array_part;
    }

    public BlockPos getMultiblockControllerPos() {
        return multiblock_controller;
    }

    public boolean is_valid() {
        return is_valid;
    }


}
