package hr.fer.zemris.apr.dz4.utils;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

import hr.fer.zemris.apr.dz4.Function;


public class Functions {

	static final Function f1 = (x) -> 100 * pow(x[1] - pow(x[0], 2), 2) + 1 * pow(1 - x[0], 2);
	
	static final Function f3 = (x) -> {
		double sum = 0;
		for (int i = 0; i < x.length; i++)
			sum += pow(x[i] - (i + 1), 2);
		
		return sum;
	};
	
	static final Function f6 = (x) -> {
		double sum = 0;
		for (int i = 0; i < x.length; i++)
			sum += pow(x[i], 2);
		
		double brojnik = pow(sin(sum), 2) - 0.5;
		double nazivnik = pow(1 + 0.001 * sum, 2);
		return 0.5 + brojnik / nazivnik;
	};
	
	static final Function f7 = (x) -> {
		double sum = 0;
		for (int i = 0; i < x.length; i++)
			sum += pow(x[i], 2);
		
		return pow(sum, 0.25) * (1 + pow(sin(50 * pow(sum, 0.1)), 2));
	};
}
