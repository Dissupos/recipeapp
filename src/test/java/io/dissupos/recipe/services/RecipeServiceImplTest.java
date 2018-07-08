package io.dissupos.recipe.services;

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipeData = new Recipe();
        recipeData.setId(1L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipeData));

        Recipe recipeRet = recipeService.findById(1L);
        assertNotNull("Null recipe returned", recipeRet);
        assertEquals(recipeData.getId(), recipeRet.getId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getAllRecipesTest() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getAllRecipes();
        assertEquals(1, recipes.size());
    }
}
