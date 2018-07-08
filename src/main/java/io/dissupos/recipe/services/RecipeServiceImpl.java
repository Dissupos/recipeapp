package io.dissupos.recipe.services;

import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.converters.RecipeCommandToRecipe;
import io.dissupos.recipe.converters.RecipeToRecipeCommand;
import io.dissupos.recipe.domain.Recipe;
import io.dissupos.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("Get All Recipes method start!");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> ret = recipeRepository.findById(id);
        if (!ret.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        return ret.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        Recipe recipe = findById(id);
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
