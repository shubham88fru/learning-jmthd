package m.pogrebinsky.performance.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiThreaded {
    public static final String SOURCE_FILE = "/Users/shubham/Desktop/Projects/learning-jmthd/multithreading/src/main/resources/white_flowers.jpeg";
    public static final String DESTINATION_FILE_ST = "/Users/shubham/Desktop/Projects/learning-jmthd/multithreading/src/main/resources/purple_flowers.jpg";

    public static final String DESTINATION_FILE_MT = "/Users/shubham/Desktop/Projects/learning-jmthd/multithreading/src/main/resources/purple_flowers_mt.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage stResultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        long stStartTime = System.currentTimeMillis();
        performSingleThreaded(image, stResultImage);
        File stOutputFile = new File(DESTINATION_FILE_ST);
        ImageIO.write(stResultImage, "jpg", stOutputFile);
        long stEndTime = System.currentTimeMillis();
        System.out.println("Single Threaded time: " + (stEndTime - stStartTime));


        BufferedImage mtResultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        long mtStartTime = System.currentTimeMillis();
        performMultiThreaded(image, mtResultImage, 3);
        File mtOutputFile = new File(DESTINATION_FILE_MT);
        ImageIO.write(mtResultImage, "jpg", mtOutputFile);
        long mtEndTime = System.currentTimeMillis();
        System.out.println("MultiThreaded time: " + (mtEndTime - mtStartTime));
    }

    public static void performMultiThreaded(BufferedImage ogImage, BufferedImage resultImage, int numThreads) {
        List<Thread> threads = new ArrayList<>();
        int width = ogImage.getWidth();
        int height = ogImage.getHeight() / numThreads;
        for (int i = 0; i < numThreads; i++) {
            final int threadMultiplier = i;
            Thread thread = new Thread(() -> {
                int leftCorner = 0; //x
                int topCorner = height * threadMultiplier; //y
                recolorImage(ogImage, resultImage, leftCorner, topCorner, width, height);
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void performSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
        recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }

    public static void recolorImage(BufferedImage originalImage, BufferedImage resultImage,
                                    int leftCorner, int topCorner, int width, int height) {
        for (int x=leftCorner; x<leftCorner + width && x < originalImage.getWidth(); x++) {
            for (int y=topCorner; y<topCorner + height && y < originalImage.getHeight(); y++) {
                recolorPixel(originalImage, resultImage, x, y);
            }
        }
    }

    public static void recolorPixel(BufferedImage ogImage, BufferedImage resultImage, int x, int y) {
        int rgb = ogImage.getRGB(x, y);

        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed, newGreen, newBlue;
        if (isShadeOfGray(red, green, blue)) {
            newRed = Math.min(255, red + 10); //increase red content to make it redish purple.
            newGreen = Math.max(0, green - 80); //purple will have very less green.
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(resultImage, x, y, newRGB);
    }

    public static void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }

    //determines if the given color is a shade of gray.
    public static boolean isShadeOfGray(int red, int green, int blue) {
        //if all the shades are almost of the same value, i.e. mixed in equalish quantities,
        //then the color is very likely some shade of gray.
        return Math.abs(red-green) < 35 && Math.abs(red-blue) < 35 && Math.abs(green-blue) < 35;
    }

    public static int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;
        rgb |= blue; //merge blue.
        rgb |= (green << 8); //merge green.
        rgb |= (red << 16); //merge red.
        rgb |= 0xFF000000; //set alpha to highest value.

        return rgb;
    }

    //extract the red from a given rgb value.
    public static int getRed(int rgb) {
        return (rgb & 0x00FF0000 ) >> 16; //alpha-r-g-b
    }

    //extract the green from a given rgb value.
    public static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8; //alpha-r-g-b
    }

    //extract the blue from a given rgb value.
    public static int getBlue(int rgb) {
        return rgb & 0x000000FF; //alpha-r-g-b
    }
}
