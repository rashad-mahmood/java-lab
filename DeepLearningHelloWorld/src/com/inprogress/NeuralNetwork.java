package com.inprogress;

import java.util.ArrayList;
import java.util.List;

import org.jblas.DoubleMatrix;
import org.jblas.Solve;
import org.jblas.benchmark.Main;

public class NeuralNetwork {
	
	int inputLayerSize;
	int hiddenLayerSize;
	int outputLayerSize;
	DoubleMatrix w1;
	DoubleMatrix w2;
	
	int hiddenLayerCount;
	
	DoubleMatrix hidden;
	DoubleMatrix activatedHidden;
	DoubleMatrix output;
	
	DoubleMatrix dJdW1;
	DoubleMatrix dJdW2;
	
	ArrayList<DoubleMatrix> weights = new ArrayList<DoubleMatrix>();
	
	public NeuralNetwork(int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {
		super();
		this.inputLayerSize = inputLayerSize;
		this.hiddenLayerSize = hiddenLayerSize;
		this.outputLayerSize = outputLayerSize;
		
		w1 = DoubleMatrix.randn(inputLayerSize, hiddenLayerSize);
		w2 = DoubleMatrix.randn(hiddenLayerSize, outputLayerSize);
	}
	
	public NeuralNetwork(int inputLayerSize, int hiddenLayerSize, int outputLayerSize, int hiddenLayerCount) {
		super();
		this.inputLayerSize = inputLayerSize;
		this.hiddenLayerSize = hiddenLayerSize;
		this.outputLayerSize = outputLayerSize;
		this.hiddenLayerCount = hiddenLayerCount;
		
		while (hiddenLayerCount != 0) {
			weights.add(DoubleMatrix.randn(inputLayerSize, hiddenLayerSize));
			hiddenLayerCount--;
		}
		weights.add(DoubleMatrix.randn(hiddenLayerSize, outputLayerSize));
	}
	
	public DoubleMatrix forwardPropagation(DoubleMatrix input) {
		//System.out.println("Input: (" + input.rows + "x" + input.columns + ")" );
		//System.out.println("w1: (" + w1.rows + "x" + w1.columns + ")\n" );
		hidden = input.mmul(w1); // number of columns left = number of rows right
		
		//System.out.println("hidden: (" + hidden.rows + "x" + hidden.columns + ")\n" );
		activatedHidden = Functions.sigmoid(hidden);
		
		//System.out.println("activatedHidden: (" + activatedHidden.rows + "x" + activatedHidden.columns + ")" );
		//System.out.println("w2: (" + w2.rows + "x" + w2.columns + ")\n" );
		output = activatedHidden.mmul(w2);
		
		//System.out.println("output: (" + output.rows + "x" + output.columns + ")\n" );
		return Functions.sigmoid(output);
	}
	
	public DoubleMatrix forwardPropagationReal(DoubleMatrix input) {
		DoubleMatrix neuron = input; 
		DoubleMatrix activatedHidden;
		for (DoubleMatrix weight : weights) {
			activatedHidden = next(neuron, weight);
			neuron = activatedHidden;
		}
		return neuron;
	}
	
	private DoubleMatrix next(DoubleMatrix neuron, DoubleMatrix weight) {
		DoubleMatrix hidden = neuron.mmul(weight);
		return Functions.sigmoidJblas(hidden);
	}
	
	/**
	 * Cost function - difference of squares
	 */
	public void costFunctionPrime(DoubleMatrix input, DoubleMatrix expectedOutput) {
		DoubleMatrix actualOutput = forwardPropagation(input);
		DoubleMatrix error = expectedOutput.subi(actualOutput); // 3 x 1
		DoubleMatrix outputPrime = Functions.sigmoidPrime(getOutput());
		
		DoubleMatrix delta3 = (error.muli(outputPrime)).muli(-1);
		dJdW2 = (getActivatedHidden().transpose()).mmul(delta3);
		
		DoubleMatrix delta2 = (delta3.mmul(getW2().transpose())).muli(Functions.sigmoidPrime(getHidden()));
		dJdW1 = (input.transpose()).mmul(delta2);
	}
	
	/*public static DoubleMatrix costFunction(DoubleMatrix input, DoubleMatrix expectedOutput) {
		DoubleMatrix actualOutput = nt.forwardPropagation(input);
		DoubleMatrix powi = MatrixFunctions.powi(expectedOutput.subi(actualOutput), 2);
	}*/
	
	public DoubleMatrix costFunctionJblas(DoubleMatrix input, DoubleMatrix expectedOutput) {
		return Solve.solveLeastSquares(expectedOutput, forwardPropagation(input));
	}
	
	/**
	 * Sigmoid function for matrix input
	 * 
	 * @param t
	 * @return
	 */
	public double costFunction(DoubleMatrix input, DoubleMatrix expected) {
		double act;
		double exp;
		double value;
		double output = 0;
		DoubleMatrix actual = forwardPropagation(input);
		
		if (actual.rows == expected.rows && actual.columns == expected.columns) {
			for (int row = 0; row < expected.rows; row++) {
				for (int col = 0; col < expected.columns; col++) {
					act = actual.get(row, col);
					exp = expected.get(row, col);
					value  = Math.pow((exp - act), 2);
					output += value;
				}
			}
		}
		return 0.5*output;
	}

	public int getInputLayerSize() {
		return inputLayerSize;
	}

	public void setInputLayerSize(int inputLayerSize) {
		this.inputLayerSize = inputLayerSize;
	}

	public int getHiddenLayerSize() {
		return hiddenLayerSize;
	}

	public void setHiddenLayerSize(int hiddenLayerSize) {
		this.hiddenLayerSize = hiddenLayerSize;
	}

	public int getOutputLayerSize() {
		return outputLayerSize;
	}

	public void setOutputLayerSize(int outputLayerSize) {
		this.outputLayerSize = outputLayerSize;
	}

	public int getHiddenLayerCount() {
		return hiddenLayerCount;
	}

	public void setHiddenLayerCount(int hiddenLayerCount) {
		this.hiddenLayerCount = hiddenLayerCount;
	}

	public DoubleMatrix getW1() {
		return w1;
	}

	public void setW1(DoubleMatrix w1) {
		this.w1 = w1;
	}

	public DoubleMatrix getW2() {
		return w2;
	}

	public void setW2(DoubleMatrix w2) {
		this.w2 = w2;
	}

	public DoubleMatrix getHidden() {
		return hidden;
	}

	public void setHidden(DoubleMatrix hidden) {
		this.hidden = hidden;
	}

	public DoubleMatrix getActivatedHidden() {
		return activatedHidden;
	}

	public void setActivatedHidden(DoubleMatrix activatedHidden) {
		this.activatedHidden = activatedHidden;
	}

	public DoubleMatrix getOutput() {
		return output;
	}

	public void setOutput(DoubleMatrix output) {
		this.output = output;
	}

	public ArrayList<DoubleMatrix> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<DoubleMatrix> weights) {
		this.weights = weights;
	}

	public DoubleMatrix getdJdW1() {
		return dJdW1;
	}

	public DoubleMatrix getdJdW2() {
		return dJdW2;
	}
	
	
	
	
	
	
	
	

}
