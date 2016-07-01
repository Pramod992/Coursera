package Assignment3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pramod on 16.6.5.
 */
public class BruteCollinearPoints {

    private int N;
    private LineSegment[] lineSegments;
    private Point[] sortedPoints;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        N = points.length;

        if (points == null) {
            throw new NullPointerException();
        }
        sortedPoints = points.clone();
        Arrays.sort(sortedPoints);


        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i] == null) {
                throw new NullPointerException();
            } else if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<LineSegment> lineSegment = new ArrayList<>();
        for (int p = 0; p < N - 3; p++) {
            for (int q = p + 1; q < N - 2; q++) {
                for (int r = q + 1; r < N - 1; r++) {
                    for (int s = r + 1; s < N; s++) {
                        if (sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[r])
                                && sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[s])) {
                            lineSegment.add(new LineSegment(sortedPoints[p], sortedPoints[s]));
                        }
                    }
                }
            }
        }
        lineSegments = lineSegment.toArray(new LineSegment[lineSegment.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // read the N points from a file
        In in = new In(new File("src/Assignment3/input80.txt"));
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
            if (segment != null) {
                segment.draw();
            }
        }
    }

}