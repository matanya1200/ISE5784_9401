package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light class represents a constant light source that affects all objects equally.
 */
public class AmbientLight extends Light {
    /**
     * Static constant representing no ambient light
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructor with Color and Double3 attenuation factor
     *
     * @param Ia the original color intensity
     * @param Ka the attenuation factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }
}