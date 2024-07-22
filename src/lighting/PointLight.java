package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The PointLight class represents a point light source in a 3D scene.
 * It extends Light and implements LightSource.
 */
public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructs a PointLight with a specified intensity and position.
     *
     * @param intensity the intensity of the point light
     * @param position  the position of the point light
     */
    public PointLight(Color intensity, Point position){
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation coefficient (kC) of the point light.
     *
     * @param kc the constant attenuation coefficient to set
     * @return this PointLight object for method chaining
     */
    public PointLight setKc(double kc){
        kC = kc;
        return this;
    }

    /**
     * Sets the linear attenuation coefficient (kL) of the point light.
     *
     * @param kl the linear attenuation coefficient to set
     * @return this PointLight object for method chaining
     */
    public PointLight setKl(double kl){
        kL = kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation coefficient (kQ) of the point light.
     *
     * @param kq the quadratic attenuation coefficient to set
     * @return this PointLight object for method chaining
     */
    public PointLight setKq(double kq){
        kQ = kq;
        return this;
    }

    /**
     * Calculates the intensity of the point light at a specified point.
     *
     * @param p the point at which the intensity is to be calculated
     * @return the intensity of the point light at the specified point
     */
    @Override
    public Color getIntensity(Point p){
        //Il = i0/kc+kl*d+kq*d^2
        double distance = position.distance(p);
        double attenuation = kC + kL * distance + kQ * distance * distance;
        return intensity.scale(1/attenuation);
    }

    /**
     * Calculates the direction vector from a specified point towards the point light.
     *
     * @param p the point from which the direction vector is calculated
     * @return the direction vector towards the point light from the specified point
     */
    @Override
    public Vector getL(Point p){
        return p.subtract(position).normalize();
    }

    /**
     * Returns the distance from the light source to the specified point.
     *
     * @param point the point to which the distance is calculated
     * @return the distance from the light source to the specified point
     */
    @Override
    public double getDistance(Point point){
        return position.distance(point);
    }
}
