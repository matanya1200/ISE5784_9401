package primitives;

/**
 * The Material class represents the material properties of an object in a 3D scene.
 * It includes diffuse coefficient (kD), specular coefficient (kS), and shininess (nShininess).
 */
public class Material {
    /**
     * Diffuse coefficient of the material.
     */
    public Double3 kD = Double3.ZERO;
    /**
     * Specular coefficient of the material.
     */
    public Double3 kS = Double3.ZERO;
    /**
     * Shininess factor of the material.
     */
    public Double3 kT = Double3.ZERO;
    public Double3 kR = Double3.ZERO;
    public int nShininess = 0;

    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kd the diffuse coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKd(Double3 kd){
        this.kD = kd;
        return this;
    }

    /**
     * Sets the diffuse coefficient of the material using a scalar value.
     *
     * @param kd the scalar value of the diffuse coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKd(double kd){
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param ks the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKs(Double3 ks){
        this.kS = ks;
        return this;
    }

    /**
     * Sets the specular coefficient of the material using a scalar value.
     *
     * @param ks the scalar value of the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKs(double ks){
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * Sets the shininess factor of the material.
     *
     * @param shine the shininess factor to set
     * @return this Material object for method chaining
     */
    public Material setShininess(int shine){
        nShininess = shine;
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kt the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKt(Double3 kt){
        this.kT = kt;
        return this;
    }

    /**
     * Sets the specular coefficient of the material using a scalar value.
     *
     * @param kt the scalar value of the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKt(double kt){
        this.kT = new Double3(kt);
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param kr the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKr(Double3 kr){
        this.kR = kr;
        return this;
    }

    /**
     * Sets the specular coefficient of the material using a scalar value.
     *
     * @param kr the scalar value of the specular coefficient to set
     * @return this Material object for method chaining
     */
    public Material setKr(double kr){
        this.kR = new Double3(kr);
        return this;
    }
}