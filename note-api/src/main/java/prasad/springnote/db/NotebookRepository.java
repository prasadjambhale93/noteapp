package prasad.springnote.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prasad.springnote.model.Notebook;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, UUID>{

}
