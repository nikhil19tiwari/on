package in.bank.vishal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class WelcomeEmail implements EmailService {
    @Autowired
	private JavaMailSender sender;
    
    
	public void sendEmail(String to, String subject, String message) {
	    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	    simpleMailMessage.setTo(to);
	    simpleMailMessage.setSubject(subject);
	    simpleMailMessage.setText(message);
	    simpleMailMessage.setFrom("nikhil19tiwari@gmail.com");
	    sender.send(simpleMailMessage);


			
		}
}
