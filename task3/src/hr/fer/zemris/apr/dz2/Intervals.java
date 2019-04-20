package hr.fer.zemris.apr.dz2;

import static java.lang.Math.sqrt;

public class Intervals {

	public static double goldenRatio(Point x0, double a, double b, boolean isUnimodal, Point v, int currentDimension,
			Function f) {
		
		double k = 0.5 * (sqrt(5) - 1);
		double e = 10E-6;
		int h = 1;

		if (v == null) {
			v = new Point(new double[] { 1 });
		}

		if (x0 == null) {
			x0 = new Point(new double[] { 0 });
		}

		if (!isUnimodal) {
			double[] arr = unimodalSearch(h, x0, v, f);
			a = arr[0];
			b = arr[1];
		}

		double c = b - k * (b - a);
		double d = a + k * (b - a);
		double fc = f.evaluate(x0.add(v.scaled(c)).toArray());
		double fd = f.evaluate(x0.add(v.scaled(d)).toArray());

		while (Math.abs(b - a) > e) {
			if (fc < fd) {
				b = d;
				d = c;
				c = b - k * (b - a);
				fd = fc;
				fc = f.evaluate(x0.add(v.scaled(c)).toArray());
			} else {
				a = c;
				c = d;
				d = a + k * (b - a);
				fc = fd;
				fd = f.evaluate(x0.add(v.scaled(d)).toArray());
			}
		}
//		System.out.println(String.format("%.3f %.3f %.3f %.3f", a, c, d, b));
		return (a + b) / 2;
	}

	public static double[] unimodalSearch(double h, Point x0, Point v, Function f) {
		int step = 1;
		double lambda = 0;
		double m = lambda;
		double left = lambda - h;
		double right = lambda + h;

		double fm = f.evaluate(x0.add(v.scaled(lambda)).toArray());
		double fl = f.evaluate(x0.add(v.scaled(left)).toArray());
		double fr = f.evaluate(x0.add(v.scaled(right)).toArray());

		if (fm < fr && fm < fl)
			return new double[] { left, right };

		else if (fm > fr)
			do {
				left = m;
				m = right;
				fm = fr;
				step *= 2;
				right = lambda + h * step;
				fr = f.evaluate(x0.add(v.scaled(right)).toArray());
			} while (fm > fr);
		else
			do {
				right = m;
				m = left;
				fm = fl;
				left = lambda - h * (step *= 2);
				fl = f.evaluate(x0.add(v.scaled(left)).toArray());
			} while (fm > fl);
		return new double[] { left, right };
	}

}
