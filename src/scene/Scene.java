package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;

    public Color background = Color.BLACK; // Default background color is black

    public AmbientLight ambientLight = AmbientLight.NONE; // Default ambient light is NONE

    public Geometries geometries = new Geometries(); // Default geometries is an empty model

    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor accepting the name of the scene
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Set the background color and return the scene (for method chaining)
     *
     * @param background the background color to set
     * @return the current scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Set the ambient light and return the scene (for method chaining)
     *
     * @param ambientLight the ambient light to set
     * @return the current scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Set the geometries and return the scene (for method chaining)
     *
     * @param geometries the geometries to set
     * @return the current scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Set the lights in the scene and return the scene (for method chaining)
     *
     * @param lights the list of light sources to set
     * @return the current scene object
     */
    public Scene setLights(List<LightSource> lights){
        this.lights = lights;
        return this;
    }

    /**
     * Retrieve the list of light sources in the scene
     *
     * @return the list of light sources
     */
    public List<LightSource> getLights() {
        return lights;
    }
}