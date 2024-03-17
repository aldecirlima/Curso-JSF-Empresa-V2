package com.algaworks.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	private List<Arquivo> files;
	private List<Arquivo> filesDone;

	public void uploadFile(FileUploadEvent event) {

		String[] array = event.getFile().getFileName().split(".");

		String extensao = array[array.length - 1];

		File file = new File(PATH + Calendar.getInstance().getTimeInMillis() + "." + extensao);

		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void upload(FileUploadEvent event) {
		Arquivo arq = new Arquivo();
		arq.setNome(event.getFile().getFileName());
		arq.setBytes(event.getFile().getContent());
		addFile(arq);

	}

	public void salveFiles() {
		for (Arquivo arq : files) {

			try {

				Date data = Calendar.getInstance().getTime();

				String nomeArquivo = SDF_FILE.format(data) + arq.getNome().replace(" ", "_");

				File file = new File(PATH + nomeArquivo);
				arq.setNome(nomeArquivo);
				OutputStream out = new FileOutputStream(file);
				out.write(arq.getBytes());
				out.close();
				addFileDone(arq);

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Upload completo", "O arquivo " + arq.getNome() + " foi salvo!"));
			} catch (IOException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
			} finally {
				PrimeFaces.current().ajax().update("messages");
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

	public List<Arquivo> getFiles() {
		return files;
	}

	public void setFiles(List<Arquivo> files) {
		this.files = files;
	}

	public void addFile(Arquivo arquivo) {
		if (files == null) {
			files = new ArrayList<Arquivo>();
		}
		files.add(arquivo);
	}
	
	public void addFileDone(Arquivo arquivo) {
		if (filesDone == null) {
			filesDone = new ArrayList<Arquivo>();
		}
		filesDone.add(arquivo);
	}

	public List<Arquivo> getFilesDone() {
		return filesDone;
	}

	public void setFilesDone(List<Arquivo> filesDone) {
		this.filesDone = filesDone;
	}

}
