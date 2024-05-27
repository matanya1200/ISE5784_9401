package primitives;

/**
 * The Ray class represents a ray in 3D space, defined by a point (origin) and a direction vector.
 */
public class Ray {

    /** The origin point of the ray. */
    private final Point head;

    /** The direction vector of the ray. */
    private final Vector direction;

    /**
     * Constructs a Ray with a specified origin point and direction vector.
     * The direction vector is normalized.
     *
     * @param head the origin point of the ray
     * @param direction the direction vector of the ray
     */
    public Ray(Point head, Vector direction){
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Checks if this ray is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the rays are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray other)) return false;
        return this.head.equals(other.head) && this.direction.equals(other.direction);
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return a string representation of the ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + head +
                ", direction=" + direction +
                '}';
    }

    public Point getP0() {
        return head;
    }

    public Vector getDir() {
        return direction;
    }
}
