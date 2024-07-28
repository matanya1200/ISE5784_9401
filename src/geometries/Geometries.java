package geometries;

import primitives.*;

import java.util.*;

/**
 * The Geometries class represents a collection of geometric objects.
 * It is used to group multiple geometries together and perform operations on them as a whole.
 */
public class Geometries extends Intersectable {

    private final List<Intersectable> geometries = new LinkedList<>();

    private Intersectable root;

    private AABB boundingBox;

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
        List<Intersectable> allGeometries = new ArrayList<>(Arrays.asList(geometries));
        if (root != null) {
            allGeometries.add(root);
        }
        root = new BVHNode(allGeometries);
    }

    /**
     * Finds the intersections of the given ray with the geometries in the list.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of GeoPoints where the ray intersects the geometries, or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
//        List<GeoPoint> Intersections = null;
//
//        for (Intersectable geometry : geometries) {
//            List<GeoPoint> tempGeoIntersections = geometry.findGeoIntersectionsHelper(ray);
//            if (tempGeoIntersections != null) {
//                if (Intersections == null) {
//                    Intersections = new ArrayList<>();
//                }
//                Intersections.addAll(tempGeoIntersections);
//            }
//        }
//        return Intersections;
        return root != null ? root.findGeoIntersections(ray) : null;
    }

    /**
     * Gets the axis-aligned bounding box (AABB) that encompasses this collection of geometries.
     *
     * @return the bounding box of this collection of geometries
     */
    @Override
    public AABB getBoundingBox() {
//        return boundingBox;
        return root != null ? root.getBoundingBox() : null;
    }

    /**
     * Updates the bounding box to encompass all the geometries in the list.
     */
    private void updateBoundingBox() {
        if (geometries.isEmpty()) {
            boundingBox = null;
            return;
        }

        Point min = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Point max = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        for (Intersectable geometry : geometries) {
            AABB geoBBox = geometry.getBoundingBox();
            if (geoBBox != null) {
                min = new Point(
                        Math.min(min.getX(), geoBBox.getMin().getX()),
                        Math.min(min.getY(), geoBBox.getMin().getY()),
                        Math.min(min.getZ(), geoBBox.getMin().getZ())
                );
                max = new Point(
                        Math.max(max.getX(), geoBBox.getMax().getX()),
                        Math.max(max.getY(), geoBBox.getMax().getY()),
                        Math.max(max.getZ(), geoBBox.getMax().getZ())
                );
            }
        }

        boundingBox = new AABB(min, max);
    }
}