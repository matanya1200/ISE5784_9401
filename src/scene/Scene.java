package scene;

import geometries.AABB;
import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.Collections;
import java.util.Comparator;
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

    /**
     * פונקציה למיון רשימה של Intersectable לפי מרכז ה-Z של ה-Bounding Box שלהם.
     *
     * @param objects רשימה של אובייקטים מהסוג Intersectable
     * @return רשימה ממוינת של Intersectable לפי ציר Z
     */
    public List<Intersectable> sortBoundingBoxesByZ(List<Intersectable> objects) {
        Collections.sort(objects, new Comparator<Intersectable>() {
            @Override
            public int compare(Intersectable obj1, Intersectable obj2) {
                // מקבלים את ה-Bounding Box של כל אובייקט
                AABB box1 = obj1.getBoundingBox();
                AABB box2 = obj2.getBoundingBox();

                // חישוב מרכז ה-Z של ה-Bounding Box
                double zCenter1 = (box1.getMin().getZ() + box1.getMax().getZ()) / 2;
                double zCenter2 = (box2.getMin().getZ() + box2.getMax().getZ()) / 2;

                return Double.compare(zCenter1, zCenter2);
            }
        });
        return objects;
    }
}