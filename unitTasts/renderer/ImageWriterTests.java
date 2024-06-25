package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * Testing ImageWriter class
 */
class ImageWriterTests {
    /**
     * Test method for creating an image with a background color and a grid
     */
    @Test
    public void testWritePixel() {
        // Define image resolution
        int width = 800; // Horizontal resolution
        int height = 500; // Vertical resolution
        String imageName = "testImage";//name

        // Create an ImageWriter object with the desired image name and resolution
        ImageWriter imageWriter = new ImageWriter(imageName, width, height);

        // Define background color (white) and grid color (blue)
        Color backgroundColor = Color.BLUE; // White color
        Color gridColor = Color.WHITE; // BLUE color

        // Fill the image with the background color
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageWriter.writePixel(j, i, backgroundColor);
            }
        }

        // Create a grid with the grid color
        int numHorizontalCells = 16;
        int numVerticalCells = 10;
        int cellWidth = width / numHorizontalCells;
        int cellHeight = height / numVerticalCells;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % cellHeight == 0 || j % cellWidth == 0) {
                    imageWriter.writePixel(j, i, gridColor);
                }
            }
        }

        // Write the image to file (creates "testImage.png" in the /images directory)
        imageWriter.writeToImage();

    }
}