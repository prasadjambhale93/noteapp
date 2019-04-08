package prasad.springnote;

import java.util.UUID;

import org.springframework.stereotype.Component;

import prasad.springnote.api.viewmodel.NoteViewModel;
import prasad.springnote.api.viewmodel.NotebookViewModel;
import prasad.springnote.db.NotebookRepository;
import prasad.springnote.model.Note;
import prasad.springnote.model.Notebook;

@Component
public class Mapper {
	
	private NotebookRepository notebookRepository;
	
	public Mapper(NotebookRepository notebookRepository) {
		this.notebookRepository = notebookRepository;
	}
	
	public NoteViewModel convertToNoteViewModel(Note entity) {
		NoteViewModel noteViewModel = new NoteViewModel();
		noteViewModel.setTitle(entity.getTitle());
		noteViewModel.setId(entity.getId().toString());
		noteViewModel.setLastModifiedOn(entity.getLastModifiedOn());
		noteViewModel.setText(entity.getText());
		noteViewModel.setNotebookId(entity.getNotebook().getId().toString());
		return noteViewModel;
	}
	
	public NotebookViewModel convertToNoteViewModel(Notebook entity) {
		NotebookViewModel notebookViewModel = new NotebookViewModel();
		notebookViewModel.setId(entity.getId().toString());
		notebookViewModel.setName(entity.getName());
		notebookViewModel.setNbNotes(entity.getNotes().size());
		return notebookViewModel;
	}
	
	public Note convertToNoteEntity(NoteViewModel viewModel) {
		Notebook notebook = this.notebookRepository.findById(UUID.fromString(viewModel.getNotebookId())).get();
		Note entity = new Note(viewModel.getId(),viewModel.getTitle(), viewModel.getText(), notebook);
		return entity;
	}
	
	public Notebook convertToNoteBookEntity(NotebookViewModel notebookViewModel) {
		Notebook notebookEntity = new Notebook(notebookViewModel.getId(), notebookViewModel.getName());
		return notebookEntity;
	}

}
