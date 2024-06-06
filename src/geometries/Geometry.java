package geometries;

import primitives.*;
import java.util.List;

/**
 * The Geometry interface represents a geometric object in 3D space.
 * It defines a method to calculate the normal vector to the surface at a given point.
 */
public interface Geometry extends Intersectable {

    /**
     * Calculates the normal vector to the surface at the given point.
     *
     * @param point the point on the surface where the normal is to be calculated
     * @return the normal vector to the surface at the given point
     */
    Vector getNormal(Point point);

}
