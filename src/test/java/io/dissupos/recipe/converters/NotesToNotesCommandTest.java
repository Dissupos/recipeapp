package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.NotesCommand;
import io.dissupos.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private NotesToNotesCommand converter;


    @Before
    public void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void testConvert() {
        // given
        final String recipeNotes = "Some Description";
        final Long id = 1L;
        final Notes notes = new Notes();
        notes.setRecipeNotes(recipeNotes);
        notes.setId(id);

        // when
        NotesCommand notesCommand = converter.convert(notes);

        // then
        assertEquals(recipeNotes, notesCommand.getRecipeNotes());
        assertEquals(id, notesCommand.getId());
    }
}
