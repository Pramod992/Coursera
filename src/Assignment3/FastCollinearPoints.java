
package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);

        //corner case
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i] == null) {
                throw new NullPointerException();
            }
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        //finding segments
        int count = 0;
        for (Point p : sortedPoints) {
            if (sortedPoints.length == count - 3) {
                break;
            }
            findAndSaveSegment(p, points);
            count++;
        }
//        System.out.println("Segments: " + segments.size());
        lineSegments = segments.toArray(new LineSegment[segments.size()]);
    }

    private void findAndSaveSegment(Point p, Point[] points) {

        Arrays.sort(points, p.slopeOrder());

        List<Point> collinearPoints = new ArrayList<>();
        double prevSlope = Double.MAX_VALUE;
        for (Point x : points) {
            if (x == p) {
                continue;
            }
            double slope = p.slopeTo(x);

            if (Double.compare(slope, prevSlope) != 0) {
                //Save if >3 collinear points
                saveSegment(collinearPoints, p);
                //clear list
                collinearPoints.clear();
            }

            collinearPoints.add(x);
            prevSlope = slope;

        }
        //save
        saveSegment(collinearPoints, p);
    }

    private void saveSegment(List<Point> collinearPoints, Point p) {
        if (collinearPoints.size() >= 3) {
            collinearPoints.add(p);
            Collections.sort(collinearPoints);
            LineSegment newSegment = new LineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1));
            if (p.compareTo(collinearPoints.get(0)) == 0) {
                segments.add(newSegment);
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }

    // the line segments
    public LineSegment[] segments() {

        return Arrays.copyOf(lineSegments, lineSegments.length);
    }

    public static void main(String[] args) {
//        In in = new In(new File("src/Assignment3/input48.txt"));
//        int N = in.readInt();
//        Point[] points = new Point[N];
//        for (int i = 0; i < N; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//        // draw the points
//        StdDraw.show(0);
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            if (segment != null) {
//                segment.draw();
//            }
//        }
    }
}