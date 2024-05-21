package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Plane class represents a plane in 3D space defined by a point and a normal vector.
 */
public class Plane implements Geometry{

    /** A point on the plane. */
    private final Point q0;

    /** The normal vector to the plane. */
    private final Vector normal;

    /**
     * Constructs a Plane given three points in the plane.
     *
     * @param x the first point in the plane
     * @param y the second point in the plane
     * @param z the third point in the plane
     */
    public Plane(Point x, Point y, Point z){
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
     * @param q0 the point on the plane
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
    public Vector getNormal(){
        return normal;
    }

    /**
     * Returns the normal vector to the plane at a given point.
     *
     * @param point the point at which the normal is to be calculated (ignored in this implementation)
     * @return the normal vector to the plane
     */
    public Vector getNormal(Point point){
        return normal;
    }
}
