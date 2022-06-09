package com.PortfolioBackend.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.PortfolioBackend.models.Contacto;
import com.PortfolioBackend.repositories.ContactoRepository;

@Service
public class ContactoService {
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
    public void save(Contacto contacto) throws UnsupportedEncodingException, MessagingException {
    	
    	String remitente = contacto.getNombre();
    	String mailRemitente = contacto.getMail();
    	String asunto = "["+contacto.getAsunto()+"]";
		String contenido = "<p>"+contacto.getMensaje()+"</p>";
		contenido += "<br><br><div><h4>Mensaje enviado por:</h4><p>"+contacto.getNombre()+"<br>"+contacto.getMail()+"</p></div>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo("nicohelman7@gmail.com");
		helper.setFrom(mailRemitente, remitente);
		helper.setReplyTo(mailRemitente);
		helper.setSubject(asunto);
		helper.setText(contenido, true);
		
		mailSender.send(message);
    	
    	contactoRepository.save(contacto);
    }

}
