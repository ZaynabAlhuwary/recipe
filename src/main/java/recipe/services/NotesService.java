/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import recipe.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NotesService {

    public void saveNote(Note note);
    public Optional<Note> getNoteById(Long id);
    public List<Note> getNotes();
}
