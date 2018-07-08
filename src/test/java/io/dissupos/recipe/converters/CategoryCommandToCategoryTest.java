package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.CategoryCommand;
import io.dissupos.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;


    @Before
    public void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void testConvert() {
        // given
        final String description = "Some Description";
        final Long id = 1L;
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(description);
        categoryCommand.setId(id);

        // when
        Category category = converter.convert(categoryCommand);

        // then
        assertEquals(description, category.getDescription());
        assertEquals(id, category.getId());
    }
}
