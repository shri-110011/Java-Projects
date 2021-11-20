package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		final String Source_File = "src/imageProcessing/images/many-flowers.jpg";
		final String Destination_File = "src/imageProcessing/images/modified_image2.jpg";
		
		BufferedImage originalImage = ImageIO.read(new File(Source_File));
		BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		long startMillis = System.currentTimeMillis();
//		recolorSingleThreaded(originalImage, resultImage);
		recolorMultiThreaded(originalImage, resultImage, 4);
		long stopMillis = System.currentTimeMillis();
		
		long duration = stopMillis - startMillis;
		File outputFile = new File(Destination_File);
		ImageIO.write(resultImage, "jpg", outputFile);
		System.out.println(duration);
	}
	public static void recolorMultiThreaded(BufferedImage originalImage, BufferedImage resultImage, int noOfThreads) {
		List<Thread> threads = new ArrayList<>();
		int width = originalImage.getWidth();
		int height = originalImage.getHeight() / noOfThreads;
		
		for(int i=0; i<noOfThreads; i++) {
			final int threadMultiplier = i;
			
			Thread thread = new Thread(()-> {
				int leftCorner = 0;
				int topCorner = height * threadMultiplier;
//				System.out.println(threadMultiplier);
				
				recolorImage(originalImage, resultImage, leftCorner, topCorner, width, height);
			});
			
			threads.add(thread);
		}
		for(Thread thread: threads) {
			thread.start();
		}
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
		recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
	}
	public static void recolorImage(BufferedImage originalImage, BufferedImage resultImage, 
			int leftCorner, int topCorner, int width, int height) {
//		System.out.println("topCorner: "+topCorner);
		for(int x=leftCorner; x<width; x++) {
			for(int y=topCorner; y<topCorner+height && y<originalImage.getHeight(); y++) {
				recolorPixel(originalImage, resultImage, x, y);
			}
		}
	}
	public static void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y) {
		int rgb = originalImage.getRGB(x, y);
		if(x==0 && y==0)
			System.out.println(rgb);
		
		int red = getRed(rgb);
		int green = getGreen(rgb);
		int blue = getBlue(rgb);
		
		int newRed;
		int newGreen;
		int newBlue;
		
		if(isShadeOfGray(red, green, blue)) {//222 228 231 184 183 191
			newRed = Math.min(255, red);
			newGreen = Math.max(0, green);
			newBlue = Math.max(0, blue-180);
		}
		else {
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
	public static Boolean isShadeOfGray(int red, int green, int blue) {
		return Math.abs(red-green)<30 && Math.abs(green-blue)<30 && Math.abs(blue- red)<30;
	}
	public static int createRGBFromColors(int red, int green, int blue) {
		int rgb = 0;
		rgb |= blue;
		rgb |= green << 8;
		rgb |= red << 16;
		
		//Making the opacity to one or 100%
		rgb |= 0xff000000;
		
		return rgb;
	}
	public static int getRed(int rgb) {
		return (rgb & 0x00ff0000)>>16;
	}
	public static int getGreen(int rgb) {
		return (rgb & 0x0000ff00)>>8;
	}
	public static int getBlue(int rgb) {
		return (rgb & 0x000000ff);
	}
	

}
