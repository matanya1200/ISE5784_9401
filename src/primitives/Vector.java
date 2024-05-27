package primitives;

import static primitives.Util.isZero;

/**
 * The Vector class represents a vector in 3D space.
 * It extends the Point class and provides vector-specific operations.
 */
public class Vector extends Point{

    /**
     * Constructs a Vector with specified x, y, and z components.
     * Throws an IllegalArgumentException if the vector is a zero vector.
     *
     * @param x the x component
     * @param y the y component
     * @param z the z component
     * @throws IllegalArgumentException if the vector is a zero vector
     */
    public Vector(double x, double y, double z){
        this(new Double3(x, y, z));
    }

    /**
     * Constructs a Vector with a specified Double3 object.
     * Throws an IllegalArgumentException if the vector is a zero vector.
     *
     * @param xyz the Double3 object representing the vector components
     * @throws IllegalArgumentException if the vector is a zero vector
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create a zero vector.");
        }
    }

    /**
     * Adds this vector to another vector.
     *
     * @param other the vector to be added
     * @return the resulting vector
     */
    public Vector add(Vector other){
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Scales this vector by a scalar.
     *
     * @param scalar the scalar to scale the vector by
     * @return the resulting scaled vector
     */
    public Vector scale(double scalar) {
        if(isZero(scalar))
            throw new IllegalArgumentException("");
        return new Vector(xyz.scale(scalar));
    }

    /**
     * Computes the dot product of this vector with another vector.
     *
     * @param otherVector the other vector
     * @return the dot product
     */
    public double dotProduct(Vector otherVector) {
        return  xyz.d1 * otherVector.xyz.d1 +
                xyz.d2 * otherVector.xyz.d2 +
                xyz.d3 * otherVector.xyz.d3;
    }

    /**
     * Computes the cross product of this vector with another vector.
     *
     * @param otherVector the other vector
     * @return the resulting cross product vector
     */
    public Vector crossProduct(Vector otherVector) {
        if (this.normalize().equals(otherVector.normalize())){
            throw new IllegalArgumentException("Cannot compute cross product: vectors are parallel");
        }
         return new Vector(
                 xyz.d2 * otherVector.xyz.d3 - xyz.d3 * otherVector.xyz.d2,
                 xyz.d3 * otherVector.xyz.d1 - xyz.d1 * otherVector.xyz.d3,
                 xyz.d1 * otherVector.xyz.d2 - xyz.d2 * otherVector.xyz.d1
         );
    }

    /**
     * Computes the squared length of this vector.
     *
     * @return the squared length
     */
    public double lengthSquared() {
        return  xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * Computes the length of this vector.
     *
     * @return the length
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector, returning a new unit vector in the same direction.
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    /**
     * Checks if this vector is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the vectors are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector other)) return false;
        return this.xyz.equals(other.xyz);
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return a string representation of the vector
     */
    @Override
    public String toString() {
        return "Vector(" + xyz.d1 + ", " + xyz.d2 + ", " + xyz.d3 + ")";
    }
}
