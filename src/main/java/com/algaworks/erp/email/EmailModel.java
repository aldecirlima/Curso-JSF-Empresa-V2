package com.algaworks.erp.email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EmailModel {

	private Long id;

	private String ownerRef;

	private String emailFrom;

	private String emailTo;

	private String subject;

	private String textEmail;

	private String sendDateEmail;

	private StatusEmail statusEmail;

	public Long getId() {
		return id;
	}

	public String getOwnerRef() {
		return ownerRef;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public String getTextEmail() {
		return textEmail;
	}

	public String getSendDateEmail() {
		return sendDateEmail;
	}

	public StatusEmail getStatusEmail() {
		return statusEmail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerRef(String ownerRef) {
		this.ownerRef = ownerRef;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTextEmail(String textEmail) {
		this.textEmail = textEmail;
	}

	public void setSendDateEmail(String sendDateEmail) {
		this.sendDateEmail = sendDateEmail;
	}
	
	public LocalDateTime getSendDateEmailDate() {
		String date = sendDateEmail;
		date = date.replace("T", " ");
		date = date.substring(0, 19);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return dateTime;
	}

	public void setStatusEmail(StatusEmail statusEmail) {
		this.statusEmail = statusEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailModel other = (EmailModel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EmailModel [id=" + id + ", emailTo=" + emailTo + ", subject=" + subject + ", sendDateEmail="
				+ getSendDateEmailDate() + "]";
	}

}
