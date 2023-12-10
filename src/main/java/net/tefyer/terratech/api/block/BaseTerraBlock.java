package net.tefyer.terratech.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BaseTerraBlock extends BaseEntityBlock {
    public static BlockEntity BLOCK_ENTITY;
    public BaseTerraBlock(BlockEntity blockEntity, Properties pProperties) {
        super(pProperties);
        BLOCK_ENTITY = blockEntity;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return BLOCK_ENTITY;
    }
}
