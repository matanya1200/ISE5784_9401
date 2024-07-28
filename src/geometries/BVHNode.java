package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The BVHNode class represents a node in a Bounding Volume Hierarchy (BVH) used for efficient
 * ray tracing. A BVHNode contains references to two child intersectable objects and a bounding box
 * that encompasses both children.
 */
public class BVHNode extends Intersectable {
    private Intersectable left;
    private Intersectable right;
    private AABB boundingBox;

    /**
     * Constructs a BVHNode from a list of intersectable objects.
     * The objects are recursively split into two groups to form the hierarchy.
     *
     * @param objects the list of intersectable objects to be included in this node.
     */
    public BVHNode(List<Intersectable> objects) {
        if (objects.size() == 1) {
            left = objects.get(0);
            right = null;
        } else if (objects.size() == 2) {
            left = objects.get(0);
            right = objects.get(1);
        } else {
            int mid = objects.size() / 2;
            left = new BVHNode(objects.subList(0, mid));
            right = new BVHNode(objects.subList(mid, objects.size()));
        }

        boundingBox = new AABB(
                left.getBoundingBox().getMin(),
                left.getBoundingBox().getMax()
        );
        if (right != null) {
            boundingBox.expandToInclude(right.getBoundingBox());
        }
    }

    /**
     * Finds the intersections of a given ray with the geometric objects in this BVHNode.
     * If the ray does not intersect the bounding box of this node, it returns null.
     *
     * @param ray the ray to intersect with the geometric objects.
     * @return a list of GeoPoint intersections, or null if there are no intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (!boundingBox.intersects(ray)) {
            return null;
        }

        List<GeoPoint> leftIntersections = left != null ? left.findGeoIntersections(ray) : null;
        List<GeoPoint> rightIntersections = right != null ? right.findGeoIntersections(ray) : null;

        if (leftIntersections == null) return rightIntersections;
        if (rightIntersections == null) return leftIntersections;

        // Create a new mutable list and add all intersections
        List<GeoPoint> allIntersections = new ArrayList<>(leftIntersections);
        allIntersections.addAll(rightIntersections);
        return allIntersections;
    }

    /**
     * Gets the bounding box of this BVHNode.
     *
     * @return the bounding box of this BVHNode.
     */
    @Override
    public AABB getBoundingBox() {
        return boundingBox;
    }
}