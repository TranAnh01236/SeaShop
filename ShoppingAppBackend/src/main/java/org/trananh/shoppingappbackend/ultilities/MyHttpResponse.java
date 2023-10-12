package org.trananh.shoppingappbackend.ultilities;

import java.io.Serializable;

public class MyHttpResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6102052845160724046L;
	private int statusCode;
	private String message;
	private Object payload;
	
	public MyHttpResponse() {
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public MyHttpResponse(int statusCode, String message, Object payload) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.payload = payload;
	}
	
	public String payloadJSON() {
		return Constants.gson.toJson(this.payload);
	}
	public String toJson() {
		return Constants.gson.toJson(this);
	}
}
