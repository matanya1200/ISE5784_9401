package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    // Default constructor
    public Geometries() {
    }

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
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;

        for (Intersectable geometry : geometries) {
            List<Point> tempIntersections = geometry.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null) {
                    intersections = new ArrayList<>();
                }
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
