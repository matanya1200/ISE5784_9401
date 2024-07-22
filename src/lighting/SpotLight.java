package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The SpotLight class represents a spot light source in a 3D scene.
 * It extends PointLight and includes a direction vector.
 */
public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * the narrow beam value.
     */
    private double narrowBeam = 1d;

    /**
     * Constructs a SpotLight with a specified intensity, position, and direction.
     *
     * @param intensity  the intensity of the spot light
     * @param position   the position of the spot light
     * @param direction  the direction vector of the spot light
     */
    public SpotLight(Color intensity, Point position, Vector direction){
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Sets the constant attenuation coefficient (kC) of the spot light.
     *
     * @param kc the constant attenuation coefficient to set
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKc(double kc){
        super.setKc(kc);
        return this;
    }

    /**
     * Sets the linear attenuation coefficient (kL) of the spot light.
     *
     * @param kl the linear attenuation coefficient to set
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKl(double kl){
        super.setKl(kl);
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient (kQ) of the spot light.
     *
     * @param kq the quadratic attenuation coefficient to set
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKq(double kq){
        super.setKq(kq);
        return this;
    }

    /**
     * Calculates the intensity of the spot light at a specified point.
     *
     * @param p the point at which the intensity is to be calculated
     * @return the intensity of the spot light at the specified point
     */
    @Override
    public Color getIntensity(Point p) {
        // Il = i0 * max(0, dir * l) / (kc + kl * d + kq * d^2)
        Color pointLightIntensity = super.getIntensity(p);
        double projection = direction.dotProduct(getL(p));
        double factor = Math.max(0, projection);
        if (narrowBeam != 1) {
            factor = Math.pow(factor, narrowBeam);
        }
        return (factor == 0) ? Color.BLACK : pointLightIntensity.scale(factor);
    }

    /**
     * Calculates the direction vector from a specified point towards the spot light.
     *
     * @param p the point from which the direction vector is calculated
     * @return the direction vector towards the spot light from the specified point
     */
    @Override
    public Vector getL(Point p){
        return super.getL(p);
    }

    /**
     * Sets the narrow beam factor for the spot light.
     *
     * @param n the narrow beam factor to set, should be >= 1
     * @return this SpotLight object for method chaining
     */
    public LightSource setNarrowBeam(double n) {
        this.narrowBeam = n;
        return this;
    }
}
