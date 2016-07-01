package Assignment3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by pramod on 16.3.6.
 */
public class FastCollinearPointsOld {
    private LineSegment[] lineSegments;
    private Set<MyLineSegment> segments = new TreeSet<>();

    public FastCollinearPointsOld(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        Point[] sortedPoints = points.clone();
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
        for (Point p : points) {
            findAndSaveSegment(p, sortedPoints);
        }

        lineSegments = new LineSegment[segments.size()];
        int i = 0;
        for (MyLineSegment mSegment : segments) {
            lineSegments[i++] = new LineSegment(mSegment.p, mSegment.q);
        }
        System.out.println("Segments: " + lineSegments.length);
    }

    private void findAndSaveSegment(Point p, Point[] points) {
        Arrays.sort(points, p.slopeOrder());
        List<Point> collinearPoints = new ArrayList<>();
        double prevSlope = Double.MAX_VALUE;
        for (Point x : points) {
            if (x == p) {
                collinearPoints.clear();
                prevSlope = Double.MAX_VALUE;
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
            segments.add(new MyLineSegment(collinearPoints.get(0), collinearPoints.get(collinearPoints.size() - 1)));
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

    private class MyLineSegment implements Comparable<MyLineSegment> {

        private Point p;
        private Point q;

        public MyLineSegment(Point p, Point q) {
            if (p == null || q == null) {
                throw new NullPointerException();
            }
            this.p = p;
            this.q = q;
        }

        @Override
        public int compareTo(MyLineSegment that) {
            if (this.p.compareTo(that.p) == 0) {
                return this.q.compareTo(that.q);
            }
            return this.p.compareTo(that.p);
        }
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // read the N points from a file
        In in = new In(new File("src/Assignment3/rs1423.txt"));
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        FastCollinearPointsOld collinear = new FastCollinearPointsOld(points);
        for (LineSegment segment : collinear.segments()) {
//            System.out.println(segment);
            if (segment != null) {
                segment.draw();
            }
        }
    }
}