package org.trananh.shoppingappbackend.ultilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseMapArray extends HashMap<String, Object> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResponseMapArray() {
	}
	
	public ResponseMapArray(int statusCode, String message, List<Map<String, Object>> payload ) {
		this.put("statusCode", statusCode);
		this.put("message", message);
		this.put("payload", payload);
	}
	
}
