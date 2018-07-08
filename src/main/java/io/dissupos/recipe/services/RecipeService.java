package io.dissupos.recipe.services;

import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long id);

    void deleteById(Long id);
}
