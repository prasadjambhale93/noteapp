package prasad.springnote.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class FeedBackMailSender implements FeedBackSender{
	
	private JavaMailSenderImpl mailSender;
	
	public FeedBackMailSender(Environment environment){
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
    }


	@Override
	public void sendFeedBack(String from, String name, String feedback) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("prasadjambhale93@gmail.com");
        message.setSubject("New feedback from " + name);
        message.setText(feedback);
        message.setFrom(from);
        this.mailSender.send(message);
	}

}
