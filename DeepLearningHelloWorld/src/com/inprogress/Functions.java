package com.inprogress;

import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;

/**
 * Sigmoid function to map input data between 0 and 1
 * 
 * * S(t) = 1 / (1 + e^(-1))
 * 
 * @author rasha
 *
 */
public class Functions {
	
	public static void main(String[] args) {
		double[][] data = new double[][] {
			{12, 11, 21, 1},
			{0, 0, 10, 30},
			{1, 0, 1, 20}
		};
		
		Functions sig = new Functions();
		DoubleMatrix sigmoid1 = sig.sigmoidJblas(new DoubleMatrix(data));
		DoubleMatrix sigmoid2 = sig.sigmoidJblas(new DoubleMatrix(data));
		System.out.println(sigmoid1);
		System.out.println(sigmoid2);
	}
	
	/**
	 * Sigmoid function for scalar input
	 * 
	 * @param t
	 * @return
	 */
	public static double sigmoid(double t) {
		return 1 / (1 + Math.exp(-t));
	}
	
	/**
	 * Sigmoid function for matrix input
	 * 
	 * @param t
	 * @return
	 */
	public static DoubleMatrix sigmoid(DoubleMatrix t) {
		DoubleMatrix output = new DoubleMatrix(t.rows, t.columns);
		double value;
		double sigval;
		
		for (int row = 0; row < t.rows; row++) {
			for (int col = 0; col < t.columns; col++) {
				value  = t.get(row, col);
				sigval = sigmoid(value);
				output.put(row, col, sigval);
			}
		}
		return output;
	}
	
	public static DoubleMatrix sigmoidJblas(DoubleMatrix t) {
		DoubleMatrix res = (MatrixFunctions.expi(t.muli(-1))).addi(1);
		return res.rdivi(1);
	}
	
	/**
	 * 
	 * S(t) = 1 / (1 + e^(-1))
	 * 
	 * S'(t) = S(t)*(1 - S(t))
	 * 
	 * @param t
	 * @return
	 */
	public static DoubleMatrix sigmoidJblasDerivateA(DoubleMatrix t) {
		return sigmoidJblas(t).mmul(sigmoidJblas(t).addi(-1));
	}
	
}
