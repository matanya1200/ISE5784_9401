package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in a 3D rendering system.
 * It defines the camera's position, orientation, screen size, and distance to the view plane.
 */
public class Camera implements Cloneable {

    /**
     * The position of the camera.
     */
    private Point position = Point.ZERO;

    /**
     * The direction in which the camera is facing.
     */
    private Vector vTo = null;

    /**
     * The up vector of the camera.
     */
    private Vector vUp = null;

    /**
     * The right vector of the camera.
     */
    private Vector vRight = null;

    /**
     * The width of the screen.
     */
    private double screenWidth = 0.0d;

    /**
     * The height of the screen.
     */
    private double screenHeight = 0.0d;

    /**
     * The distance from the camera to the screen.
     */
    private double distanceToScreen = 0.0d;

    /**
     * The ImageWriter for outputting the rendered image.
     */
    private ImageWriter imageWriter;

    /**
     * The RayTracerBase for tracing rays in the scene.
     */
    private RayTracerBase rayTracer;

    /**
     * Constructs a new Camera object.
     */
    private Camera() {
    }

    /**
     * Creates a new instance of the Builder class for constructing a Camera object.
     *
     * @return a new Builder object for constructing a Camera
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Gets the position of the camera.
     *
     * @return the position of the camera
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Gets the direction in which the camera is facing.
     *
     * @return the direction of the camera
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets the up vector of the camera.
     *
     * @return the up vector of the camera
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets the right vector of the camera.
     *
     * @return the right vector of the camera
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Gets the width of the screen.
     *
     * @return the width of the screen
     */
    public double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the height of the screen.
     *
     * @return the height of the screen
     */
    public double getScreenHeight() {
        return screenHeight;
    }

    /**
     * Gets the distance from the camera to the screen.
     *
     * @return the distance to the screen
     */
    public double getDistanceToScreen() {
        return distanceToScreen;
    }

    /**
     * Constructs a ray from the camera through the specified pixel.
     *
     * @param nX the x-coordinate of the pixel
     * @param nY the y-coordinate of the pixel
     * @param j  the x-coordinate of the pixel in the screen
     * @param i  the y-coordinate of the pixel in the screen
     * @return the ray from the camera through the specified pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Calculate the center point of the view plane
        Point pCenter = position.add(vTo.scale(distanceToScreen));

        // Calculate the width and height of each pixel
        double rY = screenHeight / nY;
        double rX = screenWidth / nX;

        // Calculate the offsets of the pixel from the center
        double yI = -(i - (nY - 1) / 2.0) * rY;
        double xJ = (j - (nX - 1) / 2.0) * rX;

        // Calculate the position of the pixel on the view plane
        Point pIJ = pCenter;
        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }

        // Calculate the direction from the camera position to the pixel
        Vector dir = pIJ.subtract(position).normalize();

        // Construct and return the ray
        return new Ray(position, dir);
    }

    /**
     * Renders the image by tracing rays through each pixel.
     */
    public void renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRay(nX, nY, j, i);
            }
        }
        //throw new RuntimeException("UnsupportedOperationException");
    }

    /**
     * Casts a ray through the specified pixel and writes the color to the image.
     *
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param j  the x-coordinate of the pixel
     * @param i  the y-coordinate of the pixel
     */
    private void castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * Writes the rendered image to a file.
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     *
     * @param interval the interval between grid lines
     * @param color    the color of the grid lines
     */
    public void printGrid(int interval, Color color) {
        for (int j = 0; j < imageWriter.getNx(); j++)
            for (int i = 0; i < imageWriter.getNy(); i++)
                if (isZero(j % interval) || isZero(i % interval))
                    imageWriter.writePixel(j, i, color);
    }

    /**
     * The Builder class provides a fluent interface for building a Camera object.
     */
    public static class Builder {

        /**
         * The camera object being built.
         */
        private final Camera camera = new Camera();


        /**
         * Sets the position of the camera.
         *
         * @param position the position of the camera
         * @return the Builder object
         * @throws IllegalArgumentException if the position is null
         */
        public Builder setLocation(Point position) {
            if (position == null) {
                throw new IllegalArgumentException("Position cannot be null");
            }

            camera.position = position;
            return this;
        }

        /**
         * Sets the direction and up vectors of the camera.
         *
         * @param to the direction vector of the camera
         * @param up the up vector of the camera
         * @return the Builder object
         * @throws IllegalArgumentException if the vectors are null or not perpendicular
         */
        public Builder setDirection(Vector to, Vector up) {
            if (to == null || up == null) {
                throw new IllegalArgumentException("Vectors cannot be null");
            }
            if (!isZero(to.dotProduct(up))) {
                throw new IllegalArgumentException("Vectors must be perpendicular");
            }

            camera.vTo = to.normalize();
            camera.vUp = up.normalize();
            return this;
        }

        /**
         * Sets the viewport size of the camera.
         *
         * @param width  the width of the viewport
         * @param height the height of the viewport
         * @return the Builder object
         * @throws IllegalArgumentException if the dimensions are not positive
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Viewport dimensions must be positive");
            }

            camera.screenWidth = width;
            camera.screenHeight = height;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param distance the distance to the view plane
         * @return the Builder object
         * @throws IllegalArgumentException if the distance is not positive
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("View plane distance must be positive");
            }

            camera.distanceToScreen = distance;
            return this;
        }

        /**
         * Sets the ImageWriter for the camera.
         *
         * @param imageWriter the ImageWriter to set
         * @return the Builder object
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the RayTracer for the camera.
         *
         * @param rayTracer the RayTracer to set
         * @return the Builder object
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Builds the camera object with the specified parameters.
         *
         * @return a new Camera object
         * @throws MissingResourceException if any relevant fields have zero values
         * @throws IllegalArgumentException if the direction vectors are parallel, or if any sizes are negative
         */
        public Camera build() {

            String missingResource = "Missing Resource";
            if (camera.position == null)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "location");

            if (camera.vUp == null || camera.vTo == null)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "direction");

            if (camera.screenWidth == 0 || camera.screenHeight == 0)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "viewPlaneSize");

            if (camera.distanceToScreen == 0.0)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "viewPlanDistance");

            if (camera.vTo.crossProduct(camera.vUp).length() == 0)
                throw new IllegalArgumentException("Vto and Vup are parallel");

            if (camera.screenHeight < 0.0 || camera.screenWidth < 0.0)
                throw new IllegalArgumentException("Negative size");// checking the parameters himself

            if (camera.distanceToScreen < 0.0)
                throw new IllegalArgumentException("Negative distance");

            if (camera.imageWriter == null)
                throw new MissingResourceException(missingResource, Camera.class.getName(), "imageWriter");

            if (camera.rayTracer == null)
                throw new MissingResourceException(missingResource, Camera.class.getName(), "rayTracer");

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();


            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
               return null;
            }
        }
    }
}
