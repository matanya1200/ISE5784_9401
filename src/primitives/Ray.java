package primitives;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in 3D space, defined by a point (origin) and a direction vector.
 */
public class Ray {

    /** The origin point of the ray. */
    private final Point head;

    /** The direction vector of the ray. */
    private final Vector direction;

    /**
     * Constructs a Ray with a specified origin point and direction vector.
     * The direction vector is normalized.
     *
     * @param head the origin point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction){
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Checks if this ray is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the rays are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray other)) return false;
        return this.head.equals(other.head) && this.direction.equals(other.direction);
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return a string representation of the ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + head +
                ", direction=" + direction +
                '}';
    }

    public Point getP0() {
        return head;
    }

    public Vector getDir() {
        return direction;
    }

    /**
     * Get a point on the ray at a distance t from p0.
     * @param t distance from p0
     * @return the point at distance t
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return head;
        }
        return head.add(direction.scale(t));
    }

    /**
     * Finds the closest point to the ray's starting point from a list of points.
     *
     * @param points the list of points to search through
     * @return the closest point to the ray's starting point, or null if the list is empty
     */
    public Point findClosestPoint(List<Point> points){
        if(points == null || points.isEmpty()){
            return null;
        }
        double closestDistance = Double.POSITIVE_INFINITY;
        double d;
        Point closestPoint = points.get(0);
        for(Point p:points){
            d = head.distanceSquared(p);
            if(d < closestDistance){
                closestDistance = d;
                closestPoint = p;
            }
        }
        return closestPoint;
    }
}
