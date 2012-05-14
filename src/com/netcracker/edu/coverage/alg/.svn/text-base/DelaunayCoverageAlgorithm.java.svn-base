package com.netcracker.edu.coverage.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.netcracker.edu.coverage.AbstractCoverageAlgorithm;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageCoreAlgorithm;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.MPolygon;
import com.netcracker.edu.coverage.Pnt;
import com.netcracker.edu.coverage.alg.delaunay.Triangle;
import com.netcracker.edu.coverage.alg.delaunay.Triangulation;

/**
 * Core algorithm based on Delaunay Triangulation. 
 * @author Nikolay Egorov
 */
public class DelaunayCoverageAlgorithm extends CoverageCoreAlgorithm {
	private int MAXSIZE = 700;

	private Triangulation dt;
	private Triangle initialTriangle;
	private HashMap<Pnt, Long> idMapper;
	private HashMap<Long, MPolygon> polyStorage;
	
	private double maxlat;
	private double maxlon;
	private double minlat;
	private double minlon;

	private double translateX(double x){
		return  MAXSIZE*(x - minlon) / (maxlon - minlon);
	}
	
	private double translateY(double y){
		return  MAXSIZE*(maxlat - y) / (maxlat - minlat);
	}
	
	private double retranslateX(double x){
		return x*(maxlon-minlon)/MAXSIZE + minlon;
	}
	
	private double retranslateY(double y){
		return maxlat - y*(maxlat-minlat)/MAXSIZE;
	}
	
	public DelaunayCoverageAlgorithm(){
		super();
	}
	public DelaunayCoverageAlgorithm(AbstractCoverageAlgorithm source) {
		super(source);
	}

	protected void createDiagram() {
		polyStorage = new HashMap<Long, MPolygon>();
		
		 HashSet<Pnt> done = new HashSet<Pnt>(initialTriangle);
	        for (Triangle triangle : dt)
	            for (Pnt site: triangle) {
	                if (done.contains(site)) continue;
	                done.add(site);
	                List<Triangle> list = dt.surroundingTriangles(site, triangle);
	                Pnt[] vertices = new Pnt[list.size()];
	                int i = 0;
	                for (Triangle tri: list)
	                    vertices[i++] = tri.getCircumcenter();	             
	                
	                Long id = idMapper.get(site);
	                if(id != null){
	                		MPolygon p  = new MPolygon();
	                		for (int i1 = 0; i1 < vertices.length; i1++) 
	                			p.add(retranslateX(vertices[i1].coord(0)),  retranslateY(vertices[i1].coord(1)));
	                		
	                		polyStorage.put(id, p);
	                }
	            }
	}

	protected void setPoints(Map<Long, Pnt> points) {
		initialTriangle = new Triangle(
                new Pnt(0, 	   			0),
                new Pnt(MAXSIZE*2, 0),
                new Pnt(0,  MAXSIZE*2));
        dt = new Triangulation(initialTriangle);
		
		idMapper = new HashMap<Pnt, Long>();
		for(Long id: points.keySet()){
			Pnt p = points.get(id);
			Pnt p1 = new Pnt(translateX(p.getX()), translateY(p.getY()));
			
			idMapper.put(p1, id);
			//TODO: implement progress here
			try{
				dt.delaunayPlace(p1);
				//System.out.println(p.coord(0) + " "  + p.coord(1) );
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}

	}
	/**
	 * @see com.netcracker.edu.coverage.CoverageCoreAlgorithm#getCalculationProgress()
	 */
	public double getCalculationProgress() {
		throw new UnsupportedOperationException("Tracking calculation progress is not implemented yet");//TODO
	}

	/**
	 * @see com.netcracker.edu.coverage.AbstractCoverageAlgorithm#modifyMap(com.netcracker.edu.coverage.CoverageMap)
	 */
	protected CoverageMap modifyMap(CoverageMap map) {
		maxlat = map.getBounds().getMaxlat();
		maxlon = map.getBounds().getMaxlon();
		minlat = map.getBounds().getMinlat();
		minlon = map.getBounds().getMinlon();
		
		setPoints(map.getAllpoints());
		createDiagram();
		
		for(Building b: map.getBuildings())
			for(Long id: b.pointIds){
				MPolygon poly = polyStorage.get(id);
				if(poly!=null)
					b.areas.put(id, poly);
			}
		return map;
	}

}
