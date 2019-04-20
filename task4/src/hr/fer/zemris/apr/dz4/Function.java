package hr.fer.zemris.apr.dz4;

@FunctionalInterface
public interface Function {

	public double evaluate(double... variables) throws IllegalArgumentException;
}
