package hr.fer.zemris.apr.dz3.optimizers;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.apr.dz2.Function;
import hr.fer.zemris.apr.dz2.FunctionCounter;
import hr.fer.zemris.apr.dz2.Point;
import hr.fer.zemris.apr.dz2.optimizers.HookeJeeves;
import hr.fer.zemris.apr.dz3.restrictions.Restriction;

public class TransformationMethod implements Optimizer2 {
	
	private Point x0;
	private Function f;
	private int t = 1;
	private double Dx = 0.5;
	private double eps = 10E-6;
	private List<Restriction> nonEquations;
	private List<Restriction> equations;
	
	public TransformationMethod(Point x0, Function f, List<Restriction> nonEquations, List<Restriction> equations) {
		this.x0 = x0;
		this.f = f;
		this.nonEquations = nonEquations;
		this.equations = equations;
	}
	
	public TransformationMethod(Point x0, Function f, List<Restriction> nonEquations) {
		this.x0 = x0;
		this.f = f;
		this.nonEquations = nonEquations;
		this.equations = new ArrayList<>();
	}


	@Override
	public Point run() {
		Function u = (x) -> f.evaluate(x) - 1 / t * g(nonEquations).evaluate(x) + t * h(equations).evaluate(x);
		
		for(Restriction r : nonEquations) {
			if(!r.test(x0)) {
				Function g = gStart();
				x0 = new HookeJeeves(new FunctionCounter(g), x0).optimize().getResult();
				break;
			}
		}
		int counter = 0;
		
		while(true) {
			Point x1 = optimize(x0, u);
			
			if(x1.distance(x0) < eps || counter == 10_000)
				return x1;
			
			x0 = x1;
			t *= 10;
			counter++;
		}
	}
	
	private Point optimize(Point x0, Function function) {
		Point xP = x0.copy();
		Point xB = x0.copy();
		
		Point xN = explore(xP.copy(), function);
		
		if(function.evaluate(xN.toArray()) < function.evaluate(xB.toArray())) {
			xP = xN.scaled(2).subtract(xB);
			xB = xN.copy();
		} else {
			Dx /= 2;
			xP = xB.copy();
		}
		return xB;
	}

	private Point explore(Point xP, Function function) {
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


	private Function g(List<Restriction> res) {
		
		return (x) -> {
			double sum = 0;
			for (Restriction r : res) {
				sum += Math.log(r.evaluate(x));
			}
			return sum;
		};
	}
	
	private Function h(List<Restriction> res) {
		
		return (x) -> {
			double sum = 0;
			for (Restriction r : res) {
				sum += Math.pow(r.evaluate(x), 2);
			}
			return sum;
		};
	}
	
	private Function gStart() {
		
		return (x) -> {
			double sum = 0;
			for (Restriction r : nonEquations) {
				sum += Math.log(r.evaluate(x)) * (r.test(new Point(x)) ? 0 : 1);
			}
			return sum;
		};
	}
	
}
