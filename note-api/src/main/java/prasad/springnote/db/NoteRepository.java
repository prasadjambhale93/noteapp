package prasad.springnote.db;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prasad.springnote.model.Note;
import prasad.springnote.model.Notebook;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
	List<Note> findAllByNotebook(Notebook notebook);
}
