import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KdTree {
    private static final boolean VECTICAL = false;

    private Node head;
    private int nodeCnt;
    private double maxX;
    private double maxY;
    private Point2D nearestPoint;

    private static class Node {
        private final Point2D point;
        private final boolean color;
        private Node left;
        private Node right;
        private final Node parent;

        public Node(Point2D point, Node parent) {
            this.point = point;
            this.parent = parent;
            this.color = (parent == null ? VECTICAL : !parent.color);
        }

        public int eq(Point2D that) {
            if (this.color == VECTICAL) {
                double thisX = this.point.x();
                double thatX = that.x();
                return Double.compare(thisX, thatX);
            } else {
                double thisY = this.point.y();
                double thatY = that.y();
                return Double.compare(thisY, thatY);
            }
        }
    }

    public KdTree() {
        nearestPoint = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return nodeCnt;
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (contains(p)) {
            return;
        }

        if (isEmpty()) {
            head = new Node(p, null);
            nodeCnt++;
            maxX = p.x();
            maxY = p.y();
            return;
        }

        Node currentNode = head;
        Node prevNode = currentNode.parent;

        boolean left = false;
        while (currentNode != null) {
            prevNode = currentNode;

            int relation = currentNode.eq(p);
            if (relation >= 0) {
                left = true;
                currentNode = currentNode.left;
            } else {
                left = false;
                currentNode = currentNode.right;
            }
        }
        currentNode = new Node(p, prevNode);
        maxX = maxX < p.x() ? p.x() : maxX;
        maxY = maxY < p.y() ? p.y() : maxY;

        if (left)
            prevNode.left = currentNode;
        else
            prevNode.right = currentNode;

        nodeCnt++;
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        Queue<Node> checkQueue = new LinkedList<>();
        checkQueue.add(head);

        while (!checkQueue.isEmpty()) {
            Node curNode = checkQueue.remove();

            if (curNode != null) {
                if (curNode.point.equals(p))
                    return true;
                if (curNode.eq(p) < 0)
                    checkQueue.add(curNode.right);
                if (curNode.eq(p) >= 0)
                    checkQueue.add(curNode.left);
            }
        }

        return false;
    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, maxX+1);
        StdDraw.setYscale(0, maxY+1);

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            if (cur != null)
            {
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                cur.point.draw();
            }
        }

//        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        List<Point2D> rangeList = new LinkedList<>();

        Queue<Node> checkQueue = new LinkedList<>();
        checkQueue.add(head);

        while (!checkQueue.isEmpty()) {
            Node curNode = checkQueue.remove();

            if (curNode != null) {
                if (rect.contains(curNode.point)) {
                    rangeList.add(curNode.point);
                    checkQueue.add(curNode.left);
                    checkQueue.add(curNode.right);
                } else {
                    if (curNode.color == VECTICAL) {
                        if (rect.xmax() < curNode.point.x())
                            checkQueue.add(curNode.left);
                        else if (rect.xmin() > curNode.point.x())
                            checkQueue.add(curNode.right);
                        else {
                            checkQueue.add(curNode.left);
                            checkQueue.add(curNode.right);
                        }
                    } else {
                        if (rect.ymax() < curNode.point.y())
                            checkQueue.add(curNode.left);
                        else if (rect.ymin() > curNode.point.y())
                            checkQueue.add(curNode.right);
                        else {
                            checkQueue.add(curNode.left);
                            checkQueue.add(curNode.right);
                        }
                    }

                }

            }
        }

        return rangeList;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        nearestPoint = head.point;
        nearest(p, head);

        return nearestPoint;
    }

    private void nearest(Point2D p, Node node)
    {
        if (node == null)
            return;

        if (p.distanceSquaredTo(node.point) < p.distanceSquaredTo(nearestPoint))
        {
            nearestPoint = node.point;
        }

        if (node.left == null) {
            nearest(p, node.right);
            return;
        }

        if (node.right == null) {
            nearest(p, node.left);
            return;
        }

        double toLeft = p.distanceSquaredTo(node.left.point);
        double toRight = p.distanceSquaredTo(node.right.point);
        boolean leftFirst = false;
        if (toLeft < toRight) {
            nearest(p, node.left);
            leftFirst = true;
        } else {
            nearest(p, node.right);
        }

        double orientationDistance = 0;
        if (leftFirst) {
            orientationDistance = orientationDistance(p, node.right);
        } else {
            orientationDistance = orientationDistance(p, node.left);
        }

        double nearestD = p.distanceSquaredTo(nearestPoint);
        if (orientationDistance < nearestD)
        {
            nearest(p, (leftFirst ? node.right : node.left));
        }
    }

    private double orientationDistance(Point2D p, Node node) {
        if (node.color == VECTICAL) {
            return p.distanceSquaredTo(new Point2D(node.point.x(), p.y()));
        } else {
            return p.distanceSquaredTo(new Point2D(p.x(), node.point.y()));
        }
    }

    public static void main(String[] args) {
        String filename = "/home/mirzaiev/me/coursera/algs4/tests/5/kdtree/circle4.txt";
        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        double x1 = 574;
        double y1 = 770;
//        double x1 = 2;
//        double y1 = 3;
        Point2D point = new Point2D(x1, y1);

        StdDraw.setPenRadius(0.01);
        kdtree.draw();
//        point.draw();
        StdDraw.show();
    }
}