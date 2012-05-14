package com.netcracker.edu.coverage.alg.voronoi;

import java.util.ArrayList;

public class LinkedIndex {

	LinkedArray array;
	private ArrayList<Integer> links0;

	public LinkedIndex(LinkedArray a, int i){
		array = a;
		links0 = new ArrayList<Integer>();
	}

	public void linkTo(int i){
		links0.add(i);
	}

	public boolean linked(int i){
		return links0.contains((Integer)i);
	}
	
	public int linkCount(){
		return links0.size();
	}
	
	public int getLink(int k){
		return (int)links0.get(k);
	}

	

}