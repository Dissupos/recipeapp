package io.dissupos.recipe.services;

import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.converters.*;
import io.dissupos.recipe.domain.Recipe;
import io.dissupos.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe,
                new RecipeToRecipeCommand(new NotesToNotesCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new CategoryToCategoryCommand()));
    }

    @Test
    public void testFindById() {
        Recipe recipeData = new Recipe();
        recipeData.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipeData));

        Recipe recipeRet = recipeService.findById(1L);
        assertNotNull("Null recipe returned", recipeRet);
        assertEquals(recipeData.getId(), recipeRet.getId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetAllRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getAllRecipes();
        assertEquals(1, recipes.size());
    }

    @Test
    public void testFindCommandById() {
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        RecipeCommand command = recipeService.findCommandById(2L);

        assertEquals(recipe.getId(), command.getId());
    }

    @Test
    public void testDeleteById() {
        Long id = 2L;
        recipeService.deleteById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
