package com.algaworks.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.algaworks.erp.model.Arquivo;

@Named
@ViewScoped
public class UploadBean implements Serializable {

	private static SimpleDateFormat SDF_FILE = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final long serialVersionUID = 1L;

	private static String PATH = "D:\\upload_files\\";

	private UploadedFile uploadedFile;

	private Set<Arquivo> files;
	private Set<Arquivo> filesDone;

	@SuppressWarnings("unused")
	public void uploadFile(FileUploadEvent event) {

		String[] array = event.getFile().getFileName().split(Pattern.quote("."));

		String extensao = array[array.length - 1];

		File file = new File(PATH + Calendar.getInstance().getTimeInMillis() + "." + extensao);

		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void upload(FileUploadEvent event) {
		Arquivo arq = new Arquivo();
		System.out.println(event.getFile().getContentType());
		arq.setNomeExibicao(event.getFile().getFileName());
		arq.setBytes(event.getFile().getContent());
		addFile(arq);

	}

	public void salveFiles() {
		for (Arquivo arq : files) {
			if (filesDone == null || !filesDone.contains(arq)) {

				try {

					Date data = Calendar.getInstance().getTime();

					String nomeArquivo = SDF_FILE.format(data) + arq.getNomeExibicao().replace(" ", "_");

					File file = new File(PATH + nomeArquivo);
					arq.setNomeCompleto(nomeArquivo);
					OutputStream out = new FileOutputStream(file);
					out.write(arq.getBytes());
					out.close();
					addFileDone(arq);

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Upload completo", "O arquivo " + arq.getNomeExibicao() + " foi salvo!"));
				} catch (IOException e) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
				} finally {
					PrimeFaces.current().ajax().update("messages");
				}
			}
		}

		files.clear();

	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Set<Arquivo> getFiles() {
		return files;
	}

	public void setFiles(Set<Arquivo> files) {
		this.files = files;
	}

	public void addFile(Arquivo arquivo) {
		if (files == null) {
			files = new LinkedHashSet<Arquivo>();
		}
		files.add(arquivo);
	}

	public void addFileDone(Arquivo arquivo) {
		if (filesDone == null) {
			filesDone = new LinkedHashSet<Arquivo>();
		}
		filesDone.add(arquivo);
	}

	public Set<Arquivo> getFilesDone() {
		return filesDone;
	}

	public void setFilesDone(Set<Arquivo> filesDone) {
		this.filesDone = filesDone;
	}

}
