package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.CategoryCommand;
import io.dissupos.recipe.commands.IngredientCommand;
import io.dissupos.recipe.commands.NotesCommand;
import io.dissupos.recipe.commands.RecipeCommand;
import io.dissupos.recipe.domain.Difficulty;
import io.dissupos.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    private RecipeCommandToRecipe converter;

    @Before
    public void setUp() {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void testConvert() {
        // given
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setUrl(SOME_URL);
        recipeCommand.setSource(SOURCE);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.setNotes(notesCommand);

        // when
        Recipe recipe = converter.convert(recipeCommand);

        // then
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(SOME_URL, recipe.getUrl());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }
}
