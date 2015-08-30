package org.usfirst.frc.team115.lib;

import java.util.HashMap;
import java.util.Set;

public class StateHolder {
	private HashMap<String, Object> states = new HashMap<String, Object>();
	
	public void put(String key, Object value) {
		states.put(key, value);
	}
	
	public Object get(String key) {
		return states.get(key);
	}
	
	public Set<String> keySet() {
		return states.keySet();
	}
}
