package com.netcracker.edu.coverage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.netcracker.edu.coverage.alg.DelaunayCoverageAlgorithm;
import com.netcracker.edu.coverage.alg.PopulateBuildingsAlgorithm;
import com.netcracker.edu.coverage.alg.ScaleAreasAlgorithm;
import com.netcracker.edu.coverage.alg.VoronoiCoverageAlgorithm;
import com.netcracker.edu.coverage.data.OSMRandomProblemLoader;
import com.netcracker.edu.coverage.view.GifRendererSolution;

/**
 * Static methods of this class is for tests only (it can work with 1 service only, does not use any DB, etc.).
 * This class is usable by main visualizer module (launched not only as main but as EJB too).
 * TO DO: think if implementation class names (loader/algorithms/solution) should be stored in the same 
 *  stream as their settings.
 * @author Alexey Evdokimov
 * @author Nikolay Egorov
 */
public class CoverageVisualizer {
	private AbstractCoverageAlgorithm algorithm;	
	private CoverageProblemLoader loader;
	private CoverageSolution solution;
	
	public CoverageVisualizer() {
	}
	public CoverageVisualizer(String settingsPath) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(settingsPath);
		setSettings(stream);
		stream.close();
	}
	public void setSettings(InputStream stream) throws IOException {
		Map<String,Object> settings = loadSettings(stream);
		getAlgorithm().setAlgorithmSettings(settings);
//		getLoader().setSettings(settings);
//		getSolution().setSettings(settings);
	}
	protected Map<String,Object> loadSettings(InputStream stream) throws IOException {
		Properties p = new Properties();
		p.loadFromXML(stream);
		Map<String,Object> map = new HashMap<String,Object>();
		Iterator<Map.Entry<Object,Object>> iter = p.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = iter.next();
			Object value = entry.getValue();
			try{ value = new Double(value.toString());
			}catch(NumberFormatException e){} //this exception is normal 
			map.put(entry.getKey().toString(), value);
		}
		return map;
	}

	public AbstractCoverageAlgorithm getAlgorithm(){
		if(algorithm == null)
			algorithm = initAlgorithm();
		return algorithm;
	}
	public CoverageCoreAlgorithm getCoreAlgorithm(){
		AbstractCoverageAlgorithm alg = getAlgorithm();
		while(!(alg instanceof CoverageCoreAlgorithm)){
			alg = alg.getSourceAlgorithm();
		}
		return (CoverageCoreAlgorithm)alg;
	}
	
	public final CoverageProblemLoader getLoader() {
		if(loader == null) 
			loader = initLoader();
		return loader;
	}
	
	public void setLoader(CoverageProblemLoader loader) {
		this.loader = loader;
	}

	public final CoverageSolution getSolution() {
		if(solution==null)
			solution = initSolution();
		return solution;
	}
	public void setSolution(CoverageSolution solution) {
		this.solution = solution;
	}

	protected AbstractCoverageAlgorithm initAlgorithm(){
		return new ScaleAreasAlgorithm( initCoreAlgorithm(//null));
				new PopulateBuildingsAlgorithm() ));
	}
	protected CoverageCoreAlgorithm initCoreAlgorithm(AbstractCoverageAlgorithm source){
		//return new VoronoiCoverageAlgorithm(source);
		return new DelaunayCoverageAlgorithm(source);
	}
	protected CoverageProblemLoader initLoader() {
		return new OSMRandomProblemLoader();
	}
	protected CoverageSolution initSolution() {
		return new GifRendererSolution();
	}
	/**
	 * Performs all calculations for the given map.
	 * @param idMap See reqs
	 * @param idServices Maybe null if multiple services are not used
	 * @param bounds May be null if all the map must be processed
	 */
	public void processMap(Object idMap, Object[] idServices, Bounds bounds) throws IOException {
		getLoader().setMapId(idMap);
		getLoader().setBounds(bounds);
		//TODO: call some CoverageProblemLoader's method to define idServices to be processed
		getLoader().loadMap();
		CoverageMap map = getLoader().getCoverageMap();
		getAlgorithm().setCoverageMap(map);
		map = getAlgorithm().getModifiedCoverageMap();
		prepareSolution(getSolution(), map, getLoader().getMapId());
	}
	public void prepareSolution(CoverageSolution solution, CoverageMap map, Object mapId){
		solution.setBounds(map.getBounds());
		solution.setMapId(getLoader().getMapId());
		for(Building b: map.getBuildings())
			solution.addBuilding(b);				
		solution.drawSolution();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Object idMap = "map-1.osm";
		if(args.length > 0) idMap = args[0];
		new CoverageVisualizer("visualizer.properties.xml").processMap(idMap, null, null);
	}

	/**
	 * Doing the same as {@link #main(String[])} in another way.
	 */
	public static void main() {
		CoverageVisualizer cv = new CoverageVisualizer();
		
		cv.getLoader().setMapId("map-1.osm");
		try {
			cv.getLoader().loadMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Getting map
		CoverageMap map = cv.getLoader().getCoverageMap();
		
		//Algorithm settings
		HashMap<String, Object> algSettings = new HashMap<String, Object>();
		algSettings.put(PopulateBuildingsAlgorithm.PopulateBuildingsParamTag, new Double(0.0002));
		algSettings.put(ScaleAreasAlgorithm.ScaleAreasParamTag, new Double(0.0005));
		
		//1st Helper Algorithm
		CoverageHelperAlgorithm cha = new PopulateBuildingsAlgorithm();
		cha.setAlgorithmSettings(algSettings);
		cha.setCoverageMap(map);
		map = cha.getModifiedCoverageMap();	
		
		//Main Algorithm
		CoverageCoreAlgorithm cca = new DelaunayCoverageAlgorithm();//cv.initCoreAlgorithm(null);
		cca.setCoverageMap(map);		
		map = cca.getCoverageMapWithPolygons();
		
		//2nd Helper Algorithm
		cha = new ScaleAreasAlgorithm();
		cha.setAlgorithmSettings(algSettings);
		cha.setCoverageMap(map);
		map = cha.getModifiedCoverageMap();	
		
		//Rendering map with Solution class
		cv.prepareSolution(cv.getSolution(), map, cv.getLoader().getMapId());
	}

}
