package hr.fer.zemris.apr.dz3;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.apr.dz2.Function;
import hr.fer.zemris.apr.dz2.Point;
import hr.fer.zemris.apr.dz3.functions.F1;
import hr.fer.zemris.apr.dz3.functions.F2;
import hr.fer.zemris.apr.dz3.functions.F3;
import hr.fer.zemris.apr.dz3.functions.F4;
import hr.fer.zemris.apr.dz3.functions.FunctionWithGradient;
import hr.fer.zemris.apr.dz3.optimizers.TransformationMethod;
import hr.fer.zemris.apr.dz3.restrictions.Restriction;
import hr.fer.zemris.apr.dz3.restrictions.Restrictions;

import static hr.fer.zemris.apr.dz3.restrictions.Restrictions.*;

public class Main {
	
	private static final boolean goldenRatio = true;
	
	private static final Point p1 = new Point(-1.9, 2);
	private static final Point p2 = new Point(0.1, 0.3);
	private static final Point p3 = new Point(0, 0);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		Ovo ne valja da jednom stvoris te fje pa dajes svima jer ce sve koristit isti brojac
//		Morat ces u pozivima instancirat objekte funkcija

		FunctionWithGradient f1 = new F1();
		FunctionWithGradient f2 = new F2();
		FunctionWithGradient f3 = new F3();
		FunctionWithGradient f4 = new F4();
		
//		Point point = new GradientDescent(p1, f4, goldenRatio).run();
//		System.out.println(point);
//		System.out.println(f4.numberOfCalls());
//		
		
//		Point point = new NewtonRaphson(p3, f4, goldenRatio).run();
//		System.out.println(point);
//		System.out.println(f4.numberOfCalls());
		
		
//		Point point = new MinimumBoundingBox(p2, f2).run();
//		System.out.println(point);
//		System.out.println(f2.numberOfCalls());
		
		List<Restriction> noneq = new ArrayList<>();
		noneq.add(implicit1);
		noneq.add(implicit2);
//		List<Restriction> eq = new ArrayList<>();
//		eq.add(implicit5);
		
		Point point = new TransformationMethod(p2, f2, noneq).run();
		System.out.println(point);
		System.out.println(f2.numberOfCalls());
	}

}
