package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light class represents a constant light source that affects all objects equally.
 */
public class AmbientLight {
    /** The intensity of the ambient light */
    private final Color intensity;

    /** Static constant representing no ambient light */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructor with Color and Double3 attenuation factor
     * @param Ia the original color intensity
     * @param Ka the attenuation factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * Constructor with Color and double attenuation factor
     * @param Ia the original color intensity
     * @param Ka the attenuation factor
     */
    public AmbientLight(Color Ia, double Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * Gets the intensity of the ambient light
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
