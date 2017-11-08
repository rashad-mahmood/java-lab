package com.inprogress;

import java.util.ArrayList;

import org.jblas.DoubleMatrix;
import org.jblas.benchmark.Main;

public class NeuralNetwork {
	
	
	int inputLayerSize;
	int hiddenLayerSize;
	int outputLayerSize;
	
	int hiddenLayerCount;
	
	DoubleMatrix w1;
	DoubleMatrix w2;
	
	DoubleMatrix hidden;
	DoubleMatrix activatedHidden;
	DoubleMatrix output;
	
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
		activatedHidden = Functions.sigmoidJblas(hidden);
		
		//System.out.println("activatedHidden: (" + activatedHidden.rows + "x" + activatedHidden.columns + ")" );
		//System.out.println("w2: (" + w2.rows + "x" + w2.columns + ")\n" );
		output = activatedHidden.mmul(w2);
		
		//System.out.println("output: (" + output.rows + "x" + output.columns + ")\n" );
		return Functions.sigmoidJblas(output);
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
	
	
	
	
	
	
	

}
