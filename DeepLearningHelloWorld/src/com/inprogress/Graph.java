package com.inprogress;

import java.util.HashMap;
import java.util.Map;

public class Graph<T> {
	
	private int nodeId = 0;
	private Map<Integer, T> nodeMap = new HashMap<Integer, T>();

	public Graph() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void add(T node) {
		nodeMap.put(nodeId++, node);
	}

}
