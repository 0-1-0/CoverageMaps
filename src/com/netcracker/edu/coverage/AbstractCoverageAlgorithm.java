package com.netcracker.edu.coverage;

import java.util.Map;

/**
 * Any algorithm - a part of coverage areas computation process. Modifies the {@link CoverageMap}.
 * The algorithms can form Chain of Responsibility with {@link #source} field.
 * If the source is defined, not only map but settings may not be set to this algorithm. 
 * TO DO: think about retaining only one Chain of Responsibility but without
 *  independent processing of each algorithm via multiplecalls to {@link #setCoverageMap(CoverageMap)}. 
 * @author Nikolay Egorov
 * @author Alexey Evdokimov
 */
public abstract class AbstractCoverageAlgorithm {
	private Map<String, Object> settings;
	private CoverageMap map;
	private AbstractCoverageAlgorithm source;
	
	/**
	 * 
	 */
	public AbstractCoverageAlgorithm() {
	}
	/**
	 * @param source Previous algorithm in Chain of Responsibility.
	 */
	public AbstractCoverageAlgorithm(AbstractCoverageAlgorithm source) {
		this.source = source;
	}
	/**
	 * @return source
	 */
	public final AbstractCoverageAlgorithm getSourceAlgorithm() {
		return source;
	}
	public void setAlgorithmSettings(Map<String, Object> settingsSet){
		if(source!=null)
			source.setAlgorithmSettings(settingsSet);
		else
			settings = settingsSet;
	}
	
	public final Map<String, Object> getAlgorithmSettings(){
		if(settings==null && source!=null) return source.getAlgorithmSettings();
		return settings;
	}
	protected final Object getSetting(String key){
		Map<String,Object> s = getAlgorithmSettings();
		return s==null ? null : s.get(key);
	}
	protected double getDouble(String key){
		Object value = getSetting(key);
		if(value==null) return Double.NaN;
		return ((Double)value).doubleValue();
	}
	protected String getString(String key){
		return (String)getSetting(key);
	}
	
	public void setCoverageMap(CoverageMap map){
		if(source!=null)
			source.setCoverageMap(map);
		else
			this.map = map;
	}
	
	/**
	 * @return map
	 */
	public final CoverageMap getCoverageMap() {
		return map;
	}
	/**
	 * The main interface method for computations.
	 */
	public CoverageMap getModifiedCoverageMap(){
		if(map==null && source!=null)
			map = modifyMap(source.getModifiedCoverageMap());
		else map = modifyMap(map);
		return map;
	}
	/**
	 * The main computation method. May return new map but usually returns the same.
	 */
	protected abstract CoverageMap modifyMap(CoverageMap map);
}
