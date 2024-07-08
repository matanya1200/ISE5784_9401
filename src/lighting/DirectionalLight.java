package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The DirectionalLight class represents a directional light source in a 3D scene.
 * It extends the Light class and implements the LightSource interface.
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    /**
     * Constructs a directional light with a specified intensity and direction.
     *
     * @param intensity  the color intensity of the light
     * @param direction  the direction vector of the light
     */
    public DirectionalLight(Color intensity, Vector direction){
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Returns the intensity of the light at a given point (unused for directional light).
     *
     * @param p the point in the scene (ignored for directional light)
     * @return the color intensity of the light
     */
    @Override
    public Color getIntensity(Point p){
        return intensity;
    }

    /**
     * Returns the direction vector of the light.
     *
     * @param p the point in the scene (ignored for directional light)
     * @return the direction vector of the light
     */
    @Override
    public Vector getL(Point p){
        return direction;
    }

    @Override
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
}
