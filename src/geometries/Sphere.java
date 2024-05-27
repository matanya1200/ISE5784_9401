package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a sphere in 3D space.
 * It extends RadialGeometry and includes a center point and a radius.
 */
public class Sphere extends RadialGeometry{

    /** The center point of the sphere. */
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
    public Vector getNormal(Point point){
        return point.subtract(center).normalize();
    }
}
