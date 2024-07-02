package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Triangle class represents a triangle in 3D space.
 * It is a specific type of polygon with exactly three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle with the specified vertices.
     *
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // First, find the intersection with the plane of the triangle
        Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
        List<Point> planeIntersections = plane.findIntersections(ray);

        if (planeIntersections == null) {
            return null;
        }

        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point p = planeIntersections.get(0);

        // Vectors from p0 to vertices
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        // Check if the point is inside the triangle using cross product
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double sign1 = alignZero(v.dotProduct(n1));
        double sign2 = alignZero(v.dotProduct(n2));
        double sign3 = alignZero(v.dotProduct(n3));

        // Check if the point is inside the triangle
        if ((sign1 > 0 && sign2 > 0 && sign3 > 0) || (sign1 < 0 && sign2 < 0 && sign3 < 0)) {
            List<GeoPoint> intersections = new ArrayList<>();
            intersections.add(new GeoPoint(this,p));
            return intersections;
        }

        return null;
    }
}