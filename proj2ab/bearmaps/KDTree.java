package bearmaps;

import java.util.List;

public class KDTree {
    private treeNode root;

    private class treeNode {
        private Point point;
        private treeNode left;
        private treeNode right;

        private treeNode(Point p) {
            point = p;
        }
    }

    public KDTree(List<Point> points) {
        for (Point point : points) {
            root = put(root, point, 0);
        }
    }

    private treeNode put(treeNode x, Point p, int level) {
        if (x == null) {
            return new treeNode(p);
        }
        if (level % 2 == 0) {
            if (x.point.getX() < p.getX()) {
                x.right = put(x.right, p, level + 1);
            } else {
                x.left = put(x.left, p, level + 1);
            }
        } else {
            if (x.point.getY() < p.getY()) {
                x.right = put(x.right, p, level + 1);
            } else {
                x.left = put(x.left, p, level + 1);
            }
        }
        return x;
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        treeNode nearestNode = nearest(root, goal, root, 0);
        return nearestNode.point;
    }

    private treeNode nearest(treeNode n, Point goal, treeNode best, int level) {
        if (n == null) {
            return best;
        }

        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }

        double referenceDist;
        treeNode goodSide, badSide;

        if (level % 2 == 0) {
            referenceDist = Math.abs(n.point.getX() - goal.getX());
            if (n.point.getX() > goal.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            referenceDist = Math.abs(n.point.getY() - goal.getY());
            if (n.point.getY() > goal.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }

        best = nearest(goodSide, goal, best, level + 1);

        if (referenceDist * referenceDist < Point.distance(n.point, goal)) {
            best = nearest(badSide, goal, best, level + 1);
        }

        return best;
    }

    public static void main(String[] args) {
        /*
         Simple Test
        * @source: Discussion 9 from CS 61B
        */
        Point p1 = new Point(5, 6);
        Point p2 = new Point(1, 5);
        Point p3 = new Point(7, 3);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(4, 9);
        Point p6 = new Point(9, 1);
        Point p7 = new Point(8, 7);

        KDTree KDT = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        System.out.println(KDT.root.point.getX());              // expected 5.0
        System.out.println(KDT.root.left.point.getX());         // expected 1.0
        System.out.println(KDT.root.right.point.getX());        // expected 7.0
        System.out.println(KDT.root.left.left.point.getX());    // expected 2.0
        System.out.println(KDT.root.left.right.point.getX());   // expected 4.0
        System.out.println(KDT.root.right.left.point.getX());   // expected 9.0
        System.out.println(KDT.root.right.right.point.getX());  // expected 8.0
        System.out.println();
        Point ret = KDT.nearest(5, 5);
        System.out.println(ret.getX());                         // expected 5.0
        System.out.println(ret.getY());                         // expected 6.0
    }
}
