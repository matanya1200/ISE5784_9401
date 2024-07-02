package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> geometries = new LinkedList<Intersectable>();

    // Default constructor
    public Geometries() {}

    // Constructor with varargs of geometries
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    // Method to add geometries to the list
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> Geointersections = null;

        for (Intersectable geometry : geometries) {
            List<GeoPoint> tempGeoIntersections = geometry.findGeoIntersectionsHelper(ray);
            if (tempGeoIntersections != null) {
                if (Geointersections == null) {
                    Geointersections = new ArrayList<>();
                }
                Geointersections.addAll(tempGeoIntersections);
            }
        }
        return Geointersections;
    }
}