/*
 *  Created By  Zaynab Osama ,  On 2/24/19 2:18 AM
 *
 */

package recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.NoteCommand;
import recipe.domain.Note;

@Component
public class NoteCommandToNote implements Converter<NoteCommand,Note>{

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCmd) {
        if(null==noteCmd){
            return null;
        }
        final Note note = new Note();
        note.setId(noteCmd.getId());
        note.setRecipeNotes(noteCmd.getRecipeNotes());

        return note;
    }
}
