package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable interface represents a geometrical object that can be intersected by a ray.
 * It defines a method to find intersections between the object and a given ray.
 */
public interface Intersectable {

    /**
     * Finds the intersection points between the object and a given ray.
     *
     * @param ray the ray with which intersections are to be found
     * @return a list of intersection points between the object and the ray
     */
    List<Point> findIntersections(Ray ray);
}
