package hr.fer.zemris.apr.dz3.optimizers;

import hr.fer.zemris.apr.dz2.Intervals;
import hr.fer.zemris.apr.dz2.Point;
import hr.fer.zemris.apr.dz3.functions.FunctionWithGradient;

public class GradientDescent implements Optimizer2 {
	
	private boolean goldenRatio;
	private FunctionWithGradient f;
	private Point x0;
	private double alpha = 1;
	private double eps = 10E-6;
	

	public GradientDescent(Point x0, FunctionWithGradient f, boolean goldenRatio, double eps) {
		this(x0, f, goldenRatio);
		this.eps = eps;
	}
	
	public GradientDescent(Point x0, FunctionWithGradient f, boolean goldenRatio) {
		this.x0 = x0;
		this.f = f;
		this.goldenRatio = goldenRatio;
	}


//	TODO
//	Prerano stane za fju 4
	
	@Override
	public Point run() {
		int counter = 0;
		
		while(true) {
			counter++;
			
			double v1 = -f.evaluateFirstGrad1(x0.toArray());
			double v2 = -f.evaluateFirstGrad2(x0.toArray());
			
			Point v = new Point(v1, v2).normalized();
			Point x1 = x0.add(v.scaled(alpha));
			
			if(goldenRatio)
				alpha = Math.abs(Intervals.goldenRatio(x0, 0, 0, false, v, 0, f));
			
			if(Math.sqrt(v1*v1 + v2*v2) <= eps || counter == 10_000)
				return x1;
			
			x0 = x1;
		}
	}

}
