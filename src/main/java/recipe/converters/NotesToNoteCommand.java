/*
 *  Created By  Zaynab Osama ,  On 2/24/19 2:05 AM
 *
 */

package recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.NoteCommand;
import recipe.domain.Note;

@Component
public class NotesToNoteCommand implements Converter<Note,NoteCommand>{

    @Nullable
    @Override
    public NoteCommand convert(Note note) {
        if(null==note) {
            return null;
        }
        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setRecipeNotes(note.getRecipeNotes());

        return noteCommand;
    }
}
