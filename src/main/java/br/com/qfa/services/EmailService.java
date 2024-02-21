package br.com.qfa.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.qfa.resources.domain.Pedido;
import br.com.qfa.resources.domain.user.User;
import jakarta.mail.internet.MimeMessage;

@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(User user, String newPass);
}
