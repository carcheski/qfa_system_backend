package br.com.qfa.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.qfa.resources.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
