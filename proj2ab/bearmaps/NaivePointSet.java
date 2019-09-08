package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> inputPoints;

    public NaivePointSet(List<Point> points) {
        inputPoints = points;
    }

    public Point nearest(double x, double y) {
        Point nearestPoint = inputPoints.get(0);
        double nearestDist = Point.distance(nearestPoint, new Point(x, y));
        for (Point point : inputPoints) {
            double thisDist = Point.distance(point, new Point(x, y));
            if (thisDist < nearestDist) {
                nearestDist = thisDist;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
