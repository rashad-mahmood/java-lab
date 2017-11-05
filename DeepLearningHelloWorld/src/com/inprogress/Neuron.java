package com.inprogress;

import java.util.List;

public class Neuron {
	
	private double input;
	private double weight;
	private double output = input * weight;

	public Neuron() {
		super();
	}

	public double getInput() {
		return input;
	}

	public void setInput(double input) {
		this.input = input;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Neuron [input=" + input + ", weight=" + weight + ", output=" + output + "]";
	}
	
	



}
