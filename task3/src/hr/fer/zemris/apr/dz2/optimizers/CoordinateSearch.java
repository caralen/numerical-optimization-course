package hr.fer.zemris.apr.dz2.optimizers;

import static hr.fer.zemris.apr.dz2.Intervals.goldenRatio;

import java.util.Arrays;

import hr.fer.zemris.apr.dz2.FunctionCounter;
import hr.fer.zemris.apr.dz2.Point;

public class CoordinateSearch implements Optimizer {
	
	private FunctionCounter function;
	private Point x0;
	private double epsilon = 10E-6;
	private Point result;
	
	public CoordinateSearch(FunctionCounter function, Point x0) {
		this.function = function;
		this.x0 = x0;
	}
	
	public void setX0(Point x0) {
		this.x0 = x0;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	
	@Override
	public Point getResult() {
		return result.copy();
	}
	
	public Optimizer optimize() {
		Point x = x0.copy();
		Point xs;
		
		do {
			xs = x.copy();
			Point direction = new Point(new double[x0.dimension()]);
			
			for(int i = 0; i < x0.dimension(); i++) {
				direction.setValue(i, 1);
				double lambda = goldenRatio(x, 0, 0, false, direction, i, function);
				x = x.add(direction.scale(lambda));
				direction.setValue(i, 0);
			}
			
		} while(Math.abs(x.norm() - xs.norm()) <= epsilon);
		result = x.copy();
		return this;
	}

	@Override
	public String toString() {
		return "CoordinateSearch: result=" + Arrays.toString(result.toArray()) + ", iterations=" + function.numberOfIterations();
	}
}
