package lighting;

import primitives.*;

/**
 * The LightSource interface represents a light source in a 3D scene.
 * It defines methods to get the intensity of the light at a point and the direction to the light source from a point.
 */
public interface LightSource {
    /**
     * Returns the intensity of the light at a specified point.
     *
     * @param p the point at which the intensity is to be calculated
     * @return the intensity of the light at the specified point
     */
    Color getIntensity(Point p);
    /**
     * Returns the direction vector from a specified point towards the light source.
     *
     * @param p the point from which the direction vector is calculated
     * @return the direction vector towards the light source from the specified point
     */
    Vector getL(Point p);

    double getDistance(Point point);
}
