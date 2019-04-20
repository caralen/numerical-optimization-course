package hr.fer.zemris.apr.dz5;

import java.util.Map;

import hr.fer.zemris.apr.dz1.Matrix;

@FunctionalInterface
public interface NumericalMethod {

	Map<Double, Point> iteration(Matrix A, Matrix xk, Matrix B,  double T, double t);
}
