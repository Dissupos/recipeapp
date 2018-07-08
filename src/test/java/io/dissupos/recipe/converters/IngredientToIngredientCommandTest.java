package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.IngredientCommand;
import io.dissupos.recipe.domain.Ingredient;
import io.dissupos.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    public static final String DESCRIPTION = "Some Description";
    public static final Long ID_VALUE = 1L;
    public static final Long UNIT_OF_MEASURE_ID = 2L;
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertWithUOM() {
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UNIT_OF_MEASURE_ID);
        final Ingredient ingredient = new Ingredient(DESCRIPTION, AMOUNT, unitOfMeasure);
        ingredient.setId(ID_VALUE);
        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UNIT_OF_MEASURE_ID, ingredientCommand.getUnitOfMeasure().getId());
    }

    @Test
    public void testConvertWithoutUOM() {
        final Ingredient ingredient = new Ingredient(DESCRIPTION, AMOUNT, null);
        ingredient.setId(ID_VALUE);
        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNull(ingredientCommand.getUnitOfMeasure());
    }
}
