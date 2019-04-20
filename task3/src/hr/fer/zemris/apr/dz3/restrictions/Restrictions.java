package hr.fer.zemris.apr.dz3.restrictions;

import hr.fer.zemris.apr.dz2.Point;

public class Restrictions {

	
	public static final Restriction implicit1 = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			return x[1] - x[0];
		}
		
		@Override
		public boolean test(Point x) {
			return x.getValue(1) - x.getValue(0) >= 0;
		}
	};
	
	public static final Restriction implicit2 = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			return 2 - x[0];
		}
		
		@Override
		public boolean test(Point x) {
			return 2 - x.getValue(0) >= 0;
		}
	};
	
	public static final Restriction implicit3 = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			return 3 - x[0] - x[1];
		}
		
		@Override
		public boolean test(Point x) {
			return 3 - x.getValue(0) - x.getValue(1) >= 0;
		}
	};
	
	public static final Restriction implicit4 = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			return 3 + 1.5 * x[0] - x[1];
		}
		
		@Override
		public boolean test(Point x) {
			return 3 + 1.5 * x.getValue(0) - x.getValue(1) >= 0;
		}
	};
	
	public static final Restriction implicit5 = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			return x[1] - 1;
		}
		
		@Override
		public boolean test(Point x) {
			return x.getValue(1) - 1 == 0;
		}
	};
	
	public static final Restriction explicit = new Restriction() {
		
		@Override
		public double evaluate(double... x) throws IllegalArgumentException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean test(Point x) {
			return x.getValue(0) <= 100 && x.getValue(0) >= -100 
					&& x.getValue(1) <= 100 && x.getValue(1) >= -100;
		}
	};
}
