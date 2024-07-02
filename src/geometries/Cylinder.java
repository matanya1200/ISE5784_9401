package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * The Cylinder class represents a cylinder in 3D space.
 * It extends Tube and includes an additional height property.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private double height;

    /**
     * Constructs a Cylinder with a specified radius, axis ray, and height.
     *
     * @param radius  the radius of the cylinder
     * @param axisRay the axis ray of the cylinder
     * @param height  the height of the cylinder
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
    public Vector getNormal(Point point) {

        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        // Calculate projection of point on the axis
        Vector v = point.subtract(p0);
        double t = dir.dotProduct(v);

        // Check if point is on the cylinder's bottom base
        if (Util.isZero(t)) {
            return dir.scale(-1).normalize();
        }
        // Check if point is on the cylinder's top base
        else if (Util.isZero(t - height)) {
            return dir.normalize();
        }

        // Otherwise, point is on the side surface
        Point o = p0.add(dir.scale(t));
        return point.subtract(o).normalize();
    }
}