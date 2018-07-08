package io.dissupos.recipe.converters;

import io.dissupos.recipe.commands.NotesCommand;
import io.dissupos.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;


    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void testConvert() {
        // given
        final String recipeNotes = "Some Description";
        final Long id = 1L;
        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setRecipeNotes(recipeNotes);
        notesCommand.setId(id);

        // when
        Notes notes = converter.convert(notesCommand);

        // then
        assertEquals(recipeNotes, notes.getRecipeNotes());
        assertEquals(id, notes.getId());
    }
}
