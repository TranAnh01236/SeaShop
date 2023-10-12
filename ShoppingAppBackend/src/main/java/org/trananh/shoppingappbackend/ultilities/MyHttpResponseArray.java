package org.trananh.shoppingappbackend.ultilities;

import java.util.ArrayList;
import java.util.List;

public class MyHttpResponseArray {
	private int statusCode;
	private String Message;
	private ArrayList<Object> payload;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public List<Object> getPayload() {
		return payload;
	}
	public void setPayload(ArrayList<Object> payload) {
		this.payload = payload;
	}
	
	public MyHttpResponseArray() {
		this.payload = new ArrayList<>();
	}
	public MyHttpResponseArray(int statusCode, String message, ArrayList<Object> payload) {
		super();
		this.statusCode = statusCode;
		Message = message;
		this.payload = payload;
	}
	
	public String payloadJSON() {
		return Constants.gson.toJson(this.payload);
	}
	public String toJson() {
		return Constants.gson.toJson(this);
	}
}
