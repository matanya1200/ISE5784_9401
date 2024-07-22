package geometries;

import primitives.*;
import java.util.List;

/**
 * Represents a node in a Bounding Volume Hierarchy (BVH) used for efficient ray tracing.
 * A BVHNode contains references to two child intersectable objects and a bounding box
 * that encompasses both children.
 */
public class BVHNode extends Intersectable {
    private Intersectable left;
    private Intersectable right;
    private AABB boundingBox;

    /**
     * Constructs a BVHNode with the given left and right child intersectables.
     *
     * @param left  The left child intersectable.
     * @param right The right child intersectable.
     */
    public BVHNode(Intersectable left, Intersectable right) {
        this.left = left;
        this.right = right;
        this.boundingBox = new AABB(
                new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY),
                new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        );
        if (left != null) this.boundingBox.expandToInclude(left.getBoundingBox());
        if (right != null) this.boundingBox.expandToInclude(right.getBoundingBox());
    }

    /**
     * Finds the intersections of the given ray with the geometries contained in this BVHNode.
     * If the ray does not intersect the bounding box, no intersections are returned.
     *
     * @param ray The ray to intersect with the geometries.
     * @return A list of intersection points (GeoPoints) or null if no intersections are found.
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

        leftIntersections.addAll(rightIntersections);
        return leftIntersections;
    }

    /**
     * Gets the bounding box that encompasses this BVHNode.
     *
     * @return The bounding box of this BVHNode.
     */
    @Override
    public AABB getBoundingBox() {
        return boundingBox;
    }
}