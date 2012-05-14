package com.netcracker.edu.coverage.alg;


import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.netcracker.edu.coverage.MPolygon;
import com.netcracker.edu.coverage.Pnt;

public class VoronoiCoverageAlgorithmTest {
	private VoronoiCoverageAlgorithm alg = new VoronoiCoverageAlgorithm();
	private HashMap<Long, Pnt> points = new HashMap<Long, Pnt>();

	@Before
	public void setUp() throws Exception {
		alg = new VoronoiCoverageAlgorithm();
		points = new HashMap<Long, Pnt>();
	}

	//this.points must contain int coordinates
	protected void testPointsInsideDiagram(){
		alg.setPoints(points);
		alg.createDiagram();
		Map<Long, MPolygon> diagram = alg.getDiagram();
		for(int i = 0; i < points.size(); i++){
			Long id = new Long(i);
			Pnt p = points.get(id);
			assertTrue(diagram.get(id).getAWTPolygon().contains((int)p.getX(), (int)p.getY()));
		}
	}

	@Test
	public void testGetDiagramLinear() {
		points.clear();
		for(int i = 0; i < 100; i++){
			points.put(new Long(i), new Pnt(i*2, i));
		}
		testPointsInsideDiagram();
	}
	
	@Test
	public void testGetDiagramParabolic() {		
		points.clear();
		for(int i = 0; i < 80; i++){
			points.put(new Long(i), new Pnt(i*i, i+50));
		}
		testPointsInsideDiagram();
	}
	
	@Test(timeout=10000)
	public void testGetDiagramLinearWithTimeOut() {		
		points.clear();
		for(int i = 0; i < 1000; i++){
			points.put(new Long(i), new Pnt(i*2, i));
		}
		testPointsInsideDiagram();
	}
}
