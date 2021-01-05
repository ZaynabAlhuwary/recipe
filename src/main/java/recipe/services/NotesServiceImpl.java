/*
 *  Created By  Zaynab Osama ,  On 2/23/19 2:45 PM
 *
 */

package recipe.services;

import org.springframework.stereotype.Service;
import recipe.domain.Note;
import recipe.exceptions.NotFoundException;
import recipe.repositories.NoteRepository;

import java.util.List;
import java.util.Optional;
@Service
public class NotesServiceImpl implements NotesService {

    private NoteRepository noteRepository;

    public NotesServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void saveNote(Note note){
        this.noteRepository.save(note);

    }

    @Override
    public Optional<Note> getNoteById(Long id){
       return Optional.ofNullable(noteRepository.findById(id).orElseThrow(()->new NotFoundException("M_Note Not Found with "+id)));
    }

    @Override
    public List<Note> getNotes(){
        return (List<Note>) noteRepository.findAll();
    }
}
