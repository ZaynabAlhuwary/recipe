package recipe.converters.mongo;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import recipe.commands.mongo.M_Note_Cmd;
import recipe.domain.mongo.M_Note;

@Component
public class M_NoteToNoteCommand implements Converter<M_Note, M_Note_Cmd> {

    @Synchronized
    @Nullable
    @Override
    public M_Note_Cmd convert(M_Note m_note) {
        if (null == m_note) {
            return null;
        }
        final M_Note_Cmd noteCmd = new M_Note_Cmd();
        noteCmd.setId(m_note.getId());
        noteCmd.setRecipeNotes(m_note.getRecipeNotes());

        return noteCmd;
    }
}
