package com.netcracker.edu.coverage.alg;
import java.util.ArrayList;

import com.netcracker.edu.coverage.Pnt;

/*
 * Copyright (c) 2007 Alexander Hristov.
 * http://www.ahristov.com
 * 
 * Feel free to use this code as you wish, as long as you keep this copyright
 * notice. The only limitation on use is that this code cannot be republished
 * on other web sites. 
 *
 * As usual, this code comes with no warranties of any kind.
 *
 * 
 */
public class ConvexHull{ 
	  @SuppressWarnings("unchecked")
	public ArrayList<Pnt> quickHull(ArrayList<Pnt> Pnts) {
	    ArrayList<Pnt> convexHull = new ArrayList<Pnt>();
	    if (Pnts.size() < 3) return (ArrayList<Pnt>)Pnts.clone();
	    // find extremals
	    int minPnt = -1, maxPnt = -1;
	    double minX = Double.MAX_VALUE;
	    double maxX = Double.MIN_VALUE;
	    for (int i = 0; i < Pnts.size(); i++) {
	      if (Pnts.get(i).getX() < minX) {
	        minX = Pnts.get(i).getX();
	        minPnt = i;
	      } 
	      if (Pnts.get(i).getX() > maxX) {
	        maxX = Pnts.get(i).getX();
	        maxPnt = i;       
	      }
	    }
	    Pnt A = Pnts.get(minPnt);
	    Pnt B = Pnts.get(maxPnt);
	    convexHull.add(A);
	    convexHull.add(B);
	    Pnts.remove(A);
	    Pnts.remove(B);
	    
	    ArrayList<Pnt> leftSet = new ArrayList<Pnt>();
	    ArrayList<Pnt> rightSet = new ArrayList<Pnt>();
	    
	    for (int i = 0; i < Pnts.size(); i++) {
	      Pnt p = Pnts.get(i);
	      if (PntLocation(A,B,p) == -1)
	        leftSet.add(p);
	      else
	        rightSet.add(p);
	    }
	    hullSet(A,B,rightSet,convexHull);
	    hullSet(B,A,leftSet,convexHull);
	    
	    return convexHull;
	  }
	  
	  /*
	   * Computes the square of the distance of Pnt C to the segment defined by Pnts AB
	   */
	  public double distance(Pnt A, Pnt B, Pnt C) {
	    double ABx = B.getX()-A.getX();
	    double ABy = B.getY()-A.getY();
	    double num = ABx*(A.getY()-C.getY())-ABy*(A.getX()-C.getX());
	    if (num < 0) num = -num;
	    return num;
	  }
	  
	  public void hullSet(Pnt A, Pnt B, ArrayList<Pnt> set, ArrayList<Pnt> hull) {
	    int insertPosition = hull.indexOf(B);
	    if (set.size() == 0) return;
	    if (set.size() == 1) {
	      Pnt p = set.get(0);
	      set.remove(p);
	      hull.add(insertPosition,p);
	      return;
	    }
	    double dist = Integer.MIN_VALUE;
	    int furthestPnt = -1;
	    for (int i = 0; i < set.size(); i++) {
	      Pnt p = set.get(i);
	      double distance  = distance(A,B,p);
	      if (distance > dist) {
	        dist = distance;
	        furthestPnt = i;
	      }
	    }
	    Pnt P = set.get(furthestPnt);
	    set.remove(furthestPnt);
	    hull.add(insertPosition,P);
	    
	    // Determine who's to the left of AP
	    ArrayList<Pnt> leftSetAP = new ArrayList<Pnt>();
	    for (int i = 0; i < set.size(); i++) {
	      Pnt M = set.get(i);
	      if (PntLocation(A,P,M)==1) {
	        leftSetAP.add(M);
	      }
	    }
	    
	    // Determine who's to the left of PB
	    ArrayList<Pnt> leftSetPB = new ArrayList<Pnt>();
	    for (int i = 0; i < set.size(); i++) {
	      Pnt M = set.get(i);
	      if (PntLocation(P,B,M)==1) {
	        leftSetPB.add(M);
	      }
	    }
	    hullSet(A,P,leftSetAP,hull);
	    hullSet(P,B,leftSetPB,hull);
	    
	  }
	
	  public double PntLocation(Pnt A, Pnt B, Pnt P) {
	    double cp1 = (B.getX()-A.getX())*(P.getY()-A.getY()) - (B.getY()-A.getY())*(P.getX()-A.getX());
	    return (cp1>0)?1:-1;
	  }
}
