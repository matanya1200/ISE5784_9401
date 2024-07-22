package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents an Axis-Aligned Bounding Box (AABB).
 * This class is used to represent a bounding box that is aligned with the coordinate axes.
 * It is useful for efficient intersection tests and spatial partitioning.
 */
public class AABB {
    private Point min;
    private Point max;

    /**
     * Constructs an AABB with the given minimum and maximum points.
     *
     * @param min The minimum point of the bounding box.
     * @param max The maximum point of the bounding box.
     */
    public AABB(Point min, Point max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if a given ray intersects with this AABB.
     *
     * @param ray The ray to test for intersection with this AABB.
     * @return true if the ray intersects with this AABB, false otherwise.
     */
    public boolean intersects(Ray ray) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        double tmin = (min.getX() - p0.getX()) / dir.getX();
        double tmax = (max.getX() - p0.getX()) / dir.getX();

        if (tmin > tmax) {
            double temp = tmin;
            tmin = tmax;
            tmax = temp;
        }

        double tymin = (min.getY() - p0.getY()) / dir.getY();
        double tymax = (max.getY() - p0.getY()) / dir.getY();

        if (tymin > tymax) {
            double temp = tymin;
            tymin = tymax;
            tymax = temp;
        }

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        double tzmin = (min.getZ() - p0.getZ()) / dir.getZ();
        double tzmax = (max.getZ() - p0.getZ()) / dir.getZ();

        if (tzmin > tzmax) {
            double temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        return true;
    }

    /**
     * Expands this AABB to include another AABB.
     * This method modifies the current AABB to also enclose the given AABB.
     *
     * @param other The other AABB to include.
     */
    public void expandToInclude(AABB other) {
        this.min = new Point(
                Math.min(this.min.getX(), other.min.getX()),
                Math.min(this.min.getY(), other.min.getY()),
                Math.min(this.min.getZ(), other.min.getZ())
        );
        this.max = new Point(
                Math.max(this.max.getX(), other.max.getX()),
                Math.max(this.max.getY(), other.max.getY()),
                Math.max(this.max.getZ(), other.max.getZ())
        );
    }

    /**
     * Gets the minimum point of this AABB.
     *
     * @return The minimum point of this AABB.
     */
    public Point getMin() {
        return min;
    }

    /**
     * Gets the maximum point of this AABB.
     *
     * @return The maximum point of this AABB.
     */
    public Point getMax() {
        return max;
    }
}