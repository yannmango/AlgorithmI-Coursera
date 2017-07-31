
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.Quick3way;

public class FastCollinearPoints {
    private final List<LineSegment> result;
    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
    	checkNull(points);
    	checkDuplcate(points);
//    	map = new HashMap<>(); 
    	result = new ArrayList<>();
	    int N=points.length;
	    int count;
	    int index;
	    Point ori;
	    Point[] otherPoint;
	    Point[] copy = Arrays.copyOf(points, points.length);
	    Quick3way.sort(copy);
        for (int p = 0; p < N; p++) {
            // Sort the points according to the slopes they makes with p.
        	ori = copy[p];
        	otherPoint = new Point[N-1];
        	//copy point array to otherPoint except ori 
        	for (int q = 0; q< N; q++) {
        		if (q < p) otherPoint[q] = copy[q];
        		if (q > p) otherPoint[q-1] = copy[q];
        	}
        	Arrays.sort(otherPoint, ori.slopeOrder());
        	count = 0;
        	index = 0;
        	if (N > 1) {
        		double tempSlope = ori.slopeTo(otherPoint[0]);  
            	for (int j=0; j < otherPoint.length; j++){  
                    if (Double.compare(ori.slopeTo(otherPoint[j]),  tempSlope) == 0){  
                        count++;  
                        continue;  
                    }
                    else {  
                        if (count >= 3){  
                            if (otherPoint[index].compareTo(ori) >= 0){  
                                result.add(new LineSegment(ori, otherPoint[j-1]));
                            }  
                        }  
                        count = 1;  
                        index = j;  
                        tempSlope = ori.slopeTo(otherPoint[j]);  
                    }  
                }  
                if (count >= 3){  
                    if (otherPoint[index].compareTo(ori) >= 0){  
                        result.add(new LineSegment(ori,otherPoint[otherPoint.length-1]));
                    }  
                }  
        	}
        }  
        
    }
    
    public int numberOfSegments() {       // the number of line segments
	   return result.size();
    }
    public LineSegment[] segments() {               // the line segments
	   return result.toArray(new LineSegment[result.size()]);
    }
    private void checkDuplcate(Point[] points) {
		 for (int i = 0; i < points.length - 1; i++) {
			 for (int j = i+1;j <points.length; j++) {
				 if (points[i].compareTo(points[j]) == 0) {
				 	throw new java.lang.IllegalArgumentException();
				 }
			 }
		 }
	 }
    private void checkNull(Point[] points) {
		if(points == null || points.length ==0) throw new java.lang.IllegalArgumentException();
		for(Point p: points){
			if(p == null) throw new java.lang.IllegalArgumentException();
		}
	}
}
