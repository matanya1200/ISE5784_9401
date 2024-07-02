package geometries;

/**
 * RadialGeometry is an abstract class that represents a radial geometric object in 3D space.
 * It defines a common property for all radial geometries: the radius.
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * The radius of the radial geometry.
     */
    protected double radius;

    /**
     * Constructs a RadialGeometry with the specified radius.
     *
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}