package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Integer COOK_TIME = 12;
    public static final Integer PREP_TIME = 15;
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final String DIRECTIONS = "Directions";
    public static final Integer SERVINGS = 4;
    public static final String SOME_URL = "Some url";
    public static final String SOURCE = "Source";
    public static final Long CATEGORY_ID1 = 2L;
    public static final Long CATEGORY_ID2 = 3L;
    public static final Long INGREDIENT_ID1 = 4L;
    public static final Long INGREDIENT_ID2 = 5L;
    public static final Long NOTES_ID = 6L;
    public static final String DESCRIPTION = "Some Description";
    public static final Long ID_VALUE = 1L;

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new CategoryToCategoryCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void testConvert() {
        // given
        final Recipe recipe = new Recipe();
        recipe.setDescription(DESCRIPTION);
        recipe.setId(ID_VALUE);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setUrl(SOME_URL);
        recipe.setSource(SOURCE);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID1);

        Category category2 = new Category();
        category2.setId(CATEGORY_ID2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        // when
        RecipeCommand recipeCommand = converter.convert(recipe);

        // then
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(ID_VALUE, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(SOME_URL, recipeCommand.getUrl());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
    }
}
