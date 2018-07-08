package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.IngredientCommand;
import io.dissupos.recipe.commands.UnitOfMeasureCommand;
import io.dissupos.recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    public static final String DESCRIPTION = "Some Description";
    public static final Long ID_VALUE = 1L;
    public static final Long UNIT_OF_MEASURE_ID = 2L;
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvertWithUOM() {
        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UNIT_OF_MEASURE_ID);
        final IngredientCommand ingredientCommand = new IngredientCommand(ID_VALUE, DESCRIPTION, AMOUNT, unitOfMeasureCommand);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UNIT_OF_MEASURE_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void testConvertWithoutUOM() {
        final IngredientCommand ingredientCommand = new IngredientCommand(ID_VALUE, DESCRIPTION, AMOUNT, null);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNull(ingredient.getUnitOfMeasure());
    }
}
