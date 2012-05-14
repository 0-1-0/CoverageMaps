package com.netcracker.edu.coverage.data;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.CoverageProblemLoader;

/**
 * Superclass for tests of any implementation of CoverageProblemLoader
 * @author Alexey Evdokimov
 */
public abstract class ProblemLoaderTest {
	protected CoverageProblemLoader loader;

	protected abstract CoverageProblemLoader initLoader() throws Exception;

	@Before
	public void setUp() throws Exception{
		loader = initLoader();
		loader.setMapId(getIdMap());
//		loader.set*(getIdServices());
//		loader.set*(getBounds());
		loader.loadMap();
	}

	protected abstract Object getIdMap();
	/**
	 * Note: for test-only loaders the bounds can be null.
	 */
	protected abstract Bounds getBounds();
	/**
	 * Note: for test-only loaders the idServices can be null (to use the only service assumed).
	 */
	protected abstract Object[] getIdServices();
	/**
	 * Checks both bounds passed to {@link CoverageProblemLoader}
	 *  (if they are not null) and more exact bounds returned by {@link CoverageMap#getBounds()}
	 */
	@Test
	public void buildingsAreWithinBounds(){
		Bounds inBounds = getBounds();
		Bounds outBounds = loader.getCoverageMap().getBounds();
		for(Building b : loader.getCoverageMap().getBuildings()){
			for(int i=b.polygon.count()-1; i>=0; i--){
				double x =b.polygon.getX(i), y = b.polygon.getY(i);
				withinBounds(x, y, outBounds);
				if(inBounds!=null) withinBounds(x, y, outBounds);
			}
		}
	}
	private void withinBounds(double x, double y, Bounds bounds){
		if(!bounds.contains(x, y))
			fail("The point ("+x+","+y+") of a buiding is not within "+bounds);
	}
	
}
