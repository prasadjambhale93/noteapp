package prasad.springnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NoteApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(NoteApiApplication.class, args);
	}

}
