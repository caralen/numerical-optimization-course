package hr.fer.zemris.apr.dz3.optimizers;

import hr.fer.zemris.apr.dz1.Matrix;
import hr.fer.zemris.apr.dz2.Intervals;
import hr.fer.zemris.apr.dz2.Point;
import hr.fer.zemris.apr.dz3.functions.FunctionWithGradient;

public class NewtonRaphson implements Optimizer2 {
	
	private boolean goldenRatio;
	private FunctionWithGradient f;
	private Point x0;
	private double alpha = 1;
	private double eps = 10E-6;
	
	private static final Matrix b1 = new Matrix(new double[][]{{1}, {0}});
	private static final Matrix b2 = new Matrix(new double[][]{{0}, {1}});
	
	public NewtonRaphson(Point x0, FunctionWithGradient f, boolean goldenRatio, double eps) {
		this(x0, f, goldenRatio);
		this.eps = eps;
	}

	public NewtonRaphson(Point x0, FunctionWithGradient f, boolean goldenRatio) {
		this.x0 = x0;
		this.f = f;
		this.goldenRatio = goldenRatio;
	}


	@Override
	public Point run() {
		int counter = 0;
		
		while(true) {
			counter++;
			
			Matrix hessian = calculateHessian();
			Matrix inverse = calculateInverse(hessian);
			
			double v1 = f.evaluateFirstGrad1(x0.toArray());
			double v2 = f.evaluateFirstGrad2(x0.toArray());
			
			Matrix m = inverse.multiply(new Matrix(new double[][] {{v1}, {v2}}));
			Point v = new Point(m.getElement(0, 0), m.getElement(1, 0)).scaled(-1).normalized();
			
			if(goldenRatio)
				alpha = Math.abs(Intervals.goldenRatio(x0, 0, 0, false, v, 0, f));
			
			Point x1 = x0.add(v.scaled(alpha));
			
			if(x0.distance(x1) <= eps || counter == 10_000)
				return x1;
			
			x0 = x1;
		}
	}

	private Matrix calculateHessian() {
		double[][] elements = new double[2][2];
		elements[0][0] = f.evaluateSecondGrad11(x0.toArray());
		elements[0][1] = f.evaluateSecondGrad12(x0.toArray());
		elements[1][0] = f.evaluateSecondGrad21(x0.toArray());
		elements[1][1] = f.evaluateSecondGrad22(x0.toArray());
		return new Matrix(elements);
	}

	private Matrix calculateInverse(Matrix hessian) {
		Matrix lu = hessian.lup();
		Matrix y1 = lu.forwardSupstitution(b1);
		Matrix y2 = lu.forwardSupstitution(b2);
		Matrix a1 = lu.backwardSupstitution(y1);
		Matrix a2 = lu.backwardSupstitution(y2);
		
		double[][] inverseElements = new double[2][2];
		inverseElements[0][0] = a1.getElement(0, 0);
		inverseElements[1][0] = a1.getElement(1, 0);
		inverseElements[0][1] = a2.getElement(0, 0);
		inverseElements[1][1] = a2.getElement(1, 0);
		return new Matrix(inverseElements);
	}

}
