package prasad.springnote.api.viewmodel;

import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prasad.springnote.mail.FeedBackSender;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedBackController {

	private FeedBackSender feedbackSender;

	public FeedBackController(FeedBackSender feedbackSender) {
		this.feedbackSender = feedbackSender;
	}

	@PostMapping
	public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel, 
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("Feedback has errors; Can not send feedback;");
		}

		this.feedbackSender.sendFeedBack(feedbackViewModel.getEmail(), feedbackViewModel.getName(),
				feedbackViewModel.getFeedback());
	}

}
