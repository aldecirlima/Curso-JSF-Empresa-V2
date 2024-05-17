package com.algaworks.erp.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class Email {

	public static void main(String[] args) {

		String meuEmail = "aldecirlima@gmail.com";
		String minhaSenha = "";

		SimpleEmail email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
		email.setStartTLSRequired(true);
		email.setStartTLSEnabled(true);
		email.setSSLOnConnect(true);

		try {
			email.setFrom(meuEmail);
			// abaixo set assunto, do ingles subject
			email.setSubject("Vídeo aula Java");
			email.setMsg("Testando como enviar um e-mail através de um programa java!");
			email.addTo(meuEmail);
			email.send();
			System.out.println("Email foi enviado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
