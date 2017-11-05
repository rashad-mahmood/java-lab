package com.inprogress;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jblas.DoubleMatrix;

public class Main {
	
	public static void main(String[] args) {
		ImageHandle ih = new ImageHandle();
		BufferedImage image;
		
		try {
			image = ImageIO.read(new File("input/1.jpg"));
			BufferedImage grayImage = ih.toGrayScale(image);
			BufferedImage resizedImage = ih.resize(grayImage, 28, 28);
			double[] pixelArray = ih.getPixelArray(resizedImage);
			
			NeuralNetwork nt = new NeuralNetwork(28*28, 10, 10);
			DoubleMatrix forwardPropagation = nt.forwardPropagation(new DoubleMatrix(pixelArray));
			System.out.println(forwardPropagation);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
