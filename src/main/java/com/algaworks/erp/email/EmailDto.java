package com.algaworks.erp.email;

import java.util.Objects;

public class EmailDto {

	private String ownerRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	private String textEmail;

	public EmailDto() {
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

	@Override
	public int hashCode() {
		return Objects.hash(emailFrom, emailTo, ownerRef, subject, textEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailDto other = (EmailDto) obj;
		return Objects.equals(emailFrom, other.emailFrom) && Objects.equals(emailTo, other.emailTo)
				&& Objects.equals(ownerRef, other.ownerRef) && Objects.equals(subject, other.subject)
				&& Objects.equals(textEmail, other.textEmail);
	}

}
