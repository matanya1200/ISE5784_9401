package geometries;

import primitives.*;

/**
 * The Geometry class represents a geometric object in 3D space.
 * It defines methods to calculate the normal vector to the surface at a given point
 * and manage the emission color and material properties of the object.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The emission color of the geometry.
     * Default is black.
     */
    protected Color emission = Color.BLACK;

    /**
     * The material properties of the geometry.
     */
    private Material material = new Material();

    /**
     * Gets the emission color of the geometry.
     *
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Gets the material properties of the geometry.
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the emission color to set
     * @return the current geometry object (for chaining)
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material properties of the geometry.
     *
     * @param material the material to set
     * @return the current geometry object (for chaining)
     */
    public Geometry setMaterial(Material material){
        this.material= material;
        return this;
    }

    /**
     * Calculates the normal vector to the surface at the given point.
     *
     * @param point the point on the surface
     * @return the normal vector
     */
    public abstract Vector getNormal(Point point);

}