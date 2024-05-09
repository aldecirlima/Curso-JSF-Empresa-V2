package com.algaworks.erp.controller;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.model.Pessoa;

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

}

