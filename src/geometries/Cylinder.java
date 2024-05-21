package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in 3D space.
 * It extends Tube and includes an additional height property.
 */
public class Cylinder extends Tube{

    /** The height of the cylinder. */
    private double height;

    /**
     * Constructs a Cylinder with a specified radius, axis ray, and height.
     *
     * @param radius the radius of the cylinder
     * @param axisRay the axis ray of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Calculates the normal vector to the cylinder at a given point.
     *
     * @param point the point on the surface of the cylinder where the normal is to be calculated
     * @return the normal vector to the surface at the given point
     */
    @Override
    public Vector getNormal(Point point){
//        // Calculate the base and top centers of the cylinder
//        Point baseCenter = axisRay.getP0();
//        Point topCenter = axisRay.getP0().add(axisRay.getDir().scale(height));
//
//        // Check if the point is on the base or top caps
//        if (point.subtract(baseCenter).dotProduct(axisRay.getDir()) == 0) {
//            return axisRay.getDir().scale(-1).normalize();
//        }
//        if (point.subtract(topCenter).dotProduct(axisRay.getDir()) == 0) {
//            return axisRay.getDir().normalize();
//        }
//
//        // Calculate the projection of the point on the axisRay
//        double t = point.subtract(baseCenter).dotProduct(axisRay.getDir());
//        Point o = baseCenter.add(axisRay.getDir().scale(t));
//
//        // The normal is the vector from the projected point to the given point, normalized
//        return point.subtract(o).normalize();
        return null;
    }
}
