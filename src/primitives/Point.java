package primitives;

/**
 * The Point class represents a point in 3D space.
 * It provides methods for point arithmetic and distance calculations.
 */
public class Point {

    /** The coordinates of the point. */
    protected Double3 xyz;

    /** A constant for the origin point (0,0,0). */
    public static final Point ZERO = new Point(0,0,0);

    /**
     * Constructs a Point with specified x, y, and z coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Point(double x, double y, double z){
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a Point with a specified Double3 object.
     *
     * @param xyz the Double3 object representing the coordinates
     */
    Point(Double3 xyz){
        this.xyz = xyz;
    }

    /**
     * Subtracts another point from this point, returning the resulting vector.
     *
     * @param other the point to be subtracted
     * @return the resulting vector
     */
    public Vector subtract(Point other){
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * Adds a vector to this point, returning the resulting point.
     *
     * @param other the vector to be added
     * @return the resulting point
     */
    public Point add(Vector other){
        return new Point(xyz.add(other.xyz));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param other the other point
     * @return the squared distance
     */
    public double distanceSquared(Point other){
        double dx = this.xyz.d1 - other.xyz.d1;
        double dy = this.xyz.d2 - other.xyz.d2;
        double dz = this.xyz.d3 - other.xyz.d3;

        return (dx * dx) + (dy * dy) + (dz * dz);
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance
     */
    public double distance(Point other){
        double dis = Math.sqrt(distanceSquared(other));
        return dis;
    }

    /**
     * Checks if this point is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the points are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Point other)) return false;
        return this.xyz.equals(other.xyz);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString(){
        return "Point(" + xyz.toString() + ")";
    }
}
