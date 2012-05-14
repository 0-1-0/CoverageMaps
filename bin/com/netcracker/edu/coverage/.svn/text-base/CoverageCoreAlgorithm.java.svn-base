package com.netcracker.edu.coverage;

/**
 * A core algorithm: calculates areas in {@link CoverageMap#getBuildings()}.
 * @author Nikolay Egorov
 */
public abstract class CoverageCoreAlgorithm extends AbstractCoverageAlgorithm{
	/**
	 * 
	 */
	public CoverageCoreAlgorithm() {
		super();
	}
	/**
	 * @param source
	 */
	public CoverageCoreAlgorithm(AbstractCoverageAlgorithm source) {
		super(source);
	}
	/**
	 * Another interface method (is required?)
	 */
	public CoverageMap getCoverageMapWithPolygons(){
		return getModifiedCoverageMap();
	}
	/**
	 * @return the value from 0 (calculations just started) to 1 (calculations finished)
	 */
	public abstract double getCalculationProgress();
}
