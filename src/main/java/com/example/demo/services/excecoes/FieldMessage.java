package com.example.demo.services.excecoes;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private String fieldName;
	private String message;
	
	public String getFildName() {
		return fieldName;
	}
	
	public void setFildName(String fildName) {
		this.fieldName = fildName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public FieldMessage(String fildName, String message) {
		super();
		this.fieldName = fildName;
		this.message = message;
	}	
}
