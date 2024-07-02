package lighting;

import primitives.Color;

/**
 * The Light class represents a generic light source in a 3D scene.
 * It provides the intensity of the light.
 */
abstract class Light {
    protected Color intensity;

    /**
     * Constructs a light source with a specified color intensity.
     *
     * @param color the color intensity of the light
     */
    protected Light(Color color){
        intensity = color;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return the color intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
