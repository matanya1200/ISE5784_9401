package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometric objects.
 * It is used to group multiple geometries together and perform operations on them as a whole.
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor for Geometries.
     * Creates an empty list of geometries.
     */
    public Geometries() {}

    /**
     * Constructor for Geometries with varargs of geometries.
     * Adds the given geometries to the list.
     *
     * @param geometries the geometries to add
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds geometries to the list.
     *
     * @param geometries the geometries to add
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * Finds the intersections of the given ray with the geometries in the list.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of GeoPoints where the ray intersects the geometries, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> Intersections = null;

        for (Intersectable geometry : geometries) {
            List<GeoPoint> tempGeoIntersections = geometry.findGeoIntersectionsHelper(ray);
            if (tempGeoIntersections != null) {
                if (Intersections == null) {
                    Intersections = new ArrayList<>();
                }
                Intersections.addAll(tempGeoIntersections);
            }
        }
        return Intersections;
    }
}