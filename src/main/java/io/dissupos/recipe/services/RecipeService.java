package io.dissupos.recipe.services;

import io.dissupos.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
}
