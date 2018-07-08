package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.UnitOfMeasureCommand;
import io.dissupos.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand converter;


    @Before
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void testConvert() {
        // given
        final String description = "Some Description";
        final Long id = 1L;
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(description);
        unitOfMeasure.setId(id);

        // when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        // then
        assertEquals(description, unitOfMeasureCommand.getDescription());
        assertEquals(id, unitOfMeasureCommand.getId());
    }
}
