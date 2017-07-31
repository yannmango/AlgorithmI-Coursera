import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private final LineSegment[] segment;
	public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
		ArrayList<LineSegment> found = new ArrayList<LineSegment>();
		checkNull(points);
		checkDuplcate(points);
		Point[] copy = Arrays.copyOf(points,points.length);    /*need a copy of point array because needing sort point[] 
		                                                        * without changing original input point[]*/
		Arrays.sort(copy);
		// find whether four consecutive points can make a line segment, if so, store it to result
		for (int p = 0; p < copy.length-3; p++) {
			for (int q = p + 1; q < copy.length-2; q++) {
				for (int r = q + 1; r < copy.length-1; r++) {
					for (int s = r + 1;s < copy.length; s++) {
						if (Double.compare(copy[p].slopeTo(copy[q]),copy[p].slopeTo(copy[r]))==0 && 
								Double.compare(copy[p].slopeTo(copy[q]), copy[p].slopeTo(copy[s])) ==0)
							found.add(new LineSegment(copy[p],copy[s]));
					}
				}
			}
		}
		// convert result to array
		segment = found.toArray(new LineSegment[found.size()]);
	}
	public int numberOfSegments() {       // the number of line segments
		return segment.length;
	}
	public LineSegment[] segments() {               // the line segments
		return Arrays.copyOf(segment,numberOfSegments());
	}
	//check whether points array contains duplicate elements,if so, throw an exception
	private void checkDuplcate(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i+1;j <points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
	}
	//check whether points array contains null element, if so, throw an exception
	private void checkNull(Point[] points) {
		if(points == null || points.length ==0) throw new java.lang.IllegalArgumentException();
		for(Point p: points){
			if(p == null) throw new java.lang.IllegalArgumentException();
		}
	}
}
