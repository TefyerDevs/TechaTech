package net.tefyert.techatech.main.compatability.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tefyert.techatech.api.recipe.MechanicalPressRecipe;
import net.tefyert.techatech.main.Techatech;
import net.tefyert.techatech.main.client.screen.container.custom.MechanicalPressScreen;
import net.tefyert.techatech.main.compatability.jei.categories.MechanicalPressingCategory;

import java.util.List;

@JeiPlugin
public class JeiTechatechModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Techatech.MODID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MechanicalPressingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<MechanicalPressRecipe> polishingRecipes = recipeManager.getAllRecipesFor(MechanicalPressRecipe.Type.INSTANCE);
        registration.addRecipes(MechanicalPressingCategory.MECHANICAL_PRESSING, polishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MechanicalPressScreen.class, 50, 35, 65, 20,
                MechanicalPressingCategory.MECHANICAL_PRESSING);
    }

}
