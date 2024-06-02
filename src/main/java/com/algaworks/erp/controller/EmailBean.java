package com.algaworks.erp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.email.EmailDto;
import com.algaworks.erp.email.EmailModel;
import com.algaworks.erp.service.EmailService;
import com.google.common.net.HttpHeaders;

@Named
@ViewScoped
public class EmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EmailService emailService;

	public void getRequestSincrono() throws URISyntaxException, IOException, InterruptedException {
		EmailModel emailModel = emailService.getSincRequestById(22L);
		System.out.println(emailModel);
	}

	public void getRequestAssincrono() throws URISyntaxException, IOException, InterruptedException {
		String encoding = Base64.getEncoder()
				.encodeToString(("user" + ":" + "efc50eec-6afa-42b7-955f-43b7f670d9cd").getBytes());
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(new URI("http://192.168.100.200:8880/get-id/23")).GET()
				.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> response.body())
				.thenAccept(response -> System.out.println(response));
		System.out.println("Final do método.");
	}
	
	public void postRequestSincrono() throws IOException, InterruptedException, URISyntaxException {
		EmailDto email = getMailDto("aldecirlima@gmail.com", "Aldecir");
		EmailModel emailModel = emailService.postSyncRequest(email);
		System.out.println(emailModel);
	}
	
	public void postAsyncRequest() throws URISyntaxException {
		List<EmailDto> listMails = new ArrayList<EmailDto>();
		listMails.add(getMailDto("aldecirlima@gmail.com", "Aldecir").setNewSubject("Teste de envio em lista"));
		listMails.add(getMailDto("anaceliaprestes@hotmail.com", "Aldecir").setNewSubject("Teste de envio em lista"));
		emailService.postAsyncRequest(listMails);
	}
	
	public void getRequestSincronoEmail() throws URISyntaxException, IOException, InterruptedException {
		List<EmailModel> emails = emailService.getSincRequestByEmailTo("aldecirlima@gmail.com");
		for (EmailModel emailModel : emails) {
			System.out.println(emailModel);
		}
	}
	
	private EmailDto getMailDto(String emailTo, String ownerRef) {
		EmailDto emailDto = new EmailDto();
		emailDto.setEmailFrom("aldecirlima@gmail.com");
		emailDto.setOwnerRef(ownerRef);
		emailDto.setSubject("Teste de envio pela aplicação");
		emailDto.setTextEmail("Teste de email\n\nEnvio de teste.");
		emailDto.setEmailTo(emailTo);
		return emailDto;
	}
	


}
