package org.trananh.shoppingappbackend.ultilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseMap extends HashMap<String, Object> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResponseMap() {
	}
	
	public ResponseMap(int statusCode, String message, Map<String, Object> payload) {
		this.put("statusCode", statusCode);
		this.put("message", message);
		this.put("payload", payload);
	}
	
}
