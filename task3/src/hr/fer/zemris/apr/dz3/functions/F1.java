package hr.fer.zemris.apr.dz3.functions;

import static java.lang.Math.pow;

public class F1 implements FunctionWithGradient {
	
	private int calls = 0;

	@Override
	public double evaluate(double... x) throws IllegalArgumentException {
		calls++;
		return 100 * pow(x[1] - pow(x[0], 2), 2) + 1 * pow(1 - x[0], 2);
	}

	@Override
	public double evaluateFirstGrad1(double... x) throws IllegalArgumentException {
		calls++;
		return 2 * (200 * pow(x[0], 3) - 200 * x[0] * x[1] + x[0] - 1);
	}

	@Override
	public double evaluateFirstGrad2(double... x) throws IllegalArgumentException {
		calls++;
		return 200 * (x[1] - pow(x[0], 2));
	}

	@Override
	public double evaluateSecondGrad11(double... x) throws IllegalArgumentException {
		calls++;
		return 2 * (600 * pow(x[0], 2) - 200 * x[1] + 1);
	}

	@Override
	public double evaluateSecondGrad12(double... x) throws IllegalArgumentException {
		calls++;
		return -400 * x[0];
	}

	@Override
	public double evaluateSecondGrad21(double... x) throws IllegalArgumentException {
		calls++;
		return -400 * x[0];
	}

	@Override
	public double evaluateSecondGrad22(double... x) throws IllegalArgumentException {
		calls++;
		return 200;
	}

	@Override
	public int numberOfCalls() {
		return calls;
	}
}
