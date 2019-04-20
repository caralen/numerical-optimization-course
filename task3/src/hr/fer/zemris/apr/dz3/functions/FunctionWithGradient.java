package hr.fer.zemris.apr.dz3.functions;

import hr.fer.zemris.apr.dz2.Function;

public interface FunctionWithGradient extends Function {

	public double evaluateFirstGrad1(double... x) throws IllegalArgumentException;
	
	public double evaluateFirstGrad2(double... x) throws IllegalArgumentException;
	
	public double evaluateSecondGrad11(double... x) throws IllegalArgumentException;
	
	public double evaluateSecondGrad12(double... x) throws IllegalArgumentException;
	
	public double evaluateSecondGrad21(double... x) throws IllegalArgumentException;
	
	public double evaluateSecondGrad22(double... x) throws IllegalArgumentException;
	
	public int numberOfCalls();
}
