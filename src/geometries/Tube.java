package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Tube class represents a tube in 3D space.
 * It extends RadialGeometry and includes an axis ray and a radius.
 */
public class Tube extends RadialGeometry{

    /** The axis ray of the tube. */
    private Ray axisRay;

    /**
     * Constructs a Tube with a specified radius and axis ray.
     *
     * @param radius the radius of the tube
     * @param axisRay the axis ray of the tube
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     *
     * @param point the point on the surface of the tube where the normal is to be calculated
     * @return the normal vector to the surface at the given point
     */
    @Override
    public Vector getNormal(Point point){
//        // Calculate the projection of the point on the axisRay
//        Point p0 = axisRay.getP0();
//        Vector v = axisRay.getDir();
//
//        // Project the point onto the axis
//        double t = point.subtract(p0).dotProduct(v);
//        Point o = p0.add(v.scale(t));
//
//        // The normal is the vector from the projected point to the given point, normalized
//        return point.subtract(o).normalize();

        return null;
    }
}
