package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.CategoryCommand;
import io.dissupos.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand converter;


    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvert() {
        // given
        final String description = "Some Description";
        final Long id = 1L;
        final Category category = new Category();
        category.setDescription(description);
        category.setId(id);

        // when
        CategoryCommand categoryCommand = converter.convert(category);

        // then
        assertEquals(description, categoryCommand.getDescription());
        assertEquals(id, categoryCommand.getId());
    }
}
