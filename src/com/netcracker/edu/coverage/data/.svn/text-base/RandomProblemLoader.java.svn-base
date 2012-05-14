package com.netcracker.edu.coverage.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import com.netcracker.edu.coverage.CoverageMap;

/**
 * Generates coverage list randomly but enables saving the result in a temp file
 *  in order to support repeatable results. Loading the buildings remains for subclass.
 * @author Alexey Evdokimov
 */
public abstract class RandomProblemLoader extends AbstractProblemLoader{
	private static Random random = new Random();
	//Options
	private String mapsPath = "maps";
	private String tempFileId = "";

	/**
	 * Loads the map with {@link #getMapId()} from {@link #getMapsPath()}.
	 * @param random Used to set service availability
	 * @throws Exception We can't know what future implementation can throw
	 */
	protected abstract CoverageMap loadCoverageMap(Random random) throws Exception; //ParserConfigurationException, SAXException

	/**
	 * If temp file exist, loads map from it, else calls {@link #loadCoverageMap(Random)} and saves its result to the temp file.
	 * @see com.netcracker.edu.coverage.CoverageProblemLoader#loadMap()
	 */
	public void loadMap() throws IOException {
		map = null;
		File tempFile = new File(getMapsPath(), getMapId() + getTempFileId() + ".tmp");
		if(tempFile.exists()) try {
			FileInputStream fin = new FileInputStream(tempFile);
			ObjectInputStream ois = new ObjectInputStream(fin);
			map = (CoverageMap) ois.readObject();
			ois.close();
			return;
		}catch(IOException e){
			//TODO: log as WARNING
		}catch(ClassNotFoundException e){
			//TODO: log as WARNING
		}
		if(map==null) try{
			map = loadCoverageMap(random);
		}catch(Exception e){
			map = new CoverageMap();//is such a map required?!
			if(e instanceof IOException) throw (IOException)e;
			throw new IOException(e.getMessage());
		}
		
		try{
			FileOutputStream fout = new FileOutputStream(tempFile);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(map);
			oos.close();
		}catch(IOException e){
			//TODO: log as WARNING
		}
	}
	/**
	 * @return mapsPath
	 */
	public final String getMapsPath() {
		return mapsPath;
	}
	/**
	 * @param mapsPath By default it's "maps" sub-directory of user.dir. 
	 */
	public void setMapsPath(String mapsPath) {
		this.mapsPath = mapsPath;
	}
	/**
	 * @return tempFileId
	 */
	public String getTempFileId() {
		if(tempFileId==null) tempFileId = "."+random.nextInt();
		return tempFileId;
	}
	/**
	 * Temp file is used to make the results repeatable: saved oncy
	 * @param tempFileId A part of file name between mapId and ".tmp" extension. Can be:
	 * 1) "" (default);
	 * 2) null (to generate randomly => make random service availability each time);
	 * 3) any string (prefix '.' is recommended).
	 */
	public void setTempFileId(String tempFileId) {
		this.tempFileId = tempFileId;
	}
	

}
