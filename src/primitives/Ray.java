package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in 3D space, defined by a point (origin) and a direction vector.
 */
public class Ray {

    private static final double DELTA = 0.1;
    /**
     * The origin point of the ray.
     */
    private final Point head;

    /**
     * The direction vector of the ray.
     */
    private final Vector direction;

    /**
     * Constructs a Ray with a specified origin point and direction vector.
     * The direction vector is normalized.
     *
     * @param head      the origin point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Constructs a Ray with a specified point, direction vector, and normal vector.
     * The direction vector is normalized and the origin is adjusted by DELTA in the direction of the normal.
     *
     * @param point     the point of origin
     * @param direction the direction vector
     * @param normal    the normal vector
     */
    public Ray(Point point, Vector direction, Vector normal) {
        double nv = normal.dotProduct(direction);
        Vector delta = normal.scale(nv > 0 ? DELTA : -DELTA);
        this.head = point.add(delta);
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
     *
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
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest geometric point to the ray's starting point from a list of geometric points.
     *
     * @param geoPoints the list of geometric points to search through
     * @return the closest geometric point to the ray's starting point, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints){
        if (geoPoints == null || geoPoints.isEmpty()) {
            return null;
        }
        double closestDistance = Double.POSITIVE_INFINITY;
        double d;
        GeoPoint closestGeoPoint = geoPoints.get(0);
        for (GeoPoint gp : geoPoints) {
            d = head.distanceSquared(gp.point);
            if (d < closestDistance) {
                closestDistance = d;
                closestGeoPoint = gp;
            }
        }
        return closestGeoPoint;
    }
}