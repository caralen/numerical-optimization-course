package hr.fer.zemris.apr.dz2.optimizers;

import java.util.Arrays;

import hr.fer.zemris.apr.dz2.FunctionCounter;
import hr.fer.zemris.apr.dz2.Point;

public class HookeJeeves implements Optimizer {
	
	private FunctionCounter function;
	private Point x0;
	private double Dx = 0.5;
	private double epsilon = 10E-6;
	private Point result;
	
	public HookeJeeves(FunctionCounter function, Point x0) {
		this.function = function;
		this.x0 = x0;
	}
	
	public void setX0(Point x0) {
		this.x0 = x0;
	}

	public void setDx(double dx) {
		Dx = dx;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	
	@Override
	public Point getResult() {
		return result.copy();
	}


	public Optimizer optimize() {
		Point xP = x0.copy();
		Point xB = x0.copy();
		
		do {
			Point xN = explore(xP.copy());
//			System.out.println("xN: " + xN);
			
			if(function.evaluate(xN.toArray()) < function.evaluate(xB.toArray())) {
				xP = xN.scaled(2).subtract(xB);
				xB = xN.copy();
			} else {
				Dx /= 2;
				xP = xB.copy();
			}
//			System.out.println("xP: " + xP);
//			System.out.println("xB: " + xB);
//			System.out.println("---------------------------");
		} while(Dx > 0.5 * epsilon);
		result = xB;
		return this;
	}

	private Point explore(Point xP) {
		Point x = xP.copy();
		
		for(int i = 0; i < xP.dimension(); i++) {
			double P = function.evaluate(x.toArray());
			x.setValue(i, x.getValue(i) + Dx);
			double N = function.evaluate(x.toArray());
			
			if(N > P) {
				x.setValue(i, x.getValue(i) - 2 * Dx);
				N = function.evaluate(x.toArray());
				
				if(N > P) {
					x.setValue(i, x.getValue(i) + Dx);
				}
			}
		}
		return x;
	}

	@Override
	public String toString() {
		return "HookeJeeves: result=" + Arrays.toString(result.toArray()) + ", iterations=" + function.numberOfIterations();
	}
	
}
