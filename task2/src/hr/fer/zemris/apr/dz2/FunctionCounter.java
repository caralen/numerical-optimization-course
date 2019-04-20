package hr.fer.zemris.apr.dz2;

public class FunctionCounter implements Function {

	private int counter;
	private Function f;
	
	public FunctionCounter(Function f) {
		counter = 0;
		this.f = f;
	}
	
	public double evaluate(double... variables) throws IllegalArgumentException {
		counter++;
		return f.evaluate(variables);
	}
	
	public int numberOfIterations(){
		return counter;
	}
}
