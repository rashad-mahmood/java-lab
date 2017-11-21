package com.inprogress;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;
import org.jblas.Solve;

public class Main {
	
	static NeuralNetwork nt = new NeuralNetwork(2, 3, 1);
	/*public static void main(String[] args) {
		ImageHandle ih = new ImageHandle();
		BufferedImage image;
		
		try {
			image = ImageIO.read(new File("input/1.jpg"));
			BufferedImage grayImage = ih.toGrayScale(image);
			BufferedImage resizedImage = ih.resize(grayImage, 28, 28);
			double[] pixelArray = ih.getPixelArray(resizedImage);
			
			DoubleMatrix forwardPropagation = nt.forwardPropagation(new DoubleMatrix(pixelArray));
			System.out.println(forwardPropagation);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args) {
		
		//initialize training data : 
		
		//(hours of sleep, hours of study)
		DoubleMatrix input = new DoubleMatrix(new double[][] {
			{3,5},
			{5,1},
			{10,2}
		});
		System.out.println("Input: Hours of Sleep, Hours of Study (" + input.rows + "x" + input.columns + ")\n" + input);
		// (test scores)
		DoubleMatrix expectedOutput = new DoubleMatrix(new double[][] {
				{.75}, 
				{.82}, 
				{.93}
		});
		//System.out.println(Functions.sigmoidJblas(expectedOutput));
		System.out.println("Expected Output: Test scores(" + expectedOutput.rows + "x" + expectedOutput.columns + ")\n" + expectedOutput);
		
		//test forward propagation
		DoubleMatrix realOutput = nt.forwardPropagation(input); //in (3,2); w1: (2, 3)
		System.out.println("Forward Propagation Test:\n" + realOutput);

		//test cost function
		
		nt.costFunctionPrime(input, expectedOutput);
		System.out.println("dJdW1:\n" + nt.getdJdW1());
		System.out.println("dJdW2:\n" + nt.getdJdW2());
		
		DoubleMatrix cost1 = nt.costFunctionJblas(input, expectedOutput);
		System.out.println(cost1);
		//double cost11 = nt.costFunction(input, expectedOutput);
		//System.out.println(cost11);
		
		nt.setW1(nt.getW1().addi(nt.getdJdW1()));
		nt.setW2(nt.getW2().addi(nt.getdJdW2()));
		
		DoubleMatrix cost2 = nt.costFunctionJblas(input, expectedOutput);
		System.out.println(cost2);
	}


}
