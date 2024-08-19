package net.tefyert.techatech.main.compatability.jei.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tefyert.techatech.api.recipe.MechanicalPressRecipe;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.block.BlockRegistery;
import org.jetbrains.annotations.Nullable;

public class MechanicalPressingCategory implements IRecipeCategory<MechanicalPressRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Techatech.MODID, "mechanical_press");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Techatech.MODID,
            "textures/gui/mechanical_press.png");

    public static final RecipeType<MechanicalPressRecipe> MECHANICAL_PRESSING =
            new RecipeType<>(UID, MechanicalPressRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MechanicalPressingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockRegistery.MECHANICAL_PRESS.get()));
    }

    @Override
    public RecipeType<MechanicalPressRecipe> getRecipeType() {
        return MECHANICAL_PRESSING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.techatech.mechanical_pressing");
    }


    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MechanicalPressRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 30, 39).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 123, 39).addItemStack(recipe.getResultItem(null));
    }
}
