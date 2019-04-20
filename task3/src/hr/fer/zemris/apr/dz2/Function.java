package hr.fer.zemris.apr.dz2;

@FunctionalInterface
public interface Function {

	public double evaluate(double... x) throws IllegalArgumentException;
}
