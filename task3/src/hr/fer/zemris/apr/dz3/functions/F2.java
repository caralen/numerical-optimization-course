package hr.fer.zemris.apr.dz3.functions;

import static java.lang.Math.pow;

public class F2 implements FunctionWithGradient {
	
	private int calls = 0;

	@Override
	public double evaluate(double... x) throws IllegalArgumentException {
		calls++;
		return pow(x[0] - 4, 2) + 4 * pow(x[1] - 2, 2);
	}

	@Override
	public double evaluateFirstGrad1(double... x) throws IllegalArgumentException {
		calls++;
		return 2 * (x[0] - 4);
	}

	@Override
	public double evaluateFirstGrad2(double... x) throws IllegalArgumentException {
		calls++;
		return 8 * (x[1] - 2);
	}

	@Override
	public double evaluateSecondGrad11(double... x) throws IllegalArgumentException {
		calls++;
		return 2;
	}

	@Override
	public double evaluateSecondGrad12(double... x) throws IllegalArgumentException {
		calls++;
		return 0;
	}

	@Override
	public double evaluateSecondGrad21(double... x) throws IllegalArgumentException {
		calls++;
		return 0;
	}

	@Override
	public double evaluateSecondGrad22(double... x) throws IllegalArgumentException {
		calls++;
		return 8;
	}

	@Override
	public int numberOfCalls() {
		return calls;
	}

}
