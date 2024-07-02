package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * The Tube class represents a tube in 3D space.
 * It extends RadialGeometry and includes an axis ray and a radius.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube.
     */
    protected Ray axisRay;

    /**
     * Constructs a Tube with a specified radius and axis ray.
     *
     * @param radius  the radius of the tube
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
     * @throws IllegalArgumentException if the point is on the tube's axis
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        // Calculate the projection of the point onto the axis
        double t = dir.dotProduct(point.subtract(p0));
        Point o;

        // Handle the case where point is exactly on the axis line
        if (Util.isZero(t)) {
            o = p0;
        } else {
            o = p0.add(dir.scale(t));
        }

        Vector normal = point.subtract(o);

        if (normal.lengthSquared() == 0) {
            throw new IllegalArgumentException("Point cannot be on the tube's axis");
        }

        return normal.normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}