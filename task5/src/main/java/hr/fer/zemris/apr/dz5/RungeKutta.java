package hr.fer.zemris.apr.dz5;

import java.util.LinkedHashMap;
import java.util.Map;

import hr.fer.zemris.apr.dz1.Matrix;

public class RungeKutta implements NumericalMethod {
	
	private int printStep;

	public RungeKutta(int printStep) {
		this.printStep = printStep;
	}

	
	@Override
	public Map<Double, Point> iteration(Matrix A, Matrix xk, Matrix B,  double T, double t) {
		
		xk = xk.copyOf();
		Map<Double, Point> results = new LinkedHashMap<>();
		results.put(T, new Point(xk.getColumn(0).toArray()));
		
		int steps = (int) (t / T);
		
		for(int i = 1; i <= steps; i++) {
			
			if(i % printStep == 0) 
				System.out.println(xk);
			
			Matrix m1 = A.multiply(xk).add(B);
			Matrix m2 = A.multiply(xk.add(m1.scaled(T / 2.0))).add(B);
			Matrix m3 = A.multiply(xk.add(m2.scaled(T / 2.0))).add(B);
			Matrix m4 = A.multiply(xk.add(m3.scaled(T))).add(B);
			
			xk = xk.add((m1.add(m2.scaled(2)).add(m3.scaled(2)).add(m4)).scaled(T / 6.0));
			results.put(i * T, new Point(xk.getColumn(0).toArray()));
		}
		return results;
	}


}
