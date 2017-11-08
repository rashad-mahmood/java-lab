package com.inprogress;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

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
				{75}, 
				{82}, 
				{93}
		});
		System.out.println("Output: Test scores(" + expectedOutput.rows + "x" + expectedOutput.columns + ")\n" + expectedOutput);
		
		//test forward propagation
		DoubleMatrix realOutput = nt.forwardPropagation(input); //in (3,2); w1: (2, 3)
		System.out.println("Forward Propagation Test:\n" + realOutput );

		//test cost function
		
		DoubleMatrix costFunction = costFunction(input, expectedOutput);
		System.out.println("Cost Function: " + costFunction);
		
	}
	
	/**
	 * Cost function - difference of squares
	 */
	public void costFunctionPrime(DoubleMatrix input, Double expectedOutput) {
		DoubleMatrix activatedOut = nt.forwardPropagation(input);
		
		DoubleMatrix delta3 = (activatedOut.mini(expectedOutput)).muli(-1);
		DoubleMatrix dJdW2 = delta3.mmul(Functions.sigmoidJblasDerivateA(nt.getOutput()));
	}
	
	public static DoubleMatrix costFunction(DoubleMatrix input, DoubleMatrix expectedOutput) {
		DoubleMatrix actualOutput = nt.forwardPropagation(input);
		return expectedOutput.subi(actualOutput);
	}
	

}
