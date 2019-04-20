package hr.fer.zemris.apr.dz2.optimizers;

import java.util.Arrays;

import hr.fer.zemris.apr.dz2.FunctionCounter;
import hr.fer.zemris.apr.dz2.Point;

public class SimplexNelderMeade implements Optimizer {
	
	private FunctionCounter function;
	
	private Point x0;
	
	private double alfa = 1;
	
	private double beta = 0.5;
	
	private double gamma = 2;
	
	private double sigma = 0.5;
	
	private double e = 10E-6;
	
	private double step = 1;
	
	private Point result;
	
	
	public SimplexNelderMeade(FunctionCounter function, Point x0) {
		this.function = function;
		this.x0 = x0;
	}
	
	public void setX0(Point x0) {
		this.x0 = x0;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public void setE(double e) {
		this.e = e;
	}

	public void setStep(double step) {
		this.step = step;
	}

	@Override
	public Point getResult() {
		return result.copy();
	}

	public Optimizer optimize() {
		Point[] arr = new Point[x0.dimension() + 1];
		for(int i = 0; i < arr.length; i++)
			arr[i] = x0.copy();
		
		for(int i = 0; i < arr.length - 1; i++) 
			arr[i].setValue(i, arr[i].getValue(i) + step);
		
		double condition = 0;
		
		do {
			int h = 0;
			int l = 0;
			
			for(int i = 1; i < arr.length; i++) {
				if(function.evaluate(arr[i].toArray()) < function.evaluate(arr[l].toArray())) 
					l = i;
				if(function.evaluate(arr[i].toArray()) > function.evaluate(arr[h].toArray())) 
					h = i;
			}
			
			double[] c = new double[x0.dimension()];
			
			for(int i = 0; i < arr.length; i++) {
				if(i == h) continue;
				
				for(int j = 0; j < arr[i].dimension(); j++) {
					c[j] += arr[i].getValue(j) / (arr.length - 1);
				}
			}
			Point xC = new Point(c);
			Point xR = reflexion(xC, arr[h].copy());
			
			
			if(function.evaluate(xR.toArray()) < function.evaluate(arr[l].toArray())) {
				Point xE = expansion(xC.copy(), xR.copy());
				if(function.evaluate(xE.toArray()) < function.evaluate(arr[l].toArray())) {
					arr[h] = xE.copy();
				} else {
					arr[h] = xR.copy();
				}
			} else {
				double max = Double.MIN_VALUE;
				for(int j = 0; j < arr.length; j++) {
					if(j == h) continue;
					double value = function.evaluate(arr[j].toArray());
					max = value > max ? value : max;
				}
				
				double fxR = function.evaluate(xR.toArray());
				
				if(fxR > max) {
					if(fxR < function.evaluate(arr[h].toArray())) {
						arr[h] = xR.copy();
					}
					Point xK = contraction(xC.copy(), arr[h].copy());
					if(function.evaluate(xK.toArray()) < function.evaluate(arr[h].toArray())) {
						arr[h] = xK.copy();
						
					} else {
						shrink(arr, l);
					}
				} else {
					arr[h] = xR.copy();
				}
			}
			condition = 0;
			
			for(int j = 0; j < arr.length; j++) {
				condition += Math.pow(function.evaluate(arr[j].toArray()) - function.evaluate(xC.toArray()), 2);
			}
			condition = Math.sqrt(condition / arr.length);
			
//			System.out.println("xC: " + xC);
//			System.out.println("f(xC): " + function.evaluate(xC.toArray()));
			result = xC;
		} while(condition > e);
		return this;
	}
	
	private Point reflexion(Point xC, Point xH) {
		return xC.scaled(1 + alfa).subtract(xH.scaled(alfa));
	}
	
	private Point expansion(Point xC, Point xR) {
		return xC.scaled(1 - gamma).add(xR.scaled(gamma));
	}
	
	private Point contraction(Point xC, Point xH) {
		return xC.scaled(1 - beta).add(xH.scaled(beta));
	}
	
	private void shrink(Point[] arr, int l) {
		for(int i = 0; i < arr.length; i++) {
			if(l == i) continue;
			arr[i] = arr[i].subtract(arr[l]).scaled(sigma).add(arr[l]);
		}
	}

	@Override
	public String toString() {
		return "SimplexNelderMeade: result=" + Arrays.toString(result.toArray()) + ", iterations=" + function.numberOfIterations();
	}
}
