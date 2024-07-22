package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Sphere class represents a sphere in 3D space.
 * It extends RadialGeometry and includes a center point and a radius.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private Point center;

    /**
     * Constructs a Sphere with a specified center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     *
     * @param point the point on the surface of the sphere where the normal is to be calculated
     * @return the normal vector to the surface at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Finds intersections between a ray and the sphere.
     *
     * @param ray the ray to intersect with the sphere
     * @return a list of GeoPoint objects representing the intersections, or null if no intersections are found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector u;
        try {
            u = center.subtract(p0); // Vector from ray's origin to the sphere's center
        } catch (IllegalArgumentException e) {
            // If p0 is the center of the sphere, return the point on the surface in the ray's direction
            List<GeoPoint> intersections = new ArrayList<>();
            intersections.add(new GeoPoint(this,p0.add(v.scale(radius))));
            return intersections;
        }

        double tm = alignZero(v.dotProduct(u)); // Projection of u on v
        double dSquared = alignZero(u.lengthSquared() - tm * tm); // Distance squared from the sphere's center to the ray
        double rSquared = alignZero(radius * radius); // Sphere's radius squared

        if (dSquared >= rSquared) {
            return null; // No intersections if the distance is greater than the sphere's radius
        }

        double th = alignZero(Math.sqrt(rSquared - dSquared)); // Distance from the intersection points to tm
        double t1 = alignZero(tm - th); // Distance to the first intersection point
        double t2 = alignZero(tm + th); // Distance to the second intersection point

        List<GeoPoint> intersections = new ArrayList<>();

        if (t1 > 0) {
            intersections.add(new GeoPoint(this,ray.getPoint(t1))); // Add the first intersection point if it's in the positive direction of the ray
        }
        if (t2 > 0) {
            intersections.add(new GeoPoint(this,ray.getPoint(t2))); // Add the second intersection point if it's in the positive direction of the ray
        }

        return intersections.isEmpty() ? null : intersections; // Return null if no valid intersections
    }

    /**
     * Returns the bounding box of the sphere.
     *
     * @return the bounding box of the sphere
     */
    @Override
    public AABB getBoundingBox() {
        Point min = center.subtract(new Vector(radius, radius, radius));
        Point max = center.add(new Vector(radius, radius, radius));
        return new AABB(min, max);
    }
}