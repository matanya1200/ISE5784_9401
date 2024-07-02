package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The Intersectable class represents a geometrical object that can be intersected by a ray.
 * It defines methods to find intersections between the object and a given ray.
 */
public abstract class Intersectable {

    /**
     * GeoPoint is a helper class to represent a point of intersection between a ray and a geometry.
     * It contains the intersected geometry and the intersection point.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor for GeoPoint.
         *
         * @param geometry the intersected geometry
         * @param point the intersection point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Finds all intersection points between the ray and the geometrical object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if no intersections are found
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds all intersections (as GeoPoint objects) between the ray and the geometrical object.
     *
     * @param ray the ray to intersect with the object
     * @return a list of GeoPoint objects representing the intersections, or null if no intersections are found
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method to find all intersections (as GeoPoint objects) between the ray and the geometrical object.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param ray the ray to intersect with the object
     * @return a list of GeoPoint objects representing the intersections, or null if no intersections are found
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}