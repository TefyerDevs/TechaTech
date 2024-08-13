package net.tefyert.techatech.datagen.Custom;

import com.google.gson.JsonObject;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.block.BlockRegistery;
import net.tefyert.techatech.main.client.CableModelLoader;

import java.util.function.BiConsumer;

public class BlockStateGen extends BlockStateProvider {

    public static final ResourceLocation BOTTOM = new ResourceLocation(Techatech.MODID, "block/machine_bottom");
    public static final ResourceLocation TOP = new ResourceLocation(Techatech.MODID, "block/machine_top");
    public static final ResourceLocation SIDE = new ResourceLocation(Techatech.MODID, "block/machine_side");

    public BlockStateGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Techatech.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerGenerator();
        registerCable();
        registerFacade();
    }
    private void registerGenerator() {
        BlockModelBuilder modelOn = models().cube(BlockRegistery.BASIC_GENERATOR.getId().getPath()+"_on", BOTTOM, TOP, modLoc("block/basic_generator_on"), SIDE, SIDE, SIDE).texture("particle", SIDE);
        BlockModelBuilder modelOff = models().cube(BlockRegistery.BASIC_GENERATOR.getId().getPath()+"_off", BOTTOM, TOP, modLoc("block/basic_generator"), SIDE, SIDE, SIDE).texture("particle", SIDE);
        directionBlock(BlockRegistery.BASIC_GENERATOR.get(), (state, builder) -> {
            builder.modelFile(state.getValue(BlockStateProperties.POWERED) ? modelOn : modelOff);
        });
    }

    private VariantBlockStateBuilder directionBlock(Block block, BiConsumer<BlockState, ConfiguredModel.Builder<?>> model) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        builder.forAllStates(state -> {
            ConfiguredModel.Builder<?> bld = ConfiguredModel.builder();
            model.accept(state, bld);
            applyRotationBld(bld, state.getValue(BlockStateProperties.FACING));
            return bld.build();
        });
        return builder;
    }

    private void applyRotationBld(ConfiguredModel.Builder<?> builder, Direction direction) {
        switch (direction) {
            case DOWN -> builder.rotationX(90);
            case UP -> builder.rotationX(-90);
            case NORTH -> { }
            case SOUTH -> builder.rotationY(180);
            case WEST -> builder.rotationY(270);
            case EAST -> builder.rotationY(90);
        }
    }


    private void registerCable() {
        BlockModelBuilder model = models().getBuilder("cable")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, false))
                .end();
        simpleBlock(BlockRegistery.ENERGY_CABLE.get(), model);
    }

    private void registerFacade() {
        BlockModelBuilder model = models().getBuilder("facade")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, true))
                .end();
        simpleBlock(BlockRegistery.FACADE_BLOCK.get(), model);
    }

    public static class CableLoaderBuilder extends CustomLoaderBuilder<BlockModelBuilder> {

        private final boolean facade;

        public CableLoaderBuilder(ResourceLocation loader, BlockModelBuilder parent, ExistingFileHelper existingFileHelper,
                                  boolean facade) {
            super(loader, parent, existingFileHelper);
            this.facade = facade;
        }

        @Override
        public JsonObject toJson(JsonObject json) {
            JsonObject obj = super.toJson(json);
            obj.addProperty("facade", facade);
            return obj;
        }
    }
}
