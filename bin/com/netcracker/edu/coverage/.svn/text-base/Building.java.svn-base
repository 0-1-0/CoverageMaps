package com.netcracker.edu.coverage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


/**
* Contains input data about building's geometry ({@link #polygon}),
* building points' identifiers ({@link #pointIds}) and service availability ({@link #service})
* as well as output data - {@link #areas}.
* @author Nikolay Egorov
*/
public class Building implements Serializable{
	private static final long serialVersionUID = 1929611658860471322L;
	
	public Building(){
		polygon = new MPolygon();
		areas = new HashMap<Long, MPolygon>();
		pointIds = new HashSet<Long>();
	}
	
	public MPolygon polygon;
	public HashMap<Long, MPolygon> areas;
	public boolean service;//TODO: support multiple services!
	public HashSet<Long> pointIds;

}
