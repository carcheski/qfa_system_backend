package br.com.qfa.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.qfa.resources.domain.Pedido;
import jakarta.mail.internet.MimeMessage;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);
}
