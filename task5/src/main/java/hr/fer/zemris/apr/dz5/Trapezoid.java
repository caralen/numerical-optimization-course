package hr.fer.zemris.apr.dz5;

import java.util.LinkedHashMap;
import java.util.Map;

import hr.fer.zemris.apr.dz1.Matrix;

public class Trapezoid implements NumericalMethod {
	
	private int printStep;

	public Trapezoid(int printStep) {
		this.printStep = printStep;
	}

	@Override
	public Map<Double, Point> iteration(Matrix A, Matrix xk, Matrix B,  double T, double t) {
		
		xk = xk.copyOf();
		Matrix U = Matrix.eye(xk.getX());
		Matrix AScaled = A.scaled(T / 2.0);
		Matrix R = U.subtract(AScaled).inverse().multiply(U.add(AScaled));
		Matrix S = U.add(AScaled).inverse().scaled(T / 2.0).multiply(B);
		
		
		Map<Double, Point> results = new LinkedHashMap<>();
		results.put(T, new Point(xk.getColumn(0).toArray()));
		
		int steps = (int) (t / T);
		
		for(int i = 1; i <= steps; i++) {
			if(i % printStep == 0) 
				System.out.println(xk);
			
			xk = R.multiply(xk).add(S);
			results.put(i * T, new Point(xk.getColumn(0).toArray()));
		}
		return results;
	}

}
