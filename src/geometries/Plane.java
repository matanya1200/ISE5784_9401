package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a plane in 3D space defined by a point and a normal vector.
 */
public class Plane implements Geometry {

    /**
     * A point on the plane.
     */
    private final Point q0;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a Plane given three points in the plane.
     *
     * @param x the first point in the plane
     * @param y the second point in the plane
     * @param z the third point in the plane
     */
    public Plane(Point x, Point y, Point z) {
        this.q0 = x;

        Vector v1 = y.subtract(x);
        Vector v2 = z.subtract(x);
        Vector n = v1.crossProduct(v2);

        if (n.lengthSquared() == 0) {
            throw new IllegalArgumentException("The points must not be collinear or coinciding");
        }

        this.normal = n.normalize();
    }

    /**
     * Constructs a Plane given a point on the plane and a normal vector.
     *
     * @param q0     the point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return the normal vector to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Returns the normal vector to the plane at a given point.
     *
     * @param point the point at which the normal is to be calculated (ignored in this implementation)
     * @return the normal vector to the plane
     */
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Finds the intersection points between the plane and a given ray.
     *
     * @param ray the ray with which intersections are to be found
     * @return a list of intersection points between the plane and the ray,
     * or null if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        // Calculate the denominator of the intersection formula
        double denominator = normal.dotProduct(v);
        if (isZero(denominator)) {
            // The ray is parallel to the plane, no intersections
            return null;
        }

        // Calculate the numerator of the intersection formula
        Vector p0ToQ0;
        try {
            p0ToQ0 = q0.subtract(p0);
        } catch (IllegalArgumentException e) {
            // p0 is the same as q0, the ray starts from the plane
            return null;
        }

        double numerator = normal.dotProduct(p0ToQ0);

        double t = alignZero(numerator / denominator);

        if (t <= 0) {
            // The intersection is behind the ray's origin or on the origin itself
            return null;
        }

        // Calculate the intersection point
        Point intersectionPoint = ray.getPoint(t);

        // Create the list of intersections and add the intersection point
        List<Point> intersections = new ArrayList<>();
        intersections.add(intersectionPoint);

        return intersections;
    }
}
