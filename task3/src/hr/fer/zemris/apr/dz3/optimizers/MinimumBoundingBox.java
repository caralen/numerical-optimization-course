package hr.fer.zemris.apr.dz3.optimizers;

import static hr.fer.zemris.apr.dz3.restrictions.Restrictions.explicit;
import static hr.fer.zemris.apr.dz3.restrictions.Restrictions.implicit1;
import static hr.fer.zemris.apr.dz3.restrictions.Restrictions.implicit2;

import java.util.Random;

import hr.fer.zemris.apr.dz2.Function;
import hr.fer.zemris.apr.dz2.Point;

public class MinimumBoundingBox implements Optimizer2 {
	
	private static final int gg = 100;
	private static final int dg = -100;
	
	private Point x0;
	private Function f;
	private double alfa = 1.3;
	private double eps = 10E-6;
	

	public MinimumBoundingBox(Point x0, Function f) {
		this.x0 = x0;
		this.f = f;
	}

	@Override
	public Point run() {
		
		for(int i = 0; i < x0.dimension(); i++) {
			if(!(explicit.test(x0) && implicit1.test(x0) && implicit2.test(x0)))
				throw new IllegalArgumentException("Starting point doesn't satisfy restrictions!");
		}
		Point Xc = x0;
		
		Point[] arr = new Point[2 * x0.dimension()];
		Random rand = new Random();
		
		for(int i = 0; i < 2 * x0.dimension(); i++) {
			double[] elements = new double[x0.dimension()];
			
			for(int j = 0; j < x0.dimension(); j++) {
				elements[j] = dg + rand.nextFloat() * (gg - dg);
			}
			arr[i] = new Point(elements);
			
			while(!(implicit1.test(arr[i]) && implicit2.test(arr[i]))) {
				arr[i] = arr[i].add(Xc).scale(0.5);
			}
		}

		while(true) {
			double max = Double.MIN_VALUE;
			double max2 = Double.MIN_VALUE;
			int h = 0, h2 = 0;
			Point Xh = null;
			
			for(int i = 0; i < arr.length; i++) {
				double funcValue = f.evaluate(arr[i].toArray());
				if(funcValue > max) {
					Xh = arr[i];
					max2 = max;
					max = funcValue;
					h2 = h;
					h = i;
				} else if(funcValue > max2) {
					max2 = funcValue;
					h2 = i;
				}
			}
			
			Xc = calculateCentroid(arr, h);
			Point Xr = reflexion(Xc, Xh);
			
			for(int i = 0; i < x0.dimension(); i++) {
				if(Xr.getValue(i) < dg)
					Xr.setValue(i, dg);
				else if(Xr.getValue(i) > gg) {
					Xr.setValue(i, gg);
				}
			}
			
			while(!(implicit1.test(Xr) && implicit2.test(Xr))) {
				Xr = Xr.add(Xc).scale(0.5);
			}
			
			if(f.evaluate(Xr.toArray()) > f.evaluate(arr[h2].toArray())) 
				Xr = Xr.add(Xc).scale(0.5);
			
			arr[h] = Xr;
			
			if(Xc.distance(arr[h]) < eps) {
				return Xc;
			}
		}
	}
	
	private Point reflexion(Point Xc, Point Xh) {
		return Xc.scaled(1 + alfa).subtract(Xh.scaled(alfa));
	}

	private Point calculateCentroid(Point[] arr, int h) {
		double[] c = new double[x0.dimension()];
		
		for(int i = 0; i < arr.length; i++) {
			if(i == h) continue;
			
			for(int j = 0; j < arr[i].dimension(); j++) {
				c[j] += arr[i].getValue(j) / (arr.length - 1);
			}
		}
		return new Point(c);
	}
	
	

}
