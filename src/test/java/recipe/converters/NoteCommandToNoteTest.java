/*
 *  Created By  Zaynab Osama ,  On 2/24/19 2:22 AM
 *
 */

package recipe.converters;

import org.junit.Before;
import org.junit.Test;
import recipe.commands.NoteCommand;
import recipe.domain.Note;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {

    public static final Long ID_VALUE =  Long.valueOf(1L);
    public static final String RECIPE_NOTES = "Notes";
    NoteCommandToNote converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteCommandToNote();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NoteCommand noteCmd = new NoteCommand();
        noteCmd.setId(ID_VALUE);
        noteCmd.setRecipeNotes(RECIPE_NOTES);

        //when
        Note note = converter.convert(noteCmd);

        //then
        assertNotNull(note);
        assertEquals(ID_VALUE, note.getId());
        assertEquals(RECIPE_NOTES, note.getRecipeNotes());
    }
}