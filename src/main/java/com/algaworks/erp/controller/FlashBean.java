package com.algaworks.erp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Base64;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.email.EmailDto;
import com.algaworks.erp.email.EmailModel;
import com.algaworks.erp.model.Pessoa;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;

@Named
@ViewScoped
public class FlashBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;

	public void setaFlash() {
		pessoa = new Pessoa("Aldecir Neves de Lima", 35);
		getContext().getFlash().putNow("pessoa", pessoa);
//		getContext().getFlash().keep("pessoa");
	}

	private ExternalContext getContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public void getRequestSincrono() throws URISyntaxException, IOException, InterruptedException {
		String encoding = Base64.getEncoder()
				.encodeToString(("user" + ":" + "efc50eec-6afa-42b7-955f-43b7f670d9cd").getBytes());

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder(new URI("http://192.168.100.200:8880/get-id/22")).GET()
				.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding).build();

		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(httpResponse.body());
		System.out.println(httpResponse.statusCode());
		System.out.println(httpResponse.version());
		
		Gson gson = new Gson();
		EmailModel email = gson.fromJson(httpResponse.body(), EmailModel.class);
		System.out.println(email);
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
		EmailDto email = new EmailDto();
		email.setEmailFrom("aldecirlima@gmail.com");
		email.setOwnerRef("Aldecir");
		email.setSubject("Teste de envio pela aplicação");
		email.setTextEmail("Teste de email\n\nEnvio de teste.");
		email.setEmailTo("aldecirlima@gmail.com");
		Gson gson = new Gson();
		String json = gson.toJson(email, EmailDto.class);
		
		String encoding = Base64.getEncoder()
				.encodeToString(("user" + ":" + "efc50eec-6afa-42b7-955f-43b7f670d9cd").getBytes());

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder(new URI("http://192.168.100.200:8880/sending-email"))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(json))
				.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding).build();

		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		Gson gsonModel = new Gson();
		EmailModel emailModel = gsonModel.fromJson(httpResponse.body(), EmailModel.class);
		System.out.println(emailModel);
		
	}


}
