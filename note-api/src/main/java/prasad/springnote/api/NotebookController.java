package prasad.springnote.api;

import java.util.List;
import java.util.UUID;

import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasad.springnote.Mapper;
import prasad.springnote.api.viewmodel.NotebookViewModel;
import prasad.springnote.db.NotebookRepository;
import prasad.springnote.model.Notebook;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {

	private NotebookRepository notebookRepository;
	private Mapper mapper;
	
	public NotebookController(NotebookRepository notebookRepository,
			Mapper mapper) {
		this.notebookRepository = notebookRepository;
		this.mapper = mapper;
	}
	
	@GetMapping("/all")
    public List<Notebook> all() {
		List<Notebook> allCategories = this.notebookRepository.findAll();
        return allCategories;
    }
	
	@PostMapping
    public Notebook save(@RequestBody NotebookViewModel notebookViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Notebook notebookEntity = this.mapper.convertToNoteBookEntity(notebookViewModel);
        // save notebookEntity instance to db
        this.notebookRepository.save(notebookEntity);

        return notebookEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notebookRepository.deleteById(UUID.fromString(id));
    }
}
