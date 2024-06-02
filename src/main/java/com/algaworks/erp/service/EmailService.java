package com.algaworks.erp.service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.algaworks.erp.email.EmailDto;
import com.algaworks.erp.email.EmailModel;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EmailService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String BASE_URL = "http://192.168.100.200:8880";

	private String getEncoding() {
		return Base64.getEncoder().encodeToString(("user" + ":" + "efc50eec-6afa-42b7-955f-43b7f670d9cd").getBytes());
	}

	private HttpRequest getPostRequest(String urlSufix, String json) throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder(new URI(BASE_URL + urlSufix));
		builder.header("Content-Type", "application/json");
		builder.POST(BodyPublishers.ofString(json));
		builder.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + getEncoding());
		return builder.build();
	}

	private HttpRequest getGetRequest(String urlSufix) throws URISyntaxException {
		Builder builder = HttpRequest.newBuilder(new URI(BASE_URL + urlSufix));
		builder.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + getEncoding());
		return builder.build();
	}

	public EmailModel postSyncRequest(EmailDto email) throws IOException, InterruptedException, URISyntaxException {
		String json = new Gson().toJson(email, EmailDto.class);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = getPostRequest("/sending-email", json);
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		return new Gson().fromJson(httpResponse.body(), EmailModel.class);
	}

	public void postAsyncRequest(List<EmailDto> emails) throws URISyntaxException {
		String json = new Gson().toJson(emails, List.class);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = getPostRequest("/sending-list", json);
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> response.body())
				.thenAccept(response -> System.out.println("Envio " + response));
	}

	public EmailModel getSincRequestById(Long id) throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = getGetRequest("/get-id/" + String.valueOf(id.longValue()));
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		return new Gson().fromJson(httpResponse.body(), EmailModel.class);
	}
	
	public List<EmailModel> getSincRequestByEmailTo(String emailTo) throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = getGetRequest("/get-email/" + emailTo);
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		Type myTypeObject = new TypeToken<ArrayList<EmailModel>>() {}.getType();
		return new Gson().fromJson(httpResponse.body(), myTypeObject);
	}

}
